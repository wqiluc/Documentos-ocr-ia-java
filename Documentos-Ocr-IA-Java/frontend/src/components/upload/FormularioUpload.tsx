import { useEffect, useState, type FormEvent } from 'react';
import { lerArquivoComoBase64 } from '../../services/api';
import type { Cliente, ValidacaoRequest } from '../../types/validacao';
import { formatarCpf } from '../../utils/formatacao';
import { CLASSE_BOTAO_PRINCIPAL, CLASSE_CAMPO_DE_FORMULARIO } from '../ui/classes';
import { IconeEnviarArquivo } from '../ui/Icones';
import PreviewDocumento from './PreviewDocumento';

interface FormularioUploadProps
{
    clientes: Cliente[];
    enviando: boolean;
    onEnviar: (clienteId: number, requisicao: ValidacaoRequest) => void;
}

export default function FormularioUpload({ clientes, enviando, onEnviar }: Readonly<FormularioUploadProps>)
{
    const [clienteSelecionadoId, setClienteSelecionadoId] = useState<number | ''>('');
    const [arquivoSelecionado, setArquivoSelecionado] = useState<File | null>(null);
    const [urlDaPreVisualizacao, setUrlDaPreVisualizacao] = useState<string | null>(null);

    useEffect(() =>
    {
        if (clienteSelecionadoId === '' && clientes.length > 0)
        {
            setClienteSelecionadoId(clientes[0].id);
        }
    }, [clientes, clienteSelecionadoId]);

    useEffect(() =>
    {
        if (!arquivoSelecionado)
        {
            setUrlDaPreVisualizacao(null);
            return;
        }

        const urlTemporaria = URL.createObjectURL(arquivoSelecionado);
        setUrlDaPreVisualizacao(urlTemporaria);
        return () => URL.revokeObjectURL(urlTemporaria);
    }, [arquivoSelecionado]);

    const podeEnviar = clienteSelecionadoId !== '' && arquivoSelecionado !== null;

    async function enviarFormulario(evento: FormEvent)
    {
        evento.preventDefault();
        if (clienteSelecionadoId === '' || !arquivoSelecionado)
        {
            return;
        }

        onEnviar(clienteSelecionadoId,
        {
            nomeArquivo: arquivoSelecionado.name,
            mimeType: arquivoSelecionado.type,
            conteudoDocumento: null,
            imagemBase64: await lerArquivoComoBase64(arquivoSelecionado),
        });
    }

    return (
        <form onSubmit={enviarFormulario} className="rounded-lg border border-stone-200 bg-white">
            <div className="space-y-6 p-5">
                <div>
                    <label htmlFor="cliente" className="mb-1.5 block text-sm font-medium text-stone-900">
                        Cliente
                    </label>
                    <select
                        id="cliente"
                        value={clienteSelecionadoId}
                        onChange={(evento) => setClienteSelecionadoId(Number(evento.target.value))}
                        className={CLASSE_CAMPO_DE_FORMULARIO}
                    >
                        {clientes.length === 0 && <option value="">Carregando clientes…</option>}
                        {clientes.map((cliente) => (
                            <option key={cliente.id} value={cliente.id}>
                                {cliente.nome} — CPF {formatarCpf(cliente.cpf)}
                            </option>
                        ))}
                    </select>
                </div>

                <fieldset>
                    <legend className="mb-1.5 text-sm font-medium text-stone-900">Comprovante</legend>

                    <label className="flex cursor-pointer flex-col items-center justify-center gap-1 rounded-md border border-dashed border-stone-300 bg-stone-50/60 px-4 py-10 text-center transition-colors hover:border-marca-500 hover:bg-marca-50/50 focus-within:border-marca-500 focus-within:ring-2 focus-within:ring-marca-100">
                        <IconeEnviarArquivo className="mb-1 h-6 w-6 text-stone-400" />
                        <span className="text-sm font-medium text-stone-800">
                            {arquivoSelecionado ? 'Escolher outro arquivo' : 'Selecione a conta de luz, água ou internet'}
                        </span>
                        <span className="text-xs text-stone-500">PNG, JPG ou PDF</span>
                        <input
                            type="file"
                            accept="image/png,image/jpeg,application/pdf"
                            className="sr-only"
                            onChange={(evento) => setArquivoSelecionado(evento.target.files?.[0] ?? null)}
                        />
                    </label>

                    {arquivoSelecionado && urlDaPreVisualizacao && (
                        <PreviewDocumento arquivo={arquivoSelecionado} urlDaPreVisualizacao={urlDaPreVisualizacao} />
                    )}
                </fieldset>
            </div>

            <div className="flex flex-wrap items-center justify-between gap-3 rounded-b-lg border-t border-stone-100 bg-stone-50/70 px-5 py-3">
                <p className="text-xs text-stone-500">A imagem passa por OpenCV e Tesseract antes da IA.</p>
                <button type="submit" disabled={!podeEnviar || enviando} className={CLASSE_BOTAO_PRINCIPAL}>
                    {enviando ? 'Validando…' : 'Validar comprovante'}
                </button>
            </div>
        </form>
    );
}
