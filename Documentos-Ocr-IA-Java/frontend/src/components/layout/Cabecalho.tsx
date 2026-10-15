import { NavLink } from 'react-router-dom';
import iconeJava from '../../assets/icone-java.svg';

function classeDoLinkDeNavegacao({ isActive }: { isActive: boolean })
{
    const classeBase = '-mb-px flex items-center border-b-2 text-sm transition-colors';
    if (isActive)
    {
        return `${classeBase} border-marca-600 font-medium text-stone-900`;
    }

    return `${classeBase} border-transparent text-stone-500 hover:text-stone-900`;
}

export default function Cabecalho()
{
    return (
        <header className="border-b border-stone-200 bg-white">
            <div className="mx-auto flex max-w-5xl items-stretch justify-between gap-6 px-4">
                <NavLink to="/" aria-label="Validação de Comprovantes" className="flex items-center gap-3 py-3">
                    <img src={iconeJava} alt="" className="h-8 w-8 shrink-0" />
                    <span className="hidden leading-tight sm:block">
                        <span className="block text-sm font-semibold text-stone-900">Validação de Comprovantes</span>
                        <span className="block text-xs text-stone-500">Comprovante de residência</span>
                    </span>
                </NavLink>

                <nav className="flex gap-6">
                    <NavLink to="/" end className={classeDoLinkDeNavegacao}>
                        Novo envio
                    </NavLink>
                    <NavLink to="/validacoes" className={classeDoLinkDeNavegacao}>
                        Histórico
                    </NavLink>
                </nav>
            </div>
        </header>
    );
}
