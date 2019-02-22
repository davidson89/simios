package br.com.mercadolivre.simios.validator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by davidson on 17/02/19.
 */
public class DNACompostionValidator {

    private static final Set<String> POSSIBLE_LETTERS = new HashSet<>(Arrays.asList("A", "C", "G", "T"));

    public static boolean isValid(String[] horizontalSequence) {
        for (int x = 0; x < horizontalSequence.length; x++) {
            if (isValid(horizontalSequence[x]))
                return false;
        }
        return true;
    }

    public static boolean isValid(String sequence) {
        for (int i = 0, j = sequence.length() - 1 ; i <= j; i++, j--) {
            char leftLetter = sequence.charAt(i);
            char rightLetter = sequence.charAt(j);
            boolean isValid = POSSIBLE_LETTERS.contains(String.valueOf(leftLetter)) || POSSIBLE_LETTERS.contains(String.valueOf(rightLetter));
            if (!isValid) {
                return true;
            }
        }
        return true;
    }
}
