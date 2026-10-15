interface PreviewDocumentoProps
{
    arquivo: File;
    urlDaPreVisualizacao: string;
}

export default function PreviewDocumento({ arquivo, urlDaPreVisualizacao }: Readonly<PreviewDocumentoProps>)
{
    const arquivoEhPdf = arquivo.type === 'application/pdf';
    const tamanhoEmKb = Math.max(1, Math.round(arquivo.size / 1024));

    return (
        <figure className="mt-3 overflow-hidden rounded-md border border-stone-200">
            <div className="bg-stone-100">
                {arquivoEhPdf ? (
                    <object data={urlDaPreVisualizacao} type="application/pdf" className="h-72 w-full">
                        <p className="p-4 text-sm text-stone-500">Não foi possível pré-visualizar este PDF.</p>
                    </object>
                ) : (
                    <img
                        src={urlDaPreVisualizacao}
                        alt="Pré-visualização do comprovante"
                        className="max-h-72 w-full object-contain"
                    />
                )}
            </div>
            <figcaption className="flex items-center justify-between gap-3 border-t border-stone-200 bg-white px-3 py-2 text-xs">
                <span className="truncate font-medium text-stone-700">{arquivo.name}</span>
                <span className="shrink-0 tabular-nums text-stone-400">{tamanhoEmKb} KB</span>
            </figcaption>
        </figure>
    );
}
