package spellchecker;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dfingerman on 9/24/17.
 */
public class BKTreeSearch {

    public static Map<String, List<String>> search(List<String> vocabList, List<String> sentenceList, int maxDistance){

        StopWatch timer = new StopWatch();
        timer.start();

        Map<String, List<String>> corrections = new HashMap<>();
        BKTree bkTree = new BKTree(vocabList.get(0));

        for (int i=1; i<vocabList.size(); i++) {
            String vocabWord = vocabList.get(i);
            bkTree.insert(vocabWord);
        }

        timer.stop();
        System.out.println("BK Tree construction time: " + timer.toString());

        timer.reset();
        timer.start();

        for (String wordToCheck : sentenceList){

            List<Pair<Integer, String>> wordCorrectionsPairs = bkTree.query(wordToCheck, maxDistance);
            List<String> wordCorrectionsList = new ArrayList<>();
            boolean existsInVocabulary = false;

            for (Pair<Integer, String> pair : wordCorrectionsPairs){
                if (pair.getLeft() == 0){
                    existsInVocabulary = true;
                }
                else{
                    wordCorrectionsList.add(pair.getRight());
                }
            }

            if (!existsInVocabulary){
                corrections.put(wordToCheck, wordCorrectionsList);
            }
        }

        timer.stop();
        System.out.println("BK Tree querying time: " + timer.toString());

        return corrections;
    }
}
