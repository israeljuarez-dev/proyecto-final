package com.proyecto_final.ms_credit.infrastructure.adapter.out.client.customer;

import com.proyecto_final.ms_credit.application.port.out.client.customer.CustomerClientPort;
import com.proyecto_final.ms_credit.application.port.out.dto.CustomerInfoDto;
import com.proyecto_final.ms_credit.domain.exception.CustomerNotFoundException;
import com.proyecto_final.ms_credit.domain.exception.CustomerServiceUnavailableException;
import com.proyecto_final.ms_credit.infrastructure.adapter.out.client.customer.dto.CustomerClientResponseDto;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;

/**
 * Implementa el puerto de salida de clientes consultando la API REST de ms-customer.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerRestClientAdapter implements CustomerClientPort {

    private final WebClient customerWebClient;

    /**
     * {@inheritDoc}
     */
    @Override
    public Single<CustomerInfoDto> findCustomerById(String customerId) {
        Mono<CustomerInfoDto> customer = customerWebClient.get()
                .uri("/customers/{customerId}", customerId)
                .retrieve()
                .onStatus(status -> status.isSameCodeAs(HttpStatus.NOT_FOUND), response -> {
                    log.warn("Customer not found in ms-customer, id {}", customerId);
                    return Mono.error(new CustomerNotFoundException(customerId));
                })
                .onStatus(HttpStatusCode::is5xxServerError, response -> {
                    log.error("ms-customer answered with server error {}", response.statusCode().value());
                    return Mono.error(new CustomerServiceUnavailableException(
                            "received status " + response.statusCode().value()));
                })
                .bodyToMono(CustomerClientResponseDto.class)
                .map(response -> new CustomerInfoDto(response.id(), response.type()))
                .onErrorMap(WebClientRequestException.class, exception -> {
                    log.error("ms-customer is not reachable", exception);
                    return new CustomerServiceUnavailableException(exception.getMessage());
                });

        // fromPublisher: convierte el Mono de Reactor (WebClient) en un Single de RxJava
        return Single.fromPublisher(customer);
    }
}
