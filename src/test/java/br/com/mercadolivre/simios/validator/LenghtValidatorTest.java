package br.com.mercadolivre.simios.validator;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by davidson on 18/02/19.
 */
public class LenghtValidatorTest {

    @Test
    public void testValid() {
        String sequence = "ATGC";
        Assert.assertTrue(LenghtValidator.INSTANCE.isValid(sequence.length(), sequence));
    }

    @Test
    public void testInvalid() {
        String sequence = "ATGC";
        Assert.assertFalse(LenghtValidator.INSTANCE.isValid(sequence.length() + 1, sequence));
        Assert.assertFalse(LenghtValidator.INSTANCE.isValid(sequence.length() - 1, sequence));
    }

}