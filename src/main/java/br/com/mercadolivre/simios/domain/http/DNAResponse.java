package br.com.mercadolivre.simios.domain.http;

/**
 * Created by davidson on 24/02/19.
 */
public class DNAResponse {

    private final boolean simios;

    private DNAResponse(boolean simios) {
        this.simios = simios;
    }

    private static DNAResponse of(boolean simios) {
        return new DNAResponse(simios);
    }

    public boolean isSimios() {
        return simios;
    }
}
