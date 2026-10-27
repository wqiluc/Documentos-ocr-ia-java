import sys
import cv2
import numpy as np
import pymupdf

DPI_PDF = 300
TAMANHO_MINIMO_PX = 1500
FATOR_AMPLIACAO = 2
INTENSIDADE_REMOCAO_RUIDO = 10
TAMANHO_BLOCO_LIMIAR = 31
AJUSTE_LIMIAR = 15
MINIMO_PIXELS_TEXTO = 50
ANGULO_MINIMO_INCLINACAO = 0.3


def carregar_imagem(caminho: str) -> np.ndarray:
    if caminho.lower().endswith(".pdf"):
        with pymupdf.open(caminho) as pdf:
            pixmap = pdf[0].get_pixmap(dpi=DPI_PDF)

        imagem = np.frombuffer(pixmap.samples, dtype=np.uint8)
        imagem = imagem.reshape(pixmap.h, pixmap.w, pixmap.n)

        if (pixmap.n == 4):
            return cv2.cvtColor(imagem, cv2.COLOR_RGBA2BGR)
        else:
            return cv2.cvtColor(imagem, cv2.COLOR_RGB2BGR)
    else:
        imagem = cv2.imread(caminho)

        if (imagem is None):
            raise ValueError(f"Não foi possível ler/carregar a imagem: {caminho}")
        else:
            return (imagem)


def corrigir_inclinacao(binaria: np.ndarray) -> np.ndarray:
    pixels_texto = np.column_stack(np.nonzero(binaria < 128))

    if (len(pixels_texto) < MINIMO_PIXELS_TEXTO):
        return binaria
    else:
        angulo = cv2.minAreaRect(pixels_texto)[-1]

        if (angulo < -45):
            angulo = -(90 + angulo)
        else:
            angulo = -angulo
            if (angulo > 45):
                angulo = angulo - 90

        if abs(angulo) < ANGULO_MINIMO_INCLINACAO:
            return binaria
        else:
            altura, largura = binaria.shape
            centro = (largura // 2, altura // 2)
            matriz_rotacao = cv2.getRotationMatrix2D(centro, angulo, 1.0)

            return cv2.warpAffine(binaria, matriz_rotacao, (largura, altura),
                                  flags=cv2.INTER_CUBIC, borderMode=cv2.BORDER_REPLICATE)


def pre_processar(imagem: np.ndarray) -> np.ndarray:
    cinza = cv2.cvtColor(imagem, cv2.COLOR_BGR2GRAY)

    if max(cinza.shape) < TAMANHO_MINIMO_PX:
        cinza = cv2.resize(cinza, None, fx=FATOR_AMPLIACAO, fy=FATOR_AMPLIACAO,
                           interpolation=cv2.INTER_CUBIC)

    sem_ruido = cv2.fastNlMeansDenoising(cinza, h=INTENSIDADE_REMOCAO_RUIDO)
    binaria = cv2.adaptiveThreshold(sem_ruido, 255, cv2.ADAPTIVE_THRESH_GAUSSIAN_C,
                                    cv2.THRESH_BINARY, TAMANHO_BLOCO_LIMIAR, AJUSTE_LIMIAR)

    return corrigir_inclinacao(binaria)


def main() -> None:
    if len(sys.argv) != 3:
        print("Uso: python3 pre-processar_documento.py <arquivoEntrada> <arquivoSaida.png>",
              file=sys.stderr)
        sys.exit(2)
    else:
        caminho_entrada = sys.argv[1]
        caminho_saida = sys.argv[2]

        imagem = carregar_imagem(caminho_entrada)
        imagem_tratada = pre_processar(imagem)
        cv2.imwrite(caminho_saida, imagem_tratada)


if __name__ == "__main__":
    main()