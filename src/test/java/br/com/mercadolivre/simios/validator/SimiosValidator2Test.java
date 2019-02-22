package br.com.mercadolivre.simios.validator;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimiosValidator2Test {

    @Test
    public void SimiosValidationWithDNALength4x4() {
        String[] dna = {"CTGA",
                        "AGTA",
                        "AGCC",
                        "TGTA"};

        boolean isSimios = SimiosValidator2.isSimios(dna);

        Assert.assertFalse(isSimios);
    }
}