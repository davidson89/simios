package br.com.mercadolivre.simios.infrastructure.services;

import br.com.mercadolivre.simios.validator.DiagonalSimiosValidator;
import br.com.mercadolivre.simios.validator.SimiosLineColumnValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("simiosPerformingService")
public class SimiosPerformingService implements SimiosService {

    private static final Logger LOG = LoggerFactory.getLogger(SimiosReadableService.class);

    public boolean isSimio(String[] horizontalSequence) {
        if (horizontalSequence == null || !isValidSequence(horizontalSequence)) {
            throw new IllegalArgumentException("The input no is valid!");
        }

        LOG.info("Validando simios na horizontal e vertical...");
        if (SimiosLineColumnValidator.INSTANCE.isSimio(horizontalSequence)) {
            return true;
        }
        LOG.info("Validação simios na horizontal e vertical finalizada!");

        LOG.info("Validando simios na diagonal...");
        return DiagonalSimiosValidator.INSTANCE.isSimio(horizontalSequence);
    }

}
