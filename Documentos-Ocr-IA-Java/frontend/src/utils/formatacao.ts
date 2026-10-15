export function formatarCpf(cpf: string): string
{
    const somenteDigitos = cpf.replace(/\D/g, '');
    if (somenteDigitos.length !== 11)
    {
        return cpf;
    }

    return somenteDigitos.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4');
}

export function formatarData(dataIso: string): string
{
    const partesDaData = /^(\d{4})-(\d{2})-(\d{2})$/.exec(dataIso);

    if (!partesDaData)
    {
        return dataIso;
    }

    const [, ano, mes, dia] = partesDaData;
    return `${dia}/${mes}/${ano}`;
}

export function formatarDataHora(dataHoraIso: string): string
{
    return new Date(dataHoraIso).toLocaleString('pt-BR', { dateStyle: 'short', timeStyle: 'short' });
}