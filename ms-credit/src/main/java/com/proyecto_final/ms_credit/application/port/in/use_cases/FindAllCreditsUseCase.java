package com.proyecto_final.ms_credit.application.port.in.use_cases;

import com.proyecto_final.ms_credit.domain.model.Credit;
import io.reactivex.rxjava3.core.Flowable;

/**
 * Caso de uso: listar los créditos activos.
 */
public interface FindAllCreditsUseCase {

    /**
     * Obtiene todos los créditos activos.
     *
     * @return flujo con los créditos activos (vacío si no hay ninguno)
     */
    Flowable<Credit> findAll();
}
