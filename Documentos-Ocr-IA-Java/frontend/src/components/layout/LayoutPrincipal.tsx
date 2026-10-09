import { Outlet } from 'react-router-dom';
import Cabecalho from './Cabecalho';

export default function LayoutPrincipal()
{
    return (
        <div className="flex min-h-screen flex-col">
            <Cabecalho />

            <main className="mx-auto w-full max-w-5xl flex-1 px-4 py-10">
                <Outlet />
            </main>

            <footer className="border-t border-stone-200">
                <div className="mx-auto flex max-w-5xl flex-wrap justify-between gap-2 px-4 py-5 text-xs text-stone-400">
                    <span>CESAR School — projeto acadêmico</span>
                    <span>Spring Boot · OpenCV · Tesseract</span>
                </div>
            </footer>
        </div>
    );
}