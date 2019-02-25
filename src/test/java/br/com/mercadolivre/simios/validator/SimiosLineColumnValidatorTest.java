package br.com.mercadolivre.simios.validator;

import org.junit.Assert;
import org.junit.Test;

public class SimiosLineColumnValidatorTest {

    @Test
    public void SimiosValidationWithDNALength4x4() {
        String[] dna = {"CTGA",
                        "AGTA",
                        "AGCC",
                        "TGTA"};

        boolean isSimio = SimiosLineColumnValidator.INSTANCE.isSimio(dna);

        Assert.assertFalse(isSimio);

        String[] dnaSimioInLine = {"CTGA",
                                   "AAAA",
                                   "AGCC",
                                   "TGTA"};

        isSimio = SimiosLineColumnValidator.INSTANCE.isSimio(dnaSimioInLine);

        Assert.assertTrue(isSimio);

        String[] dnaSimioInColumn = {"CTGA",
                                     "AATA",
                                     "AGCA",
                                     "TGTA"};

        isSimio = SimiosLineColumnValidator.INSTANCE.isSimio(dnaSimioInColumn);

        Assert.assertTrue(isSimio);

    }

    @Test
    public void SimiosValidationWithDNALength5x5() {

        String[] dna = {"CTGAC",
                        "AGTAT",
                        "AGCCG",
                        "TGTAA",
                        "TCTAC"};

        boolean isSimio = SimiosLineColumnValidator.INSTANCE.isSimio(dna);

        Assert.assertFalse(isSimio);

        String[] dnaSimioInLine = {"CTGAC",
                                   "AGTAT",
                                   "CCCCC",
                                   "TGTAA",
                                   "TCTAC"};

        isSimio = SimiosLineColumnValidator.INSTANCE.isSimio(dnaSimioInLine);

        Assert.assertTrue(isSimio);


        String[] dnaSimioInColumn = {"CTTAC",
                                     "AGTAT",
                                     "CCTAC",
                                     "TGTAA",
                                     "TCGAC"};

        isSimio = SimiosLineColumnValidator.INSTANCE.isSimio(dnaSimioInColumn);

        Assert.assertTrue(isSimio);

    }
}