package com.forohub.foro.topico;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.format.DateTimeFormatter;

import java.time.LocalDateTime;

@Table(name = "topico")//para guardarlo en la tabla medico
@Entity(name = "Topico")//
@Getter //es de lombook y genera todos los getter necesarios
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")//Esto significa que dos instancias de la clase serán consideradas iguales si sus valores de id son iguales, y el cálculo de su código hash también dependerá solo del id. Esto es útil para las comparaciones y el uso en colecciones como HashMap o HashSet


public class Topico {
    @Id//para definirlo como clave
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String mensaje;
    @NotBlank
    private String autor;
    @NotBlank
    private String curso;

    private LocalDateTime fechaCreacion;

    private Boolean estado;




    public Topico(DatosTopico datosTopico){

         this.titulo=datosTopico.titulo();
         this.autor=datosTopico.autor();
         this.curso=datosTopico.curso();
         this.mensaje=datosTopico.mensaje();
        this.fechaCreacion = LocalDateTime.now(); // Fecha actual
        this.estado = true;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico) {

        if (datosActualizarTopico.titulo() != null) {
            this.titulo = datosActualizarTopico.titulo();
        }

        if (datosActualizarTopico.mensaje() != null) {
            this.mensaje = datosActualizarTopico.mensaje();
        }

        if (datosActualizarTopico.autor() != null) {
            this.autor = datosActualizarTopico.autor();
        }
        if (datosActualizarTopico.curso() != null) {
            this.curso = datosActualizarTopico.curso();
        }
    }


    public void desactivartopico() {
        this.estado=false;
    }
}