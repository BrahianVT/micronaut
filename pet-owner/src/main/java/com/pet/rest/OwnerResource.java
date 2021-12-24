package com.pet.rest;


import com.pet.rest.erros.BadRequestAlertException;
import com.pet.service.OwnerService;
import com.pet.service.dto.OwnerDTO;
import com.pet.utils.HeaderUtil;
import com.pet.utils.PaginationUtil;
import io.micronaut.context.annotation.Value;
import io.micronaut.core.version.annotation.Version;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.uri.UriBuilder;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Controller("/api")
public class OwnerResource {
    private final Logger log = LoggerFactory.getLogger(OwnerResource.class);
    private final String ENTITY_NAME = "owner";

    @Value("${micronaut.application.name}")
    private String applicationName;

    private final OwnerService ownerService;

    public OwnerResource(String applicationName, OwnerService ownerService) {
        this.applicationName = applicationName;
        this.ownerService = ownerService;
    }

    @Post("/owners")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<OwnerDTO> createOwner(@Body OwnerDTO ownerDTO) throws URISyntaxException {
        log.debug("Rest createOwner " + ownerDTO);
        if(ownerDTO.getId() != null)throw new BadRequestAlertException("Id can be null", ENTITY_NAME, "idexists");
        OwnerDTO result = ownerService.save(ownerDTO);
        URI location = new URI("/api/owners/" + result.getId());
        return HttpResponse.created(result).headers(headers -> {
           headers.location(location);
            HeaderUtil.createEntityCreationAlert(headers, applicationName, true, ENTITY_NAME, result.getId().toString());
        });
    }

    @Put("/owners")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<OwnerDTO> updateOwner(@Body OwnerDTO ownerDTO) throws URISyntaxException {
        log.debug("Rest request to to update Owner :" + ownerDTO);
        if(ownerDTO.getId() == null) throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");

        OwnerDTO result = ownerService.save(ownerDTO);
        return HttpResponse.ok(result).headers(headers ->
                HeaderUtil.createEntityUpdateAlert(headers, applicationName, true, ENTITY_NAME, ownerDTO.getId().toString()));
    }

    @Get("/owners")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<List<OwnerDTO>> getAllOwners(HttpRequest request, Pageable pageable){
        log.debug(" Rest request to get a page ot owners ");
        Page<OwnerDTO> page = ownerService.findAll(pageable);
        return HttpResponse.ok(page.getContent()).headers(headers ->
                PaginationUtil.generatePaginationHttpHeaders(headers, UriBuilder.of(request.getPath()),page));
    }

    @Get("/owners/{id}")
    @Version("1")
    @ExecuteOn(TaskExecutors.IO)
    public Optional<OwnerDTO> getOwner(@PathVariable Long id){
        log.debug("Rest request get Owner " + id);
        return ownerService.findOne(id);
    }

    @Version("2")
    @Get("/owners/{id}")
    @ExecuteOn(TaskExecutors.IO)
    public Optional<OwnerDTO> getOwnerV2(@PathVariable Long id){
        log.debug("Rest request get Owner v2  " + id);
        return ownerService.findOne(id);
    }

    @Delete("/owners/{id}")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse deleteOwners(@PathVariable Long id){
        log.debug("Request to delete Owner :" + id);
        ownerService.delete(id);
        return HttpResponse.noContent().headers( headers ->
            HeaderUtil.createEntityDeletionAlert(headers, applicationName, true, ENTITY_NAME, id.toString()));
    }

}
