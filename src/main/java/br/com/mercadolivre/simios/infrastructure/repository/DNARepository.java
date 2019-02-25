package br.com.mercadolivre.simios.infrastructure.repository;

import br.com.mercadolivre.simios.domain.persistence.DNAEntity;
import br.com.mercadolivre.simios.domain.persistence.DNAType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by davidson on 24/02/19.
 */
public interface DNARepository extends CrudRepository<DNAEntity, String> {

    @Query("SELECT " +
            "    new br.com.mercadolivre.simios.infrastructure.repository.DNARepository.DNAGroupByType(dna.dnaType, COUNT(dna)) " +
            "FROM " +
            "    br.com.mercadolivre.simios.domain.persistence.DNAEntity dna " +
            "GROUP BY " +
            "    dna.dnaType")
    List<DNAGroupByType> findDNAGroupByType();

    class DNAGroupByType {
        private final DNAType dnaType;
        private final int count;

        public DNAGroupByType(DNAType dnaType, int count) {
            this.dnaType = dnaType;
            this.count = count;
        }

        public DNAType getDnaType() {
            return dnaType;
        }

        public int getCount() {
            return count;
        }

        public boolean isHuman() {
            return this.dnaType.isHuman();
        }

        public boolean isSimio() {
            return !this.isHuman();
        }
    }

}
