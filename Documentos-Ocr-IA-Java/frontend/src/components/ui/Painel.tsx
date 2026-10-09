import type { ReactNode } from 'react';

interface PainelProps
{
    titulo?: string;
    children: ReactNode;
}

export default function Painel({ titulo, children }: Readonly<PainelProps>)
{
    return (
        <section className="rounded-lg border border-stone-200 bg-white">
            {titulo && (
                <h2 className="border-b border-stone-100 px-5 py-3 text-sm font-medium text-stone-900">{titulo}</h2>
            )}
            <div className="p-5">{children}</div>
        </section>
    );
}
