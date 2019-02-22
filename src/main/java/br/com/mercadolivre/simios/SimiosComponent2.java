package br.com.mercadolivre.simios;

import br.com.mercadolivre.simios.validator.DNACompostionValidator;
import br.com.mercadolivre.simios.validator.DiagonalSimiosValidator;
import br.com.mercadolivre.simios.validator.LenghtValidator;
import br.com.mercadolivre.simios.validator.SimiosValidator2;

import java.util.stream.Stream;

public class SimiosComponent2 {

    public boolean isSimios(String[] horizontalSequence) {
        if (horizontalSequence == null) {
            return false;
        }

        final int length = horizontalSequence.length;
        System.out.println("Iniciando Validações de entrada...");
        boolean isValid = Stream.of(horizontalSequence)
                        .parallel()
                        .allMatch(seq -> LenghtValidator.isValid(length, seq) && DNACompostionValidator.isValid(seq));
        System.out.println("Validações de entrada finalizada!");

        if (!isValid) {
            return false;
        }

        System.out.println("Validando simios na horizontal...");
        boolean isSimios = SimiosValidator2.isSimios(horizontalSequence);
        if (isSimios) {
            return true;
        }
        System.out.println("Validações simios na horizontal finalizada!");

        System.out.println("Validando simios na diagonal...");
        return DiagonalSimiosValidator.isSimios(horizontalSequence);
    }


}
