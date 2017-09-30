package spellchecker;

import static java.lang.Integer.max;
import static java.lang.Math.min;

/**
 * Created by vagrant on 9/23/17.
 */
public class LevenshteinDistance {

    public static int distance(String word1, String word2){
        return levenshteinDistance(word1, word2);
    }

    private static int levenshteinDistance(String word1, String word2){

        char[][] matrix = new char[word1.length()+2][word2.length()+2];

        for (int i=0; i<matrix.length; i++){
            for (int j=0; j<matrix[0].length; j++){
                matrix[i][j] = '.';
            }
        }

        for (int i=2; i<matrix.length; i++){
            matrix[i][0] = word1.charAt(i-2);
        }


        for (int i=2; i<matrix[0].length; i++){
            matrix[0][i] = word2.charAt(i-2);
        }

        for (int i=1; i<matrix.length; i++){
            matrix[i][1] = Character.forDigit(i-1, 10);
        }

        for (int i=1; i<matrix[1].length; i++){
            matrix[1][i] = Character.forDigit(i-1, 10);
        }

        for (int i=2; i<matrix.length; i++){
            for (int j=2; j<matrix[0].length; j++){

                int cost = 1;
                if (matrix[i][0] == matrix[0][j]){
                    cost = 0;
                }

                int costUp = Character.getNumericValue(matrix[i-1][j]) + 1;
                int costLeft = Character.getNumericValue(matrix[i][j-1]) + 1;
                int costDiagonal = Character.getNumericValue(matrix[i-1][j-1]) + cost;

                int finalCost = min(costUp, min(costLeft, costDiagonal));

                matrix[i][j] = Character.forDigit(finalCost, 10);
            }
        }

        //printMatrix(matrix);

        return Character.getNumericValue(matrix[matrix.length-1][matrix[0].length-1]);
    }

    private static void printMatrix(char[][] matrix){

        for (int i=0; i<matrix.length; i++){
            for (int j=0; j<matrix[0].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
