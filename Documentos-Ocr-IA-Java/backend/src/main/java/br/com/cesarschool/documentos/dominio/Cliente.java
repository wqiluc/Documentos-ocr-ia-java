package br.com.cesarschool.documentos.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cpf;

    protected Cliente() 
    {}

    public Cliente(Long id, String nome, String cpf) 
    {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    public Long getId() 
    {
        return id;
    }

    public String getNome() 
    {
        return nome;
    }

    public String getCpf() 
    {
        return cpf;
    }
}