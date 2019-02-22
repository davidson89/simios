package br.com.mercadolivre.simios.component;

import br.com.mercadolivre.simios.generator.SequenceGenerator;
import br.com.mercadolivre.simios.validator.DNACompostionValidator;
import br.com.mercadolivre.simios.validator.LenghtValidator;
import br.com.mercadolivre.simios.validator.SimiosLineValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class SimiosReadableComponent implements SimiosComponent {

    private static final Logger LOG = LoggerFactory.getLogger(SimiosReadableComponent.class);    

    public boolean isSimios(String[] horizontalSequence) {
        if (horizontalSequence == null || !isValidSequence(horizontalSequence)) {
            return false;
        }

        LOG.info("Validando simios na horizontal...");
        boolean isSimios = Stream.of(horizontalSequence).parallel().anyMatch(SimiosLineValidator::isSimios);
        if (isSimios) {
            return true;
        }
        LOG.info("Validações simios na horizontal finalizada!");

        LOG.info("Gerando vertical...");
        String[] verticalSequence = SequenceGenerator.generateVerticalSequence(horizontalSequence);
        LOG.info("Vertical gerada!");

        LOG.info("Validando simios na vertical...");
        isSimios = Stream.of(verticalSequence).parallel().anyMatch(SimiosLineValidator::isSimios);
        if (isSimios) {
	    return true;
        }
        LOG.info("Validações simios na vertical finalizada!");

        LOG.info("Gerando diagonais...");
        String[] diagonalSequence = SequenceGenerator.generateDiagonalSequence(horizontalSequence);
        LOG.info("Diagonais geradas!");
        LOG.info("Validando simios na diagonal...");
        return Stream.of(diagonalSequence).parallel().anyMatch(SimiosLineValidator::isSimios);
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
