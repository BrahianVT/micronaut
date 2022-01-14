package com.example.rest;


import com.example.services.VetService;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.annotation.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/api")
public class VetResource {

    private final Logger log = LoggerFactory.getLogger(VetResource.class);

    private static final String ENTITY_NAME = "vet";

    @Value("${micronaut.application.name}")
    private String applicationName;

    private final VetService vetService;

    public VetResource(VetService vetService) {
        this.vetService = vetService;
    }



}
