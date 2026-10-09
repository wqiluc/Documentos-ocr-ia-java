import type { StatusValidacao } from '../../types/validacao';

export interface ExemploDeComprovante
{
    rotulo: string;
    resultadoEsperado: StatusValidacao;
    gerarTexto: () => string;
}

const CEP_VALIDO = '50050-000';
const CEP_INVALIDO = '5005-00';
const NOME_DE_OUTRA_PESSOA = 'Carlos Pereira Lima';

function dataDeEmissaoHaDias(quantidadeDeDias: number): string
{
    const dataDeEmissao = new Date();
    dataDeEmissao.setDate(dataDeEmissao.getDate() - quantidadeDeDias);
    return dataDeEmissao.toLocaleDateString('pt-BR');
}

function montarTextoDaConta(nomeDoTitular: string, cep: string, diasDesdeAEmissao: number): string
{
    return [
        'CONTA DE ENERGIA',
        'Empresa: Neoenergia Pernambuco',
        `Titular: ${nomeDoTitular}`,
        'Endereco: Rua das Flores, 150',
        'Bairro: Boa Vista',
        'Cidade: Recife - PE',
        `CEP: ${cep}`,
        `Data de emissao: ${dataDeEmissaoHaDias(diasDesdeAEmissao)}`,
    ].join('\n');
}

/** Textos prontos que simulam a saída do OCR, um para cada desfecho possível. */
export function montarExemplosDeComprovante(nomeDoCliente: string): ExemploDeComprovante[]
{
    return [
        {
            rotulo: 'Conta recente',
            resultadoEsperado: 'APROVADO',
            gerarTexto: () => montarTextoDaConta(nomeDoCliente, CEP_VALIDO, 10),
        },
        {
            rotulo: 'Emitida há 120 dias',
            resultadoEsperado: 'REPROVADO',
            gerarTexto: () => montarTextoDaConta(nomeDoCliente, CEP_VALIDO, 120),
        },
        {
            rotulo: 'CEP inválido',
            resultadoEsperado: 'REPROVADO',
            gerarTexto: () => montarTextoDaConta(nomeDoCliente, CEP_INVALIDO, 10),
        },
        {
            rotulo: 'Titular diferente',
            resultadoEsperado: 'ANALISE_MANUAL',
            gerarTexto: () => montarTextoDaConta(NOME_DE_OUTRA_PESSOA, CEP_VALIDO, 10),
        },
    ];
}
