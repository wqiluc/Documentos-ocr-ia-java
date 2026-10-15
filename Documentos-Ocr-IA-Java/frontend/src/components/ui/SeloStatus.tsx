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
        classeDoSelo: 'bg-confirma-50 text-confirma-800 ring-confirma-600/25',
        classeDoPonto: 'bg-confirma-500',
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
        classeDoSelo: 'bg-sky-50 text-sky-800 ring-sky-600/20',
        classeDoPonto: 'bg-sky-500',
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
