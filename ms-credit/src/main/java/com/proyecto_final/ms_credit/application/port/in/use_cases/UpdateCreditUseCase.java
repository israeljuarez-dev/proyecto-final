package com.proyecto_final.ms_credit.application.port.in.use_cases;

import com.proyecto_final.ms_credit.application.port.in.dto.UpdateCreditDto;
import com.proyecto_final.ms_credit.domain.model.Credit;
import io.reactivex.rxjava3.core.Single;

/**
 * Caso de uso: actualizar los montos de un crédito.
 */
public interface UpdateCreditUseCase {

    /**
     * Actualiza un crédito activo validando que los montos sean coherentes con su tipo.
     *
     * @param updateCreditDto identificador del crédito y nuevos montos
     * @return el crédito actualizado, o error CreditNotFoundException si no existe
     */
    Single<Credit> update(UpdateCreditDto updateCreditDto);
}
