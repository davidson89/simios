package br.com.mercadolivre.simios.validator;

import org.junit.Assert;
import org.junit.Test;

public class SimiosLineValidatorTest {

    @Test
    public void testSimiosValidationWithSimio() {
        Assert.assertTrue(SimiosLineValidator.INSTANCE.isSimio("AGGGG"));
        Assert.assertTrue(SimiosLineValidator.INSTANCE.isSimio("GGGGA"));
        Assert.assertTrue(SimiosLineValidator.INSTANCE.isSimio("AGGGGA"));
    }

    @Test
    public void testSimiosValidationWithHuman() {
        Assert.assertFalse(SimiosLineValidator.INSTANCE.isSimio("ATCG"));
        Assert.assertFalse(SimiosLineValidator.INSTANCE.isSimio("ACCCG"));
    }
}