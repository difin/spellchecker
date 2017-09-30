package spellchecker;

import org.apache.commons.lang3.time.StopWatch;

import java.util.*;

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

            if (!vocabList.contains(wordToCheck)){

                List<String> wordCorrections = bkTree.query(wordToCheck, maxDistance);
                corrections.put(wordToCheck, wordCorrections);
            }
        }

        timer.stop();
        System.out.println("BK Tree querying time: " + timer.toString());

        return corrections;
    }
}
