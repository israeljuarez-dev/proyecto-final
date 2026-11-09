package com.proyecto_final.ms_credit.application.port.out;

import com.proyecto_final.ms_credit.domain.model.Credit;
import com.proyecto_final.ms_credit.domain.model.CreditTypeEnum;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

/**
 * Puerto de salida: operaciones de persistencia de créditos que necesita la aplicación.
 */
public interface CreditRepositoryPort {

    /**
     * Guarda un crédito nuevo o actualiza uno existente.
     *
     * @param credit crédito a guardar
     * @return el crédito guardado
     */
    Single<Credit> save(Credit credit);

    /**
     * Busca un crédito activo por su identificador.
     *
     * @param creditId identificador del crédito
     * @return el crédito, o vacío si no existe o está inactivo
     */
    Maybe<Credit> findActiveById(String creditId);

    /**
     * Obtiene todos los créditos activos.
     *
     * @return flujo con los créditos activos
     */
    Flowable<Credit> findAllActive();

    /**
     * Cuenta los créditos activos de un tipo que tiene un cliente.
     *
     * @param customerId identificador del cliente
     * @param type       tipo de crédito
     * @return cantidad de créditos activos de ese tipo
     */
    Single<Long> countActiveByCustomerIdAndType(String customerId, CreditTypeEnum type);
}
