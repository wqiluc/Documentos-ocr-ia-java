import type { ResultadoValidacao } from '../../types/validacao';
import { IconeConfirmado, IconeRecusado } from '../ui/Icones';

// As descrições precisam ser iguais às constantes DESCRICAO das regras no backend.
const REGRAS_DE_NEGOCIO = [
    { codigo: 'R01', descricao: 'Documento Legível ✅☕️' },
    { codigo: 'R02', descricao: 'Campos obrigatórios presentes ✅☕️' },
    { codigo: 'R06', descricao: 'Emissão dentro de 90 dias ✅☕️' },
    { codigo: 'R07', descricao: 'CEP válido ✅☕️' },
    { codigo: 'R08', descricao: 'Titular igual ao cliente cadastrado ✅☕️' },
];

interface ListaRegrasProps
{
    resultado: ResultadoValidacao;
}

export default function ListaRegras({ resultado }: Readonly<ListaRegrasProps>)
{
    return (
        <ul className="divide-y divide-stone-100">
            {REGRAS_DE_NEGOCIO.map((regra) =>
            {
                const regraAtendida = resultado.regrasAtendidas.includes(regra.descricao);

                return (
                    <li key={regra.codigo} className="flex items-center gap-3 py-2.5 text-sm first:pt-0 last:pb-0">
                        <span
                            className={`flex h-5 w-5 shrink-0 items-center justify-center rounded-full ${
                                regraAtendida ? 'bg-confirma-100 text-confirma-700' : 'bg-red-100 text-red-700'
                            }`}
                        >
                            {regraAtendida ? <IconeConfirmado className="h-3 w-3" /> : <IconeRecusado className="h-3 w-3" />}
                            <span className="sr-only">{regraAtendida ? 'Atendida' : 'Não atendida'}</span>
                        </span>
                        <span className="w-8 shrink-0 font-mono text-xs text-stone-400">{regra.codigo}</span>
                        <span className={regraAtendida ? 'text-stone-800' : 'text-stone-500'}>{regra.descricao}</span>
                    </li>
                );
            })}
        </ul>
    );
}