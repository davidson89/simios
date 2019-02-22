package br.com.mercadolivre.simios.validator;

import org.apache.commons.lang.StringUtils;

import java.util.stream.Stream;

/**
 * Created by davidson on 17/02/19.
 */
public class LenghtValidator {

    private static final int MIN_LENGHT = 4;

    public static boolean isValid(String[] horizontalSequence) {
        if (horizontalSequence == null || horizontalSequence.length < MIN_LENGHT) {
            return false;
        }
        final int length = horizontalSequence.length;
        return Stream.of(horizontalSequence)
                .allMatch(seq -> LenghtValidator.isValid(length, seq));
    }

    public static boolean isValid(int length, String sequence) {
        if (length < MIN_LENGHT || StringUtils.isEmpty(sequence)) {
            return false;
        }

        return sequence.length() == length;
    }

}
