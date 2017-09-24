package spellchecker;

import java.util.Arrays;
import java.util.List;

/**
 * Created by vagrant on 9/23/17.
 */
public class SpellChecker {

    public static void main(String[] args){

        String vocabFile = "vocab.txt";
        String sentenceFile = "sentence.txt";
        String maxDistanceFile = "MaxDistance.txt";
        String outputFile = "MisspelledWords.txt";

        List<String> vocabList = FileReader.fileToList(vocabFile);
        List<String> sentenceList = Arrays.asList(FileReader.fileToList(sentenceFile).get(0).split(" "));
        int maxDistance = Integer.valueOf(FileReader.fileToList(maxDistanceFile).get(0));
    }
}
