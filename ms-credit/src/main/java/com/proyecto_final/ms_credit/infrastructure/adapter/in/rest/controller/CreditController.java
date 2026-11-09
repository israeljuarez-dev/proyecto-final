package com.proyecto_final.ms_credit.infrastructure.adapter.in.rest.controller;

import com.proyecto_final.ms_credit.application.port.in.use_cases.*;
import com.proyecto_final.ms_credit.infrastructure.adapter.in.rest.dto.request.CreateCreditRequestDto;
import com.proyecto_final.ms_credit.infrastructure.adapter.in.rest.dto.request.UpdateCreditRequestDto;
import com.proyecto_final.ms_credit.infrastructure.adapter.in.rest.dto.response.CreditResponseDto;
import com.proyecto_final.ms_credit.infrastructure.adapter.in.rest.mappper.CreditRestMapper;
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
 * Controlador REST reactivo de créditos.
 */
@RestController
@RequestMapping("/credits")
@RequiredArgsConstructor
public class CreditController {

    private final CreateCreditUseCase createCreditUseCase;

    private final FindAllCreditsUseCase findAllCreditsUseCase;

    private final FindCreditByIdUseCase findCreditByIdUseCase;

    private final UpdateCreditUseCase updateCreditUseCase;

    private final DeleteCreditUseCase deleteCreditUseCase;

    private final CreditRestMapper creditRestMapper;

    /**
     * Crea un crédito nuevo.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Single<CreditResponseDto> create(@Valid @RequestBody CreateCreditRequestDto request) {
        return createCreditUseCase.create(creditRestMapper.toCreateDto(request))
                .map(creditRestMapper::toResponse);
    }

    /**
     * Lista los créditos activos.
     */
    @GetMapping
    public Flowable<CreditResponseDto> findAll() {
        return findAllCreditsUseCase.findAll()
                .map(creditRestMapper::toResponse);
    }

    /**
     * Consulta un crédito activo por su identificador.
     */
    @GetMapping("/{creditId}")
    public Single<CreditResponseDto> findById(@PathVariable("creditId") String creditId) {
        return findCreditByIdUseCase.findById(creditId)
                .map(creditRestMapper::toResponse);
    }

    /**
     * Actualiza los montos de un crédito.
     */
    @PutMapping("/{creditId}")
    public Single<CreditResponseDto> update(
            @PathVariable("creditId") String creditId,
            @Valid @RequestBody UpdateCreditRequestDto request
    ) {
        return updateCreditUseCase.update(creditRestMapper.toUpdateDto(creditId, request))
                .map(creditRestMapper::toResponse);
    }

    /**
     * Elimina lógicamente un crédito.
     */
    @DeleteMapping("/{creditId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Completable delete(@PathVariable("creditId") String creditId) {
        return deleteCreditUseCase.delete(creditId);
    }
}