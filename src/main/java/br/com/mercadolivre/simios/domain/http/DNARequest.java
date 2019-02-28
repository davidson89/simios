package br.com.mercadolivre.simios.domain.http;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by davidson on 24/02/19.
 */
public class DNARequest {

    private final String[] dnaSequence;

    @JsonCreator
    public DNARequest(@JsonProperty("dna") String[] dnaSequence) {
        this.dnaSequence = dnaSequence;
    }

    @JsonIgnore
    public String[] getDnaSequence() {
        return dnaSequence;
    }
}
