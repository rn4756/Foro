package com.forohub.foro.topico;

public record DatosListadoTopico( String titulo, String mensaje, String fechaCreacion, Boolean estado,String autor, String curso) {

    public DatosListadoTopico(Topico topico){
        this(topico.getTitulo(),
             topico.getMensaje(),
             topico.getFechaCreacion().toString(),
             topico.getEstado(),
             topico.getAutor(),
             topico.getCurso());
    }

}
