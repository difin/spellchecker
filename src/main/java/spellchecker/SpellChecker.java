package spellchecker;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by vagrant on 9/23/17.
 */
public class SpellChecker {

    public static void main(String[] args) throws IOException {

        String vocabFile = "vocab.txt";
        String sentenceFile = "sentence.txt";
        String maxDistanceFile = "MaxDistance.txt";
        String outputFile = "MisspelledWords.txt";

        List<String> vocabList = FileUtils.fileToList(vocabFile);
        List<String> sentenceList = Arrays.asList(FileUtils.fileToList(sentenceFile).get(0).split(" "));
        int maxDistance = Integer.valueOf(FileUtils.fileToList(maxDistanceFile).get(0));

        Map<String, List<String>> outputMap = LinearSearch.search(vocabList, sentenceList, maxDistance);
        FileUtils.createOutputFile(outputFile, outputMap);
    }
}