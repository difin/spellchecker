package spellchecker;

import static java.lang.Integer.max;
import static java.lang.Math.min;

/**
 * Created by dfingerman on 9/23/17.
 */
public class LevenshteinDistance {

    public static int distance(String word1, String word2){
        return levenshteinDistance(word1, word2);
    }

    private static int levenshteinDistance(String word1, String word2){

        String[][] matrix = new String[word1.length()+2][word2.length()+2];

        for (int i=0; i<matrix.length; i++){
            for (int j=0; j<matrix[0].length; j++){
                matrix[i][j] = ".";
            }
        }

        for (int i=2; i<matrix.length; i++){
            matrix[i][0] = word1.charAt(i-2) + "";
        }


        for (int i=2; i<matrix[0].length; i++){
            matrix[0][i] = word2.charAt(i-2) + "";
        }

        for (int i=1; i<matrix.length; i++){
            matrix[i][1] = Integer.toString(i-1);
        }

        for (int i=1; i<matrix[1].length; i++){
            matrix[1][i] = Integer.toString(i-1);
        }

        for (int i=2; i<matrix.length; i++){
            for (int j=2; j<matrix[0].length; j++){

                int cost = 1;
                if (matrix[i][0].equals(matrix[0][j])){
                    cost = 0;
                }

                int costUp = Integer.valueOf(matrix[i-1][j]) + 1;
                int costLeft = Integer.valueOf(matrix[i][j-1]) + 1;
                int costDiagonal = Integer.valueOf(matrix[i-1][j-1]) + cost;

                int finalCost = min(costUp, min(costLeft, costDiagonal));

                matrix[i][j] = Integer.toString(finalCost);
            }
        }

        //printMatrix(matrix);

        return Integer.valueOf(matrix[matrix.length-1][matrix[0].length-1]);
    }

    private static void printMatrix(String[][] matrix){

        for (int i=0; i<matrix.length; i++){
            for (int j=0; j<matrix[0].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
