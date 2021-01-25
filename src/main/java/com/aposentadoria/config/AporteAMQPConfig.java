package com.aposentadoria.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

import static java.lang.System.getenv;

@Configuration
public class AporteAMQPConfig {

    public static final String EXCHANGE_NAME = "aportes";

//    @Value("${app.amqp.url}")
//    private String url;

    @Bean
    public Exchange declareExchange() {
        return ExchangeBuilder.directExchange(EXCHANGE_NAME)
                .durable(true)
                .build();
    }

//    @Bean
//    public ConnectionFactory connectionFactory() {
//        final URI amqpUrl;
//        try {
//            amqpUrl = new URI(getEnvOrThrow());
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
//
//        final CachingConnectionFactory factory = new CachingConnectionFactory();
//        factory.setUsername(amqpUrl.getUserInfo().split(":")[0]);
//        factory.setPassword(amqpUrl.getUserInfo().split(":")[1]);
//        factory.setHost(amqpUrl.getHost());
//        factory.setPort(amqpUrl.getPort());
//        factory.setVirtualHost(amqpUrl.getPath().substring(1));
//
//        return factory;
//    }

//    @Bean
//    public AmqpAdmin amqpAdmin() {
//        return new RabbitAdmin(connectionFactory());
//    }
//
//    @Bean
//    public RabbitTemplate rabbitTemplate() {
//        return new RabbitTemplate(connectionFactory());
//    }
//
//    private String getEnvOrThrow() {
//        final String env = getenv("CLOUDAMQP_URL");
//        if (env == null) {
//            return url;
//        }
//        return env;
//    }

}
