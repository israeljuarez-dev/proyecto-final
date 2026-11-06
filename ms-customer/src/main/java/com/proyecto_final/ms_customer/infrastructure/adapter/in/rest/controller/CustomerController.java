package com.proyecto_final.ms_customer.infrastructure.adapter.in.rest.controller;

import com.proyecto_final.ms_customer.application.port.in.use_cases.*;
import com.proyecto_final.ms_customer.infrastructure.adapter.in.rest.dto.request.CreateCustomerRequestDto;
import com.proyecto_final.ms_customer.infrastructure.adapter.in.rest.dto.request.UpdateCustomerRequestDto;
import com.proyecto_final.ms_customer.infrastructure.adapter.in.rest.dto.response.CustomerResponseDto;
import com.proyecto_final.ms_customer.infrastructure.adapter.in.rest.mapper.CustomerRestMapper;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST reactivo de clientes.
 */
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;

    private final FindAllCustomersUseCase findAllCustomersUseCase;

    private final FindCustomerByIdUseCase findCustomerByIdUseCase;

    private final UpdateCustomerUseCase updateCustomerUseCase;

    private final DeleteCustomerUseCase deleteCustomerUseCase;

    private final CustomerRestMapper customerRestMapper;

    /**
     * Registra un cliente nuevo.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Single<CustomerResponseDto> create(@Valid @RequestBody CreateCustomerRequestDto request) {
        return createCustomerUseCase.create(customerRestMapper.toCreateDto(request))
                .map(customerRestMapper::toResponse);
    }

    /**
     * Lista los clientes activos.
     */
    @GetMapping
    public Flowable<CustomerResponseDto> findAll() {
        return findAllCustomersUseCase.findAll()
                .map(customerRestMapper::toResponse);
    }

    /**
     * Consulta un cliente activo por su identificador.
     */
    @GetMapping("/{customerId}")
    public Single<CustomerResponseDto> findById(@PathVariable("customerId") String customerId) {
        return findCustomerByIdUseCase.findById(customerId)
                .map(customerRestMapper::toResponse);
    }

    /**
     * Actualiza los datos de contacto de un cliente.
     */
    @PutMapping("/{customerId}")
    public Single<CustomerResponseDto> update(
            @PathVariable("customerId") String customerId,
            @Valid @RequestBody UpdateCustomerRequestDto request
    ) {
        return updateCustomerUseCase.update(customerRestMapper.toUpdateDto(customerId, request))
                .map(customerRestMapper::toResponse);
    }

    /**
     * Elimina lógicamente un cliente.
     */
    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Completable delete(@PathVariable("customerId") String customerId) {
        return deleteCustomerUseCase.delete(customerId);
    }
}
