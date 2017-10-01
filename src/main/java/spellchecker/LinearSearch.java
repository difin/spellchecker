package spellchecker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dfingerman on 9/24/17.
 */
public class LinearSearch {

    public static Map<String, List<String>> search(List<String> vocabList, List<String> sentenceList, int maxDistance){

        Map<String, List<String>> corrections = new HashMap<>();

        for (String wordToCheck : sentenceList){

            List<String> wordCorrections = new ArrayList<>();
            boolean existsInVocabulary = false;

            for (String wordFromVocab : vocabList){

                int distance = LevenshteinDistance.distance(wordToCheck, wordFromVocab);

                if (distance == 0){
                    existsInVocabulary = true;
                }
                else if (distance <= maxDistance){
                    wordCorrections.add(wordFromVocab);
                }
            }

            if (!existsInVocabulary){
                corrections.put(wordToCheck, wordCorrections);
            }
        }

        return corrections;
    }
}
