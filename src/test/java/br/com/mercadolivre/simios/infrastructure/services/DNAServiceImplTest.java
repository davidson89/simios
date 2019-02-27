package br.com.mercadolivre.simios.infrastructure.services;

import br.com.mercadolivre.simios.domain.persistence.DNAEntity;
import br.com.mercadolivre.simios.domain.persistence.DNAType;
import br.com.mercadolivre.simios.infrastructure.controller.DNAEndpoint;
import br.com.mercadolivre.simios.infrastructure.repository.DNARepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
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
        Mockito.when(dnaRepository.findAllByHashEquals(any())).thenReturn(Collections.emptyList());
        dnaService.save(SIMIO_DNA, true);

        Mockito.verify(dnaRepository, Mockito.times(1)).save(any());
    }

    @Test
    public void noSaveDNABecauseDiscoveryExistentDNAInDatabase() {
        DNAEntity dnaEntity = DNAEntity.of(SIMIO_DNA, DNAType.SIMIO);

        Mockito.when(dnaRepository.findAllByHashEquals(any())).thenReturn(List.of(dnaEntity));
        dnaService.save(SIMIO_DNA, true);

        Mockito.verify(dnaRepository, Mockito.never()).save(any());
    }

    @Test
    public void isSimio() {

    }

    @Test
    public void getStats() {
    }
}