package com.proyecto_final.ms_account.infrastructure.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configura el WebClient de ms-account.
 */
@Configuration
@EnableConfigurationProperties(CustomerClientProperties.class)
public class WebClientConfig {

    /**
     * Builder con balanceo de carga: reemplaza el nombre del servicio de la URL
     * (ms-customer) por la dirección real que Eureka tiene registrada.
     *
     * @return builder de WebClient con balanceo
     */
    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

    /**
     * WebClient preconfigurado con la URL base de ms-customer.
     *
     * @param loadBalancedWebClientBuilder builder con balanceo de carga
     * @param properties                   propiedades del cliente de ms-customer
     * @return WebClient listo para consultar clientes
     */
    @Bean
    public WebClient customerWebClient(
            WebClient.Builder loadBalancedWebClientBuilder,
            CustomerClientProperties properties
    ) {
        // clone: evita modificar el builder compartido
        return loadBalancedWebClientBuilder.clone()
                .baseUrl(properties.baseUrl())
                .build();
    }
}
