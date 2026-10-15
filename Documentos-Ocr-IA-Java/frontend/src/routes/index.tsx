import { createBrowserRouter } from 'react-router-dom';
import LayoutPrincipal from '../components/layout/LayoutPrincipal';
import PaginaUpload from '../pages/Upload/PaginaUpload';
import PaginaResultado from '../pages/Resultado/PaginaResultado';
import PaginaValidacoes from '../pages/Validacoes/PaginaValidacoes';

export const rotas = createBrowserRouter([
    {
        element: <LayoutPrincipal />,
        children: 
        [
            { path: '/', element: <PaginaUpload /> },
            { path: '/resultado/:clienteId/:validacaoId', element: <PaginaResultado /> },
            { path: '/validacoes', element: <PaginaValidacoes /> },
        ],
    },
]);