package com.example;

import com.example.utils.PetClinicCliClient;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;

@Singleton
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
