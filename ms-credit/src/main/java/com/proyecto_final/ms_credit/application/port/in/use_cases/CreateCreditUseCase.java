package com.proyecto_final.ms_credit.application.port.in.use_cases;

import com.proyecto_final.ms_credit.application.port.in.dto.CreateCreditDto;
import com.proyecto_final.ms_credit.domain.model.Credit;
import io.reactivex.rxjava3.core.Single;

/**
 * Caso de uso: crear un producto de crédito.
 */
public interface CreateCreditUseCase {

    /**
     * Crea un crédito validando el cliente, los montos y el límite de créditos del tipo.
     *
     * @param createCreditDto datos del crédito a crear
     * @return el crédito creado
     */
    Single<Credit> create(CreateCreditDto createCreditDto);
}