package com.proyecto_final.ms_credit.application.port.in.use_cases;

import com.proyecto_final.ms_credit.domain.model.Credit;
import io.reactivex.rxjava3.core.Single;

/**
 * Caso de uso: consultar un crédito activo por su identificador.
 */
public interface FindCreditByIdUseCase {

    /**
     * Busca un crédito activo por su identificador.
     *
     * @param creditId identificador del crédito
     * @return el crédito, o error CreditNotFoundException si no existe
     */
    Single<Credit> findById(String creditId);
}