package com.proyecto_final.ms_customer.infrastructure.adapter.out.persistence.repository;

import com.proyecto_final.ms_customer.application.port.out.CustomerRepositoryPort;
import com.proyecto_final.ms_customer.domain.model.Customer;
import com.proyecto_final.ms_customer.domain.model.DocumentTypeEnum;
import com.proyecto_final.ms_customer.infrastructure.adapter.out.persistence.mapper.CustomerPersistenceMapper;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Implementa el puerto de salida de clientes sobre Spring Data MongoDB.
 */
@Component
@RequiredArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {

    private final CustomerRepository customerRepository;

    private final CustomerPersistenceMapper customerPersistenceMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public Single<Customer> save(Customer customer) {
        return customerRepository.save(customerPersistenceMapper.toEntity(customer))
                .map(customerPersistenceMapper::toDomain);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Maybe<Customer> findActiveById(String customerId) {
        return customerRepository.findByIdAndIsActiveTrue(customerId)
                .map(customerPersistenceMapper::toDomain);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Flowable<Customer> findAllActive() {
        return customerRepository.findByIsActiveTrue()
                .map(customerPersistenceMapper::toDomain);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Single<Boolean> existsActiveByDocument(DocumentTypeEnum documentType, String documentNumber) {
        return customerRepository.existsByDocumentTypeAndDocumentNumberAndIsActiveTrue(
                documentType, documentNumber);
    }
}