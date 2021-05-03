package com.coronaclientservice.controller;

import com.coronaclientservice.service.CoronaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/corona", produces = MediaType.APPLICATION_JSON_VALUE)
public class CoronaController {

    @Autowired
    private CoronaService coronaService;

    @GetMapping
    public String getMessage() throws IOException {
        return coronaService.getCoronaCases();
    }
}
