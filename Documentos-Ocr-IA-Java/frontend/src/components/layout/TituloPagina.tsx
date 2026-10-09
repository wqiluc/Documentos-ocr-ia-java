import type { ReactNode } from 'react';

interface TituloPaginaProps
{
    titulo: string;
    descricao?: ReactNode;
    acoes?: ReactNode;
}

export default function TituloPagina({ titulo, descricao, acoes }: Readonly<TituloPaginaProps>)
{
    return (
        <div className="flex flex-wrap items-end justify-between gap-4">
            <div className="min-w-0">
                <h1 className="text-2xl font-semibold tracking-tight text-stone-900">{titulo}</h1>
                {descricao && <p className="mt-1.5 max-w-2xl text-sm text-stone-500">{descricao}</p>}
            </div>
            {acoes}
        </div>
    );
}
