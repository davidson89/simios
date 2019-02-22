package br.com.mercadolivre.simios.validator;

import java.util.stream.Stream;

public class DiagonalSimiosValidator {

    private static final int DIAGONAL_DELIMITER = 3;
    private static final int SIMIOS_LENGHT = 4;

    /**
     * A quantidade de diagonais é resolvida pela formula da reta y = a*x + c, onde a = 4 e c = -14.
     * Logo a formula que defini a quantidade de diagonáis é y = 4*x - 14
     *
     * @param horizontalSequence as sequencias horizontais
     */
    public static boolean isSimios(String[] horizontalSequence) {

        int horizontalLength = horizontalSequence.length;

        return Stream.iterate(0, i -> i + 1)
                        .limit(horizontalLength - DIAGONAL_DELIMITER)
                        .parallel()
                        .anyMatch(i ->  {

            String greaterDiagonal = "X";
            String greaterDiagonalInverse = "X";
            String lowerDiagonal = "X";
            String upperDiagonal = "X";
            String lowerDiagonalInverse = "X";
            String upperDiagonalInverse = "X";
            int z = 0;
            int w = horizontalLength - 1;
            for (int j = i; j < horizontalLength; j++) {
                if (i == 0) {
                    String sequence = horizontalSequence[j];
                    greaterDiagonal = getAcumulateLetters(greaterDiagonal, sequence.charAt(z));

                    String sequenceInverse = horizontalSequence[j];
                    greaterDiagonalInverse = getAcumulateLetters(greaterDiagonalInverse, sequenceInverse.charAt(w));

                    if (greaterDiagonal.length() >= SIMIOS_LENGHT || greaterDiagonalInverse.length() >= SIMIOS_LENGHT){
                        return true;
                    }
                } else {
                    String lowerSequence = horizontalSequence[j];
                    lowerDiagonal = getAcumulateLetters(lowerDiagonal, lowerSequence.charAt(z));

                    String upperSequence = horizontalSequence[z];
                    upperDiagonal = getAcumulateLetters(upperDiagonal, upperSequence.charAt(j));

                    String lowerSequenceInverse = horizontalSequence[j];
                    lowerDiagonalInverse = getAcumulateLetters(lowerDiagonalInverse, lowerSequenceInverse.charAt(w));

                    String upperSequenceInverse = horizontalSequence[j - i];
                    upperDiagonalInverse = getAcumulateLetters(upperDiagonalInverse, upperSequenceInverse.charAt(w - i));
                    if (lowerDiagonal.length() >= SIMIOS_LENGHT || upperDiagonal.length() >= SIMIOS_LENGHT ||
                                    lowerDiagonalInverse.length() >= SIMIOS_LENGHT || upperDiagonalInverse.length() >= SIMIOS_LENGHT){
                        return true;
                    }
                }
                z++;
                w--;
            }

            return false;
        });
    }

    private static String getAcumulateLetters(String accumulatedLetters, char currentLetter) {
        char lastLetter = accumulatedLetters.charAt(0);
        return currentLetter == lastLetter ? accumulatedLetters + currentLetter : String.valueOf(currentLetter);
    }

}
