package br.com.mercadolivre.simios.validator;

import org.apache.commons.lang.StringUtils;

import java.util.stream.Stream;

/**
 * Created by davidson on 17/02/19.
 */
public class LenghtValidator {

    private static final int MIN_LENGHT = 4;

    public static final LenghtValidator INSTANCE = new LenghtValidator();

    private LenghtValidator() {}

    public boolean isValid(int length, String sequence) {
        if (length < MIN_LENGHT || StringUtils.isEmpty(sequence)) {
            return false;
        }

        return sequence.length() == length;
    }

}
