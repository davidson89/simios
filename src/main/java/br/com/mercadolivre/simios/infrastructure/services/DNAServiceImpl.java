package br.com.mercadolivre.simios.infrastructure.services;

import br.com.mercadolivre.simios.domain.http.DNAStats;
import br.com.mercadolivre.simios.domain.persistence.DNAEntity;
import br.com.mercadolivre.simios.domain.persistence.DNAType;
import br.com.mercadolivre.simios.infrastructure.repository.DNARepository;
import br.com.mercadolivre.simios.infrastructure.repository.projections.DNAInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by davidson on 24/02/19.
 */
@Service
public class DNAServiceImpl implements DNAService {

    @Autowired
    private DNARepository dnaRepository;

    @Autowired
    @Qualifier("simiosPerformingService")
    private SimiosService simiosService;

    /**
     * Persiste o DNA com a informação de seu tipo, caso o DNA não exista no banco.
     *
     * A validação de unicidade é feita via banco de dados.
     *
     * @param sequence a sequencia a ser persistida
     * @param isSimio <code>true</code> caso a sequencia de DNA seja de um simio
     */
    public void save(String[] sequence, boolean isSimio) {
        DNAEntity entity = DNAEntity.of(sequence, isSimio ? DNAType.SIMIO : DNAType.HUMAN);
        Long dnaHash = entity.getHash();
        boolean dnaExists = this.dnaRepository.existsDNAEntityByHashEquals(dnaHash);

        if(!dnaExists) {
            this.dnaRepository.save(entity);
        }
    }

    @Override
    public boolean isSimio(String[] sequence) {
        boolean isSimio = this.simiosService.isSimio(sequence);
        // O correto seria criar um executor que tenha timeout para não ficar segurando thread
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> this.save(sequence, isSimio));
        return isSimio;
    }

    public DNAStats getStats() {
        List<DNAInfo> dnaGroupByType = this.dnaRepository.findDNAInfo();
        Integer countHuman = dnaGroupByType.stream()
                .filter(DNAInfo::isHuman)
                .findFirst()
                .map(DNAInfo::getCount)
                .orElse(0);

        Integer countSimio = dnaGroupByType.stream()
                .filter(DNAInfo::isSimio)
                .findFirst()
                .map(DNAInfo::getCount)
                .orElse(0);

        return DNAStats.of(countHuman, countSimio);
    }
}
