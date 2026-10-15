<h1 align="center"> Documentos OCR + IA 📝🤖<br>
<img src="./img/Logo_OCR_Java.png" alt="Logo Projeto" width="550"> <br>
<img src="https://img.shields.io/badge/-Java_21-020617?style=flat-square&logo=openjdk&logoColor=orange" height="19"/>
<img src="https://img.shields.io/badge/-Spring_Boot-020617?style=flat-square&logo=springboot&logoColor=6DB33F" height="19"/>
<img src="https://img.shields.io/badge/-Maven-020617?style=flat-square&logo=apachemaven&logoColor=C71A36" height="19"/>
<img src="https://img.shields.io/badge/-PostgreSQL-020617?style=flat-square&logo=postgresql&logoColor=4169E1" height="19"/>

[![License: MIT](https://img.shields.io/badge/License-MIT-orange.svg)](https://github.com/wqiluc/Documentos-ocr-ia-java/blob/main/LICENSE)
![Version](https://img.shields.io/badge/version-1.0.0--SNAPSHOT-purple)
![Status](https://img.shields.io/badge/status-MVP_em_desenvolvimento-blue)
</h1>

<p align="center">
Validação inteligente de documentos no <b>cadastro de novos clientes (onboarding) de um banco digital</b> —
o cliente envia o documento de identificação (RG/CNH) e o comprovante de residência, e o sistema faz
<b>OpenCV → Tesseract (OCR) → IA → regras em Java</b> para aprovar ou reprovar o cadastro, sempre
informando o motivo.
</p>

> 🚧 **Status:** MVP do comprovante de residência implementado (backend Spring Boot + frontend React). A API sobe com
> **H2 em memória** por padrão; PostgreSQL é opcional. Coleção do Postman/Insomnia em [`postman/`](postman/) e contrato
> OpenAPI em [`docs/openapi.yaml`](docs/openapi.yaml).


<h2 align="center" id="docente">👨‍🏫 Docente: </h2>
<ul>
<li><b>Kleber Meira</b> <a href="https://www.linkedin.com/in/kleber-m-3b5532b6/" target="_blank"><img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/linkedin/linkedin-original.svg" width="14"/></a> <a href="https://github.com/KleberMeira" target="_blank"><img src="https://img.shields.io/badge/GitHub-000000?style=for-the-badge&logo=github&logoColor=white" height="16"/></a></li>
</ul>

<h2 align="center" id="autor">👤👨‍🎓 Autor: </h2>
<ul>
<li><b>Lucas Paguetti Pereira</b> <a href="https://www.linkedin.com/in/lucas-paguetti-pereira" target="_blank"><img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/linkedin/linkedin-original.svg" width="14"/></a> <a href="https://github.com/wqiluc" target="_blank"><img src="https://img.shields.io/badge/GitHub-000000?style=for-the-badge&logo=github&logoColor=white" height="16"/></a></li>
</ul>


<h2 align="center" id="tecnologias"> ⛏️💻 Tecnologias Utilizadas: <br>
<img src="https://img.shields.io/badge/Stack-111827?style=flat&logo=openjdk&logoColor=orange" height="22"/>
</h2>
<p align="center">
  <img src="https://upload.wikimedia.org/wikipedia/commons/9/9a/Visual_Studio_Code_1.35_icon.svg" width="32" height="32" alt="VS Code"/> <br>
  <img src="https://img.shields.io/badge/-Java_21-111827?style=flat-square&logo=openjdk&logoColor=orange"/>
  <img src="https://img.shields.io/badge/-Spring_Boot_3.5.16-111827?style=flat-square&logo=springboot&logoColor=6DB33F"/>
  <img src="https://img.shields.io/badge/-Spring_Data_JPA-111827?style=flat-square&logo=hibernate&logoColor=BCAE79"/>
  <img src="https://img.shields.io/badge/-Maven-111827?style=flat-square&logo=apachemaven&logoColor=C71A36"/>
  <img src="https://img.shields.io/badge/-JUnit_5_·_AssertJ-111827?style=flat-square&logo=junit5&logoColor=25A162"/> <br>
  <img src="https://img.shields.io/badge/-OpenCV_4-111827?style=flat-square&logo=opencv&logoColor=5C3EE8"/>
  <img src="https://img.shields.io/badge/-Tesseract_OCR_5.5-111827?style=flat-square&logo=google&logoColor=4285F4"/>
  <img src="https://img.shields.io/badge/-OpenAI_API-111827?style=flat-square&logo=openai&logoColor=white"/>
  <img src="https://img.shields.io/badge/-Python_(OpenCV_·_PyMuPDF)-111827?style=flat-square&logo=python&logoColor=3776AB"/> <br>
  <img src="https://img.shields.io/badge/-PostgreSQL_16-111827?style=flat-square&logo=postgresql&logoColor=white"/>
  <img src="https://img.shields.io/badge/-H2_(em_memória)-111827?style=flat-square&logo=databricks&logoColor=1E90FF"/>
  <img src="https://img.shields.io/badge/-Flyway-111827?style=flat-square&logo=flyway&logoColor=CC0200"/>
  <img src="https://img.shields.io/badge/-Docker_Compose-111827?style=flat-square&logo=docker&logoColor=2496ED"/>
  <img src="https://img.shields.io/badge/-API_REST-111827?style=flat-square&logo=openapiinitiative&logoColor=6BA539"/> <br>
  <img src="https://img.shields.io/badge/-React_18-111827?style=flat-square&logo=react&logoColor=61DAFB"/>
  <img src="https://img.shields.io/badge/-TypeScript_5-111827?style=flat-square&logo=typescript&logoColor=3178C6"/>
  <img src="https://img.shields.io/badge/-Vite_5-111827?style=flat-square&logo=vite&logoColor=646CFF"/>
  <img src="https://img.shields.io/badge/-Tailwind_CSS_3-111827?style=flat-square&logo=tailwindcss&logoColor=38BDF8"/>
  <img src="https://img.shields.io/badge/-React_Router_6-111827?style=flat-square&logo=reactrouter&logoColor=CA4245"/> <br>
  <img src="https://img.shields.io/badge/-Postman-111827?style=flat-square&logo=postman&logoColor=FF6C37"/>
  <img src="https://img.shields.io/badge/-Insomnia-111827?style=flat-square&logo=insomnia&logoColor=4000BF"/> <br>
  <img src="https://img.shields.io/badge/-Git-111827?style=flat-square&logo=git&logoColor=F05032"/>
  <img src="https://img.shields.io/badge/-GitHub-111827?style=flat-square&logo=github&logoColor=white"/>
  <img src="https://img.shields.io/badge/-GitHub_Desktop-111827?style=flat-square&logo=github&logoColor=purple"/>
</p>

<details>
<summary>▶️📦 <b>Versões e papel de cada tecnologia</b></summary>

| Tecnologia | Versão | Papel |
| :--- | :---: | :--- |
| ![Java](https://img.shields.io/badge/-Java-111827?style=flat-square&logo=openjdk&logoColor=orange) | 21 | Linguagem do backend |
| ![Spring Boot](https://img.shields.io/badge/-Spring_Boot-111827?style=flat-square&logo=springboot&logoColor=6DB33F) | 3.5.16 | API REST, JPA e validação |
| ![Maven](https://img.shields.io/badge/-Maven-111827?style=flat-square&logo=apachemaven&logoColor=C71A36) | 3.9+ | Build e dependências |
| ![JUnit 5 · AssertJ](https://img.shields.io/badge/-JUnit_5_·_AssertJ-111827?style=flat-square&logo=junit5&logoColor=25A162) | — | Testes das regras e do pipeline |
| ![PyMuPDF](https://img.shields.io/badge/-PyMuPDF-111827?style=flat-square&logo=python&logoColor=3776AB) | 1.24+ | Etapa 2: PDF → imagem |
| ![OpenCV](https://img.shields.io/badge/-OpenCV-111827?style=flat-square&logo=opencv&logoColor=5C3EE8) | 4.10+ | Etapa 2: tratamento da imagem |
| ![NumPy](https://img.shields.io/badge/-NumPy-111827?style=flat-square&logo=numpy&logoColor=4DABCF) | 1.26+ | Etapa 2: matrizes da imagem |
| ![Tesseract OCR](https://img.shields.io/badge/-Tesseract_OCR-111827?style=flat-square&logo=google&logoColor=4285F4) | 5.5.3 | Etapa 3: texto (`por`) + confiança |
| ![OpenAI API](https://img.shields.io/badge/-OpenAI_API-111827?style=flat-square&logo=openai&logoColor=white) | `gpt-4o-mini` | Etapa 4: texto → JSON |
| ![PostgreSQL](https://img.shields.io/badge/-PostgreSQL-111827?style=flat-square&logo=postgresql&logoColor=4169E1) | 16 | Banco de dados |
| ![H2](https://img.shields.io/badge/-H2-111827?style=flat-square) | — | Banco em memória (padrão) |
| ![Flyway](https://img.shields.io/badge/-Flyway-111827?style=flat-square&logo=flyway&logoColor=CC0200) | — | Migrações |
| ![Docker Compose](https://img.shields.io/badge/-Docker_Compose-111827?style=flat-square&logo=docker&logoColor=2496ED) | — | Sobe o PostgreSQL |
| ![React](https://img.shields.io/badge/-React-111827?style=flat-square&logo=react&logoColor=61DAFB) | 18.3 | Frontend |
| ![TypeScript](https://img.shields.io/badge/-TypeScript-111827?style=flat-square&logo=typescript&logoColor=3178C6) | 5.6 | Tipagem do frontend |
| ![Vite](https://img.shields.io/badge/-Vite-111827?style=flat-square&logo=vite&logoColor=646CFF) | 5.4 | Build do frontend |
| ![Tailwind CSS](https://img.shields.io/badge/-Tailwind_CSS-111827?style=flat-square&logo=tailwindcss&logoColor=38BDF8) | 3.4 | Estilos |
| ![React Router](https://img.shields.io/badge/-React_Router-111827?style=flat-square&logo=reactrouter&logoColor=CA4245) | 6.28 | Rotas |
| ![Postman](https://img.shields.io/badge/-Postman-111827?style=flat-square&logo=postman&logoColor=FF6C37) ![Insomnia](https://img.shields.io/badge/-Insomnia-111827?style=flat-square&logo=insomnia&logoColor=4000BF) | — | Coleção da API |
| ![OpenAPI](https://img.shields.io/badge/-OpenAPI-111827?style=flat-square&logo=openapiinitiative&logoColor=6BA539) | 3.0.3 | Contrato da API |
</details>

<h2 align="center" id="problema">🧩 Problema e Usuários <br>
<img src="https://img.shields.io/badge/Cenário-Onboarding_bancário-111827?style=flat&logo=cashapp&logoColor=22C55E" height="22"/>
</h2>

No cadastro de novos clientes, o backoffice confere à mão o documento de identificação e o comprovante de residência:
lê os dados, **redigita no sistema** e verifica se nome, CPF e endereço batem. O processo é **lento** (o cliente espera
dias pela conta), sujeito a **erros de digitação** (CPF, CEP) e **vulnerável a fraudes** (documento vencido,
comprovante antigo ou em nome de outra pessoa).

| Usuário | O que faz no sistema |
| :--- | :--- |
| ⭐ Analista de cadastro (backoffice) | Acompanha as validações e trata os casos de análise manual |
| 🛡️ Equipe de compliance | Consulta o histórico e os motivos de reprovação |
| 📱 Cliente | Envia os documentos e acompanha o status do cadastro |

<h2 align="center" id="regras">📏 Documentos e Regras de Negócio </h2>

| Documento | Exemplos | Dados extraídos |
| :--- | :--- | :--- |
| 🪪 Identificação | RG ou CNH | Nome, CPF, data de nascimento, número do documento, órgão emissor/UF e validade (CNH) |
| 🏠 Comprovante de residência | Conta de luz, água ou internet | Titular, endereço, bairro, cidade/UF, CEP, empresa emissora e data de emissão |

| # | Regra | Documento | Se falhar | MVP |
| :---: | :--- | :--- | :---: | :---: |
| R01 | Documento legível (≥ 40 caracteres e confiança do OCR ≥ 60%) | Ambos | `REPROVADO` | ✅ |
| R02 | Campos obrigatórios presentes | Ambos | `REPROVADO` | ✅ |
| R03 | CPF com formato e dígitos verificadores válidos | Identificação | `REPROVADO` | 🔜 |
| R04 | CNH dentro da validade | Identificação | `REPROVADO` | 🔜 |
| R05 | Cliente maior de 18 anos | Identificação | `REPROVADO` | 🔜 |
| R06 | Comprovante emitido há no máximo 90 dias | Comprovante | `REPROVADO` | ✅ |
| R07 | CEP com formato válido (8 dígitos) | Comprovante | `REPROVADO` | ✅ |
| R08 | Titular do comprovante igual ao cliente (pode ser de um familiar) | Cruzada | `ANALISE_MANUAL` | ✅ |
| R09 | CPF não cadastrado anteriormente | Identificação | `REPROVADO` | 🔜 |

**Status final:** `APROVADO` quando todas as regras são atendidas; `ANALISE_MANUAL` quando falha **apenas** a R08;
`REPROVADO` quando falha qualquer outra (mesmo junto com a R08). Se um campo não é encontrado, só a R02 reporta a
ausência — as regras que dependem dele (R06, R07, R08) são ignoradas. A resposta **sempre informa o motivo**.

<h3 align="left">🎯 Escopo do MVP</h3>

O MVP processa **apenas o comprovante de residência de um cliente já cadastrado**: upload → OpenCV → OCR → IA →
regras R01, R02, R06, R07 e R08 → banco → consulta via API e frontend (upload, timeline do pipeline, resultado e
histórico do backoffice).

**Próximos passos:** documento de identificação (R03, R04, R05, R09), validação cruzada de nome e CPF entre RG/CNH e
comprovante, e um endpoint de cadastro de clientes (hoje eles vêm da migração do Flyway).

<h2 align="center" id="fluxo">🔄 Fluxo do Sistema </h2>

```mermaid
flowchart LR
    A[📤 Upload<br/>PDF/imagem] --> B[🖼️ OpenCV<br/>tratamento da imagem]
    B --> C[🔤 Tesseract<br/>OCR]
    C --> D[🤖 IA<br/>texto → JSON]
    D --> E[☕ Spring Boot<br/>objetos Java + regras]
    E --> F[(🐘 PostgreSQL / H2<br/>cadastro + resultado)]
    F --> G[🌐 API REST<br/>aprovado/reprovado + motivos]
```

| # | Etapa | Classe | O que faz | Fallback |
| :---: | :--- | :--- | :--- | :--- |
| 1 | Documento | `ComprovanteController` | Recebe o texto e/ou a imagem/PDF em base64 | — |
| 2 | OpenCV | `OpenCvService` | PDF → imagem (300 dpi), escala de cinza, remoção de ruído, binarização e correção de inclinação | Imagem original |
| 3 | Tesseract | `OcrService` | Extrai o texto (`por`, saída TSV) e a confiança média das palavras | Texto de `conteudoDocumento` (confiança 95%) |
| 4 | IA | `InterpretadorIaService` | Texto → JSON do comprovante (OpenAI, `json_object`, `temperature 0`) | Interpretador local (regex) |
| 5 | Regras | `ValidacaoService` + `regras/*` | JSON → `ComprovanteResidencia` e aplicação das regras | — |
| 6 | Banco | `ValidacaoRepository` | Persiste dados extraídos, etapas e resultado | H2 em memória (padrão) |

> 💡 A resposta traz o campo `etapas`, dizendo o que aconteceu em cada passo — inclusive quando um fallback foi
> usado (ex.: `"OpenCV indisponível — imagem original"`). Assim a demo funciona sem credenciais nem ferramentas instaladas.

<h2 align="center" id="api">🌐 Endpoints </h2>

| Método | Rota | Descrição | Respostas |
| :---: | :--- | :--- | :--- |
| `GET` | `/api/clientes` | Lista os clientes cadastrados | `200` |
| `GET` | `/api/documentos/exemplo` | Payload pronto para a demo (data de emissão sempre recente) | `200` |
| `POST` | `/api/clientes/{clienteId}/comprovantes` | Envia o comprovante e executa o pipeline | `201` · `400` · `404` |
| `GET` | `/api/clientes/{clienteId}/comprovantes/{validacaoId}` | Consulta o resultado de uma validação | `200` · `404` |
| `GET` | `/api/validacoes` | Histórico de validações, da mais recente para a mais antiga | `200` |

O `POST` devolve `201` mesmo quando o comprovante é reprovado: o veredito fica em `resultado.status`. Erros voltam como
`{ "erro": "mensagem" }` — cliente ou validação inexistente (`404`), corpo sem texto nem imagem ou base64 inválido (`400`).

<details>
<summary>▶️📥 <b>Exemplo de request</b></summary>

```json
{
  "nomeArquivo": "comprovante-residencia.pdf",
  "mimeType": "application/pdf",
  "conteudoDocumento": "CONTA DE ENERGIA\nEmpresa: Neoenergia Pernambuco\nTitular: Joao da Silva\nEndereco: Rua das Flores, 150\nBairro: Boa Vista\nCidade: Recife - PE\nCEP: 50050-000\nData de emissao: 28/09/2026",
  "imagemBase64": null
}
```

| Campo | Obrigatório | Descrição |
| :--- | :---: | :--- |
| `nomeArquivo` | não | Nome exibido no resultado (padrão: `comprovante`) |
| `mimeType` | não | `image/png`, `image/jpeg` ou `application/pdf` |
| `conteudoDocumento` | um dos dois | Texto já extraído; também é o fallback se o OCR falhar |
| `imagemBase64` | um dos dois | Imagem ou PDF em base64 (aceita o prefixo `data:image/png;base64,`) |

O interpretador local reconhece linhas `Rótulo: valor`: `Titular`/`Nome`/`Cliente`, `Endereço`, `Bairro`, `Cidade`,
`CEP`, `Empresa`/`Distribuidora` e `Data de emissão` (`dd/mm/aaaa`).

</details>

<details>
<summary>▶️📤 <b>Exemplo de retorno — aprovado (<code>201 Created</code>)</b></summary>

```json
{
  "validacaoId": 1,
  "clienteId": 1,
  "nomeCliente": "Joao da Silva",
  "nomeArquivo": "comprovante-residencia.pdf",
  "criadoEm": "2026-10-08T13:56:48.703274",
  "etapas": [
    { "ordem": 1, "nome": "Documento", "resultado": "comprovante-residencia.pdf" },
    { "ordem": 2, "nome": "OpenCV", "resultado": "Imagem tratada (cinza, ruído, binarização, inclinação)" },
    { "ordem": 3, "nome": "Tesseract", "resultado": "Texto extraído (confiança 96%)" },
    { "ordem": 4, "nome": "IA", "resultado": "JSON estruturado gerado (interpretador local — regex)" },
    { "ordem": 5, "nome": "Regras", "resultado": "5 de 5 regras atendidas" }
  ],
  "dadosExtraidos": {
    "nomeTitular": "Joao da Silva",
    "endereco": "Rua das Flores, 150",
    "bairro": "Boa Vista",
    "cidadeUf": "Recife - PE",
    "cep": "50050-000",
    "empresaEmissora": "Neoenergia Pernambuco",
    "dataEmissao": "2026-09-28"
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

Em caso de reprovação ou análise manual, `motivos` explica o que fazer — ex.: `"Comprovante emitido há mais de 90 dias
(120 dias). Envie um comprovante mais recente."` ou `"Comprovante em nome de \"Carlos Pereira Lima\", diferente do
cliente \"Joao da Silva\". Encaminhado para análise manual."`

</details>

<h2 align="center" id="executar">▶️ Como Executar </h2>

| Ferramenta | Obrigatória? | Para quê |
| :--- | :---: | :--- |
| JDK 21+ e Maven 3.9+ | ✅ | Backend |
| Node.js 18+ e npm | ✅ | Frontend |
| Tesseract + `tesseract-lang` | opcional | OCR de imagens de verdade |
| Python 3 + `scripts/requirements.txt` | opcional | Pré-processamento OpenCV e suporte a PDF |
| Docker | opcional | PostgreSQL |
| `OPENAI_API_KEY` | opcional | Extração com IA (sem a chave, usa regex) |

```bash
git clone https://github.com/wqiluc/Documentos-ocr-ia-java.git
cd Documentos-ocr-ia-java

# backend (http://localhost:8080)
cd Documentos-Ocr-IA-Java/backend && mvn spring-boot:run

# frontend, em outro terminal (http://localhost:5173)
cd Documentos-Ocr-IA-Java/frontend && npm install && npm run dev

# testes do backend
cd Documentos-Ocr-IA-Java/backend && mvn test
```

O Vite repassa `/api` para `http://localhost:8080`, então não há problema de CORS. Para apontar para outra API, crie
`frontend/.env` com `VITE_API_URL=<url>`.

<details>
<summary>▶️🧪 <b>O que os testes cobrem</b></summary>

| Classe de teste | O que cobre |
| :--- | :--- |
| `RegraEmissao90DiasTest` | Comprovante recente, exatamente 90 dias, mais de 90, data no futuro e sem data (`Clock` fixo) |
| `RegraCepValidoTest` | CEPs válidos (com/sem hífen) e inválidos (curto, longo, letras, `00000-000`) |
| `RegraTitularIgualClienteTest` | Nome igual ignorando acento/caixa/espaços e nome diferente → `ANALISE_MANUAL` |
| `ValidacaoServiceTest` | Pipeline completo com H2: aprovado, reprovado, análise manual, prioridade do reprovado e cliente inexistente |

</details>

<h3 align="left">⚙️ Integrações (opcionais)</h3>

Toda a configuração fica em `backend/src/main/resources/application.properties`. Sem nenhuma delas, a API usa os
fallbacks do [pipeline](#fluxo).

<details>
<summary>▶️🤖 <b>OpenAI</b></summary>

```bash
export OPENAI_API_KEY="sua-chave"
export OPENAI_MODEL="gpt-4o-mini"   # opcional
```

Propriedades: `app.ai.openai.api-key`, `app.ai.openai.model` e `app.ai.openai.url`. O campo `etapas[3].resultado`
mostra se foi usada a OpenAI ou o interpretador local.

</details>

<details>
<summary>▶️🔤 <b>Tesseract</b></summary>

```bash
brew install tesseract tesseract-lang   # macOS
tesseract --list-langs | grep por       # confere o português
```

Propriedades: `app.ocr.command=tesseract`, `app.ocr.tessdata-path=/opt/homebrew/share/tessdata` e `app.ocr.language=por`.
O `OcrService` roda `tesseract <arquivo> stdout -l por tsv`.

Instalação feita em 23/09 no macOS (Apple Silicon) — `tesseract 5.5.3` + `tesseract-lang 4.1.0`:

| <img src="./img/tesseract1.png" width="240"/> | <img src="./img/tesseract2.png" width="240"/> | <img src="./img/tesseract3.png" width="240"/> |
| :---: | :---: | :---: |
| 1️⃣ Comando de instalação | 2️⃣ Resolução de dependências | 3️⃣ Instalação concluída |

</details>

<details>
<summary>▶️🖼️ <b>OpenCV</b></summary>

```bash
cd Documentos-Ocr-IA-Java/backend
pip3 install -r scripts/requirements.txt

# testando o script sozinho
python3 scripts/pre-processar_documento.py conta-luz.pdf tratada.png
tesseract tratada.png stdout -l por

# documentação do script
python3 scripts/docs_pre-processar_documento.py
```

Propriedade: `app.opencv.command=python3 scripts/pre-processar_documento.py` (recebe `arquivoEntrada arquivoSaida.png`).
O caminho é relativo a `backend/`, então suba a API de dentro dela.

> ⚠️ O Tesseract não lê PDF. Sem o script, um PDF só é validado se o texto também vier em `conteudoDocumento`.

</details>

<details>
<summary>▶️🐘 <b>Banco de dados — H2 (padrão) ou PostgreSQL</b></summary>

O H2 em memória (modo PostgreSQL) não precisa de instalação, mas os dados somem quando a API para. Para o PostgreSQL:

```bash
cd Documentos-Ocr-IA-Java/backend
docker compose up -d                                       # PostgreSQL 16 na porta 5432
mvn spring-boot:run -Dspring-boot.run.profiles=postgres    # usuário/senha: DB_USER / DB_PASSWORD (padrão postgres)
```

Nos dois casos, o **Flyway** cria as tabelas `cliente` e `validacao_comprovante` e insere os clientes de exemplo; o
Hibernate fica em `ddl-auto=validate`.

| id | Nome | CPF |
| :---: | :--- | :--- |
| 1 | Joao da Silva | 529.982.247-25 |
| 2 | Maria Oliveira Santos | 111.444.777-35 |
| 3 | Ana Beatriz Cavalcanti | 407.324.134-60 |
| 4 | Pedro Henrique Albuquerque | 361.970.653-04 |
| 5 | Fernanda Lima Rocha | 347.031.846-80 |
| 6 | Rafael Souza Barbosa | 777.005.171-18 |
| 7 | Juliana Freitas Monteiro | 598.915.250-74 |

</details>

<h2 align="center" id="telas">🖥️ Telas do Frontend <br>
<img src="https://img.shields.io/badge/-React_18-111827?style=flat&logo=react&logoColor=61DAFB" height="22"/>
<img src="https://img.shields.io/badge/-3_rotas-111827?style=flat&logo=reactrouter&logoColor=CA4245" height="22"/>
</h2>

Fluxo completo com o cliente **Joao da Silva** e o arquivo [`pdf/conta-luz-exemplo.png`](pdf/conta-luz-exemplo.png).

| <img src="./pdf/tela-novo-envio.png" alt="Tela de novo envio" width="380"/> | <img src="./pdf/tela-novo-envio-arquivo-selecionado.png" alt="Novo envio com arquivo selecionado" width="380"/> |
| :---: | :---: |
| 1️⃣ Novo envio (`/`) — escolha do cliente e do comprovante | 2️⃣ Arquivo selecionado, com a prévia antes de validar |

| <img src="./pdf/tela-resultado-validacao-aprovado.png" alt="Resultado da validação aprovado" width="560"/> |
| :---: |
| 3️⃣ Resultado (`/resultado/:clienteId/:validacaoId`) — dados extraídos, regras R01–R08 e timeline das 6 etapas |

| <img src="./pdf/tela-historico-vazio.png" alt="Histórico sem validações" width="380"/> | <img src="./pdf/tela-historico-com-validacao.png" alt="Histórico com uma validação" width="380"/> |
| :---: | :---: |
| 4️⃣ Histórico (`/validacoes`) ainda vazio | 5️⃣ Histórico com a validação aprovada |

> ℹ️ As capturas foram feitas sem o script OpenCV configurado, por isso a etapa 2 mostra o fallback
> "OpenCV indisponível — imagem original".

<h2 align="center" id="postman">🧰 Testando no Postman / Insomnia <br>
<img src="https://img.shields.io/badge/-Postman-111827?style=flat&logo=postman&logoColor=FF6C37" height="22"/>
<img src="https://img.shields.io/badge/-Insomnia-111827?style=flat&logo=insomnia&logoColor=4000BF" height="22"/>
</h2>

Com o backend rodando, importe [`postman/Documentos-OCR-IA.postman_collection.json`](postman/Documentos-OCR-IA.postman_collection.json)
(Postman: `Import` · Insomnia: `Create` → `Import`). A coleção traz os 7 requests, as variáveis `baseUrl`, `clienteId`
e `validacaoId` (atualizada sozinha após o request "APROVADO") e um script que gera `{{dataRecente}}` (hoje − 10 dias).

> ⚠️ Use o **Postman Desktop** — o Web só acessa `localhost` com o *Postman Agent*. O [`docs/openapi.yaml`](docs/openapi.yaml)
> também pode ser importado (ou aberto no [Swagger Editor](https://editor.swagger.io)), mas seus exemplos têm a data
> fixa `28/09/2026`: ajuste para os últimos 90 dias, senão o cenário aprovado cai na R06.

| # | Request | Rota | Resultado esperado |
| :---: | :--- | :--- | :--- |
| 1 | Listar clientes | `GET /api/clientes` | `200` com os 7 clientes |
| 2 | Payload de exemplo | `GET /api/documentos/exemplo` | `200` com um corpo pronto para o POST |
| 3 | Comprovante — APROVADO | `POST /api/clientes/1/comprovantes` | `201` · `APROVADO` · 5 de 5 regras |
| 4 | Comprovante antigo — REPROVADO | `POST /api/clientes/1/comprovantes` | `201` · `REPROVADO` · "mais de 90 dias" |
| 5 | Comprovante de outra pessoa | `POST /api/clientes/1/comprovantes` | `201` · `ANALISE_MANUAL` |
| 6 | Consultar validação | `GET /api/clientes/1/comprovantes/{{validacaoId}}` | `200` com a validação do passo 3 |
| 7 | Histórico (backoffice) | `GET /api/validacoes` | `200` com todas as validações |

Outros cenários, editando o corpo:

| Altere… | Resultado |
| :--- | :--- |
| `CEP: 5005-00` | `REPROVADO` · CEP inválido (R07) |
| Remova a linha `Data de emissao` | `REPROVADO` · campo obrigatório ausente (R02) |
| Data de emissão no futuro | `REPROVADO` · data no futuro (R06) |
| Texto com menos de 40 caracteres | `REPROVADO` · documento ilegível (R01) |
| `Titular: JOÃO  DA SILVA` | `APROVADO` — acento, caixa e espaços extras são ignorados (R08) |
| URL `/api/clientes/999/comprovantes` | `404` · "Cliente 999 não encontrado." |
| Corpo `{}` | `400` · "Envie conteudoDocumento (texto) e/ou imagemBase64." |

Para testar o OCR com um arquivo de verdade, envie-o em `imagemBase64` (`base64 -i conta-luz.png | tr -d '\n' | pbcopy`
no macOS) com o `mimeType` correspondente — ou simplesmente escolha o arquivo no frontend. Com o H2, os dados e os ids
recomeçam a cada reinício da API.

<details>
<summary>▶️💻 <b>Os mesmos testes com curl</b></summary>

```bash
curl http://localhost:8080/api/clientes

# usa o payload de exemplo direto no POST
curl -s http://localhost:8080/api/documentos/exemplo \
  | curl -s -X POST http://localhost:8080/api/clientes/1/comprovantes \
         -H 'Content-Type: application/json' -d @-

curl http://localhost:8080/api/clientes/1/comprovantes/1
curl http://localhost:8080/api/validacoes
```

</details>

<details>
<summary>▶️🎬 <b>Roteiro para a demo</b></summary>

1. Suba o backend e o frontend **sem** chave OpenAI nem script OpenCV, para mostrar os fallbacks em `etapas`.
2. Use os botões de exemplo do frontend: conta recente → `APROVADO`; emitida há 120 dias ou CEP inválido →
   `REPROVADO` com o motivo; titular diferente → `ANALISE_MANUAL`.
3. Configure OpenAI, Tesseract e OpenCV e envie a foto de uma conta, mostrando a timeline passando pelas 6 etapas.
4. Abra o **Backoffice** (`/validacoes`) para mostrar o histórico de compliance.

</details>

<h2 align="center" id="rotas">🗺️ Rotas da API no Postman e no Insomnia <br>
<img src="https://img.shields.io/badge/-Postman-111827?style=flat&logo=postman&logoColor=FF6C37" height="22"/>
<img src="https://img.shields.io/badge/-Insomnia-111827?style=flat&logo=insomnia&logoColor=4000BF" height="22"/>
<img src="https://img.shields.io/badge/-5_rotas_·_HTTP-111827?style=flat&logo=openapiinitiative&logoColor=6BA539" height="22"/>
</h2>

| <img src="./img/postman_http_routes.png" alt="Rotas da API no Postman" width="380"/> | <img src="./img/insomnia_https_routes.png" alt="Rotas da API no Insomnia" width="380"/> |
| :---: | :---: |
| <img src="https://img.shields.io/badge/-Postman_·_pastas_por_rota-111827?style=flat&logo=postman&logoColor=FF6C37" height="20"/> | <img src="https://img.shields.io/badge/-Insomnia_·_pastas_por_tag-111827?style=flat&logo=insomnia&logoColor=4000BF" height="20"/> |

<h2 align="center" id="arquitetura">🏰 Arquitetura do Projeto <br>
<img src="https://img.shields.io/badge/Architecture-111827?style=flat-square&logo=instructure&logoColor=white" height="22"/>
</h2>

<pre>
Documentos-ocr-ia-java📄🔍/
├── Documentos-Ocr-IA-Java <img src="https://img.shields.io/badge/-Aplicação-111827?style=flat-square&logo=openjdk&logoColor=orange" height="18"/>/
│   ├── backend <img src="https://img.shields.io/badge/-Java_21-111827?style=flat&logo=openjdk&logoColor=orange" height="18"/> <img src="https://img.shields.io/badge/-Spring_Boot_3.5-111827?style=flat&logo=springboot&logoColor=6DB33F" height="18"/> <img src="https://img.shields.io/badge/-Maven-111827?style=flat&logo=apachemaven&logoColor=C71A36" height="18"/>/
│   │   ├── pom.xml <img src="https://img.shields.io/badge/-Maven_POM-111827?style=flat&logo=apachemaven&logoColor=C71A36" height="18"/>
│   │   ├── docker-compose.yml <img src="https://img.shields.io/badge/-PostgreSQL_16_(opcional)-111827?style=flat&logo=docker&logoColor=2496ED" height="18"/>
│   │   ├── scripts <img src="https://img.shields.io/badge/-Python_·_OpenCV-111827?style=flat-square&logo=opencv&logoColor=5C3EE8" height="18"/>/
│   │   │   ├── pre-processar_documento.py <img src="https://img.shields.io/badge/-PDF_→_imagem_·_cinza_·_binarização_·_inclinação-111827?style=flat&logo=opencv&logoColor=5C3EE8" height="18"/> <img src="https://img.shields.io/badge/-Etapa_2-111827?style=flat&logo=python&logoColor=3776AB" height="18"/>
│   │   │   ├── docs_pre-processar_documento.py <img src="https://img.shields.io/badge/-Documentação_do_script-111827?style=flat&logo=python&logoColor=3776AB" height="18"/>
│   │   │   ├── cores.py <img src="https://img.shields.io/badge/-Cores_ANSI-111827?style=flat&logo=python&logoColor=3776AB" height="18"/>
│   │   │   └── requirements.txt <img src="https://img.shields.io/badge/-opencv_·_numpy_·_pymupdf-111827?style=flat&logo=python&logoColor=3776AB" height="18"/>
│   │   └── src <img src="https://img.shields.io/badge/-src-111827?style=flat-square&logo=openjdk&logoColor=orange" height="18"/>/
│   │       ├── main <img src="https://img.shields.io/badge/-main-111827?style=flat-square&logo=openjdk&logoColor=orange" height="18"/>/
│   │       │   ├── resources <img src="https://img.shields.io/badge/-Resources-111827?style=flat-square&logo=springboot&logoColor=6DB33F" height="18"/>/
│   │       │   │   ├── application.properties <img src="https://img.shields.io/badge/-H2_padrão_·_OpenAI_·_Tesseract_·_OpenCV_·_regras-111827?style=flat&logo=springboot&logoColor=6DB33F" height="18"/>
│   │       │   │   ├── application-postgres.properties <img src="https://img.shields.io/badge/-Perfil_postgres-111827?style=flat&logo=postgresql&logoColor=4169E1" height="18"/>
│   │       │   │   └── db/migration <img src="https://img.shields.io/badge/-Flyway-111827?style=flat-square&logo=flyway&logoColor=CC0200" height="18"/>/
│   │       │   │       └── V1__criar_tabelas.sql <img src="https://img.shields.io/badge/-cliente_·_validacao__comprovante_·_7_clientes-111827?style=flat&logo=postgresql&logoColor=4169E1" height="18"/>
│   │       │   └── java/br/com/cesarschool/documentos <img src="https://img.shields.io/badge/-Pacote_raiz-111827?style=flat-square&logo=openjdk&logoColor=orange" height="18"/>/
│   │       │       ├── ValidacaoDocumentosApplication.java <img src="https://img.shields.io/badge/-Entry_Point_·_bean_Clock-111827?style=flat&logo=springboot&logoColor=6DB33F" height="18"/>
│   │       │       ├── config <img src="https://img.shields.io/badge/-Config-111827?style=flat-square&logo=springboot&logoColor=6DB33F" height="18"/>/
│   │       │       │   ├── AppProperties.java <img src="https://img.shields.io/badge/-%40ConfigurationProperties_app.*-111827?style=flat&logo=springboot&logoColor=6DB33F" height="18"/>
│   │       │       │   └── CorsConfig.java <img src="https://img.shields.io/badge/-CORS_%2Fapi%2F**_→_:5173-111827?style=flat&logo=springboot&logoColor=6DB33F" height="18"/>
│   │       │       ├── controller <img src="https://img.shields.io/badge/-REST_Controllers-111827?style=flat-square&logo=springboot&logoColor=6DB33F" height="18"/>/
│   │       │       │   ├── ComprovanteController.java <img src="https://img.shields.io/badge/-GET_%2Fclientes_·_POST,GET_%2Fcomprovantes_·_GET_%2Fvalidacoes-111827?style=flat&logo=springboot&logoColor=6DB33F" height="18"/>
│   │       │       │   └── DocumentoExemploController.java <img src="https://img.shields.io/badge/-GET_%2Fdocumentos%2Fexemplo-111827?style=flat&logo=springboot&logoColor=6DB33F" height="18"/>
│   │       │       ├── dto <img src="https://img.shields.io/badge/-DTO-111827?style=flat-square&logo=openapiinitiative&logoColor=6BA539" height="18"/>/
│   │       │       │   ├── ValidacaoRequest.java <img src="https://img.shields.io/badge/-Request-111827?style=flat&logo=openapiinitiative&logoColor=6BA539" height="18"/>
│   │       │       │   ├── ValidacaoResponse.java <img src="https://img.shields.io/badge/-Response-111827?style=flat&logo=openapiinitiative&logoColor=6BA539" height="18"/>
│   │       │       │   ├── EtapaPipeline.java <img src="https://img.shields.io/badge/-Response-111827?style=flat&logo=openapiinitiative&logoColor=6BA539" height="18"/>
│   │       │       │   └── DadosComprovanteDto.java <img src="https://img.shields.io/badge/-JSON_da_IA-111827?style=flat&logo=openai&logoColor=white" height="18"/>
│   │       │       ├── service <img src="https://img.shields.io/badge/-Pipeline-111827?style=flat-square&logo=springboot&logoColor=6DB33F" height="18"/>/
│   │       │       │   ├── ValidacaoService.java <img src="https://img.shields.io/badge/-%40Transactional_·_orquestra_o_pipeline-111827?style=flat&logo=springboot&logoColor=6DB33F" height="18"/>
│   │       │       │   ├── OpenCvService.java <img src="https://img.shields.io/badge/-Etapa_2-111827?style=flat&logo=opencv&logoColor=5C3EE8" height="18"/>
│   │       │       │   ├── OcrService.java <img src="https://img.shields.io/badge/-Etapa_3_·_TSV_+_confiança-111827?style=flat&logo=google&logoColor=4285F4" height="18"/>
│   │       │       │   ├── InterpretadorIaService.java <img src="https://img.shields.io/badge/-Etapa_4_·_OpenAI_ou_regex-111827?style=flat&logo=openai&logoColor=white" height="18"/>
│   │       │       │   └── ComandoExterno.java <img src="https://img.shields.io/badge/-ProcessBuilder_·_timeout_60s-111827?style=flat&logo=gnubash&logoColor=4EAA25" height="18"/>
│   │       │       ├── regras <img src="https://img.shields.io/badge/-Regras_de_Negócio-111827?style=flat-square&logo=openjdk&logoColor=FFD700" height="18"/>/
│   │       │       │   ├── Regra.java <img src="https://img.shields.io/badge/-Interface-111827?style=flat&logo=openjdk&logoColor=22C55E" height="18"/>
│   │       │       │   ├── ResultadoRegra.java <img src="https://img.shields.io/badge/-Record_·_ATENDIDA_·_FALHOU_·_IGNORADA-111827?style=flat&logo=openjdk&logoColor=A855F7" height="18"/>
│   │       │       │   ├── RegraLegibilidade.java <img src="https://img.shields.io/badge/-R01-111827?style=flat&logo=openjdk&logoColor=FFD700" height="18"/> <img src="https://img.shields.io/badge/-texto_≥_40_·_confiança_≥_60%25-111827?style=flat&logo=openjdk&logoColor=orange" height="18"/>
│   │       │       │   ├── RegraCamposObrigatorios.java <img src="https://img.shields.io/badge/-R02-111827?style=flat&logo=openjdk&logoColor=FFD700" height="18"/>
│   │       │       │   ├── RegraEmissao90Dias.java <img src="https://img.shields.io/badge/-R06-111827?style=flat&logo=openjdk&logoColor=FFD700" height="18"/> <img src="https://img.shields.io/badge/-Clock_injetável-111827?style=flat&logo=openjdk&logoColor=orange" height="18"/>
│   │       │       │   ├── RegraCepValido.java <img src="https://img.shields.io/badge/-R07-111827?style=flat&logo=openjdk&logoColor=FFD700" height="18"/>
│   │       │       │   └── RegraTitularIgualCliente.java <img src="https://img.shields.io/badge/-R08-111827?style=flat&logo=openjdk&logoColor=FFD700" height="18"/> <img src="https://img.shields.io/badge/-→_ANALISE__MANUAL-111827?style=flat&logo=openjdk&logoColor=F59E0B" height="18"/>
│   │       │       ├── dominio <img src="https://img.shields.io/badge/-Domínio_(POO)-111827?style=flat-square&logo=openjdk&logoColor=purple" height="18"/>/
│   │       │       │   ├── Cliente.java <img src="https://img.shields.io/badge/-Entidade_JPA-111827?style=flat&logo=openjdk&logoColor=3B82F6" height="18"/>
│   │       │       │   ├── ResultadoValidacao.java <img src="https://img.shields.io/badge/-Entidade_JPA-111827?style=flat&logo=openjdk&logoColor=3B82F6" height="18"/>
│   │       │       │   ├── ComprovanteResidencia.java <img src="https://img.shields.io/badge/-Record_·_Value_Object-111827?style=flat&logo=openjdk&logoColor=A855F7" height="18"/>
│   │       │       │   └── StatusValidacao.java <img src="https://img.shields.io/badge/-Enum-111827?style=flat&logo=openjdk&logoColor=FFD700" height="18"/> <img src="https://img.shields.io/badge/-APROVADO_·_REPROVADO_·_ANALISE__MANUAL-111827?style=flat&logo=openjdk&logoColor=FFD700" height="18"/>
│   │       │       ├── repository <img src="https://img.shields.io/badge/-Repositório_(Spring_Data)-111827?style=flat-square&logo=spring&logoColor=6DB33F" height="18"/>/
│   │       │       │   ├── ClienteRepository.java <img src="https://img.shields.io/badge/-JpaRepository-111827?style=flat&logo=springboot&logoColor=6DB33F" height="18"/>
│   │       │       │   └── ValidacaoRepository.java <img src="https://img.shields.io/badge/-JpaRepository_·_%40EntityGraph-111827?style=flat&logo=springboot&logoColor=6DB33F" height="18"/>
│   │       │       └── exception <img src="https://img.shields.io/badge/-Erros-111827?style=flat-square&logo=openjdk&logoColor=EF4444" height="18"/>/
│   │       │           ├── ClienteNaoEncontradoException.java <img src="https://img.shields.io/badge/-404-111827?style=flat&logo=openjdk&logoColor=EF4444" height="18"/>
│   │       │           └── GlobalExceptionHandler.java <img src="https://img.shields.io/badge/-%40RestControllerAdvice_·_400%2F404-111827?style=flat&logo=openjdk&logoColor=EF4444" height="18"/>
│   │       └── test/java/br/com/cesarschool/documentos <img src="https://img.shields.io/badge/-JUnit_5_·_20_testes-111827?style=flat-square&logo=junit5&logoColor=25A162" height="18"/>/
│   │           ├── regras <img src="https://img.shields.io/badge/-Regras-111827?style=flat-square&logo=junit5&logoColor=25A162" height="18"/>/
│   │           │   ├── RegraEmissao90DiasTest.java <img src="https://img.shields.io/badge/-%40Test_·_limite_90_dias_·_futuro-111827?style=flat&logo=junit5&logoColor=25A162" height="18"/>
│   │           │   ├── RegraCepValidoTest.java <img src="https://img.shields.io/badge/-%40ParameterizedTest_·_CEPs-111827?style=flat&logo=junit5&logoColor=25A162" height="18"/>
│   │           │   └── RegraTitularIgualClienteTest.java <img src="https://img.shields.io/badge/-%40Test_·_acentos_·_análise_manual-111827?style=flat&logo=junit5&logoColor=25A162" height="18"/>
│   │           └── service <img src="https://img.shields.io/badge/-Service-111827?style=flat-square&logo=junit5&logoColor=25A162" height="18"/>/
│   │               └── ValidacaoServiceTest.java <img src="https://img.shields.io/badge/-%40SpringBootTest_·_pipeline_completo_com_H2-111827?style=flat&logo=junit5&logoColor=25A162" height="18"/>
│   │
│   └── frontend <img src="https://img.shields.io/badge/-React_18-111827?style=flat&logo=react&logoColor=61DAFB" height="18"/> <img src="https://img.shields.io/badge/-TypeScript-111827?style=flat&logo=typescript&logoColor=3178C6" height="18"/> <img src="https://img.shields.io/badge/-Vite-111827?style=flat&logo=vite&logoColor=646CFF" height="18"/> <img src="https://img.shields.io/badge/-Tailwind_CSS-111827?style=flat&logo=tailwindcss&logoColor=38BDF8" height="18"/>/
│       ├── index.html <img src="https://img.shields.io/badge/-HTML5-111827?style=flat&logo=html5&logoColor=E34F26" height="18"/>
│       ├── package.json <img src="https://img.shields.io/badge/-npm-111827?style=flat&logo=npm&logoColor=CB3837" height="18"/>
│       ├── package-lock.json <img src="https://img.shields.io/badge/-npm_lock-111827?style=flat&logo=npm&logoColor=CB3837" height="18"/>
│       ├── vite.config.ts <img src="https://img.shields.io/badge/-Proxy_%2Fapi_→_:8080-111827?style=flat&logo=vite&logoColor=646CFF" height="18"/>
│       ├── tailwind.config.ts <img src="https://img.shields.io/badge/-Cores_da_marca-111827?style=flat&logo=tailwindcss&logoColor=38BDF8" height="18"/>
│       ├── postcss.config.js <img src="https://img.shields.io/badge/-PostCSS-111827?style=flat&logo=postcss&logoColor=DD3A0A" height="18"/>
│       ├── tsconfig.json <img src="https://img.shields.io/badge/-TS_Config-111827?style=flat&logo=typescript&logoColor=3178C6" height="18"/>
│       └── src <img src="https://img.shields.io/badge/-TSX_·_TS-111827?style=flat-square&logo=typescript&logoColor=3178C6" height="18"/>/
│           ├── main.tsx <img src="https://img.shields.io/badge/-Entry_Point-111827?style=flat&logo=react&logoColor=61DAFB" height="18"/>
│           ├── App.tsx <img src="https://img.shields.io/badge/-RouterProvider-111827?style=flat&logo=react&logoColor=61DAFB" height="18"/>
│           ├── index.css <img src="https://img.shields.io/badge/-Tailwind-111827?style=flat&logo=css&logoColor=663399" height="18"/>
│           ├── vite-env.d.ts <img src="https://img.shields.io/badge/-VITE__API__URL_(opcional)-111827?style=flat&logo=vite&logoColor=646CFF" height="18"/>
│           ├── routes <img src="https://img.shields.io/badge/-React_Router-111827?style=flat-square&logo=reactrouter&logoColor=CA4245" height="18"/>/
│           │   └── index.tsx <img src="https://img.shields.io/badge/-%2F_·_%2Fresultado%2F:clienteId%2F:validacaoId_·_%2Fvalidacoes-111827?style=flat&logo=reactrouter&logoColor=CA4245" height="18"/>
│           ├── services <img src="https://img.shields.io/badge/-Cliente_HTTP-111827?style=flat-square&logo=javascript&logoColor=F7DF1E" height="18"/>/
│           │   └── api.ts <img src="https://img.shields.io/badge/-fetch_·_4_endpoints-111827?style=flat&logo=typescript&logoColor=3178C6" height="18"/>
│           ├── types <img src="https://img.shields.io/badge/-Tipos-111827?style=flat-square&logo=typescript&logoColor=3178C6" height="18"/>/
│           │   └── validacao.ts <img src="https://img.shields.io/badge/-Espelha_os_DTOs_do_backend-111827?style=flat&logo=typescript&logoColor=3178C6" height="18"/>
│           ├── utils <img src="https://img.shields.io/badge/-Utilitários-111827?style=flat-square&logo=typescript&logoColor=3178C6" height="18"/>/
│           │   └── formatacao.ts <img src="https://img.shields.io/badge/-CPF_·_datas-111827?style=flat&logo=typescript&logoColor=3178C6" height="18"/>
│           ├── components <img src="https://img.shields.io/badge/-Componentes-111827?style=flat-square&logo=react&logoColor=61DAFB" height="18"/>/
│           │   ├── layout <img src="https://img.shields.io/badge/-Layout-111827?style=flat-square&logo=react&logoColor=61DAFB" height="18"/>/
│           │   │   ├── LayoutPrincipal.tsx <img src="https://img.shields.io/badge/-Estrutura_base_·_Outlet-111827?style=flat&logo=react&logoColor=61DAFB" height="18"/>
│           │   │   ├── Cabecalho.tsx <img src="https://img.shields.io/badge/-Header_·_navegação-111827?style=flat&logo=react&logoColor=61DAFB" height="18"/>
│           │   │   └── TituloPagina.tsx <img src="https://img.shields.io/badge/-Título_·_descrição_·_ações-111827?style=flat&logo=react&logoColor=61DAFB" height="18"/>
│           │   ├── upload <img src="https://img.shields.io/badge/-Upload-111827?style=flat-square&logo=react&logoColor=E0234E" height="18"/>/
│           │   │   ├── FormularioUpload.tsx <img src="https://img.shields.io/badge/-Cliente_·_arquivo-111827?style=flat&logo=react&logoColor=E0234E" height="18"/>
│           │   │   └── PreviewDocumento.tsx <img src="https://img.shields.io/badge/-Pré--visualização_imagem%2FPDF-111827?style=flat&logo=react&logoColor=E0234E" height="18"/>
│           │   ├── resultado <img src="https://img.shields.io/badge/-Resultado-111827?style=flat-square&logo=react&logoColor=22C55E" height="18"/>/
│           │   │   ├── LinhaEtapas.tsx <img src="https://img.shields.io/badge/-Timeline_animada_·_6_etapas-111827?style=flat&logo=react&logoColor=A855F7" height="18"/>
│           │   │   ├── CartaoResultado.tsx <img src="https://img.shields.io/badge/-Status_·_resumo_·_motivos-111827?style=flat&logo=react&logoColor=22C55E" height="18"/>
│           │   │   ├── DadosExtraidos.tsx <img src="https://img.shields.io/badge/-Campos_extraídos_do_comprovante-111827?style=flat&logo=react&logoColor=22C55E" height="18"/>
│           │   │   └── ListaRegras.tsx <img src="https://img.shields.io/badge/-R01_·_R02_·_R06_·_R07_·_R08-111827?style=flat&logo=react&logoColor=FFD700" height="18"/>
│           │   └── ui <img src="https://img.shields.io/badge/-UI_Kit-111827?style=flat-square&logo=react&logoColor=A855F7" height="18"/>/
│           │       ├── SeloStatus.tsx <img src="https://img.shields.io/badge/-Badge_de_status-111827?style=flat&logo=react&logoColor=A855F7" height="18"/>
│           │       ├── Carregando.tsx <img src="https://img.shields.io/badge/-Loading-111827?style=flat&logo=react&logoColor=A855F7" height="18"/>
│           │       ├── MensagemErro.tsx <img src="https://img.shields.io/badge/-Alerta_de_erro-111827?style=flat&logo=react&logoColor=A855F7" height="18"/>
│           │       ├── Painel.tsx <img src="https://img.shields.io/badge/-Card_com_título-111827?style=flat&logo=react&logoColor=A855F7" height="18"/>
│           │       ├── Icones.tsx <img src="https://img.shields.io/badge/-Ícones_SVG-111827?style=flat&logo=react&logoColor=A855F7" height="18"/>
│           │       └── classes.ts <img src="https://img.shields.io/badge/-Classes_Tailwind_compartilhadas-111827?style=flat&logo=tailwindcss&logoColor=38BDF8" height="18"/>
│           └── pages <img src="https://img.shields.io/badge/-Páginas-111827?style=flat-square&logo=react&logoColor=61DAFB" height="18"/>/
│               ├── Upload/PaginaUpload.tsx <img src="https://img.shields.io/badge/-Cliente_envia_comprovante-111827?style=flat&logo=react&logoColor=E0234E" height="18"/> <img src="https://img.shields.io/badge/-Implementada-22C55E?style=flat-square" height="18"/>
│               ├── Resultado/PaginaResultado.tsx <img src="https://img.shields.io/badge/-Aprovado_·_Reprovado_+_motivos-111827?style=flat&logo=react&logoColor=22C55E" height="18"/> <img src="https://img.shields.io/badge/-Implementada-22C55E?style=flat-square" height="18"/>
│               └── Validacoes/PaginaValidacoes.tsx <img src="https://img.shields.io/badge/-Backoffice_·_Compliance-111827?style=flat&logo=react&logoColor=A855F7" height="18"/> <img src="https://img.shields.io/badge/-Implementada-22C55E?style=flat-square" height="18"/>
│
├── docs <img src="https://img.shields.io/badge/-Docs-111827?style=flat&logo=markdown&logoColor=blue" height="18"/>/
│   └── openapi.yaml <img src="https://img.shields.io/badge/-OpenAPI_3.0.3_·_contrato_da_API-111827?style=flat&logo=openapiinitiative&logoColor=6BA539" height="18"/>
│
├── postman <img src="https://img.shields.io/badge/-Postman_·_Insomnia-111827?style=flat&logo=postman&logoColor=FF6C37" height="18"/>/
│   └── Documentos-OCR-IA.postman_collection.json <img src="https://img.shields.io/badge/-7_requests_·_3_cenários_·_variáveis-111827?style=flat&logo=postman&logoColor=FF6C37" height="18"/>
│
├── pdf <img src="https://img.shields.io/badge/-PDF-111827?style=flat-square&logo=adobeacrobatreader&logoColor=EC1C24" height="18"/>/
│   ├── respostas_validacao_documentos_Paguetti.pdf <img src="https://img.shields.io/badge/-Especificação_entregue-111827?style=flat&logo=adobeacrobatreader&logoColor=EC1C24" height="18"/>
│   ├── conta-luz-exemplo.png <img src="https://img.shields.io/badge/-Comprovante_de_teste-111827?style=flat&logo=image&logoColor=white" height="18"/>
│   └── tela-*.png <img src="https://img.shields.io/badge/-Capturas_do_frontend-111827?style=flat&logo=react&logoColor=61DAFB" height="18"/>
│
├── img <img src="https://img.shields.io/badge/-Assets-green?style=flat&logo=image&logoColor=white" height="18"/>/
│
├── .gitignore <img src="https://img.shields.io/badge/-GitIgnore-111827?style=flat&logo=git&logoColor=F05032" height="18"/>
├── LICENSE <img src="https://img.shields.io/badge/License-MIT-FF8C00?style=flat&logo=opensource&logoColor=white" height="18"/>
└── README.md <img src="https://img.shields.io/badge/-Markdown-111827?style=flat&logo=markdown&logoColor=white" height="18"/>
</pre>
