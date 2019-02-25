package br.com.mercadolivre.simios.infrastructure.services;

import br.com.mercadolivre.simios.validator.DNACompostionValidator;
import br.com.mercadolivre.simios.validator.LenghtValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public interface SimiosService {

    Logger LOG = LoggerFactory.getLogger(SimiosReadableService.class);

    boolean isSimio(String[] horizontalSequence);

    /**
     *
     * @param horizontalSequence a sequencia de DNA de entrada
     * @return true caso todas as sequencias tenham o mesmo tamanho do array de entrada e que todas as sequencia seja compostas pela base do DNA(ATCG)
     */
    default boolean isValidSequence(String[] horizontalSequence){
        final int length = horizontalSequence.length;
        LOG.info("Iniciando Validações de entrada...");
        boolean isValid = Stream.of(horizontalSequence)
                        .parallel()
                        .allMatch(seq -> LenghtValidator.INSTANCE.isValid(length, seq) && DNACompostionValidator.INSTANCE.isValid(seq));

        LOG.info("Validações de entrada finalizada!");
        return isValid;
    }
}
