export type StatusValidacao = 'APROVADO' | 'REPROVADO' | 'ANALISE_MANUAL';

export interface Cliente
{
    id: number;
    nome: string;
    cpf: string;
}

export interface ValidacaoRequest
{
    nomeArquivo: string;
    mimeType: string;
    conteudoDocumento: string | null;
    imagemBase64: string | null;
}

export interface EtapaPipeline
{
    ordem: number;
    nome: string;
    resultado: string;
}

export interface DadosComprovante
{
    nomeTitular: string | null;
    endereco: string | null;
    bairro: string | null;
    cidadeUf: string | null;
    cep: string | null;
    empresaEmissora: string | null;
    dataEmissao: string | null;
}

export interface ResultadoValidacao
{
    status: StatusValidacao;
    regrasAtendidas: string[];
    motivos: string[];
}

export interface ValidacaoResponse
{
    validacaoId: number;
    clienteId: number;
    nomeCliente: string;
    nomeArquivo: string;
    criadoEm: string;
    etapas: EtapaPipeline[];
    dadosExtraidos: DadosComprovante;
    resultado: ResultadoValidacao;
}
