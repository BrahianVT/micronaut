package com.gateway.rest.petowner;

import com.gateway.service.dto.OwnerDTO;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.tracing.annotation.NewSpan;
import io.micronaut.data.model.Pageable;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Controller("/api")
public class OwnerResourceClientController {

    @Inject
    OwnerResourceClient ownerResourceClient;

    @NewSpan
    @Post("/owners")
    public HttpResponse<OwnerDTO> createOwner(OwnerDTO ownerDTO){
        return ownerResourceClient.createOwner(ownerDTO);
    }

    @NewSpan
    @Put("/owners")
    HttpResponse<OwnerDTO> updateOwner(@Body OwnerDTO ownerDTO){
        return ownerResourceClient.updateOwner(ownerDTO);
    }

    @NewSpan
    @Get("/owners")
    public HttpResponse<List<OwnerDTO>> getAllOwners(HttpRequest request, Pageable pageable) {
        return ownerResourceClient.getAllOwners(request, pageable);
    }

    @NewSpan
    @Get("/owners/{id}")
    public Optional<OwnerDTO> getOwner(@PathVariable Long id) {
        return ownerResourceClient.getOwner(id);
    }

    @NewSpan
    @Delete("/owners/{id}")
    HttpResponse deleteOwner(@PathVariable Long id) {
        return ownerResourceClient.deleteOwner(id);
    }

}
