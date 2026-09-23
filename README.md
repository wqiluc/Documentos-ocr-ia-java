<h1 align="center"> Documentos OCR + IA 📄🔍🤖🏦<br>
<img src="https://img.shields.io/badge/-Java_21-020617?style=flat-square&logo=openjdk&logoColor=orange" height="19"/>
<img src="https://img.shields.io/badge/-Spring_Boot-020617?style=flat-square&logo=springboot&logoColor=6DB33F" height="19"/>
<img src="https://img.shields.io/badge/-Maven-020617?style=flat-square&logo=apachemaven&logoColor=C71A36" height="19"/>
<img src="https://img.shields.io/badge/-PostgreSQL-020617?style=flat-square&logo=postgresql&logoColor=4169E1" height="19"/>

[![License: MIT](https://img.shields.io/badge/License-MIT-orange.svg)](https://github.com/wqiluc/Documentos-ocr-ia-java/blob/main/LICENSE)
![Version](https://img.shields.io/badge/version-0.1.0--SNAPSHOT-purple)
![Status](https://img.shields.io/badge/status-em_planejamento-yellow)
</h1>

<p align="center">
Validação inteligente de documentos no <b>cadastro de novos clientes (onboarding) de um banco digital</b> —
o cliente envia o documento de identificação (RG/CNH) e o comprovante de residência, e o sistema faz
<b>OpenCV → Tesseract (OCR) → IA → regras em Java</b> para aprovar ou reprovar o cadastro, sempre
informando o motivo.
</p>

> ⚠️ **Status:** a estrutura de pastas do **backend** e do **frontend** já está criada em `Documentos-Ocr-IA-Java/`, mas os arquivos
> ainda estão **vazios**. As funcionalidades descritas abaixo ainda não estão implementadas — o README serve
> como especificação do projeto e do MVP. O Tesseract já foi instalado localmente (ver [prints](#tesseract-instalacao)).

<h2 align="center" id="autor">👤👨‍🎓 Autor: </h2>
<ul>
<li><b>Lucas Paguetti Pereira</b> — ADS · 3º período · CESAR School <a href="https://www.linkedin.com/in/lucas-paguetti-pereira" target="_blank"><img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/linkedin/linkedin-original.svg" width="14"/></a> <a href="https://github.com/wqiluc" target="_blank"><img src="https://img.shields.io/badge/GitHub-000000?style=for-the-badge&logo=github&logoColor=white" height="16"/></a></li>
</ul>

<h2 align="center" id="tecnologias"> ⛏️💻 Tecnologias Utilizadas: <br>
<img src="https://img.shields.io/badge/Stack-111827?style=flat&logo=openjdk&logoColor=orange" height="22"/>
</h2>
<p align="center">
  <img src="https://upload.wikimedia.org/wikipedia/commons/9/9a/Visual_Studio_Code_1.35_icon.svg" width="32" height="32" alt="VS Code"/> <br>
  <img src="https://img.shields.io/badge/-Java_21-111827?style=flat-square&logo=openjdk&logoColor=orange"/>
  <img src="https://img.shields.io/badge/-Spring_Boot_3.5-111827?style=flat-square&logo=springboot&logoColor=6DB33F"/>
  <img src="https://img.shields.io/badge/-Maven-111827?style=flat-square&logo=apachemaven&logoColor=C71A36"/>
  <img src="https://img.shields.io/badge/-JUnit_5-111827?style=flat-square&logo=junit5&logoColor=25A162"/> <br>
  <img src="https://img.shields.io/badge/-OpenCV-111827?style=flat-square&logo=opencv&logoColor=5C3EE8"/>
  <img src="https://img.shields.io/badge/-Tesseract_OCR-111827?style=flat-square&logo=google&logoColor=4285F4"/>
  <img src="https://img.shields.io/badge/-OpenAI_API-111827?style=flat-square&logo=openai&logoColor=white"/>
  <img src="https://img.shields.io/badge/-Python_(script_OpenCV)-111827?style=flat-square&logo=python&logoColor=3776AB"/> <br>
  <img src="https://img.shields.io/badge/-PostgreSQL-111827?style=flat-square&logo=postgresql&logoColor=white"/>
  <img src="https://img.shields.io/badge/-API_REST-111827?style=flat-square&logo=openapiinitiative&logoColor=6BA539"/> <br>
  <img src="https://img.shields.io/badge/-React_18-111827?style=flat-square&logo=react&logoColor=61DAFB"/>
  <img src="https://img.shields.io/badge/-TypeScript-111827?style=flat-square&logo=typescript&logoColor=3178C6"/>
  <img src="https://img.shields.io/badge/-Vite-111827?style=flat-square&logo=vite&logoColor=646CFF"/>
  <img src="https://img.shields.io/badge/-Tailwind_CSS-111827?style=flat-square&logo=tailwindcss&logoColor=38BDF8"/> <br>
  <img src="https://img.shields.io/badge/-Git-111827?style=flat-square&logo=git&logoColor=F05032"/>
  <img src="https://img.shields.io/badge/-GitHub-111827?style=flat-square&logo=github&logoColor=white"/>
  <img src="https://img.shields.io/badge/-GitHub_Desktop-111827?style=flat-square&logo=github&logoColor=purple"/>
</p>

<h2 align="center" id="problema">🧩 Problema <br>
<img src="https://img.shields.io/badge/Cenário-Onboarding_bancário-111827?style=flat&logo=cashapp&logoColor=22C55E" height="22"/>
</h2>

<p align="center">
No cadastro de novos clientes, a equipe de backoffice confere manualmente o documento de identificação e o
comprovante de residência: lê os dados, <b>redigita no sistema</b> e verifica se nome, CPF e endereço batem.
</p>

| Dor | Consequência |
| :--- | :--- |
| 🐢 Processo lento | O cliente espera dias pela abertura da conta |
| ⌨️ Redigitação manual | Erros de digitação de CPF e CEP |
| 🕵️ Conferência visual | Vulnerável a fraudes: documentos vencidos, comprovantes antigos ou em nome de outra pessoa |

<h2 align="center" id="usuarios">👥 Usuários </h2>

| Tipo | Quem | O que faz no sistema |
| :--- | :--- | :--- |
| ⭐ Principal | Analista de cadastro do backoffice | Acompanha os cadastros validados e trata os casos encaminhados para análise manual |
| 🛡️ Secundário | Equipe de compliance | Consulta o histórico e os motivos de reprovação |
| 📱 Secundário | Cliente | Envia os documentos pelo app e acompanha o status do cadastro |

<h2 align="center" id="documentos">📑 Documentos e Dados Extraídos </h2>

| Documento | Exemplos | Dados extraídos |
| :--- | :--- | :--- |
| 🪪 Identificação | RG ou CNH | Nome completo, CPF, data de nascimento, número do documento, órgão emissor/UF e data de validade (CNH) |
| 🏠 Comprovante de residência | Conta de luz, água ou internet | Nome do titular, endereço (logradouro e número), bairro, cidade/UF, CEP, empresa emissora e data de emissão |

<h2 align="center" id="regras">📏 Regras de Negócio </h2>

| # | Regra | Documento | No MVP? |
| :---: | :--- | :--- | :---: |
| R01 | Documento legível (OCR retornou texto suficiente e com boa confiança) | Ambos | 🚀 |
| R02 | Campos obrigatórios presentes (nome, CPF, datas e endereço) | Ambos | 🚀 |
| R03 | CPF com formato e dígitos verificadores válidos | Identificação | 🚀 |
| R04 | CNH dentro da data de validade | Identificação | 🚀 |
| R05 | Cliente maior de 18 anos, pela data de nascimento | Identificação | 🚀 |
| R06 | Comprovante emitido há no máximo 90 dias | Comprovante | 🚀 |
| R07 | CEP com formato válido (8 dígitos) | Comprovante | 🚀 |
| R08 | Nome do titular do comprovante igual ao nome do cliente | Cruzada | 🚀 |
| R09 | CPF não cadastrado anteriormente (cliente não duplicado) | Identificação | 🚀 |

<h3 align="left">✅ Aprovação</h3>

O cadastro é **aprovado** quando os dois documentos foram enviados, os campos obrigatórios foram extraídos,
o CPF é válido, a CNH está na validade, o cliente é maior de idade, o comprovante é recente e está no nome
do cliente — ou seja, **todas as regras foram atendidas**. Os dados validados são registrados para a
abertura da conta.

<h3 align="left">❌ Rejeição</h3>

O cadastro é **reprovado** quando ocorre qualquer um destes casos:

| Motivo | Regra | Resultado |
| :--- | :---: | :--- |
| Documento ilegível (imagem borrada, escura ou cortada) | R01 | `REPROVADO` |
| Campo obrigatório ausente (ex.: sem CPF ou sem data de emissão) | R02 | `REPROVADO` |
| CPF inválido ou já cadastrado | R03 · R09 | `REPROVADO` |
| CNH vencida ou cliente menor de 18 anos | R04 · R05 | `REPROVADO` |
| Comprovante com mais de 90 dias ou CEP inválido | R06 · R07 | `REPROVADO` |
| Comprovante em nome de outra pessoa (pode ser de um familiar) | R08 | `ANALISE_MANUAL` |

> A resposta **sempre informa o motivo**, para o cliente saber qual documento reenviar.

<h2 align="center" id="fluxo">🔄 Fluxo do Sistema </h2>

```mermaid
flowchart LR
    A[📤 Upload<br/>PDF/imagem] --> B[🖼️ OpenCV<br/>tratamento da imagem]
    B --> C[🔤 Tesseract<br/>OCR]
    C --> D[🤖 IA<br/>texto → JSON por tipo]
    D --> E[☕ Spring Boot<br/>objetos Java + regras]
    E --> F[(🐘 PostgreSQL<br/>cadastro + resultado)]
    F --> G[🌐 API REST<br/>aprovado/reprovado + motivos]
```

| Etapa | Responsável | O que faz |
| :---: | :--- | :--- |
| 1 | Upload | Cliente envia o documento (PDF ou imagem) |
| 2 | OpenCV | Pré-processa a imagem (escala de cinza, contraste, binarização, correção de inclinação) |
| 3 | Tesseract | Extrai o texto do documento (idioma `por`) |
| 4 | IA (OpenAI) | Interpreta o texto e devolve um JSON estruturado de acordo com o tipo de documento |
| 5 | Spring Boot | Converte o JSON em objetos Java e aplica as regras de negócio e a validação cruzada |
| 6 | PostgreSQL | Persiste o cadastro, os dados extraídos e o resultado da validação |
| 7 | API REST | Expõe o resultado: aprovado/reprovado + motivos |

<h2 align="center" id="mvp">🎯 MVP <br>
<img src="https://img.shields.io/badge/Escopo-Comprovante_de_residência-111827?style=flat&logo=todoist&logoColor=E44332" height="22"/>
</h2>

<p align="center">
O MVP processa <b>apenas o comprovante de residência de um cliente já cadastrado</b>. O documento de
identificação (RG/CNH) e a validação cruzada completa ficam como evolução depois da demo.
</p>

| Item do MVP | Descrição | Status |
| :--- | :--- | :---: |
| 📤 Upload | Endpoint de upload do comprovante vinculado ao cliente | ⏳ |
| 🔤 OCR + IA | Pré-processamento, OCR e extração estruturada via IA | ⏳ |
| 📏 Regras básicas | Legibilidade, campos obrigatórios, emissão ≤ 90 dias, CEP válido e nome do titular igual ao do cliente | ⏳ |
| 💾 Persistência | Resultado salvo no PostgreSQL | ⏳ |
| 🌐 Consulta | Resultado consultável via endpoint REST | ⏳ |

<h2 align="center" id="api">🌐 Endpoints Planejados </h2>

| Método | Rota | Descrição |
| :---: | :--- | :--- |
| `GET` | `/api/documentos/exemplo` | Retorna um payload pronto para demonstração |
| `POST` | `/api/clientes/{clienteId}/comprovantes` | Envia o comprovante de residência do cliente e executa o pipeline |
| `GET` | `/api/clientes/{clienteId}/comprovantes/{validacaoId}` | Consulta o resultado de uma validação |

<details>
<summary>▶️📥 <b>Exemplo de request</b></summary>

```json
{
  "nomeArquivo": "comprovante-residencia.pdf",
  "mimeType": "application/pdf",
  "conteudoDocumento": "CONTA DE ENERGIA\nTitular: Joao da Silva\nEndereco: Rua das Flores, 150\nBairro: Boa Vista\nCidade: Recife - PE\nCEP: 50050-000\nData de emissao: 10/08/2026",
  "imagemBase64": null
}
```

Você pode enviar apenas `conteudoDocumento` (texto já extraído), apenas `imagemBase64`, ou ambos.

</details>

<details>
<summary>▶️📤 <b>Exemplo de retorno — aprovado</b></summary>

```json
{
  "validacaoId": 1,
  "clienteId": 42,
  "nomeArquivo": "comprovante-residencia.pdf",
  "etapas": [
    { "ordem": 1, "nome": "Documento", "resultado": "comprovante-residencia.pdf" },
    { "ordem": 2, "nome": "OpenCV", "resultado": "Imagem tratada" },
    { "ordem": 3, "nome": "Tesseract", "resultado": "Texto extraído (confiança 91%)" },
    { "ordem": 4, "nome": "IA", "resultado": "JSON estruturado gerado" },
    { "ordem": 5, "nome": "Regras", "resultado": "5 de 5 regras atendidas" }
  ],
  "dadosExtraidos": {
    "nomeTitular": "Joao da Silva",
    "endereco": "Rua das Flores, 150",
    "bairro": "Boa Vista",
    "cidadeUf": "Recife - PE",
    "cep": "50050-000",
    "empresaEmissora": "Neoenergia Pernambuco",
    "dataEmissao": "2026-08-10"
  },
  "resultado": {
    "status": "APROVADO",
    "regrasAtendidas": [
      "Documento legível",
      "Campos obrigatórios presentes",
      "Emissão dentro de 90 dias",
      "CEP válido",
      "Titular igual ao cliente cadastrado"
    ],
    "motivos": []
  }
}
```

</details>

<details>
<summary>▶️⚠️ <b>Exemplo de retorno — reprovado</b></summary>

```json
{
  "resultado": {
    "status": "REPROVADO",
    "regrasAtendidas": [
      "Documento legível",
      "Campos obrigatórios presentes",
      "CEP válido",
      "Titular igual ao cliente cadastrado"
    ],
    "motivos": [
      "Comprovante emitido há mais de 90 dias. Envie um comprovante mais recente."
    ]
  }
}
```

</details>

<h2 align="center" id="integracoes">🔌 Integrações e Configuração </h2>

<p align="center">
A ideia é ter integração real com OpenAI, Tesseract e OpenCV, mas com <b>fallback local</b> para a demo
funcionar mesmo sem credenciais ou ferramentas instaladas.
</p>

<details>
<summary>▶️🤖 <b>OpenAI</b></summary>

Defina a chave antes de subir a aplicação:

```bash
export OPENAI_API_KEY="sua-chave"
```

Em `application.properties`:

```properties
app.ai.openai.api-key=${OPENAI_API_KEY:}
```

Com a chave configurada, o `InterpretadorIaService` chama a API da OpenAI e pede um JSON estruturado.
Sem a chave, usa um interpretador local baseado em expressões regulares.

</details>

<details>
<summary>▶️🔤 <b>Tesseract</b></summary>

```bash
brew install tesseract tesseract-lang   # macOS
```

```properties
app.ocr.command=tesseract
app.ocr.tessdata-path=/opt/homebrew/share/tessdata
app.ocr.language=por
```

Se o Tesseract não estiver configurado, a aplicação usa o texto enviado em `conteudoDocumento` como fallback.

</details>

<h3 align="center" id="tesseract-instalacao">🔤 Instalação do Tesseract — 23/09 <br>
<img src="https://img.shields.io/badge/tesseract-5.5.3-111827?style=flat-square&logo=homebrew&logoColor=FBB040" height="20"/>
<img src="https://img.shields.io/badge/tesseract--lang-4.1.0-111827?style=flat-square&logo=homebrew&logoColor=FBB040" height="20"/>
<img src="https://img.shields.io/badge/Status-Instalado-22C55E?style=flat" height="20"/>
</h3>

<p align="center">
Instalação do Tesseract OCR no macOS (Apple Silicon) via Homebrew, com o pacote de idiomas
<code>tesseract-lang</code> — que inclui o português (<code>por</code>) usado no OCR dos comprovantes.
</p>

| Imagem | Etapa | Descrição |
| :---: | :--- | :--- |
| <img src="./img/tesseract1.png" width="260"/> | 1️⃣ Comando de instalação | `brew install tesseract tesseract-lang` — o Homebrew se atualiza antes de começar |
| <img src="./img/tesseract2.png" width="260"/> | 2️⃣ Resolução de dependências | Manifests de `tesseract 5.5.3` e `tesseract-lang 4.1.0`, com as dependências (`leptonica`, `openjpeg`, `pango`, …) a instalar/atualizar |
| <img src="./img/tesseract3.png" width="260"/> | 3️⃣ Instalação concluída | Dependências instaladas e `tesseract` + `tesseract-lang` copiados para `/opt/homebrew/Cellar` |

<details>
<summary>▶️🖼️ <b>OpenCV</b></summary>

Comando externo que recebe `arquivoEntrada arquivoSaida` e faz o pré-processamento real com OpenCV:

```properties
app.opencv.command=python3 scripts/preprocessar_documento.py
```

Quando `imagemBase64` for enviado, o `OpenCvService` chama esse comando, recebe a imagem tratada e a
encaminha para o Tesseract.

</details>

<details>
<summary>▶️🐘 <b>PostgreSQL</b></summary>

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/validacao_documentos
spring.datasource.username=${DB_USER:postgres}
spring.datasource.password=${DB_PASSWORD:postgres}
spring.jpa.hibernate.ddl-auto=update
```

</details>

<h2 align="center" id="executar">▶️ Como Executar </h2>

```bash
git clone https://github.com/wqiluc/Documentos-ocr-ia-java.git
cd Documentos-ocr-ia-java

# backend (API em http://localhost:8080)
cd Documentos-Ocr-IA-Java/backend && mvn spring-boot:run

# frontend (em outro terminal — http://localhost:5173)
cd Documentos-Ocr-IA-Java/frontend && npm install && npm run dev
```

<h3 align="left">🎬 Roteiro para a demo</h3>

1. Suba a API com `mvn spring-boot:run` (em `Documentos-Ocr-IA-Java/backend/`) e o frontend com `npm run dev` (em `Documentos-Ocr-IA-Java/frontend/`).
2. Use `GET /api/documentos/exemplo` para pegar um payload base.
3. Comece **sem** chave OpenAI nem Tesseract para mostrar o fallback local.
4. Envie um comprovante recente no nome do cliente → `APROVADO`.
5. Envie um comprovante antigo ou com CEP inválido → `REPROVADO` com o motivo.
6. Envie um comprovante em nome de outra pessoa → `ANALISE_MANUAL`.
7. Configure OpenAI, Tesseract e OpenCV para mostrar a versão mais próxima do mundo real.

<h2 align="center" id="arquitetura">🏰 Arquitetura do Projeto <br>
<img src="https://img.shields.io/badge/Architecture-111827?style=flat-square&logo=instructure&logoColor=white" height="22"/>
</h2>

<p align="center">
Estrutura já criada no repositório — <b>todos os arquivos estão vazios</b> e serão preenchidos durante a implementação do MVP.
</p>

<pre>
Documentos-ocr-ia-java <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" title="Java" alt="Java" width="40" height="22"/>&nbsp;/
├── img <img src="https://img.shields.io/badge/-Prints-111827?style=flat&logo=homebrew&logoColor=FBB040" height="18"/>/
├── pdf <img src="https://img.shields.io/badge/PDF-Arquivo-FF0000?style=flat-square&logo=data:image/svg+xml;base64,PHN2ZyByb2xlPSJpbWciIHZpZXdCb3g9IjAgMCAyNCAyNCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj48cGF0aCBkPSJNMTguMTE3IDUuMTY0YzEuMDE0IDAgMS44MzYuODIyIDEuODM2IDEuODM2djEwYzAgMS4wMTQtLjgyMiAxLjgzNi0xLjgzNiAxLjgzNkg1Ljg4M2MtMS4wMTQgMC0xLjgzNi0uODIyLTEuODM2LTEuODM2di0xMGMwLTEuMDE0LjgyMi0xLjgzNiAxLjgzNi0xLjgzNmgxMi4yMzR6bS02LjA2NSA1LjA1N0w2LjkxMiAxNC42OWgxMC4yOGwtNS4xNDQtOS41MjZ6IiBmaWxsPSJ3aGl0ZSIvPjwvc3ZnPg==&logoColor=white" height="18">/
│
└── Documentos-Ocr-IA-Java <img src="https://img.shields.io/badge/-Aplicação-111827?style=flat&logo=openjdk&logoColor=orange" height="18"/>/
    ├── backend <img src="https://img.shields.io/badge/-Java_21-111827?style=flat&logo=openjdk&logoColor=orange" height="18"/> <img src="https://img.shields.io/badge/-Spring_Boot-111827?style=flat&logo=springboot&logoColor=6DB33F" height="18"/> <img src="https://img.shields.io/badge/-Maven-111827?style=flat&logo=apachemaven&logoColor=C71A36" height="18"/>/
    │   ├── pom.xml <img src="https://img.shields.io/badge/-Maven_POM-111827?style=flat&logo=apachemaven&logoColor=C71A36" height="18"/>
    │   ├── scripts/ <img src="https://img.shields.io/badge/-Python_·_OpenCV-111827?style=flat&logo=opencv&logoColor=5C3EE8" height="18"/>
    │   │   ├── preprocessar_documento.py <img src="https://img.shields.io/badge/Etapa_2-111827?style=flat&logo=opencv&logoColor=5C3EE8" height="18"/>
    │   │   └── requirements.txt
    │   └── src/
    │       ├── main/java/br/com/cesarschool/documentos <img src="https://img.shields.io/badge/-Java-111827?style=flat&logo=openjdk&logoColor=orange" height="18"/>/
    │       │   ├── ValidacaoDocumentosApplication.java <img src="https://img.shields.io/badge/-Entry_Point-111827?style=flat&logo=springboot&logoColor=6DB33F" height="18"/>
    │       │   ├── config <img src="https://img.shields.io/badge/-Config-111827?style=flat&logo=springboot&logoColor=6DB33F" height="18"/>/
    │       │   │   ├── AppProperties.java
    │       │   │   └── CorsConfig.java
    │       │   ├── controller <img src="https://img.shields.io/badge/-API_REST-111827?style=flat&logo=openapiinitiative&logoColor=6BA539" height="18"/>/
    │       │   │   ├── ComprovanteController.java
    │       │   │   └── DocumentoExemploController.java
    │       │   ├── dto <img src="https://img.shields.io/badge/-DTO-111827?style=flat&logo=openapiinitiative&logoColor=6BA539" height="18"/>/
    │       │   │   ├── ValidacaoRequest.java
    │       │   │   ├── ValidacaoResponse.java
    │       │   │   ├── EtapaPipeline.java
    │       │   │   └── DadosComprovanteDto.java
    │       │   ├── service <img src="https://img.shields.io/badge/-Pipeline-111827?style=flat&logo=springboot&logoColor=6DB33F" height="18"/>/
    │       │   │   ├── OpenCvService.java <img src="https://img.shields.io/badge/Etapa_2-111827?style=flat&logo=opencv&logoColor=5C3EE8" height="18"/>
    │       │   │   ├── OcrService.java <img src="https://img.shields.io/badge/Etapa_3-111827?style=flat&logo=google&logoColor=4285F4" height="18"/>
    │       │   │   ├── InterpretadorIaService.java <img src="https://img.shields.io/badge/Etapa_4-111827?style=flat&logo=openai&logoColor=white" height="18"/>
    │       │   │   └── ValidacaoService.java <img src="https://img.shields.io/badge/Etapa_5-111827?style=flat&logo=springboot&logoColor=6DB33F" height="18"/>
    │       │   ├── regras <img src="https://img.shields.io/badge/-Regras_de_Negócio-111827?style=flat&logo=openjdk&logoColor=FFD700" height="18"/>/
    │       │   │   ├── Regra.java <img src="https://img.shields.io/badge/Interface-111827?style=flat&logo=openjdk&logoColor=22C55E" height="18"/>
    │       │   │   ├── ResultadoRegra.java
    │       │   │   ├── RegraLegibilidade.java <img src="https://img.shields.io/badge/R01-111827?style=flat&logo=openjdk&logoColor=FFD700" height="18"/>
    │       │   │   ├── RegraCamposObrigatorios.java <img src="https://img.shields.io/badge/R02-111827?style=flat&logo=openjdk&logoColor=FFD700" height="18"/>
    │       │   │   ├── RegraEmissao90Dias.java <img src="https://img.shields.io/badge/R06-111827?style=flat&logo=openjdk&logoColor=FFD700" height="18"/>
    │       │   │   ├── RegraCepValido.java <img src="https://img.shields.io/badge/R07-111827?style=flat&logo=openjdk&logoColor=FFD700" height="18"/>
    │       │   │   └── RegraTitularIgualCliente.java <img src="https://img.shields.io/badge/R08-111827?style=flat&logo=openjdk&logoColor=FFD700" height="18"/>
    │       │   ├── dominio <img src="https://img.shields.io/badge/-Domínio_(POO)-111827?style=flat&logo=openjdk&logoColor=purple" height="18"/>/
    │       │   │   ├── Cliente.java <img src="https://img.shields.io/badge/Entidade-111827?style=flat&logo=openjdk&logoColor=3B82F6" height="18"/>
    │       │   │   ├── ComprovanteResidencia.java <img src="https://img.shields.io/badge/Entidade-111827?style=flat&logo=openjdk&logoColor=3B82F6" height="18"/>
    │       │   │   ├── ResultadoValidacao.java <img src="https://img.shields.io/badge/Entidade-111827?style=flat&logo=openjdk&logoColor=3B82F6" height="18"/>
    │       │   │   └── StatusValidacao.java <img src="https://img.shields.io/badge/-APROVADO_·_REPROVADO_·_ANALISE__MANUAL-111827?style=flat&logo=openjdk&logoColor=FFD700" height="18"/>
    │       │   ├── repository <img src="https://img.shields.io/badge/-PostgreSQL-111827?style=flat&logo=postgresql&logoColor=4169E1" height="18"/>/
    │       │   │   ├── ClienteRepository.java
    │       │   │   └── ValidacaoRepository.java
    │       │   └── exception <img src="https://img.shields.io/badge/-Erros-111827?style=flat&logo=openjdk&logoColor=EF4444" height="18"/>/
    │       │       ├── ClienteNaoEncontradoException.java
    │       │       └── GlobalExceptionHandler.java
    │       ├── main/resources/
    │       │   ├── application.properties
    │       │   └── db/migration/V1__criar_tabelas.sql <img src="https://img.shields.io/badge/-SQL-111827?style=flat&logo=postgresql&logoColor=4169E1" height="18"/>
    │       └── test/java/br/com/cesarschool/documentos <img src="https://img.shields.io/badge/-JUnit_5-111827?style=flat&logo=junit5&logoColor=25A162" height="18"/>/
    │           ├── regras/
    │           │   ├── RegraEmissao90DiasTest.java
    │           │   ├── RegraCepValidoTest.java
    │           │   └── RegraTitularIgualClienteTest.java
    │           └── service/
    │               └── ValidacaoServiceTest.java
    │
    └── frontend <img src="https://img.shields.io/badge/-React_18-111827?style=flat&logo=react&logoColor=61DAFB" height="18"/> <img src="https://img.shields.io/badge/-TypeScript-111827?style=flat&logo=typescript&logoColor=3178C6" height="18"/> <img src="https://img.shields.io/badge/-Vite-111827?style=flat&logo=vite&logoColor=646CFF" height="18"/> <img src="https://img.shields.io/badge/-Tailwind_CSS-111827?style=flat&logo=tailwindcss&logoColor=38BDF8" height="18"/>/
        ├── index.html <img src="https://img.shields.io/badge/-HTML5-111827?style=flat-square&logo=html5&logoColor=E34F26" height="18"/>
        ├── package.json <img src="https://img.shields.io/badge/-npm-111827?style=flat-square&logo=npm&logoColor=CB3837" height="18"/>
        ├── vite.config.ts <img src="https://img.shields.io/badge/-Vite-111827?style=flat-square&logo=vite&logoColor=646CFF" height="18"/>
        ├── tailwind.config.ts <img src="https://img.shields.io/badge/-Tailwind_CSS-111827?style=flat-square&logo=tailwindcss&logoColor=38BDF8" height="18"/>
        ├── postcss.config.js <img src="https://img.shields.io/badge/-PostCSS-111827?style=flat-square&logo=postcss&logoColor=DD3A0A" height="18"/>
        ├── tsconfig.json <img src="https://img.shields.io/badge/-TypeScript-111827?style=flat-square&logo=typescript&logoColor=3178C6" height="18"/>
        ├── public/
        └── src <img src="https://img.shields.io/badge/-TSX_·_TS-111827?style=flat&logo=typescript&logoColor=3178C6" height="18"/>/
            ├── main.tsx · App.tsx · index.css · vite-env.d.ts
            ├── routes/index.tsx <img src="https://img.shields.io/badge/-React_Router-111827?style=flat&logo=reactrouter&logoColor=CA4245" height="18"/>
            ├── services/api.ts <img src="https://img.shields.io/badge/-Cliente_HTTP-111827?style=flat&logo=javascript&logoColor=F7DF1E" height="18"/>
            ├── types/validacao.ts <img src="https://img.shields.io/badge/-Tipos-111827?style=flat&logo=typescript&logoColor=3178C6" height="18"/>
            ├── components <img src="https://img.shields.io/badge/-Componentes-111827?style=flat&logo=react&logoColor=61DAFB" height="18"/>/
            │   ├── layout <img src="https://img.shields.io/badge/-Layout-111827?style=flat&logo=react&logoColor=61DAFB" height="18"/>/
            │   │   ├── LayoutPrincipal.tsx <img src="https://img.shields.io/badge/Estrutura_base-111827?style=flat&logo=react&logoColor=61DAFB" height="18"/>
            │   │   └── Cabecalho.tsx <img src="https://img.shields.io/badge/Header-111827?style=flat&logo=react&logoColor=61DAFB" height="18"/>
            │   ├── upload <img src="https://img.shields.io/badge/-Upload-111827?style=flat&logo=react&logoColor=E0234E" height="18"/>/
            │   │   ├── FormularioUpload.tsx <img src="https://img.shields.io/badge/Envio_do_arquivo-111827?style=flat&logo=react&logoColor=E0234E" height="18"/>
            │   │   └── PreviewDocumento.tsx <img src="https://img.shields.io/badge/Pré--visualização-111827?style=flat&logo=react&logoColor=E0234E" height="18"/>
            │   ├── resultado <img src="https://img.shields.io/badge/-Resultado-111827?style=flat&logo=react&logoColor=22C55E" height="18"/>/
            │   │   ├── CartaoResultado.tsx <img src="https://img.shields.io/badge/Status_final-111827?style=flat&logo=react&logoColor=22C55E" height="18"/>
            │   │   ├── ListaRegras.tsx <img src="https://img.shields.io/badge/Regras_R01–R08-111827?style=flat&logo=react&logoColor=FFD700" height="18"/>
            │   │   └── LinhaEtapas.tsx <img src="https://img.shields.io/badge/Etapas_1–5-111827?style=flat&logo=react&logoColor=A855F7" height="18"/>
            │   └── ui <img src="https://img.shields.io/badge/-UI-111827?style=flat&logo=react&logoColor=61DAFB" height="18"/>/
            │       ├── SeloStatus.tsx <img src="https://img.shields.io/badge/Badge_de_status-111827?style=flat&logo=react&logoColor=22C55E" height="18"/>
            │       └── Carregando.tsx <img src="https://img.shields.io/badge/Loading-111827?style=flat&logo=react&logoColor=61DAFB" height="18"/>
            └── pages <img src="https://img.shields.io/badge/-Páginas-111827?style=flat&logo=react&logoColor=61DAFB" height="18"/>/
                ├── Upload/PaginaUpload.tsx <img src="https://img.shields.io/badge/Cliente_envia_comprovante-111827?style=flat&logo=react&logoColor=E0234E" height="18"/>
                ├── Resultado/PaginaResultado.tsx <img src="https://img.shields.io/badge/Aprovado_·_Reprovado_+_motivos-111827?style=flat&logo=react&logoColor=22C55E" height="18"/>
                └── Validacoes/PaginaValidacoes.tsx <img src="https://img.shields.io/badge/Backoffice_·_Compliance-111827?style=flat&logo=react&logoColor=A855F7" height="18"/>
</pre>