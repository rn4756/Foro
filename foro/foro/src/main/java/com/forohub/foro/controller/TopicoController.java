package com.forohub.foro.controller;

import com.forohub.foro.ValidacionException;
import com.forohub.foro.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topicos")
@EqualsAndHashCode(of = "id")

public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
     public ResponseEntity<String> registrarTopico(@RequestBody @Valid DatosTopico datosTopico) {
        boolean tituloExistente = topicoRepository.findByTitulo(datosTopico.titulo()).isPresent();
        boolean mensajeExistente = topicoRepository.findByMensaje(datosTopico.mensaje()).isPresent();

        if (tituloExistente || mensajeExistente) {
            // Si ya existe un tópico con el mismo título o mensaje, devolvemos un error con un mensaje adecuado
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe un tópico con ese título o mensaje");
        }
       Topico topico = topicoRepository.save(new Topico(datosTopico));
        return ResponseEntity.status(HttpStatus.CREATED).body("Tópico registrado exitosamente");
    }

    @GetMapping
    public ResponseEntity<List<DatosListadoTopico>> listarTopicos() {

        List<Topico> topicos = topicoRepository.findAll();


        List<DatosListadoTopico> listadoTopicos = topicos.stream()
                .map(DatosListadoTopico::new)
                .collect(Collectors.toList());


        return ResponseEntity.ok(listadoTopicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> detalletopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosRespuestaTopico(topico.getMensaje(), topico.getTitulo(), topico.getFechaCreacion(),
                topico.getEstado(), topico.getAutor(),topico.getCurso());

        return ResponseEntity.ok(datosTopico);
    }

   /* @GetMapping
    public ResponseEntity<List<DatosListadoTopico>> listadoTopico() {
       List<DatosListadoTopico> listatopico=topicoRepository.findAll().stream()
               .map(DatosListadoTopico::new)
               .sorted(Comparator.comparing(DatosListadoTopico::fechaCreacion).reversed())
               .limit(10)
               .collect(Collectors.toList());
       return ResponseEntity.ok(listatopico);
    }*/

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {

        if(!topicoRepository.existsById(datosActualizarTopico.id())){//! qieredecir en caso de que no exista
            throw new ValidacionException("No existe un topico con el id informado");
        }if (topicoRepository.findByTitulo(datosActualizarTopico.titulo()).isPresent() ||
                topicoRepository.findByMensaje(datosActualizarTopico.mensaje()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe un tópico con ese título o mensaje.");
        }

        Topico topico = topicoRepository.getReferenceById();
        topico.actualizarDatos(datosActualizarTopico);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getAutor(),
                topico.getMensaje(), topico.getEstado(),topico.getFechaCreacion(),topico.getCurso()));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }





}
