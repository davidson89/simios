package br.com.mercadolivre.simios.validator;

import java.util.stream.Stream;

/**
 * Created by davidson on 17/02/19.
 */
public final class SimiosLineColumnValidator {

    private static final int SIMIOS_LENGHT = 4;

    public static final SimiosLineColumnValidator INSTANCE = new SimiosLineColumnValidator();

    private SimiosLineColumnValidator() {}

    public boolean isSimios(String[] horizontalSequence) {

        int length = horizontalSequence.length;
        return Stream.iterate(0, n -> n + 1)
                        .limit(length)
                        .parallel()
                        .anyMatch(n -> {

            String accumulatedLeftLettersLine = "X";
            String accumulatedRightLettersLine = "X";
            String accumulatedUpperLettersColumn = "X";
            String accumulatedLowerLettersColumn = "X";

            for (int i = 0, j = length - 1; i <= j; i++, j--) {
                char leftLetterLine = horizontalSequence[n].charAt(i);
                char rightLetterLine = horizontalSequence[n].charAt(j);

                char upperLetterColumn = horizontalSequence[i].charAt(n);
                char lowerLetterColumn = horizontalSequence[j].charAt(n);

                char lastLeftLetter = accumulatedLeftLettersLine.charAt(0);
                char lastRightLetter = accumulatedRightLettersLine.charAt(0);

                char lastUpperLetter = accumulatedUpperLettersColumn.charAt(0);
                char lastLowerLetter = accumulatedLowerLettersColumn.charAt(0);

                accumulatedLeftLettersLine = getAcumulateLetters(accumulatedLeftLettersLine, leftLetterLine, lastLeftLetter);
                accumulatedUpperLettersColumn = getAcumulateLetters(accumulatedUpperLettersColumn, upperLetterColumn, lastUpperLetter);
                if (accumulatedLeftLettersLine.length() >= SIMIOS_LENGHT || accumulatedUpperLettersColumn.length() >= SIMIOS_LENGHT) {
                    return true;
                }

                accumulatedRightLettersLine = getAcumulateLetters(accumulatedRightLettersLine, rightLetterLine, lastRightLetter);
                accumulatedLowerLettersColumn = getAcumulateLetters(accumulatedLowerLettersColumn, lowerLetterColumn, lastLowerLetter);
                if (accumulatedRightLettersLine.length() >= SIMIOS_LENGHT || accumulatedLowerLettersColumn.length() >= SIMIOS_LENGHT) {
                    return true;
                }
                boolean isLastExecution = j - i <= 1;
                if(isLastExecution) {
                    // quando j - i for igual a '0' é pq está na mesma posição e não devemos considerar um character.
                    int x = j - i == 0 ? -1 : 0;

                    int lenghtAccumulatedLine = accumulatedLeftLettersLine.length() + accumulatedRightLettersLine.length() - x;
                    int lenghtAccumulatedColumn = accumulatedUpperLettersColumn.length() + accumulatedLowerLettersColumn.length() - x;
                    if (lastLeftLetter == lastRightLetter && lenghtAccumulatedLine >= SIMIOS_LENGHT) {
                        return true;
                    } else if (lastUpperLetter == lastLowerLetter && lenghtAccumulatedColumn >= SIMIOS_LENGHT) {
                        return true;
                    }
                }
            }
                return false;
        });

    }

    private static String getAcumulateLetters(String accumulatedLetters, char currentLetter, char lastLetter) {
        return currentLetter == lastLetter ? accumulatedLetters + currentLetter : String.valueOf(currentLetter);
    }
}
