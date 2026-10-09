package br.com.cesarschool.documentos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cesarschool.documentos.dominio.ResultadoValidacao;

public interface ValidacaoRepository extends JpaRepository<ResultadoValidacao, Long> 
{

    @EntityGraph(attributePaths = "cliente")
    Optional<ResultadoValidacao> findByIdAndClienteId(Long id, Long clienteId);

    @EntityGraph(attributePaths = "cliente")
    List<ResultadoValidacao> findAllByOrderByCriadoEmDesc();
}