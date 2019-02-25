package br.com.mercadolivre.simios.domain.persistence;

/**
 * Created by davidson on 24/02/19.
 */
public enum DNAType {
    HUMAN,
    SIMIO;

    public boolean isHuman() {
        return this == HUMAN;
    }
}
