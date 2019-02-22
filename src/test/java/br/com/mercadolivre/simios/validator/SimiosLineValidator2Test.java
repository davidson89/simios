package br.com.mercadolivre.simios.validator;

import org.junit.Assert;
import org.junit.Test;

public class SimiosLineValidator2Test {

    @Test
    public void SimiosValidationWithDNALength4x4() {
        String[] dna = {"CTGA",
                        "AGTA",
                        "AGCC",
                        "TGTA"};

        boolean isSimios = SimiosLineColumnValidator.INSTANCE.isSimios(dna);

        Assert.assertFalse(isSimios);
    }
}