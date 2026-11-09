package com.proyecto_final.ms_account.application.service;

import com.proyecto_final.ms_account.application.port.in.dto.UpdateAccountDto;
import com.proyecto_final.ms_account.application.port.in.uses_cases.FindAccountByIdUseCase;
import com.proyecto_final.ms_account.application.port.in.uses_cases.UpdateAccountUseCase;
import com.proyecto_final.ms_account.application.port.out.AccountRepositoryPort;
import com.proyecto_final.ms_account.application.validator.AccountRulesValidator;
import com.proyecto_final.ms_account.domain.model.Account;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;

/**
 * Implementa el caso de uso UpdateAccountUseCase de la cuenta: orquesta las reglas del negocio.
 */
@Service
@RequiredArgsConstructor
public class UpdateAccountService implements UpdateAccountUseCase {

    private final FindAccountByIdUseCase findAccountByIdUseCase;

    private final AccountRepositoryPort accountRepositoryPort;

    private final AccountRulesValidator accountRulesValidator;

    private final Clock clock;

    /**
     * {@inheritDoc}
     */
    @Override
    public Single<Account> update(UpdateAccountDto updateAccountDto) {
        return findAccountByIdUseCase.findById(updateAccountDto.accountId())
                .map(account -> applyUpdate(account, updateAccountDto))
                .flatMap(accountRepositoryPort::save);
    }

    /**
     * Valida los datos nuevos contra el tipo de cuenta y de cliente, y devuelve la cuenta actualizada.
     */
    private Account applyUpdate(Account account, UpdateAccountDto updateAccountDto) {
        accountRulesValidator.validateConditions(
                account.getType(),
                updateAccountDto.monthlyMovementLimit(),
                updateAccountDto.maintenanceFee(),
                updateAccountDto.movementDayOfMonth());
        accountRulesValidator.validateParticipants(
                account.getCustomerType(), updateAccountDto.holders(), updateAccountDto.signers());

        return account.update(
                updateAccountDto.monthlyMovementLimit(),
                updateAccountDto.maintenanceFee(),
                updateAccountDto.movementDayOfMonth(),
                updateAccountDto.holders(),
                updateAccountDto.signers(),
                clock.instant());
    }
}
