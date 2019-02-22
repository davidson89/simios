package br.com.mercadolivre.simios.component;

import br.com.mercadolivre.simios.validator.DNACompostionValidator;
import br.com.mercadolivre.simios.validator.DiagonalSimiosValidator;
import br.com.mercadolivre.simios.validator.LenghtValidator;
import br.com.mercadolivre.simios.validator.SimiosLineColumnValidator;

import java.util.stream.Stream;

public class SimiosPerformaticComponent implements SimiosComponent {

    public boolean isSimios(String[] horizontalSequence) {
        if (horizontalSequence == null) {
            return false;
        }

        final int length = horizontalSequence.length;
        System.out.println("Iniciando Validações de entrada...");
        boolean isValid = Stream.of(horizontalSequence)
                        .parallel()
                        .allMatch(seq -> LenghtValidator.INSTANCE.isValid(length, seq) && DNACompostionValidator.INSTANCE.isValid(seq));
        System.out.println("Validações de entrada finalizada!");

        if (!isValid) {
            return false;
        }

        System.out.println("Validando simios na horizontal...");
        boolean isSimios = SimiosLineColumnValidator.INSTANCE.isSimios(horizontalSequence);
        if (isSimios) {
            return true;
        }
        System.out.println("Validações simios na horizontal finalizada!");

        System.out.println("Validando simios na diagonal...");
        return DiagonalSimiosValidator.isSimios(horizontalSequence);
    }


}
