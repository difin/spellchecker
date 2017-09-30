package spellchecker;

import org.apache.commons.lang3.time.StopWatch;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by dfingerman on 9/23/17.
 */
public class SpellChecker {

    private String vocabFile = "vocab.txt";
    private String sentenceFile = "sentence.txt";
    private String maxDistanceFile = "MaxDistance.txt";
    private String outputFile = "MisspelledWords.txt";

    private Map<String, List<String>> linearSearchOutput;
    private Map<String, List<String>> bktreeSearchOutput;

    private List<String> vocabList;
    private List<String> sentenceList;
    private int maxDistance;

    public SpellChecker(String vocabFile, String sentenceFile, String maxDistanceFile, String outputFile){
        this.vocabFile = vocabFile;
        this.sentenceFile = sentenceFile;
        this.maxDistance = maxDistance;
        this.outputFile = outputFile;
    }

    public SpellChecker(){}

    public static void main(String[] args) throws IOException {

        SpellChecker spellChecker = new SpellChecker();
        spellChecker.runSpellCheck();
    }

    public void runSpellCheck(){
        readFilesIntoMemory();
        runLinearSearch();
        runBKTreeSearch();
        verifyOutputEquality();
        createOutputFile(linearSearchOutput);
    }

    private void readFilesIntoMemory(){

        StopWatch timer = new StopWatch();
        timer.start();

        vocabList = FileUtils.fileToList(vocabFile);
        sentenceList = Arrays.asList(FileUtils.fileToList(sentenceFile).get(0).split(" "));
        maxDistance = Integer.valueOf(FileUtils.fileToList(maxDistanceFile).get(0));

        timer.stop();
        System.out.println("Files reading time: " + timer.toString());

        timer.reset();
        timer.start();
    }

    private void runLinearSearch(){

        StopWatch timer = new StopWatch();
        timer.start();

        linearSearchOutput = LinearSearch.search(vocabList, sentenceList, maxDistance);

        timer.stop();
        System.out.println("Linear search time: " + timer.toString());
    }

    private void runBKTreeSearch(){

        bktreeSearchOutput = BKTreeSearch.search(vocabList, sentenceList, maxDistance);
    }

    private void verifyOutputEquality(){

        StopWatch timer = new StopWatch();
        timer.start();

        assert linearSearchOutput.size() == bktreeSearchOutput.size();

        for (Map.Entry<String, List<String>> linearSearchEntry : linearSearchOutput.entrySet()){

            String mispelledWord = linearSearchEntry.getKey();
            List<String> correctionsLinearSearch = linearSearchEntry.getValue();

            assert bktreeSearchOutput.containsKey(mispelledWord);

            List<String> correctionsBKTree = bktreeSearchOutput.get(linearSearchEntry.getKey());

            assert correctionsBKTree.size() == correctionsLinearSearch.size();
            assert correctionsBKTree.containsAll(correctionsLinearSearch);
        }

        timer.stop();
        System.out.println("Checking time that 2 outputs are equal: " + timer.toString());
    }

    private void createOutputFile(Map<String, List<String>> outputMap){

        StopWatch timer = new StopWatch();
        timer.start();

        FileUtils.createOutputFile(outputFile, outputMap);

        timer.stop();
        System.out.println("Output file writing time: " + timer.toString());
    }
}