import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

// /api é repassado para o Spring Boot — sem problema de CORS no navegador.
export default defineConfig(
{
    plugins: [react()],
    server: 
    {
        port: 5173,
        proxy: 
        {
            '/api': 'http://localhost:8080',
        },
    },
});