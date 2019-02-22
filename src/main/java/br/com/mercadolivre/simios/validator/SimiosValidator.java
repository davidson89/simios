package br.com.mercadolivre.simios.validator;

/**
 * Created by davidson on 17/02/19.
 */
public final class SimiosValidator {

    private SimiosValidator() {}

    private static final int SIMIOS_LENGHT = 4;

    public static boolean isSimios(String sequence) {
        String accumulatedLeftLetters = "X";
        String accumulatedRightLetters = "X";
        for (int i = 0, j = sequence.length() - 1; i <= j; i++,j--) {
            char leftLetter = sequence.charAt(i);
            char rightLetter = sequence.charAt(j);

            char lastLeftLetter = accumulatedLeftLetters.charAt(0);
            char lastRightLetter = accumulatedRightLetters.charAt(0);

            accumulatedLeftLetters = getAcumulateLetters(accumulatedLeftLetters, leftLetter, lastLeftLetter);
            if (accumulatedLeftLetters.length() >= SIMIOS_LENGHT) {
                return true;
            }

            accumulatedRightLetters = getAcumulateLetters(accumulatedRightLetters, rightLetter, lastRightLetter);
            if (accumulatedRightLetters.length() >= SIMIOS_LENGHT) {
                return true;
            }
            boolean isLastExecution = j - i <= 1;
            if(isLastExecution && lastLeftLetter == lastRightLetter) {
                // quando j - i for igual a '0' é pq está na mesma posição e não devemos considerar um character.
                int x = j - i == 0 ? -1 : 0;
                return accumulatedLeftLetters.length() + accumulatedRightLetters.length() - x >= SIMIOS_LENGHT;
            }
        }
        return false;
    }

    private static String getAcumulateLetters(String accumulatedLetters, char currentLetter, char lastLetter) {
        return currentLetter == lastLetter ? accumulatedLetters + currentLetter : String.valueOf(currentLetter);
    }
}
