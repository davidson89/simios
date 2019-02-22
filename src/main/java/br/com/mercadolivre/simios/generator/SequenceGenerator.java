package br.com.mercadolivre.simios.generator;

import java.util.stream.Stream;

public class SequenceGenerator {

    private static final int DIAGONAL_DELIMITER = 3;

    public static final SequenceGenerator INSTANCE = new SequenceGenerator();

    private SequenceGenerator(){}

    public String[] generateVerticalSequence(String[] horizontalSequence) {
        int length = horizontalSequence.length;
        String[] verticalSequence = new String[length];
        Stream.iterate(0, n -> n + 1)
                        .limit(Math.round(((float)length)/2))
                        .forEach( i -> {
                            int j = length - (i + 1);
                            StringBuilder builderI = new StringBuilder();
                            StringBuilder builderJ = new StringBuilder();
                            for (String seq : horizontalSequence) {
                                builderI.append(seq.charAt(i));
                                if (i != j) {
                                    builderJ.append(seq.charAt(j));
                                }
                            }
                            verticalSequence[j] = builderJ.toString();
                            verticalSequence[i] = builderI.toString();
                            builderI.setLength(0);
                            builderJ.setLength(0);

                        });
        return verticalSequence;
    }

    /**
     * A quantidade de diagonais é resolvida pela formula da reta y = a*x + c, onde a = 4 e c = -14.
     * Logo a formula que defini a quantidade de diagonáis é y = 4*x - 14
     *
     * @param horizontalSequence as sequencias horizontais
     * @return todas as diagonáis possíveis que tenham tamanho maior que 4
     */
    public String[] generateDiagonalSequence(String[] horizontalSequence) {

        int horizontalLength = horizontalSequence.length;
        int diagonalsLength = 4*horizontalLength - 14;

        String[] diagonals = new String[diagonalsLength];
        Stream.iterate(0, i -> i + 1)
                        .limit(horizontalLength - DIAGONAL_DELIMITER)
                        .parallel()
                        .forEach(i ->  {

            StringBuilder greaterDiagonal = new StringBuilder();
            StringBuilder greaterDiagonalInverse = new StringBuilder();

            StringBuilder lowerDiagonal = new StringBuilder();
            StringBuilder upperDiagonal = new StringBuilder();
            StringBuilder lowerDiagonalInverse = new StringBuilder();
            StringBuilder upperDiagonalInverse = new StringBuilder();
            int z = 0;
            int w = horizontalLength - 1;
            for (int j = i; j < horizontalLength; j++) {
                if (i == 0) {
                    String sequence = horizontalSequence[j];
                    greaterDiagonal.append(sequence.charAt(z));

                    String sequenceInverse = horizontalSequence[j];
                    greaterDiagonalInverse.append(sequenceInverse.charAt(w));
                } else {
                    String lowerSequence = horizontalSequence[j];
                    lowerDiagonal.append(lowerSequence.charAt(z));

                    String upperSequence = horizontalSequence[z];
                    upperDiagonal.append(upperSequence.charAt(j));

                    String lowerSequenceInverse = horizontalSequence[j];
                    lowerDiagonalInverse.append(lowerSequenceInverse.charAt(w));

                    String upperSequenceInverse = horizontalSequence[j - i];
                    upperDiagonalInverse.append(upperSequenceInverse.charAt(w - i));
                }
                z++;
                w--;
            }
            if (i == 0) {
                diagonals[0] = greaterDiagonal.toString();
                diagonals[1] = greaterDiagonalInverse.toString();
            } else {
                int index = (i * 4) - 2;
                diagonals[index++] = lowerDiagonal.toString();
                diagonals[index++] = upperDiagonal.toString();

                diagonals[index++] = lowerDiagonalInverse.toString();
                diagonals[index] = upperDiagonalInverse.toString();
            }
        });
        return diagonals;
    }

}
