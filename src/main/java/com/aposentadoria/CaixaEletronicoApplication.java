package com.aposentadoria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CaixaEletronicoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaixaEletronicoApplication.class, args);
    }

}
