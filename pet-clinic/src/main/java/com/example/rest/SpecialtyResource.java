package com.example.rest;


import com.example.domain.Specialty;
import com.example.services.SpecialtyService;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;


import java.util.List;

@Controller("/api")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class SpecialtyResource {

    private final SpecialtyService specialtyService;
    public SpecialtyResource(SpecialtyService specialtyService){
        this.specialtyService = specialtyService;
    }

    @Get("/specialties")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<List<Specialty>> getSpecialties(HttpRequest request) throws Exception{
        List<Specialty> page = (List<Specialty>) specialtyService.findAll();
        return HttpResponse.ok(page);
    }

    @Get("/helloSpe")
    public String hello(){ return "Hello specialties"; }
}
