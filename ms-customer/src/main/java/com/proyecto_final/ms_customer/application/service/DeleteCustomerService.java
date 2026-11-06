package com.proyecto_final.ms_customer.application.service;

import com.proyecto_final.ms_customer.application.port.in.use_cases.DeleteCustomerUseCase;
import com.proyecto_final.ms_customer.application.port.in.use_cases.FindCustomerByIdUseCase;
import com.proyecto_final.ms_customer.application.port.out.CustomerRepositoryPort;
import io.reactivex.rxjava3.core.Completable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Clock;

/**
 * Implementa el caso de uso DeleteCustomerUseCase del cliente: orquesta las reglas del negocio.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DeleteCustomerService implements DeleteCustomerUseCase {
    private final FindCustomerByIdUseCase findCustomerByIdUseCase;

    private final CustomerRepositoryPort customerRepositoryPort;

    private final Clock clock;

    /**
     * {@inheritDoc}
     */
    @Override
    public Completable delete(String customerId) {
        return findCustomerByIdUseCase.findById(customerId)
                .map(customer -> customer.deactivate(clock.instant()))
                .flatMap(customerRepositoryPort::save)
                .ignoreElement();
    }
}
