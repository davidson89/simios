package br.com.mercadolivre.simios.infrastructure.repository;

import br.com.mercadolivre.simios.domain.persistence.DNAEntity;
import br.com.mercadolivre.simios.infrastructure.repository.projections.DNAInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by davidson on 24/02/19.
 */
@Repository
public interface DNARepository extends CrudRepository<DNAEntity, String> {

    Boolean existsDNAEntityByHashEquals(Long hash);

    @Query("SELECT " +
            "    new br.com.mercadolivre.simios.infrastructure.repository.projections.DNAInfo(dna.dnaType, COUNT(dna)) " +
            "FROM " +
            "    DNAEntity dna " +
            "GROUP BY " +
            "    dna.dnaType")
    List<DNAInfo> findDNAInfo();

}
