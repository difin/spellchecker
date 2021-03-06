package spellchecker;

import static java.lang.Math.min;

/**
 * Created by dfingerman on 9/23/17.
 */
public class LevenshteinDistance {

    public static int distance(String word1, String word2){

        int[][] matrix = new int[word1.length()+2][word2.length()+2];

        for (int i=2; i<matrix.length; i++){
            matrix[i][0] = Character.getNumericValue(word1.charAt(i-2));
        }

        for (int i=2; i<matrix[0].length; i++){
            matrix[0][i] = Character.getNumericValue(word2.charAt(i-2));
        }

        for (int i=1; i<matrix.length; i++){
            matrix[i][1] = i-1;
        }

        for (int i=1; i<matrix[1].length; i++){
            matrix[1][i] = i-1;
        }

        for (int i=2; i<matrix.length; i++){
            for (int j=2; j<matrix[0].length; j++){

                int cost = 1;
                if (matrix[i][0] == matrix[0][j]){
                    cost = 0;
                }

                int costUp = matrix[i-1][j] + 1;
                int costLeft = matrix[i][j-1] + 1;
                int costDiagonal = matrix[i-1][j-1] + cost;

                int finalCost = min(costUp, min(costLeft, costDiagonal));

                matrix[i][j] = finalCost;
            }
        }

        //printMatrix(matrix);

        return matrix[matrix.length-1][matrix[0].length-1];
    }

    private static void printMatrix(int[][] matrix){

        for (int i=0; i<matrix.length; i++){
            for (int j=0; j<matrix[0].length; j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
