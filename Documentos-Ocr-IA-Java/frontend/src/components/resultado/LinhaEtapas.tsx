import type { EtapaPipeline } from '../../types/validacao';
import { IconeConfirmado } from '../ui/Icones';

export const ETAPAS = [
    { nome: 'Documento', descricao: 'Recebimento do arquivo' },
    { nome: 'OpenCV', descricao: 'Tratamento da imagem: tons de cinza, ruído e inclinação' },
    { nome: 'Tesseract', descricao: 'Leitura do texto em português' },
    { nome: 'IA', descricao: 'Extração dos campos do comprovante' },
    { nome: 'Regras', descricao: 'Conferência das regras R01, R02, R06, R07 e R08' },
    { nome: 'PostgreSQL', descricao: 'Registro do resultado' },
];

const ORDEM_DA_ETAPA_DE_REGISTRO = 6;

/** Trechos que o backend escreve quando uma etapa foi pulada ou trocada por uma alternativa. */
const INDICADORES_DE_ALTERNATIVA = ['indisponível', 'Pulado', 'recebido'];

interface LinhaEtapasProps
{
    /** Etapas devolvidas pela API, quando a validação já terminou. */
    etapasConcluidas?: EtapaPipeline[];
    /** Enquanto a API processa: número da etapa em andamento (1 a 6). */
    etapaEmAndamento?: number;
    validacaoId?: number;
}

function descreverResultadoDaEtapa(ordem: number, etapasConcluidas?: EtapaPipeline[], validacaoId?: number)
{
    if (ordem === ORDEM_DA_ETAPA_DE_REGISTRO)
    {
        return validacaoId === undefined ? undefined : `Salvo como validação nº ${validacaoId}`;
    }

    return etapasConcluidas?.find((etapa) => etapa.ordem === ordem)?.resultado;
}

interface MarcadorDaEtapaProps
{
    ordem: number;
    concluida: boolean;
    atual: boolean;
}

function MarcadorDaEtapa({ ordem, concluida, atual }: Readonly<MarcadorDaEtapaProps>)
{
    if (concluida)
    {
        return (
            <span className="relative z-10 flex h-6 w-6 shrink-0 items-center justify-center rounded-full bg-marca-600 text-white">
                <IconeConfirmado className="h-3.5 w-3.5" />
            </span>
        );
    }

    if (atual)
    {
        return (
            <span className="relative z-10 flex h-6 w-6 shrink-0 items-center justify-center rounded-full border-2 border-marca-600 bg-white">
                <span className="h-2 w-2 animate-pulse rounded-full bg-marca-600" />
            </span>
        );
    }

    return (
        <span className="relative z-10 flex h-6 w-6 shrink-0 items-center justify-center rounded-full border border-stone-300 bg-white text-[11px] font-medium tabular-nums text-stone-400">
            {ordem}
        </span>
    );
}

export default function LinhaEtapas({ etapasConcluidas, etapaEmAndamento, validacaoId }: Readonly<LinhaEtapasProps>)
{
    const processamentoTerminou = etapasConcluidas !== undefined;

    return (
        <ol>
            {ETAPAS.map((etapa, indice) =>
            {
                const ordem = indice + 1;
                const resultadoDaEtapa = descreverResultadoDaEtapa(ordem, etapasConcluidas, validacaoId);
                const etapaConcluida = processamentoTerminou || (etapaEmAndamento !== undefined && ordem < etapaEmAndamento);
                const etapaAtual = !processamentoTerminou && etapaEmAndamento === ordem;
                const usouAlternativa = INDICADORES_DE_ALTERNATIVA.some((indicador) => resultadoDaEtapa?.includes(indicador));
                const ehUltimaEtapa = ordem === ETAPAS.length;

                let corDaDescricao = 'text-stone-400';
                if (usouAlternativa)
                {
                    corDaDescricao = 'text-amber-700';
                }
                else if (etapaConcluida || etapaAtual)
                {
                    corDaDescricao = 'text-stone-500';
                }

                return (
                    <li key={etapa.nome} className="relative flex gap-3 pb-5 last:pb-0">
                        {!ehUltimaEtapa && (
                            <span
                                aria-hidden="true"
                                className={`absolute bottom-1 left-[11.5px] top-7 w-px transition-colors duration-300 ${
                                    etapaConcluida ? 'bg-marca-500' : 'bg-stone-200'
                                }`}
                            />
                        )}

                        <MarcadorDaEtapa ordem={ordem} concluida={etapaConcluida} atual={etapaAtual} />

                        <div className="min-w-0 pt-0.5">
                            <p className={`text-sm ${etapaConcluida || etapaAtual ? 'font-medium text-stone-900' : 'text-stone-400'}`}>
                                {etapa.nome}
                                {etapaAtual && <span className="ml-2 text-xs font-normal text-marca-600">em andamento</span>}
                            </p>
                            <p className={`mt-0.5 text-xs leading-relaxed ${corDaDescricao}`}>
                                {resultadoDaEtapa ?? etapa.descricao}
                            </p>
                        </div>
                    </li>
                );
            })}
        </ol>
    );
}
