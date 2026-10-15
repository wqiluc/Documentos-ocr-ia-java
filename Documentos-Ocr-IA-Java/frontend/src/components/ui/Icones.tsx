import type { ReactNode } from 'react';

interface IconeProps
{
    className?: string;
}

interface DesenhoDoIconeProps extends IconeProps
{
    espessuraDoTraco?: number;
    children: ReactNode;
}

function DesenhoDoIcone({ className = 'h-4 w-4', espessuraDoTraco = 1.75, children }: Readonly<DesenhoDoIconeProps>)
{
    return (
        <svg
            viewBox="0 0 20 20"
            fill="none"
            stroke="currentColor"
            strokeWidth={espessuraDoTraco}
            strokeLinecap="round"
            strokeLinejoin="round"
            aria-hidden="true"
            className={className}
        >
            {children}
        </svg>
    );
}

export function IconeConfirmado({ className }: Readonly<IconeProps>)
{
    return (
        <DesenhoDoIcone className={className} espessuraDoTraco={2.25}>
            <path d="M4.5 10.5l3.5 3.5 7.5-8" />
        </DesenhoDoIcone>
    );
}

export function IconeRecusado({ className }: Readonly<IconeProps>)
{
    return (
        <DesenhoDoIcone className={className} espessuraDoTraco={2.25}>
            <path d="M5.5 5.5l9 9M14.5 5.5l-9 9" />
        </DesenhoDoIcone>
    );
}

export function IconeAtencao({ className }: Readonly<IconeProps>)
{
    return (
        <DesenhoDoIcone className={className}>
            <circle cx="10" cy="10" r="7.25" />
            <path d="M10 6.25v4.5M10 13.75v.01" />
        </DesenhoDoIcone>
    );
}

export function IconeEnviarArquivo({ className }: Readonly<IconeProps>)
{
    return (
        <DesenhoDoIcone className={className}>
            <path d="M10 12.5V3.75M6.5 7.25L10 3.75l3.5 3.5" />
            <path d="M3.75 12.5v2.75c0 .55.45 1 1 1h10.5c.55 0 1-.45 1-1V12.5" />
        </DesenhoDoIcone>
    );
}

export function IconeVoltar({ className }: Readonly<IconeProps>)
{
    return (
        <DesenhoDoIcone className={className}>
            <path d="M11.5 5.5L7 10l4.5 4.5" />
        </DesenhoDoIcone>
    );
}
