package br.com.mercadolivre.simios.validator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by davidson on 17/02/19.
 */
public class DNACompostionValidator {

    private static final Set<String> POSSIBLE_LETTERS = new HashSet<>(Arrays.asList("A", "C", "G", "T"));

    public static final DNACompostionValidator INSTANCE = new DNACompostionValidator();

    private DNACompostionValidator() {}

    public boolean isValid(String sequence) {
        for (int i = 0, j = sequence.length() - 1 ; i <= j; i++, j--) {
            char leftLetter = sequence.charAt(i);
            char rightLetter = sequence.charAt(j);
            boolean isValid = POSSIBLE_LETTERS.contains(String.valueOf(leftLetter)) && POSSIBLE_LETTERS.contains(String.valueOf(rightLetter));
            if (!isValid) {
                return false;
            }
        }
        return true;
    }
}
