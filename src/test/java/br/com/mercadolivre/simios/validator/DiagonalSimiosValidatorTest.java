package br.com.mercadolivre.simios.validator;

import org.junit.Assert;
import org.junit.Test;

public class DiagonalSimiosValidatorTest {

    @Test
    public void validatorSimioInDNA4x4() {
        String[] dnaNoSimio = {"CTGA",
                               "AGTA",
                               "AGCC",
                               "TGTA"};
        Assert.assertFalse(DiagonalSimiosValidator.INSTANCE.isSimio(dnaNoSimio));

        String[] dnaSimioGreaterDiagonal = {"CTGA",
                                            "ACTA",
                                            "AGCC",
                                            "TGTC"};
        Assert.assertTrue(DiagonalSimiosValidator.INSTANCE.isSimio(dnaSimioGreaterDiagonal));

        String[] dnaSimioGreaterDiagonalInverse = {"CTGA",
                                                   "AGAA",
                                                   "AACC",
                                                   "AGTA"};
        Assert.assertTrue(DiagonalSimiosValidator.INSTANCE.isSimio(dnaSimioGreaterDiagonalInverse));
    }

    @Test
    public void validatorSimioInDNA5x5() {
        String[] dnaNoSimio = {"ATGAC",
                               "ACTAG",
                               "AGGCT",
                               "TGTAA",
                               "CGTGT"};
        Assert.assertFalse(DiagonalSimiosValidator.INSTANCE.isSimio(dnaNoSimio));

        String[] dnaSimioGreaterDiagonal = {"ATGAC",
                                            "AATAG",
                                            "AGACT",
                                            "TGTAA",
                                            "CGTGA"};
        Assert.assertTrue(DiagonalSimiosValidator.INSTANCE.isSimio(dnaSimioGreaterDiagonal));

        String[] dnaSimioGreaterDiagonalInverse = {"ATGAC",
                                                   "ACTCG",
                                                   "AGCCT",
                                                   "TCTAA",
                                                   "AGTGT"};
        Assert.assertTrue(DiagonalSimiosValidator.INSTANCE.isSimio(dnaSimioGreaterDiagonalInverse));

        String[] dnaSimioUpperDiagonal = {"ATGAC",
                                          "AATAG",
                                          "AGGTT",
                                          "TGTAT",
                                          "CGTGA"};
        Assert.assertTrue(DiagonalSimiosValidator.INSTANCE.isSimio(dnaSimioUpperDiagonal));

        String[] dnaSimioLowerDiagonal = {"ATGAC",
                                          "AATAG",
                                          "AAGCT",
                                          "TGAAA",
                                          "CGTAA"};
        Assert.assertTrue(DiagonalSimiosValidator.INSTANCE.isSimio(dnaSimioLowerDiagonal));

        String[] dnaSimioUpperDiagonalInverse = {"ATGAC",
                                                 "ACAAG",
                                                 "AAGCT",
                                                 "AGTAA",
                                                 "CGTGt"};
        Assert.assertTrue(DiagonalSimiosValidator.INSTANCE.isSimio(dnaSimioUpperDiagonalInverse));

        String[] dnaSimioLowerDiagonalInverse = {"ATGAC",
                                                 "ACTAG",
                                                 "AGGGT",
                                                 "TGGAA",
                                                 "CGTGT"};
        Assert.assertTrue(DiagonalSimiosValidator.INSTANCE.isSimio(dnaSimioLowerDiagonalInverse));
    }

    @Test
    public void validatorSimioInDNA6x6() {
        String[] dnaNoSimio = {"ATGACT",
                               "ACTAGA",
                               "AGGCTG",
                               "TGTAAC",
                               "CGTGTA",
                               "AGCGTC"};
        Assert.assertFalse(DiagonalSimiosValidator.INSTANCE.isSimio(dnaNoSimio));

        String[] dnaSimioUpperDiagonal = {"ATGACT",
                                          "ACTGGA",
                                          "AGGCGG",
                                          "TGTAAG",
                                          "CGTGTA",
                                          "AGCGTC"};
        Assert.assertTrue(DiagonalSimiosValidator.INSTANCE.isSimio(dnaSimioUpperDiagonal));

        String[] dnaSimioLowerDiagonal = {"ATGACT",
                                          "ACTAGA",
                                          "AGGCTG",
                                          "TATAAC",
                                          "CGAGTA",
                                          "AGCATC"};
        Assert.assertTrue(DiagonalSimiosValidator.INSTANCE.isSimio(dnaSimioLowerDiagonal));

        String[] dnaSimioUpperDiagonalInverse = {"ATGACT",
                                                 "ACAAGA",
                                                 "AAGCTG",
                                                 "AGTAAC",
                                                 "CGTGTA",
                                                 "AGCGTC"};
        Assert.assertTrue(DiagonalSimiosValidator.INSTANCE.isSimio(dnaSimioUpperDiagonalInverse));

        String[] dnaSimioLowerDiagonalInverse = {"ATGACT",
                                                 "ACTAGA",
                                                 "AGGCTG",
                                                 "TGTAGC",
                                                 "CGTGTA",
                                                 "AGGGTC"};
        Assert.assertTrue(DiagonalSimiosValidator.INSTANCE.isSimio(dnaSimioLowerDiagonalInverse));
    }
}