package br.com.cesarschool.documentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cesarschool.documentos.dominio.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> 
{}