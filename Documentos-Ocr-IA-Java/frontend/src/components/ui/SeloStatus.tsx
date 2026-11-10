import type { StatusValidacao } from '../../types/validacao';
import { IconeConfirmado } from './Icones';

interface AparenciaDoSelo
{
    rotulo: string;
    classeDoSelo: string;
    classeDoPonto: string;
    /** Quando presente, o selo mostra o check no lugar do ponto colorido. */
    exibeCheck?: boolean;
}

const APARENCIA_POR_STATUS: Record<StatusValidacao, AparenciaDoSelo> =
{
    APROVADO:
    {
        rotulo: 'Aprovado',
        classeDoSelo: 'bg-emerald-50 text-emerald-800 ring-emerald-600/25',
        classeDoPonto: 'text-emerald-600',
        exibeCheck: true,
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
    const { rotulo, classeDoSelo, classeDoPonto, exibeCheck } = APARENCIA_POR_STATUS[status];

    return (
        <span
            className={`inline-flex items-center gap-1.5 whitespace-nowrap rounded px-2 py-0.5 text-xs font-medium ring-1 ring-inset ${classeDoSelo}`}
        >
            {exibeCheck
                ? <IconeConfirmado className={`h-3 w-3 ${classeDoPonto}`} />
                : <span className={`h-1.5 w-1.5 rounded-full ${classeDoPonto}`} />}
            {rotulo}
        </span>
    );
}
