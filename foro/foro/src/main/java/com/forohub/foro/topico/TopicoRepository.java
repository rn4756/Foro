package com.forohub.foro.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Optional<Topico> findByTitulo(String titulo);  // Busca un tópico por su título
    Optional<Topico> findByMensaje(String mensaje);
    Page<Topico> findByEstadoTrue(Pageable paginacion);
}


