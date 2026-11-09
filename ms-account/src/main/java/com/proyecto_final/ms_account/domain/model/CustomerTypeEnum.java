package com.proyecto_final.ms_account.domain.model;

/**
 * Tipos de cliente del banco. Cada tipo define cuántos titulares y firmantes admite una cuenta.
 */
public enum CustomerTypeEnum {

    PERSONAL,
    BUSINESS;

    private static final int MINIMUM_BUSINESS_HOLDERS = 1;

    /**
     * Indica si la cantidad de titulares y firmantes es válida para este tipo de cliente.
     * Personal: ninguno (el propio cliente es el titular). Empresarial: al menos un titular.
     *
     * @param holdersCount cantidad de titulares
     * @param signersCount cantidad de firmantes autorizados
     * @return true si la combinación es válida
     */
    public boolean acceptsParticipants(int holdersCount, int signersCount) {
        return switch (this) {
            case PERSONAL -> holdersCount == 0 && signersCount == 0;
            case BUSINESS -> holdersCount >= MINIMUM_BUSINESS_HOLDERS;
        };
    }
}