package br.com.mercadolivre.simios.component;

import br.com.mercadolivre.simios.validator.DNACompostionValidator;
import br.com.mercadolivre.simios.validator.DiagonalSimiosValidator;
import br.com.mercadolivre.simios.validator.LenghtValidator;
import br.com.mercadolivre.simios.validator.SimiosLineColumnValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class SimiosPerformaticComponent implements SimiosComponent {

    private static final Logger LOG = LoggerFactory.getLogger(SimiosReadableComponent.class);

    public boolean isSimios(String[] horizontalSequence) {
        if (horizontalSequence == null || !isValidSequence(horizontalSequence)) {
            return false;
        }

        LOG.info("Validando simios na horizontal e vertical...");
        boolean isSimios = SimiosLineColumnValidator.INSTANCE.isSimios(horizontalSequence);
        if (isSimios) {
            return true;
        }
        LOG.info("Validação simios na horizontal e vertical finalizada!");

        LOG.info("Validando simios na diagonal...");
        return DiagonalSimiosValidator.isSimios(horizontalSequence);
    }

    /**
     *
     * @param horizontalSequence a sequencia de DNA de entrada
     * @return true caso todas as sequencias tenham o mesmo tamanho do array de entrada e que todas as sequencia seja compostas pela base do DNA(ATCG)
     */
    private boolean isValidSequence(String[] horizontalSequence) {
        final int length = horizontalSequence.length;
        LOG.info("Iniciando Validações de entrada...");
        boolean isValid = Stream.of(horizontalSequence)
                        .parallel()
                        .allMatch(seq -> LenghtValidator.INSTANCE.isValid(length, seq) && DNACompostionValidator.INSTANCE.isValid(seq));

        LOG.info("Validações de entrada finalizada!");
        return isValid;
    }


}
