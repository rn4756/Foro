package com.forohub.foro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//hay que ponerlo para decirle que es un controller
@RequestMapping("/hello")//Si entro a localhost/hello se realiza este metodo
public class HelloController {

    @GetMapping//Funciona porque le estoy diciendo que mapee en la ruta hello
    public String helloWorld() {
        return "Hello world from Europe!";
    }

}
