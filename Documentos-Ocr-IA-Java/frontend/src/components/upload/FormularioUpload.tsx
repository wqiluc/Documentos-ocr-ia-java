import { useEffect, useState, type FormEvent } from 'react';
import { api, lerArquivoComoBase64 } from '../../services/api';
import type { Cliente, StatusValidacao, ValidacaoRequest } from '../../types/validacao';
import { formatarCpf } from '../../utils/formatacao';
import { CLASSE_BOTAO_PRINCIPAL, CLASSE_CAMPO_DE_FORMULARIO } from '../ui/classes';
import { IconeEnviarArquivo } from '../ui/Icones';
import { montarExemplosDeComprovante } from './exemplosDeComprovante';
import PreviewDocumento from './PreviewDocumento';

type FormaDeEnvio = 'arquivo' | 'texto';

const FORMAS_DE_ENVIO: { valor: FormaDeEnvio; rotulo: string }[] = [
    { valor: 'arquivo', rotulo: 'Enviar arquivo' },
    { valor: 'texto', rotulo: 'Colar texto' },
];

const COR_DO_RESULTADO_ESPERADO: Record<StatusValidacao, string> =
{
    APROVADO: 'bg-emerald-500',
    REPROVADO: 'bg-red-500',
    ANALISE_MANUAL: 'bg-amber-500',
};

const NOME_PADRAO_DO_CLIENTE = 'Joao da Silva';

interface FormularioUploadProps
{
    clientes: Cliente[];
    enviando: boolean;
    onEnviar: (clienteId: number, requisicao: ValidacaoRequest) => void;
}

export default function FormularioUpload({ clientes, enviando, onEnviar }: Readonly<FormularioUploadProps>)
{
    const [clienteSelecionadoId, setClienteSelecionadoId] = useState<number | ''>('');
    const [formaDeEnvio, setFormaDeEnvio] = useState<FormaDeEnvio>('arquivo');
    const [arquivoSelecionado, setArquivoSelecionado] = useState<File | null>(null);
    const [urlDaPreVisualizacao, setUrlDaPreVisualizacao] = useState<string | null>(null);
    const [textoDoComprovante, setTextoDoComprovante] = useState('');

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

    const clienteSelecionado = clientes.find((cliente) => cliente.id === clienteSelecionadoId);
    const exemplos = montarExemplosDeComprovante(clienteSelecionado?.nome ?? NOME_PADRAO_DO_CLIENTE);
    const podeEnviar = clienteSelecionadoId !== ''
        && (formaDeEnvio === 'arquivo' ? arquivoSelecionado !== null : textoDoComprovante.trim().length > 0);

    async function carregarExemploDoServidor()
    {
        try
        {
            const documentoExemplo = await api.buscarDocumentoExemplo();
            setTextoDoComprovante(documentoExemplo.conteudoDocumento ?? '');
        }
        catch
        {
            setTextoDoComprovante(exemplos[0].gerarTexto());
        }
    }

    async function enviarFormulario(evento: FormEvent)
    {
        evento.preventDefault();
        if (clienteSelecionadoId === '')
        {
            return;
        }

        if (formaDeEnvio === 'arquivo' && arquivoSelecionado)
        {
            onEnviar(clienteSelecionadoId,
            {
                nomeArquivo: arquivoSelecionado.name,
                mimeType: arquivoSelecionado.type,
                conteudoDocumento: textoDoComprovante.trim() || null,
                imagemBase64: await lerArquivoComoBase64(arquivoSelecionado),
            });
            return;
        }

        onEnviar(clienteSelecionadoId,
        {
            nomeArquivo: 'comprovante-digitado.txt',
            mimeType: 'text/plain',
            conteudoDocumento: textoDoComprovante,
            imagemBase64: null,
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

                    <div className="mb-3 inline-flex rounded-md border border-stone-200 bg-stone-50 p-0.5 text-sm">
                        {FORMAS_DE_ENVIO.map(({ valor, rotulo }) => (
                            <button
                                key={valor}
                                type="button"
                                aria-pressed={formaDeEnvio === valor}
                                onClick={() => setFormaDeEnvio(valor)}
                                className={`rounded px-3 py-1.5 transition-colors ${
                                    formaDeEnvio === valor
                                        ? 'bg-white font-medium text-stone-900 shadow-sm ring-1 ring-stone-200'
                                        : 'text-stone-500 hover:text-stone-800'
                                }`}
                            >
                                {rotulo}
                            </button>
                        ))}
                    </div>

                    {formaDeEnvio === 'arquivo' ? (
                        <div>
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
                        </div>
                    ) : (
                        <div className="space-y-3">
                            <div className="flex flex-wrap items-center gap-2">
                                <span className="text-xs text-stone-500">Preencher com:</span>
                                <button
                                    type="button"
                                    onClick={carregarExemploDoServidor}
                                    className="rounded border border-stone-300 bg-white px-2.5 py-1 text-xs text-stone-700 transition-colors hover:border-stone-400 hover:text-stone-900"
                                >
                                    Exemplo do servidor
                                </button>
                                {exemplos.map((exemplo) => (
                                    <button
                                        key={exemplo.rotulo}
                                        type="button"
                                        onClick={() => setTextoDoComprovante(exemplo.gerarTexto())}
                                        className="inline-flex items-center gap-1.5 rounded border border-stone-300 bg-white px-2.5 py-1 text-xs text-stone-700 transition-colors hover:border-stone-400 hover:text-stone-900"
                                    >
                                        <span className={`h-1.5 w-1.5 rounded-full ${COR_DO_RESULTADO_ESPERADO[exemplo.resultadoEsperado]}`} />
                                        {exemplo.rotulo}
                                    </button>
                                ))}
                            </div>

                            <textarea
                                aria-label="Texto do comprovante"
                                value={textoDoComprovante}
                                onChange={(evento) => setTextoDoComprovante(evento.target.value)}
                                rows={9}
                                placeholder="Cole aqui o texto do comprovante, como se tivesse saído do OCR."
                                className={`${CLASSE_CAMPO_DE_FORMULARIO} font-mono leading-relaxed`}
                            />
                        </div>
                    )}
                </fieldset>
            </div>

            <div className="flex flex-wrap items-center justify-between gap-3 rounded-b-lg border-t border-stone-100 bg-stone-50/70 px-5 py-3">
                <p className="text-xs text-stone-500">
                    {formaDeEnvio === 'arquivo'
                        ? 'A imagem passa por OpenCV e Tesseract antes da IA.'
                        : 'O texto pula o OCR e vai direto para a IA.'}
                </p>
                <button type="submit" disabled={!podeEnviar || enviando} className={CLASSE_BOTAO_PRINCIPAL}>
                    {enviando ? 'Validando…' : 'Validar comprovante'}
                </button>
            </div>
        </form>
    );
}
