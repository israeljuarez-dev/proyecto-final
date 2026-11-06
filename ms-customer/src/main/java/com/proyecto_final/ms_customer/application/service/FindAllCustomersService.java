package com.proyecto_final.ms_customer.application.service;

import com.proyecto_final.ms_customer.application.port.in.use_cases.FindAllCustomersUseCase;
import com.proyecto_final.ms_customer.application.port.out.CustomerRepositoryPort;
import com.proyecto_final.ms_customer.domain.model.Customer;
import io.reactivex.rxjava3.core.Flowable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementa el caso de uso FindAllCustomersUseCase del cliente: orquesta las reglas del negocio.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class FindAllCustomersService implements FindAllCustomersUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    /**
     * {@inheritDoc}
     */
    @Override
    public Flowable<Customer> findAll() {
        return customerRepositoryPort.findAllActive();
    }
}
