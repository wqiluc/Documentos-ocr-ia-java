import { IconeAtencao } from './Icones';

interface MensagemErroProps
{
    mensagem: string;
}

export default function MensagemErro({ mensagem }: Readonly<MensagemErroProps>)
{
    return (
        <div role="alert" className="flex gap-3 rounded-md border border-red-200 bg-red-50 px-4 py-3 text-sm text-red-800">
            <IconeAtencao className="mt-px h-4 w-4 shrink-0" />
            <p>{mensagem}</p>
        </div>
    );
}
