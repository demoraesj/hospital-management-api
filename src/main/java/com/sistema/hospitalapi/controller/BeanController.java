package com.sistema.hospitalapi.controller;

import com.sistema.hospitalapi.model.Consulta;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bean")
public class BeanController {

    private final Consulta consultaPadrao;

    public BeanController(Consulta consultaPadrao) {
        this.consultaPadrao = consultaPadrao;
    }

    @GetMapping
    public Consulta consultaPadrao() {

        return consultaPadrao;
    }
}
