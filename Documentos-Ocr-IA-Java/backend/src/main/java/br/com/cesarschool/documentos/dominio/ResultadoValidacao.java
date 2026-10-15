package br.com.cesarschool.documentos.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/** Uma execução do pipeline para um comprovante: dados extraídos + resultado das regras. */
@Entity
@Table(name = "validacao_comprovante")
public class ResultadoValidacao 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private String nomeArquivo;

    @Enumerated(EnumType.STRING)
    private StatusValidacao status;

    @Column(columnDefinition = "TEXT")
    private String textoOcr;

    private Double confiancaOcr;
    private String nomeTitular;
    private String endereco;
    private String bairro;
    private String cidadeUf;
    private String cep;
    private String empresaEmissora;
    private LocalDate dataEmissao;

    @Column(columnDefinition = "TEXT")
    private String regrasAtendidas;

    @Column(columnDefinition = "TEXT")
    private String motivos;

    @Column(columnDefinition = "TEXT")
    private String etapas;

    private LocalDateTime criadoEm;

    protected ResultadoValidacao() 
    {}

    public ResultadoValidacao(Cliente cliente, 
    String nomeArquivo, ComprovanteResidencia comprovante,
    StatusValidacao status, String regrasAtendidas, String motivos, 
    String etapas, LocalDateTime criadoEm) 
    {
        this.cliente = cliente;
        this.nomeArquivo = nomeArquivo;
        this.status = status;
        this.textoOcr = comprovante.textoOcr();
        this.confiancaOcr = comprovante.confiancaOcr();
        this.nomeTitular = comprovante.nomeTitular();
        this.endereco = comprovante.endereco();
        this.bairro = comprovante.bairro();
        this.cidadeUf = comprovante.cidadeUf();
        this.cep = comprovante.cep();
        this.empresaEmissora = comprovante.empresaEmissora();
        this.dataEmissao = comprovante.dataEmissao();
        this.regrasAtendidas = regrasAtendidas;
        this.motivos = motivos;
        this.etapas = etapas;
        this.criadoEm = criadoEm;
    }

    public Long getId() 
    {
        return id;
    }

    public Cliente getCliente() 
    {
        return cliente;
    }

    public String getNomeArquivo() 
    {
        return nomeArquivo;
    }

    public StatusValidacao getStatus() 
    {
        return status;
    }

    public String getTextoOcr() 
    {
        return textoOcr;
    }

    public Double getConfiancaOcr() 
    {
        return confiancaOcr;
    }

    public String getNomeTitular() 
    {
        return nomeTitular;
    }

    public String getEndereco() 
    {
        return endereco;
    }

    public String getBairro() 
    {
        return bairro;
    }

    public String getCidadeUf() 
    {
        return cidadeUf;
    }

    public String getCep() 
    {
        return cep;
    }

    public String getEmpresaEmissora() 
    {
        return empresaEmissora;
    }

    public LocalDate getDataEmissao() 
    {
        return dataEmissao;
    }

    public String getRegrasAtendidas() 
    {
        return regrasAtendidas;
    }

    public String getMotivos() 
    {
        return motivos;
    }

    public String getEtapas() 
    {
        return etapas;
    }

    public LocalDateTime getCriadoEm() 
    {
        return criadoEm;
    }
}