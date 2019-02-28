package br.com.mercadolivre.simios.infrastructure.services;

import br.com.mercadolivre.simios.domain.http.DNAStats;
import br.com.mercadolivre.simios.domain.persistence.DNAType;
import br.com.mercadolivre.simios.infrastructure.repository.DNARepository;
import br.com.mercadolivre.simios.infrastructure.repository.projections.DNAInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class DNAServiceImplTest {

    private static final String[] SIMIO_DNA = {"AAAA", "ATCG", "GCGG", "TACC"};

    @Mock
    public DNARepository dnaRepository;

    @Mock
    public SimiosService simiosService;

    @InjectMocks
    public DNAServiceImpl dnaService;


    @Test
    public void saveNewDNA() {
        Mockito.when(dnaRepository.existsDNAEntityByHashEquals(any())).thenReturn(false);
        dnaService.save(SIMIO_DNA, true);

        Mockito.verify(dnaRepository, Mockito.times(1)).save(any());
    }

    @Test
    public void noSaveDNABecauseDNAExistsInDatabase() {
        Mockito.when(dnaRepository.existsDNAEntityByHashEquals(any())).thenReturn(true);
        dnaService.save(SIMIO_DNA, true);

        Mockito.verify(dnaRepository, Mockito.never()).save(any());
    }

    @Test
    public void isSimio() {
        dnaService.isSimio(SIMIO_DNA);
        Mockito.verify(simiosService, Mockito.times(1)).isSimio(any());
    }

    @Test
    public void getStatsWithHumanTwo() {
        var dnaInfoHuman = new DNAInfo(DNAType.HUMAN, 2L);
        var dnaInfoSimio = new DNAInfo(DNAType.SIMIO, 1L);
        Mockito.when(dnaRepository.findDNAInfo()).thenReturn(List.of(dnaInfoHuman, dnaInfoSimio));

        DNAStats dnaStats = dnaService.getStats();
        Assert.assertEquals(2, dnaStats.getCountHumanDNA());
        Assert.assertEquals(1, dnaStats.getCountMutantDNA());
        Assert.assertEquals(0,0.5, dnaStats.getRatio());
    }

    @Test
    public void getStatsWithHumanZero() {
        var dnaInfoHuman = new DNAInfo(DNAType.HUMAN, 0L);
        var dnaInfoSimio = new DNAInfo(DNAType.SIMIO, 1L);
        Mockito.when(dnaRepository.findDNAInfo()).thenReturn(List.of(dnaInfoHuman, dnaInfoSimio));

        DNAStats dnaStats = dnaService.getStats();
        Assert.assertEquals(0, dnaStats.getCountHumanDNA());
        Assert.assertEquals(1, dnaStats.getCountMutantDNA());
        Assert.assertEquals(0,0, dnaStats.getRatio());
    }
}