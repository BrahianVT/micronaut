package com.pet.rest;

import com.pet.domain.Owner;
import com.pet.repository.OwnerRepository;
import com.pet.service.dto.OwnerDTO;
import com.pet.service.mapper.OwnerMapper;
import io.micronaut.context.annotation.Property;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.inject.Inject;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest(transactional = false)
@Property(name ="micronaut.security.enabled", value = "false")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OwnerResourceIntegrationTest {
    private static final String DEFAULT_FIRST_NAME = "FOO";
    private static final String UPDATED_FIRST_NAME = "BAR";

    private static final String DEFAULT_LAST_NAME = "FOO";
    private static final String UPDATED_LAST_NAME = "BAR";

    private static final String DEFAULT_ADDRESS = "FOO";
    private static final String UPDATED_ADDRESS = "BAR";

    private static final String DEFAULT_CITY = "FOO";
    private static final String UPDATED_CITY = "BAR";

    private static final String DEFAULT_TELEPHONE = "FOO";
    private static final String UPDATED_TELEPHONE = "BAR";

    @Inject
    private OwnerMapper ownerMapper;

    @Inject
    private OwnerRepository ownerRepository;

    @Inject @Client("/")
    RxHttpClient client;

    private Owner owner;

    @BeforeEach
    public void initTest(){
        owner = createEntity();
    }

    public Owner createEntity(){
        Owner owner = new Owner()
                .firstName(DEFAULT_FIRST_NAME)
                .lastName(DEFAULT_LAST_NAME)
                .address(DEFAULT_ADDRESS)
                .city(DEFAULT_CITY)
                .telephone(DEFAULT_TELEPHONE);
        return owner;
    }

    @Test
    public void createOwner() throws Exception{
        int databaseSizeBeforeCreate = ownerRepository.findAll().size();
        OwnerDTO ownerDTO = ownerMapper.toDto(owner);

        HttpResponse<OwnerDTO> response = client.exchange(HttpRequest.POST("/api/owners",ownerDTO), OwnerDTO.class).blockingFirst();
        assertThat(response.status().getCode()).isEqualTo(HttpStatus.CREATED.getCode());


        // Validate the Owner
        List<Owner> ownerList = ownerRepository.findAll();

        for (Owner owner1 : ownerList) {
            System.out.println(owner1.getId());
            System.out.println(owner1.getFirstName() + "-- ");
        }
        Owner testOwner = ownerList.get(ownerList.size() -1);

        assertThat(ownerList).hasSize(databaseSizeBeforeCreate +1);
        assertThat(testOwner.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testOwner.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testOwner.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testOwner.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testOwner.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);

        // Cleanup
        owner.setId(testOwner.getId());
        ownerRepository.deleteById(owner.getId());
    }

    @Test
    public void createOwnerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ownerRepository.findAll().size();

        owner.setId(444L);
        OwnerDTO ownerDTO = ownerMapper.toDto(owner);

        @SuppressWarnings("unchecked")
        HttpResponse<OwnerDTO> response = client.exchange(HttpRequest.POST("/api/owners", ownerDTO), OwnerDTO.class)
        .onErrorReturn(t -> (HttpResponse<OwnerDTO>) ((HttpClientResponseException) t).getResponse()).blockingFirst();

        assertThat(response.status().getCode()).isEqualTo(HttpStatus.CREATED.getCode());

        List<Owner> ownerList = ownerRepository.findAll();
        assertThat(ownerList).hasSize(databaseSizeBeforeCreate +1);
    }

    @Test
    public void getAllOwners() throws Exception{
        Owner savedOwner = ownerRepository.saveAndFlush(owner);

        List<OwnerDTO> owners = client.retrieve(HttpRequest.GET("/api/owners?eagerload=true"), Argument.listOf(OwnerDTO.class)).blockingFirst();
        OwnerDTO testOwner = owners.get(owners.size() - 1);
    }

    @Test
    public void getOwner() throws Exception {
        Owner savedOwner = ownerRepository.saveAndFlush(owner);
        owner.setId(savedOwner.getId());

        OwnerDTO testOwner = client.retrieve(HttpRequest.GET("/api/owners/" + owner.getId()), OwnerDTO.class).blockingFirst();

        assertThat(testOwner.getFirstNam()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testOwner.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testOwner.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testOwner.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testOwner.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);

        ownerRepository.deleteById(owner.getId());
    }

    @Test
    public void getNonExistingOwner() throws Exception {
        @SuppressWarnings("unchecked")
        HttpResponse<OwnerDTO> response = client.exchange(HttpRequest.GET("/api/owners/"+ Long.MAX_VALUE), OwnerDTO.class)
                .onErrorReturn(t -> (HttpResponse<OwnerDTO>) ((HttpClientResponseException) t).getResponse()).blockingFirst();

        assertThat(response.status().getCode()).isEqualTo(HttpStatus.NOT_FOUND.getCode());
    }

    @Test
    public void updateOwner() throws Exception {
        Owner savedOwner = ownerRepository.saveAndFlush(owner);
        owner.setId(savedOwner.getId());

        int databaseSizeBeforeUpdate = ownerRepository.findAll().size();
        Owner updatedOwner = ownerRepository.findById(owner.getId()).get();

        updatedOwner
                .firstName(UPDATED_FIRST_NAME)
                .lastName(UPDATED_LAST_NAME)
                .address(UPDATED_ADDRESS)
                .city(UPDATED_CITY)
                .telephone(UPDATED_TELEPHONE);
        OwnerDTO updatedOwnerDTO = ownerMapper.toDto(updatedOwner);

        @SuppressWarnings("unchecked")
        HttpResponse<OwnerDTO> response = client.exchange(HttpRequest.PUT("/api/owners", updatedOwnerDTO), OwnerDTO.class)
                .onErrorReturn(t -> (HttpResponse<OwnerDTO>) ((HttpClientResponseException) t).getResponse()).blockingFirst();

        assertThat(response.status().getCode()).isEqualTo(HttpStatus.OK.getCode());

        // Validate the Owner in the database
        List<Owner> ownerList = ownerRepository.findAll();
        assertThat(ownerList).hasSize(databaseSizeBeforeUpdate);

        Owner testOwner = ownerList.get(ownerList.size() - 1);
        assertThat(testOwner.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testOwner.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testOwner.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testOwner.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testOwner.getTelephone()).isEqualTo(UPDATED_TELEPHONE);

        // Cleanup
        ownerRepository.deleteById(owner.getId());
    }

    @Test
    public  void updateNonExistingOwner() throws Exception {
        int databaseSizeBeforeUpdate = ownerRepository.findAll().size();
        // create the Owner
        owner.setId(null);
        OwnerDTO ownerDTO = ownerMapper.toDto(owner);

        @SuppressWarnings("unchecked")
        HttpResponse<OwnerDTO> response = client.exchange(HttpRequest.PUT("/api/owners", ownerDTO), OwnerDTO.class)
                .onErrorReturn(t -> (HttpResponse<OwnerDTO>) ((HttpClientResponseException) t).getResponse()).blockingFirst();

        assertThat(response.status().getCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.getCode());

        // Validate the Owner in the database
        List<Owner> ownerList = ownerRepository.findAll();
        assertThat(ownerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteOwner() throws Exception {
        // Initialize the database with one entity
        Owner savedOwner = ownerRepository.saveAndFlush(owner);
        owner.setId(savedOwner.getId());

        int databaseSizeBeforeDelete = ownerRepository.findAll().size();

        // Delete the owner
        @SuppressWarnings("unchecked")
        HttpResponse<OwnerDTO> response = client.exchange(HttpRequest.DELETE("/api/owners/"+ owner.getId()), OwnerDTO.class)
                .onErrorReturn(t -> (HttpResponse<OwnerDTO>) ((HttpClientResponseException) t).getResponse()).blockingFirst();

        assertThat(response.status().getCode()).isEqualTo(HttpStatus.NO_CONTENT.getCode());

        // Validate the database is now empty
        List<Owner> ownerList = ownerRepository.findAll();
        assertThat(ownerList).hasSize(databaseSizeBeforeDelete - 1);
    }



}
