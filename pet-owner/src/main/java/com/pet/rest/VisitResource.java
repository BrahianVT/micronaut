package com.pet.rest;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

@Controller("/api")
public class VisitResource {

    @Get("/visits")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<String> hello(){
        String res = "Hello in visits";
        return HttpResponse.ok(res);
    }
}
