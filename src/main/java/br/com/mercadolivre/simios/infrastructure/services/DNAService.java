package br.com.mercadolivre.simios.infrastructure.services;

import br.com.mercadolivre.simios.domain.http.DNAStats;

/**
 * Created by davidson on 24/02/19.
 */
public interface DNAService {

    void save(String[] sequence, boolean isSimio);

    DNAStats getStats();
}
