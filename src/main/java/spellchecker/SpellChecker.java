package spellchecker;

import org.apache.commons.lang3.time.StopWatch;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.lang.Math.min;

/**
 * Created by dfingerman on 9/23/17.
 */
public class SpellChecker {

    private String vocabFile;
    private String sentenceFile;
    private String maxDistanceFile;
    private String outputFile;

    private Map<String, List<String>> linearSearchOutput;
    private Map<String, List<String>> bktreeSearchOutput;

    private List<String> vocabList;
    private List<String> sentenceList;
    private int maxDistance;

    // These variables are used for tests
    private int overridenMaxDistance;
    private int maxDictionaryLines;

    public SpellChecker(){
        vocabFile = "vocab.txt";
        sentenceFile = "sentence.txt";
        maxDistanceFile = "MaxDistance.txt";
        outputFile = "MisspelledWords.txt";
        overridenMaxDistance = -1;
        maxDictionaryLines = -1;
    }

    public SpellChecker(String directory, String outputFile){
        this.vocabFile = directory + "/" + "vocab.txt";
        this.sentenceFile = directory + "/" + "sentence.txt";
        this.maxDistanceFile = directory + "/" + "MaxDistance.txt";
        this.outputFile = directory + "/" + outputFile;
        overridenMaxDistance = -1;
        maxDictionaryLines = -1;

    }

    public static void main(String[] args) throws IOException {

        SpellChecker spellChecker = new SpellChecker();
        spellChecker.runSpellCheck();
    }

    public void runSpellCheck(){
        readFilesIntoMemory();
        runLinearSearch();
        runBKTreeSearch();
        verifyOutputEquality(linearSearchOutput, bktreeSearchOutput);
        createOutputFile(linearSearchOutput);
    }

    private void readFilesIntoMemory(){

        StopWatch timer = new StopWatch();
        timer.start();

        vocabList = FileUtils.fileToList(vocabFile);
        sentenceList = Arrays.asList(FileUtils.fileToList(sentenceFile).get(0).split(" "));
        maxDistance = Integer.valueOf(FileUtils.fileToList(maxDistanceFile).get(0));

        if (maxDistance < 0 || maxDistance > 5){
            throw new RuntimeException("Max distance is not in range [0,5]");
        }

        timer.stop();
        System.out.println("Files reading time: " + timer.toString());

        if (overridenMaxDistance != -1){
            maxDistance = overridenMaxDistance;
        }

        if (maxDictionaryLines != -1){
            vocabList = vocabList.subList(0, min(maxDictionaryLines, vocabList.size()));
        }
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

    public static void verifyOutputEquality(Map<String, List<String>> output1, Map<String, List<String>> output2){

        StopWatch timer = new StopWatch();
        timer.start();

        assert output1.size() == output2.size();

        for (Map.Entry<String, List<String>> output1Entry : output1.entrySet()){

            String mispelledWord = output1Entry.getKey();
            List<String> correctionsOutput1 = output1Entry.getValue();

            assert output2.containsKey(mispelledWord);

            List<String> correctionsOutput2 = output2.get(mispelledWord);

            assert correctionsOutput2.size() == correctionsOutput1.size();
            assert correctionsOutput2.containsAll(correctionsOutput1);
        }

        timer.stop();
        System.out.println("Checking time that 2 outputs are equal: " + timer.toString());
    }

    private void createOutputFile(Map<String, List<String>> outputMap){

        StopWatch timer = new StopWatch();
        timer.start();

        FileUtils.createSpellCheckingOutputFile(outputFile, outputMap);

        timer.stop();
        System.out.println("Output file writing time: " + timer.toString());
    }

    public void overrideMaxDistance(int maxDistance){
        overridenMaxDistance = maxDistance;
    }

    public void setMaxDictionaryLines(int maxLines){
        maxDictionaryLines = maxLines;
    }
}