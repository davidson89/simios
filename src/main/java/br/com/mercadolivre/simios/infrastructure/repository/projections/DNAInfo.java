package br.com.mercadolivre.simios.infrastructure.repository.projections;

import br.com.mercadolivre.simios.domain.persistence.DNAType;

public class DNAInfo {

    private final DNAType dnaType;
    private final int count;

    public DNAInfo(DNAType dnaType, long count) {
        this.dnaType = dnaType;
        this.count = (int) count;
    }

    public int getCount() {
        return count;
    }

    public boolean isHuman() {
        return this.dnaType.isHuman();
    }

    public boolean isSimio() {
        return !this.isHuman();
    }
}
