package br.com.mercadolivre.simios.infrastructure.services;

import br.com.mercadolivre.simios.domain.http.DNAStats;
import br.com.mercadolivre.simios.domain.persistence.DNAEntity;
import br.com.mercadolivre.simios.domain.persistence.DNAType;
import br.com.mercadolivre.simios.infrastructure.repository.DNARepository;
import br.com.mercadolivre.simios.infrastructure.repository.DNARepository.DNAGroupByType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * Created by davidson on 24/02/19.
 */
public class DNAServiceImpl implements DNAService {

    @Autowired
    private DNARepository dnaRepository;

    @Async
    public void save(String[] sequence, boolean isSimio) {
        DNAEntity entity = DNAEntity.of(sequence, isSimio ? DNAType.SIMIO : DNAType.HUMAN);
        dnaRepository.save(entity);
    }

    public DNAStats getStats() {
        List<DNAGroupByType> dnaGroupByType = dnaRepository.findDNAGroupByType();
        Integer countHuman = dnaGroupByType.stream()
                .filter(DNAGroupByType::isHuman)
                .findFirst()
                .map(DNAGroupByType::getCount)
                .orElse(0);

        Integer countSimio = dnaGroupByType.stream()
                .filter(DNAGroupByType::isSimio)
                .findFirst()
                .map(DNAGroupByType::getCount)
                .orElse(0);

        return DNAStats.of(countHuman, countSimio);
    }
}
