package com.pet.rest;


import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("api")
public class PetResource {

    private final Logger log = LoggerFactory.getLogger(PetResource.class);


    @Get("/hello")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<String> hi() {
        String message = "Hello in pets";
        return HttpResponse.ok(message);
    }
}
