package com.example;


import io.micronaut.runtime.Micronaut;

import javax.inject.Singleton;
import javax.net.ssl.SSLServerSocketFactory;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

@Singleton
public class Application {

    public static void main(String[] args) {


        System.out.println("______________________________ 5");
        Micronaut.run(Application.class, args);
    }


}
