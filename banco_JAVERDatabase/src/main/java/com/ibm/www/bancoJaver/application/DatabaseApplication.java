package com.ibm.www.bancoJaver.application;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.ibm.www.bancoJaver.clienteapp.model.entity")
public class DatabaseApplication {

    public static void main(String[] args) {

        SpringApplication.run(DatabaseApplication.class, args);


    }
}
