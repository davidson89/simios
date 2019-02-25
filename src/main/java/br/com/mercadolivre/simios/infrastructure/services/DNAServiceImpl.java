package br.com.mercadolivre.simios.infrastructure.services;

import br.com.mercadolivre.simios.domain.http.DNAStats;
import br.com.mercadolivre.simios.domain.persistence.DNAEntity;
import br.com.mercadolivre.simios.domain.persistence.DNAType;
import br.com.mercadolivre.simios.infrastructure.repository.DNARepository;
import br.com.mercadolivre.simios.infrastructure.repository.projections.DNAInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by davidson on 24/02/19.
 */
@Service
public class DNAServiceImpl implements DNAService {

    @Autowired
    private DNARepository dnaRepository;

//    @Autowired
//    private DNAInfoRepository dnaInfoRepository;

    @Autowired
    @Qualifier("simiosPerformingService")
    private SimiosService simiosService;

    /**
     * Persiste o DNA com a informação de seu tipo, caso o DNA não exista no banco.
     *
     * A validação de unicidade é feita via código, pois o campo que representa o DNA pode ser gigantesco.
     * Por esse motivo, decidi calcular um hash simples (com colisão), e apartir dele verificar se existe uma sequencia igual
     *
     * @param sequence a sequencia a ser persistida
     * @param isSimio <code>true</code> caso a sequencia de DNA seja de um simio
     */
    @Async
    public void save(String[] sequence, boolean isSimio) {
        DNAEntity entity = DNAEntity.of(sequence, isSimio ? DNAType.SIMIO : DNAType.HUMAN);
        Integer dnaHash = entity.getHash();
        List<DNAEntity> entities = this.dnaRepository.findAllByHashEquals(dnaHash);

        if(entities.stream().noneMatch(entity::equals)) {
            this.dnaRepository.save(entity);
        }
    }

    @Override
    public boolean isSimio(String[] sequence) {
        boolean isSimio = this.simiosService.isSimio(sequence);
        this.save(sequence, isSimio);
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
