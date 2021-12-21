package com.example.config;

import io.micronaut.context.annotation.Factory;

import javax.sql.DataSource;

@Factory
public class MybatisFactory {

    private  final DataSource dataSource;

    public MybatisFactory(DataSource dataSource){
        this.dataSource = dataSource;
    }

    

}
