package com.artyom.esalr234;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableRabbit
public class EsaLr234Application {

    public static void main(String[] args) {
        SpringApplication.run(EsaLr234Application.class, args);
    }

}
