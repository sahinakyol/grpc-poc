package com.coronaclientservice.controller;

import com.coronaclientservice.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/cases", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
public class CaseController {

    @Autowired
    private CaseService caseService;

    @GetMapping
    public Flux<String> getMessage() {
        return caseService.getCases();
    }
}