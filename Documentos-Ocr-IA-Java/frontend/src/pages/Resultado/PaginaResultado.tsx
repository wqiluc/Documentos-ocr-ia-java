import { useEffect, useState } from 'react';
import { Link, useLocation, useParams } from 'react-router-dom';
import TituloPagina from '../../components/layout/TituloPagina';
import CartaoResultado from '../../components/resultado/CartaoResultado';
import DadosExtraidos from '../../components/resultado/DadosExtraidos';
import LinhaEtapas from '../../components/resultado/LinhaEtapas';
import ListaRegras from '../../components/resultado/ListaRegras';
import Carregando from '../../components/ui/Carregando';
import { CLASSE_BOTAO_SECUNDARIO } from '../../components/ui/classes';
import { IconeVoltar } from '../../components/ui/Icones';
import MensagemErro from '../../components/ui/MensagemErro';
import Painel from '../../components/ui/Painel';
import { api } from '../../services/api';
import type { ValidacaoResponse } from '../../types/validacao';
import { formatarDataHora } from '../../utils/formatacao';

export default function PaginaResultado()
{
    const { clienteId, validacaoId } = useParams();
    const localizacao = useLocation();
    const validacaoRecebidaNaNavegacao = localizacao.state as ValidacaoResponse | null;
    const [validacao, setValidacao] = useState<ValidacaoResponse | null>(validacaoRecebidaNaNavegacao);
    const [mensagemDeErro, setMensagemDeErro] = useState<string | null>(null);

    useEffect(() =>
    {
        if (validacao?.validacaoId === Number(validacaoId))
        {
            return;
        }

        api.buscarValidacao(Number(clienteId), Number(validacaoId))
            .then(setValidacao)
            .catch((erro: Error) => setMensagemDeErro(erro.message));
    }, [clienteId, validacaoId, validacao]);

    if (mensagemDeErro)
    {
        return <MensagemErro mensagem={mensagemDeErro} />;
    }

    if (!validacao)
    {
        return <Carregando mensagem="Buscando a validação…" />;
    }

    return (
        <div className="space-y-8">
            <TituloPagina
                titulo={`Validação nº ${validacao.validacaoId}`}
                descricao={
                    <>
                        <span className="font-medium text-stone-700">{validacao.nomeCliente}</span>
                        {' · '}
                        {validacao.nomeArquivo}
                        {' · '}
                        {formatarDataHora(validacao.criadoEm)}
                    </>
                }
                acoes=
                {
                    <Link to="/" className={CLASSE_BOTAO_SECUNDARIO}>
                        <IconeVoltar className="h-4 w-4" />
                        Novo envio
                    </Link>
                }
            />

            <div className="grid items-start gap-6 md:grid-cols-[1fr_18rem]">
                <div className="space-y-6">
                    <CartaoResultado resultado={validacao.resultado} />
                    <Painel titulo="Dados extraídos">
                        <DadosExtraidos dados={validacao.dadosExtraidos} />
                    </Painel>
                    <Painel titulo="Regras de negócio">
                        <ListaRegras resultado={validacao.resultado} />
                    </Painel>
                </div>

                <Painel titulo="Etapas do processamento">
                    <LinhaEtapas etapasConcluidas={validacao.etapas} validacaoId={validacao.validacaoId} />
                </Painel>
            </div>
        </div>
    );
}