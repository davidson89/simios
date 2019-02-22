package br.com.mercadolivre.simios;

import br.com.mercadolivre.simios.generator.SequenceGenerator;
import br.com.mercadolivre.simios.validator.DNACompostionValidator;
import br.com.mercadolivre.simios.validator.LenghtValidator;
import br.com.mercadolivre.simios.validator.SimiosValidator;

import java.util.stream.Stream;

public class SimiosComponent {


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
        boolean isSimios = Stream.of(horizontalSequence).parallel().anyMatch(SimiosValidator::isSimios);
        if (isSimios) {
            return true;
        }
        System.out.println("Validações simios na horizontal finalizada!");

        System.out.println("Gerando vertical...");
        String[] verticalSequence = SequenceGenerator.generateVerticalSequence(horizontalSequence);
        System.out.println("Vertical gerada!");

        System.out.println("Validando simios na vertical...");
        isSimios = Stream.of(verticalSequence).parallel().anyMatch(SimiosValidator::isSimios);
        if (isSimios) {
	    return true;
        }
        System.out.println("Validações simios na vertical finalizada!");

        System.out.println("Gerando diagonais...");
        String[] diagonalSequence = SequenceGenerator.generateDiagonalSequence(horizontalSequence);
        System.out.println("Diagonais geradas!");
        System.out.println("Validando simios na diagonal...");
        return Stream.of(diagonalSequence).parallel().anyMatch(SimiosValidator::isSimios);
    }


}
