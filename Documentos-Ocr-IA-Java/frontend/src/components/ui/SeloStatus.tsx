import type { StatusValidacao } from '../../types/validacao';

interface AparenciaDoSelo
{
    rotulo: string;
    classeDoSelo: string;
    classeDoPonto: string;
}

const APARENCIA_POR_STATUS: Record<StatusValidacao, AparenciaDoSelo> =
{
    APROVADO:
    {
        rotulo: 'Aprovado',
        classeDoSelo: 'bg-emerald-50 text-emerald-800 ring-emerald-600/20',
        classeDoPonto: 'bg-emerald-600',
    },
    REPROVADO:
    {
        rotulo: 'Reprovado',
        classeDoSelo: 'bg-red-50 text-red-800 ring-red-600/20',
        classeDoPonto: 'bg-red-600',
    },
    ANALISE_MANUAL:
    {
        rotulo: 'Análise manual',
        classeDoSelo: 'bg-amber-50 text-amber-800 ring-amber-600/25',
        classeDoPonto: 'bg-amber-500',
    },
};

interface SeloStatusProps
{
    status: StatusValidacao;
}

export default function SeloStatus({ status }: Readonly<SeloStatusProps>)
{
    const { rotulo, classeDoSelo, classeDoPonto } = APARENCIA_POR_STATUS[status];

    return (
        <span
            className={`inline-flex items-center gap-1.5 whitespace-nowrap rounded px-2 py-0.5 text-xs font-medium ring-1 ring-inset ${classeDoSelo}`}
        >
            <span className={`h-1.5 w-1.5 rounded-full ${classeDoPonto}`} />
            {rotulo}
        </span>
    );
}
