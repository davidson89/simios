package br.com.mercadolivre.simios.generator;

import br.com.mercadolivre.simios.util.DNAGenerator;
import org.junit.Assert;
import org.junit.Test;

public class SequenceGeneratorTest {

    @Test
    public void generateVerticalSequenceWithDNALength4x4() {
        String[] dna = {"CTGA",
                        "AGTA",
                        "AGCC",
                        "TGTA"};

        String[] diagonalSequence = SequenceGenerator.generateVerticalSequence(dna);

        Assert.assertEquals(4, diagonalSequence.length);

        Assert.assertEquals("CAAT", diagonalSequence[0]);
        Assert.assertEquals("TGGG", diagonalSequence[1]);
        Assert.assertEquals("GTCT", diagonalSequence[2]);
        Assert.assertEquals("AACA", diagonalSequence[3]);
    }

    @Test
    public void generateVerticalSequenceWithDNALength5x5() {
        String[] dna = {"CTGAG",
                        "AGTAC",
                        "AGCCC",
                        "TGTAT",
                        "AAGCA"};

        String[] diagonalSequence = SequenceGenerator.generateVerticalSequence(dna);

        Assert.assertEquals(5, diagonalSequence.length);

        Assert.assertEquals("CAATA", diagonalSequence[0]);
        Assert.assertEquals("TGGGA", diagonalSequence[1]);
        Assert.assertEquals("GTCTG", diagonalSequence[2]);
        Assert.assertEquals("AACAC", diagonalSequence[3]);
        Assert.assertEquals("GCCTA", diagonalSequence[4]);
    }


    @Test
    public void generateDiagonalSequenceWithDNALength4x4() {
        String[] dna = {"CTGA",
                        "AGTA",
                        "AGCC",
                        "TGTA"};

        String[] diagonalSequence = SequenceGenerator.generateDiagonalSequence(dna);

        Assert.assertEquals(2, diagonalSequence.length);

        Assert.assertEquals("CGCA", diagonalSequence[0]);
        Assert.assertEquals("ATGT", diagonalSequence[1]);
    }

    @Test
    public void generateDiagonalSequenceWithDNALength5x5() {
        String[] dna = {"CTGAG",
                        "AGTAC",
                        "AGCCA",
                        "TGTAT",
                        "AATAC"};

        String[] diagonalSequence2 = SequenceGenerator.generateDiagonalSequence(dna);

        Assert.assertEquals(6, diagonalSequence2.length);

        Assert.assertEquals("CGCAC", diagonalSequence2[0]);
        Assert.assertEquals("GACGA", diagonalSequence2[1]);

        Assert.assertEquals("AGTA", diagonalSequence2[2]);
        Assert.assertEquals("TTCT", diagonalSequence2[3]);

        Assert.assertEquals("CCTA", diagonalSequence2[4]);
        Assert.assertEquals("ATGT", diagonalSequence2[5]);
    }

    @Test
    public void generateDiagonalSequenceWithDNALength6x6() {
        String[] dna = {"CTGAGT",
                        "AGTACG",
                        "AGCCAA",
                        "TGTATA",
                        "AATACC",
                        "CGTACT"};

        String[] diagonalSequence2 = SequenceGenerator.generateDiagonalSequence(dna);

        Assert.assertEquals(10, diagonalSequence2.length);

        Assert.assertEquals("CGCACT", diagonalSequence2[0]);
        Assert.assertEquals("TCCTAC", diagonalSequence2[1]);

        Assert.assertEquals("AGTAC", diagonalSequence2[2]);
        Assert.assertEquals("TTCTC", diagonalSequence2[3]);

        Assert.assertEquals("GAATG", diagonalSequence2[4]);
        Assert.assertEquals("GACGA", diagonalSequence2[5]);

        Assert.assertEquals("AGTA", diagonalSequence2[6]);
        Assert.assertEquals("GAAA", diagonalSequence2[7]);

        Assert.assertEquals("ATAT", diagonalSequence2[8]);
        Assert.assertEquals("ATGT", diagonalSequence2[9]);
    }

    @Test
    public void generateDiagonalSequenceValidateNLenghts() {
        for (int x = 7; x <= 100 ; x++) {
            String[] dna = DNAGenerator.generateRandomDNA(x);
            String[] diagonalSequence = SequenceGenerator.generateDiagonalSequence(dna);
            int expectedDiagonalLenght = 4 * x - 14;
            Assert.assertEquals(expectedDiagonalLenght, diagonalSequence.length);
        }
    }
}