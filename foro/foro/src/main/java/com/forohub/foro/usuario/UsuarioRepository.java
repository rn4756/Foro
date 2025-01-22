package com.forohub.foro.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

    public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
        Optional<Usuario> findByCorreoElectronico(String correoElectronico);
    }
}
