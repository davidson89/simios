package br.com.mercadolivre.simios.domain.http;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created by davidson on 24/02/19.
 */
public class DNAStats {

    public static final int SCALE = 2;
    private final int countHumanDNA;

    private final int countMutantDNA;

    private final float ratio;

    private DNAStats(int countHumanDNA, int countMutantDNA) {
        this.countHumanDNA = countHumanDNA;
        this.countMutantDNA = countMutantDNA;

        this.ratio = countHumanDNA == 0 ? 0 :
                new BigDecimal((double) this.countMutantDNA / (double) this.countHumanDNA)
                        .setScale(SCALE, BigDecimal.ROUND_HALF_DOWN)
                        .floatValue();
    }

    public static DNAStats of(int countHumanDNA, int countMutantDNA) {
        return new DNAStats(countHumanDNA, countMutantDNA);
    }

    @JsonProperty("count_human_dna")
    public int getCountHumanDNA() {
        return countHumanDNA;
    }

    @JsonProperty("count_mutant_dna")
    public int getCountMutantDNA() {
        return countMutantDNA;
    }

    @JsonProperty("ratio")
    public float getRatio() {
        return ratio;
    }
}
