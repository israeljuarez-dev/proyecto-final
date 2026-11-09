package com.proyecto_final.ms_account.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

/**
 * Expone el reloj del sistema como bean para poder inyectarlo (y fijarlo en los tests).
 */
@Configuration
public class ClockConfig {

    /**
     * Reloj en UTC usado para las fechas de creación y actualización.
     *
     * @return reloj del sistema en UTC
     */
    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }
}