package spellchecker;

import static java.lang.Integer.max;

/**
 * Created by vagrant on 9/23/17.
 */
public class DistanceMeasurer {

    public static int distance(String word1, String word2){

        int differences = 0;

        for (int i=0; i < max(word1.length(), word2.length()); i++){

            if (i>word1.length()-1 || i>word2.length()-1){
                differences++;
                continue;
            }

            if (word1.charAt(i) != word2.charAt(i)){
                differences++;
            }
        }

        return differences;
    }
}
