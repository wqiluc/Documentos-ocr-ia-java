import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import TituloPagina from '../../components/layout/TituloPagina';
import Carregando from '../../components/ui/Carregando';
import { CLASSE_BOTAO_SECUNDARIO } from '../../components/ui/classes';
import MensagemErro from '../../components/ui/MensagemErro';
import SeloStatus from '../../components/ui/SeloStatus';
import { api } from '../../services/api';
import type { ValidacaoResponse } from '../../types/validacao';
import { formatarDataHora } from '../../utils/formatacao';

function descreverQuantidade(validacoes: ValidacaoResponse[] | null)
{
    if (!validacoes || validacoes.length === 0)
    {
        return 'Os comprovantes enviados aparecem aqui, do mais recente para o mais antigo.';
    }

    const quantidade = validacoes.length === 1 ? '1 validação registrada' : `${validacoes.length} validações registradas`;
    return `${quantidade}, da mais recente para a mais antiga.`;
}

function HistoricoVazio()
{
    return (
        <div className="rounded-lg border border-dashed border-stone-300 bg-white px-6 py-12 text-center">
            <p className="text-sm font-medium text-stone-900">Nenhuma validação por enquanto</p>
            <p className="mt-1 text-sm text-stone-500">Envie o primeiro comprovante para ele aparecer aqui.</p>
            <Link to="/" className={`${CLASSE_BOTAO_SECUNDARIO} mt-5`}>
                Enviar comprovante
            </Link>
        </div>
    );
}

interface TabelaDeValidacoesProps
{
    validacoes: ValidacaoResponse[];
}

function TabelaDeValidacoes({ validacoes }: Readonly<TabelaDeValidacoesProps>)
{
    return (
        <div className="overflow-x-auto rounded-lg border border-stone-200 bg-white">
            <table className="w-full min-w-[56rem] text-left text-sm">
                <thead className="border-b border-stone-200 text-xs text-stone-500">
                    <tr>
                        <th scope="col" className="px-4 py-3 font-medium">Nº</th>
                        <th scope="col" className="px-4 py-3 font-medium">Cliente</th>
                        <th scope="col" className="px-4 py-3 font-medium">Arquivo</th>
                        <th scope="col" className="px-4 py-3 font-medium">Status</th>
                        <th scope="col" className="px-4 py-3 font-medium">Motivo</th>
                        <th scope="col" className="px-4 py-3 font-medium">Enviado em</th>
                    </tr>
                </thead>
                <tbody className="divide-y divide-stone-100">
                    {validacoes.map((validacao) =>
                    {
                        const [primeiroMotivo, ...outrosMotivos] = validacao.resultado.motivos;

                        return (
                            <tr key={validacao.validacaoId} className="align-top transition-colors hover:bg-stone-50">
                                <td className="px-4 py-3 tabular-nums">
                                    <Link
                                        to={`/resultado/${validacao.clienteId}/${validacao.validacaoId}`}
                                        className="font-medium text-marca-700 underline-offset-2 hover:underline"
                                    >
                                        {validacao.validacaoId}
                                    </Link>
                                </td>
                                <td className="whitespace-nowrap px-4 py-3 font-medium text-stone-900">{validacao.nomeCliente}</td>
                                <td className="max-w-[10rem] truncate px-4 py-3 text-stone-500" title={validacao.nomeArquivo}>
                                    {validacao.nomeArquivo}
                                </td>
                                <td className="px-4 py-3">
                                    <SeloStatus status={validacao.resultado.status} />
                                </td>
                                <td className="max-w-xs px-4 py-3 text-stone-600">
                                    {primeiroMotivo ?? <span className="text-stone-300">—</span>}
                                    {outrosMotivos.length > 0 && (
                                        <span className="ml-1 text-xs text-stone-400">(+{outrosMotivos.length})</span>
                                    )}
                                </td>
                                <td className="whitespace-nowrap px-4 py-3 tabular-nums text-stone-500">
                                    {formatarDataHora(validacao.criadoEm)}
                                </td>
                            </tr>
                        );
                    })}
                </tbody>
            </table>
        </div>
    );
}

export default function PaginaValidacoes()
{
    const [validacoes, setValidacoes] = useState<ValidacaoResponse[] | null>(null);
    const [mensagemDeErro, setMensagemDeErro] = useState<string | null>(null);

    useEffect(() =>
    {
        api.listarValidacoes()
            .then(setValidacoes)
            .catch((erro: Error) => setMensagemDeErro(erro.message));
    }, []);

    const carregando = validacoes === null && mensagemDeErro === null;

    return (
        <div className="space-y-8">
            <TituloPagina titulo="Histórico de validações" descricao={descreverQuantidade(validacoes)} />

            {mensagemDeErro && <MensagemErro mensagem={mensagemDeErro} />}
            {carregando && <Carregando mensagem="Buscando o histórico…" />}
            {validacoes?.length === 0 && <HistoricoVazio />}
            {validacoes && validacoes.length > 0 && <TabelaDeValidacoes validacoes={validacoes} />}
        </div>
    );
}
