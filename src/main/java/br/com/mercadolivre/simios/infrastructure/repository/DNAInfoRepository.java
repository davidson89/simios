package br.com.mercadolivre.simios.infrastructure.repository;

import br.com.mercadolivre.simios.infrastructure.repository.projections.DNAInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

//@org.springframework.stereotype.Repository
//public interface DNAInfoRepository extends Repository<DNAInfo, String> {
//
//    @Query("SELECT " +
//            "    new br.com.mercadolivre.simios.infrastructure.repository.projections.DNAInfo(dna.dnaType, COUNT(dna)) " +
//            "FROM " +
//            "    DNAEntity dna " +
//            "GROUP BY " +
//            "    dna.dnaType")
//    List<DNAInfo> findDNAInfo();
//}
