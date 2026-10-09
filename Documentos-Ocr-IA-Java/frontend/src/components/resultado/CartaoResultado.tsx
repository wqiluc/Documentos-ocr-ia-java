import type { ComponentType } from 'react';
import type { ResultadoValidacao, StatusValidacao } from '../../types/validacao';
import { IconeAtencao, IconeConfirmado, IconeRecusado } from '../ui/Icones';

interface ApresentacaoDoStatus
{
    titulo: string;
    resumo: string;
    Icone: ComponentType<{ className?: string }>;
    classeDoIcone: string;
    classeDoMarcador: string;
}

const APRESENTACAO_POR_STATUS: Record<StatusValidacao, ApresentacaoDoStatus> =
{
    APROVADO:
    {
        titulo: 'Comprovante aprovado',
        resumo: 'Todas as regras foram atendidas. O endereço do cliente pode ser confirmado.',
        Icone: IconeConfirmado,
        classeDoIcone: 'bg-emerald-100 text-emerald-700',
        classeDoMarcador: 'bg-emerald-500',
    },
    REPROVADO:
    {
        titulo: 'Comprovante reprovado',
        resumo: 'O documento não atende a todas as regras. Peça ao cliente um novo comprovante.',
        Icone: IconeRecusado,
        classeDoIcone: 'bg-red-100 text-red-700',
        classeDoMarcador: 'bg-red-500',
    },
    ANALISE_MANUAL:
    {
        titulo: 'Encaminhado para análise manual',
        resumo: 'Algum dado precisa ser conferido por uma pessoa antes de seguir com o cadastro.',
        Icone: IconeAtencao,
        classeDoIcone: 'bg-amber-100 text-amber-700',
        classeDoMarcador: 'bg-amber-500',
    },
};

interface CartaoResultadoProps
{
    resultado: ResultadoValidacao;
}

export default function CartaoResultado({ resultado }: Readonly<CartaoResultadoProps>)
{
    const { titulo, resumo, Icone, classeDoIcone, classeDoMarcador } = APRESENTACAO_POR_STATUS[resultado.status];

    return (
        <section className="rounded-lg border border-stone-200 bg-white">
            <div className="flex items-start gap-4 p-5">
                <span className={`flex h-10 w-10 shrink-0 items-center justify-center rounded-full ${classeDoIcone}`}>
                    <Icone className="h-5 w-5" />
                </span>
                <div className="min-w-0">
                    <h2 className="text-base font-semibold text-stone-900">{titulo}</h2>
                    <p className="mt-1 text-sm text-stone-600">{resumo}</p>
                </div>
            </div>

            {resultado.motivos.length > 0 && (
                <div className="border-t border-stone-100 px-5 py-4">
                    <h3 className="mb-2.5 text-xs font-medium text-stone-500">
                        {resultado.motivos.length === 1 ? 'Motivo' : 'Motivos'}
                    </h3>
                    <ul className="space-y-2">
                        {resultado.motivos.map((motivo) => (
                            <li key={motivo} className="flex gap-2.5 text-sm text-stone-800">
                                <span className={`mt-[9px] h-0.5 w-2.5 shrink-0 rounded-full ${classeDoMarcador}`} />
                                {motivo}
                            </li>
                        ))}
                    </ul>
                </div>
            )}
        </section>
    );
}
