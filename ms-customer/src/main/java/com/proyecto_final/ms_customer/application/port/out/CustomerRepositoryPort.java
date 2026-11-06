package com.proyecto_final.ms_customer.application.port.out;

import com.proyecto_final.ms_customer.domain.model.Customer;
import com.proyecto_final.ms_customer.domain.model.DocumentTypeEnum;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

/**
 * Operaciones de persistencia de clientes que necesita la aplicación.
 * La implementación concreta (MongoDB) vive en infrastructure.
 */
public interface CustomerRepositoryPort {

    /**
     * Guarda un cliente nuevo o actualiza uno existente.
     *
     * @param customer cliente a guardar
     * @return el cliente guardado
     */
    Single<Customer> save(Customer customer);

    /**
     * Busca un cliente activo por su identificador.
     *
     * @param customerId identificador del cliente
     * @return el cliente, o vacío si no existe o está inactivo
     */
    Maybe<Customer> findActiveById(String customerId);

    /**
     * Obtiene todos los clientes activos.
     *
     * @return flujo con los clientes activos
     */
    Flowable<Customer> findAllActive();

    /**
     * Indica si existe un cliente activo con el documento indicado.
     *
     * @param documentType   tipo de documento
     * @param documentNumber número de documento
     * @return true si existe un cliente activo con ese documento
     */
    Single<Boolean> existsActiveByDocument(DocumentTypeEnum documentType, String documentNumber);
}
