import type { DadosComprovante } from '../../types/validacao';
import { formatarData } from '../../utils/formatacao';

const CAMPOS_DO_COMPROVANTE: { chave: keyof DadosComprovante; rotulo: string }[] = [
    { chave: 'nomeTitular', rotulo: 'Titular' },
    { chave: 'empresaEmissora', rotulo: 'Empresa emissora' },
    { chave: 'endereco', rotulo: 'Endereço' },
    { chave: 'bairro', rotulo: 'Bairro' },
    { chave: 'cidadeUf', rotulo: 'Cidade/UF' },
    { chave: 'cep', rotulo: 'CEP' },
    { chave: 'dataEmissao', rotulo: 'Data de emissão' },
];

function formatarValorDoCampo(chave: keyof DadosComprovante, valor: string | null)
{
    if (valor && chave === 'dataEmissao')
    {
        return formatarData(valor);
    }

    return valor;
}

interface DadosExtraidosProps
{
    dados: DadosComprovante;
}

export default function DadosExtraidos({ dados }: Readonly<DadosExtraidosProps>)
{
    return (
        <dl className="grid grid-cols-1 gap-x-6 sm:grid-cols-2">
            {CAMPOS_DO_COMPROVANTE.map(({ chave, rotulo }) =>
            {
                const valorFormatado = formatarValorDoCampo(chave, dados[chave]);

                return (
                    <div key={chave} className="min-w-0 border-b border-stone-100 py-2.5">
                        <dt className="text-xs text-stone-500">{rotulo}</dt>
                        <dd
                            title={valorFormatado ?? undefined}
                            className={`mt-0.5 truncate text-sm ${valorFormatado ? 'text-stone-900' : 'text-red-700'}`}
                        >
                            {valorFormatado ?? 'Não encontrado'}
                        </dd>
                    </div>
                );
            })}
        </dl>
    );
}
