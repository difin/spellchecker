package spellchecker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vagrant on 9/24/17.
 */
public class LinearSearch {

    public static Map<String, List<String>> search(List<String> vocabList, List<String> sentenceList, int maxDistance){

        Map<String, List<String>> corrections = new HashMap<>();

        for (String wordToCheck : sentenceList){

            if (!vocabList.contains(wordToCheck)){

                List<String> wordCorrections = new ArrayList<>();

                for (String wordFromVocab : vocabList){
                    if (DistanceMeasurer.distance(wordToCheck, wordFromVocab) <= maxDistance){
                        wordCorrections.add(wordFromVocab);
                    }
                }

                corrections.put(wordToCheck, wordCorrections);
            }
        }

        return corrections;
    }
}
