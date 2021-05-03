package com.coronaclientservice.controller;

import com.coronaclientservice.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/country", produces = MediaType.APPLICATION_JSON_VALUE)
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public String getMessage() throws IOException {
        return countryService.getCountries();
    }
}
