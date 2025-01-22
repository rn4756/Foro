package com.forohub.foro.topico;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(@NotNull Long id, String titulo, String mensaje, String fechaCreacion, String autor, String curso) {
}


