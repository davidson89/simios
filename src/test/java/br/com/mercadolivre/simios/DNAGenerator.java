package br.com.mercadolivre.simios;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by davidson on 17/02/19.
 */
public final class DNAGenerator {

    private static final List<String> POSSIBLE_LETTERS = Arrays.asList("A", "C", "G", "T");

    private DNAGenerator(){}

    public static String[] generateRandomDNA(int lenght) {
        Random rand = new Random();
        String[] sequences = new String[lenght];
        for (int i = 0; i < lenght; i++) {
            StringBuilder sequenceAcumulator = new StringBuilder();
            for (int j = 0; j < lenght; j++) {
                String letter = POSSIBLE_LETTERS.get(rand.nextInt(4));
                sequenceAcumulator.append(letter);
            }
            sequences[i] = sequenceAcumulator.toString();
        }
        return sequences;
    }
}
