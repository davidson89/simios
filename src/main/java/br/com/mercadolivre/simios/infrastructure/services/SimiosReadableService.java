package br.com.mercadolivre.simios.infrastructure.services;

import br.com.mercadolivre.simios.generator.SequenceGenerator;
import br.com.mercadolivre.simios.validator.DNACompostionValidator;
import br.com.mercadolivre.simios.validator.LenghtValidator;
import br.com.mercadolivre.simios.validator.SimiosLineValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class SimiosReadableService implements SimiosService {

    private static final Logger LOG = LoggerFactory.getLogger(SimiosReadableService.class);

    public boolean isSimios(String[] horizontalSequence) {
        if (horizontalSequence == null || !this.isValidSequence(horizontalSequence)) {
            return false;
        }

        LOG.info("Validando simios na horizontal...");
        boolean isSimios = Stream.of(horizontalSequence)
                        .parallel()
                        .anyMatch(SimiosLineValidator.INSTANCE::isSimios);
        if (isSimios) {
            return true;
        }
        LOG.info("Validações simios na horizontal finalizada!");

        LOG.info("Gerando vertical...");
        String[] verticalSequence = SequenceGenerator.INSTANCE.generateVerticalSequence(horizontalSequence);
        LOG.info("Vertical gerada!");

        LOG.info("Validando simios na vertical...");
        isSimios = Stream.of(verticalSequence)
                        .parallel()
                        .anyMatch(SimiosLineValidator.INSTANCE::isSimios);
        if (isSimios) {
	    return true;
        }
        LOG.info("Validações simios na vertical finalizada!");

        LOG.info("Gerando diagonais...");
        String[] diagonalSequence = SequenceGenerator.INSTANCE.generateDiagonalSequence(horizontalSequence);
        LOG.info("Diagonais geradas!");

        LOG.info("Validando simios na diagonal...");
        return Stream.of(diagonalSequence)
                        .parallel()
                        .anyMatch(SimiosLineValidator.INSTANCE::isSimios);
    }
}
