package com.proyecto_final.ms_account.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Propiedades del cliente HTTP hacia ms-customer (prefijo clients.customer).
 *
 * @param baseUrl URL base de ms-customer, con el nombre del servicio en Eureka
 */
@ConfigurationProperties(prefix = "clients.customer")
public record CustomerClientProperties(String baseUrl) {
}
