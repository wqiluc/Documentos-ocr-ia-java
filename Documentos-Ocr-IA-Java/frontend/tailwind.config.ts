import type { Config } from 'tailwindcss';

export default 
{
    content: ['./index.html', './src/**/*.{ts,tsx}'],
    theme: 
    {
        extend: 
        {
            fontFamily: 
            {
                sans: ['"IBM Plex Sans"', 'system-ui', '-apple-system', 'Segoe UI', 'Roboto', 'sans-serif'],
                mono: ['"IBM Plex Mono"', 'ui-monospace', 'SFMono-Regular', 'Menlo', 'monospace'],
            },

            colors: 
            {
                papel: '#f6f5f1',
                marca: 
                {
                    50: '#eef6f3',
                    100: '#d3e9e1',
                    200: '#a8d3c4',
                    500: '#1f7a63',
                    600: '#17654f',
                    700: '#114f3f',
                    900: '#0b2f27',
                },
                // Laranja do logo do projeto (500), usado em botões de confirmação e estados aprovados.
                confirma:
                {
                    50: '#fef7ec',
                    100: '#fdebd0',
                    200: '#fad49e',
                    400: '#f5b766',
                    500: '#f2a341',
                    600: '#dd8a22',
                    700: '#b86c14',
                    800: '#8f5313',
                    900: '#5f3810',
                },
            },
        },
    },
    plugins: [],
} satisfies Config;