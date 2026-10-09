import type { Cliente, ValidacaoRequest, ValidacaoResponse } from '../types/validacao';

const URL_BASE_DA_API = import.meta.env.VITE_API_URL ?? '';

async function chamarApi<T>(caminho: string, opcoes?: RequestInit): Promise<T>
{
    let resposta: Response;
    try
    {
        resposta = await fetch(URL_BASE_DA_API + caminho,
        {
            ...opcoes,
            headers: { 'Content-Type': 'application/json', ...opcoes?.headers },
        });
    }
    catch
    {
        throw new Error('Não foi possível conectar à API. Confira se o backend está rodando em http://localhost:8080.');
    }

    if (!resposta.ok)
    {
        const corpoDoErro = await resposta.json().catch(() => null);
        throw new Error(corpoDoErro?.erro ?? `A API respondeu com erro ${resposta.status}.`);
    }

    return resposta.json() as Promise<T>;
}

export const api =
{
    listarClientes: () => chamarApi<Cliente[]>('/api/clientes'),

    buscarDocumentoExemplo: () => chamarApi<ValidacaoRequest>('/api/documentos/exemplo'),

    enviarComprovante: (clienteId: number, requisicao: ValidacaoRequest) =>
        chamarApi<ValidacaoResponse>(`/api/clientes/${clienteId}/comprovantes`,
        {
            method: 'POST',
            body: JSON.stringify(requisicao),
        }),

    buscarValidacao: (clienteId: number, validacaoId: number) =>
        chamarApi<ValidacaoResponse>(`/api/clientes/${clienteId}/comprovantes/${validacaoId}`),

    listarValidacoes: () => chamarApi<ValidacaoResponse[]>('/api/validacoes'),
};

export function lerArquivoComoBase64(arquivo: File): Promise<string>
{
    return new Promise((resolve, reject) =>
    {
        const leitorDeArquivo = new FileReader();
        leitorDeArquivo.onload = () => resolve(leitorDeArquivo.result as string);
        leitorDeArquivo.onerror = () => reject(leitorDeArquivo.error ?? new Error('Não foi possível ler o arquivo.'));
        leitorDeArquivo.readAsDataURL(arquivo);
    });
}
