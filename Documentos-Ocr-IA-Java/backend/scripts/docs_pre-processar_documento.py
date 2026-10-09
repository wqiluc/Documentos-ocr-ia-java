from cores import Amarelo, CinzaClaro, Reset, Verde, Vermelho

def verde(texto): return f"{Verde}{texto}{CinzaClaro}"
def amarelo(texto): return f"{Amarelo}{texto}{CinzaClaro}"
def vermelho(texto): return f"{Vermelho}{texto}{CinzaClaro}"

print(f"""{CinzaClaro}Documentação de {verde("pre-processar_documento.py")} — {amarelo("Etapa 2")} do pipeline ({verde("OpenCV")}).

Pré-processa um documento ({verde("imagem")} ou {verde("PDF")}) para {verde("OCR")}.

Uso:
    {verde("python3 pre-processar_documento.py <arquivoEntrada> <arquivoSaida.png>")}

Etapas:
    1. {verde("Carga")}: PDF → {amarelo("1ª página")} renderizada a {amarelo("300 dpi")}; demais formatos via OpenCV.
    2. {verde("Escala de cinza")} (imagens com lado maior < {amarelo("1500 px")} são ampliadas {amarelo("2x")}).
    3. {verde("Remoção de ruído")} (Non-Local Means, {amarelo("h=10")}).
    4. {verde("Binarização adaptativa gaussiana")} ({amarelo("bloco 31")}, {amarelo("C=15")}).
    5. {verde("Correção de inclinação")} (ignorada se o ângulo for < {amarelo("0,3°")}).

Integração:
    Chamado pelo {verde("OpenCvService")} (Java) via {verde("ProcessBuilder")}. {vermelho("Código de saída 2")} indica
    {vermelho("uso incorreto")}; em qualquer {vermelho("falha")} o backend segue com a {amarelo("imagem original")}.

Funções:
    {verde("carregar(caminho) -> np.ndarray")}
        Lê o arquivo e devolve a imagem em {amarelo("BGR")}. PDFs têm apenas a {amarelo("1ª página")}
        renderizada ({amarelo("300 dpi")}). Lança {vermelho("ValueError")} se o OpenCV não conseguir ler
        a imagem.

    {verde("corrigir_inclinacao(binaria) -> np.ndarray")}
        Endireita o texto de uma imagem binarizada. Estima o ângulo pelo
        {amarelo("retângulo de área mínima")} que envolve os pixels escuros. Devolve a
        imagem {vermelho("inalterada")} se houver menos de {amarelo("50 pixels escuros")} ou se a
        inclinação for desprezível (< {amarelo("0,3°")}).

    {verde("preprocessar(img) -> np.ndarray")}
        Aplica cinza → ampliação (se pequena) → remoção de ruído →
        binarização → correção de inclinação.

Dependências: ver {verde("requirements.txt")} ({verde("opencv-python-headless")}, {verde("numpy")}, {verde("pymupdf")}).
{Reset}""")