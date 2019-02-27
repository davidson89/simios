package br.com.mercadolivre.simios.infrastructure.services;

import org.junit.Assert;
import org.junit.Test;

public abstract class SimiosServiceTest {

    private final SimiosService simiosService;

    SimiosServiceTest(SimiosService simiosService) {
        this.simiosService = simiosService;
    }

    @Test(expected = IllegalArgumentException.class)
    public void isSimioIllegalArgumentNull() {
        simiosService.isSimio(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isSimioIllegalArgumentMinLength() {
        simiosService.isSimio(new String[]{"ATC", "AGC", "TTC"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void isSimioIllegalArgumentDivergentLength() {
        simiosService.isSimio(new String[]{"ATCC", "AGCA", "TTCT", "AAC"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void isSimioIllegalArgumentCompositionNoAccept() {
        simiosService.isSimio(new String[]{"ATCC", "AGCA", "TTCT", "AAC" + "X"});
    }

    @Test
    public void isSimioInTheHorizontal() {
        Assert.assertTrue(simiosService.isSimio(new String[]{"ATCC", "AGCA", "TTCT", "AAAA" }));
    }

    @Test
    public void isSimioInTheVertical() {
        Assert.assertTrue(simiosService.isSimio(new String[]{"ATCC", "AGCA", "ATCT", "ACTG" }));
    }

    @Test
    public void isSimioInTheDiagonal() {
        Assert.assertTrue(simiosService.isSimio(new String[]{"ATCC", "TACA", "TTAT", "CGTA" }));
    }

    @Test
    public void isSimioFalse() {
        Assert.assertFalse(simiosService.isSimio(new String[]{"ATCC", "TACC", "TTAT", "CGTG" }));
    }
}