package br.com.mercadolivre.simios.domain.persistence;

import javax.persistence.*;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by davidson on 24/02/19.
 */
@Entity
@Table(name = "dna")
public class DNAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "sequence", nullable = false)
    private String[] dnaSequence;

    /**
     * O hash não foi utilizado para definir unicidade na tabela, pois ele pode ter colisão.
     * Isso faria com que tivessemos falsos positvos e falsos negativos.
     * Ele é utilizado apenas para auxiliar a comparação, uma vez que hashes iguais podem ter a mesma sequencia de DNA.
     */
    @Column(name = "hash", nullable = false)
    private Integer hash;

    @Column(name = "type", nullable = false)
    private DNAType dnaType;

    private DNAEntity() {}

    public DNAEntity(String[] dnaSequence, DNAType dnaType) {
        this.dnaSequence = dnaSequence;
        this.dnaType = dnaType;
        this.hash = Arrays.hashCode(dnaSequence);
    }

    public static DNAEntity of(String[] sequence, DNAType dnaType) {
        return new DNAEntity(sequence, dnaType);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String[] getDnaSequence() {
        return dnaSequence;
    }

    public Integer getHash() {
        return hash;
    }

    public DNAType getDnaType() {
        return dnaType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DNAEntity dnaEntity = (DNAEntity) o;

        if (!Arrays.equals(dnaSequence, dnaEntity.dnaSequence)) return false;
        if (hash != null ? !hash.equals(dnaEntity.hash) : dnaEntity.hash != null) return false;
        return dnaType == dnaEntity.dnaType;

    }

    @Override
    public int hashCode() {
        return hash;
    }
}
