package com.proyecto_final.ms_account.application.validator;

import com.proyecto_final.ms_account.domain.exception.AccountTypeNotAllowedException;
import com.proyecto_final.ms_account.domain.exception.InvalidAccountConditionsException;
import com.proyecto_final.ms_account.domain.exception.InvalidAccountParticipantsException;
import com.proyecto_final.ms_account.domain.model.AccountPerson;
import com.proyecto_final.ms_account.domain.model.AccountTypeEnum;
import com.proyecto_final.ms_account.domain.model.CustomerTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Aplica las reglas de negocio de las cuentas que comparten la creación y la actualización.
 */
@Slf4j
@Component
public class AccountRulesValidator {

    /**
     * Verifica que las condiciones recibidas correspondan al tipo de cuenta.
     */
    public void validateConditions(
            AccountTypeEnum accountType,
            Integer monthlyMovementLimit,
            BigDecimal maintenanceFee,
            Integer movementDayOfMonth) {
        if (!accountType.acceptsConditions(monthlyMovementLimit, maintenanceFee, movementDayOfMonth)) {
            log.warn("Account rejected, conditions do not match account type {}", accountType);
            throw new InvalidAccountConditionsException(accountType);
        }
    }

    /**
     * Verifica que el tipo de cuenta se ofrezca al tipo de cliente.
     */
    public void validateAccountTypeAllowed(AccountTypeEnum accountType, CustomerTypeEnum customerType) {
        if (!accountType.isAllowedFor(customerType)) {
            log.warn("Account rejected, account type {} not allowed for customer type {}",
                    accountType, customerType);
            throw new AccountTypeNotAllowedException(customerType, accountType);
        }
    }

    /**
     * Verifica que los titulares y firmantes sean válidos para el tipo de cliente.
     */
    public void validateParticipants(
            CustomerTypeEnum customerType, List<AccountPerson> holders, List<AccountPerson> signers) {
        if (!customerType.acceptsParticipants(holders.size(), signers.size())) {
            log.warn("Account rejected, invalid holders ({}) and signers ({}) for customer type {}",
                    holders.size(), signers.size(), customerType);
            throw new InvalidAccountParticipantsException(customerType);
        }
    }
}
