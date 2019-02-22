package br.com.mercadolivre.simios.validator;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by davidson on 18/02/19.
 */
public class DNACompostionValidatorTest {

    private static final String VALID_SEQUENCE = "ATCGAT";

    @Test
    public void testValid(){
        Assert.assertTrue(DNACompostionValidator.INSTANCE.isValid(VALID_SEQUENCE));
    }

    @Test
    public void testInvalid(){
	Assert.assertFalse(DNACompostionValidator.INSTANCE.isValid(VALID_SEQUENCE + "X"));
        Assert.assertFalse(DNACompostionValidator.INSTANCE.isValid("X" + VALID_SEQUENCE));
    }

}