interface CarregandoProps
{
    mensagem?: string;
}

export default function Carregando({ mensagem = 'Carregando…' }: Readonly<CarregandoProps>)
{
    return (
        <output className="flex items-center gap-2.5 text-sm text-stone-500">
            <span className="h-4 w-4 animate-spin rounded-full border-2 border-stone-300 border-t-marca-600" />
            {mensagem}
        </output>
    );
}
