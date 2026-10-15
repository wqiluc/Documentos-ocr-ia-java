import { useEffect, useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import TituloPagina from '../../components/layout/TituloPagina';
import LinhaEtapas, { ETAPAS } from '../../components/resultado/LinhaEtapas';
import MensagemErro from '../../components/ui/MensagemErro';
import Painel from '../../components/ui/Painel';
import FormularioUpload from '../../components/upload/FormularioUpload';
import { api } from '../../services/api';
import type { Cliente, ValidacaoRequest } from '../../types/validacao';

const DURACAO_DE_CADA_ETAPA_MS = 650;

function esperar(milissegundos: number): Promise<void>
{
    return new Promise((resolve) => setTimeout(resolve, milissegundos));
}

export default function PaginaUpload()
{
    const navegar = useNavigate();
    const [clientes, setClientes] = useState<Cliente[]>([]);
    const [mensagemDeErro, setMensagemDeErro] = useState<string | null>(null);
    const [etapaEmAndamento, setEtapaEmAndamento] = useState<number | undefined>();
    const animacaoInterrompida = useRef(false);

    useEffect(() =>
    {
        api.listarClientes()
            .then(setClientes)
            .catch((erro: Error) => setMensagemDeErro(erro.message));

        return () =>
        {
            animacaoInterrompida.current = true;
        };
    }, []);

    async function validarComprovante(clienteId: number, requisicao: ValidacaoRequest)
    {
        setMensagemDeErro(null);
        animacaoInterrompida.current = false;
        setEtapaEmAndamento(1);

        const animacaoDasEtapas = (async () =>
        {
            for (let proximaEtapa = 2; proximaEtapa <= ETAPAS.length; proximaEtapa++)
            {
                await esperar(DURACAO_DE_CADA_ETAPA_MS);
                if (animacaoInterrompida.current)
                {
                    return;
                }

                setEtapaEmAndamento(proximaEtapa);
            }

            await esperar(DURACAO_DE_CADA_ETAPA_MS);
        })();

        try
        {
            const [validacao] = await Promise.all([api.enviarComprovante(clienteId, requisicao), animacaoDasEtapas]);
            navegar(`/resultado/${validacao.clienteId}/${validacao.validacaoId}`, { state: validacao });
        }
        catch (erro)
        {
            animacaoInterrompida.current = true;
            setEtapaEmAndamento(undefined);
            setMensagemDeErro((erro as Error).message);
        }
    }

    return (
        <div className="space-y-8">
            <TituloPagina
                titulo="Novo comprovante"
                descricao="Escolha o cliente e envie uma conta de luz, água ou internet emitida nos últimos 90 dias."
            />

            {mensagemDeErro && <MensagemErro mensagem={mensagemDeErro} />}

            <div className="grid items-start gap-6 md:grid-cols-[1fr_18rem]">
                <FormularioUpload
                    clientes={clientes}
                    enviando={etapaEmAndamento !== undefined}
                    onEnviar={validarComprovante}
                />
                <Painel titulo="Etapas do processamento">
                    <LinhaEtapas etapaEmAndamento={etapaEmAndamento} />
                </Painel>
            </div>
        </div>
    );
}