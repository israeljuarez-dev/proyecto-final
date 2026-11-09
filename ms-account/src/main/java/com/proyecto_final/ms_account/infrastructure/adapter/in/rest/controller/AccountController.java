package com.proyecto_final.ms_account.infrastructure.adapter.in.rest.controller;

import com.proyecto_final.ms_account.application.port.in.uses_cases.CreateAccountUseCase;
import com.proyecto_final.ms_account.application.port.in.uses_cases.DeleteAccountUseCase;
import com.proyecto_final.ms_account.application.port.in.uses_cases.FindAccountByIdUseCase;
import com.proyecto_final.ms_account.application.port.in.uses_cases.FindAllAccountsUseCase;
import com.proyecto_final.ms_account.application.port.in.uses_cases.UpdateAccountUseCase;
import com.proyecto_final.ms_account.infrastructure.adapter.in.rest.dto.request.CreateAccountRequestDto;
import com.proyecto_final.ms_account.infrastructure.adapter.in.rest.dto.request.UpdateAccountRequestDto;
import com.proyecto_final.ms_account.infrastructure.adapter.in.rest.dto.response.AccountResponseDto;
import com.proyecto_final.ms_account.infrastructure.adapter.in.rest.mapper.AccountRestMapper;
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
 * Controlador REST reactivo de cuentas.
 */
@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final CreateAccountUseCase createAccountUseCase;

    private final FindAllAccountsUseCase findAllAccountsUseCase;

    private final FindAccountByIdUseCase findAccountByIdUseCase;

    private final UpdateAccountUseCase updateAccountUseCase;

    private final DeleteAccountUseCase deleteAccountUseCase;

    private final AccountRestMapper accountRestMapper;

    /**
     * Abre una cuenta nueva.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Single<AccountResponseDto> create(@Valid @RequestBody CreateAccountRequestDto request) {
        return createAccountUseCase.create(accountRestMapper.toCreateDto(request))
                .map(accountRestMapper::toResponse);
    }

    /**
     * Lista las cuentas activas.
     */
    @GetMapping
    public Flowable<AccountResponseDto> findAll() {
        return findAllAccountsUseCase.findAll()
                .map(accountRestMapper::toResponse);
    }

    /**
     * Consulta una cuenta activa por su identificador.
     */
    @GetMapping("/{accountId}")
    public Single<AccountResponseDto> findById(@PathVariable("accountId") String accountId) {
        return findAccountByIdUseCase.findById(accountId)
                .map(accountRestMapper::toResponse);
    }

    /**
     * Actualiza las condiciones, titulares y firmantes de una cuenta.
     */
    @PutMapping("/{accountId}")
    public Single<AccountResponseDto> update(
            @PathVariable("accountId") String accountId,
            @Valid @RequestBody UpdateAccountRequestDto request
    ) {
        return updateAccountUseCase.update(accountRestMapper.toUpdateDto(accountId, request))
                .map(accountRestMapper::toResponse);
    }

    /**
     * Elimina lógicamente una cuenta.
     */
    @DeleteMapping("/{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Completable delete(@PathVariable("accountId") String accountId) {
        return deleteAccountUseCase.delete(accountId);
    }
}