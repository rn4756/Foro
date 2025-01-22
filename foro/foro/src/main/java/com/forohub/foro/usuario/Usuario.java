package com.forohub.foro.usuario;



import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
@Getter

    @Entity
    public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true, nullable = false)
    private String correoElectronico;

    private String contrasena;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "usuario_perfiles", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "perfil")
    private Set<String> perfiles = new HashSet<>();

    public Usuario() {
    }

    public Usuario(String nombre, String correoElectronico, String contrasena, Set<String> perfiles) {
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.perfiles = perfiles;
    }
}
