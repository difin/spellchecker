package spellchecker;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dfingerman on 9/30/17.
 */
public class SpellCheckerTest {

    private String resourcesDirectoryPath = new File("src/test/resources").getAbsolutePath();

    private Map<String, List<String>> readOutputFromFile(String directory, String fileName){
        String outputFile = directory + "/" + fileName;
        Map<String, List<String>> output = new HashMap<>();

        List<String> lines = FileUtils.fileToList(outputFile);

        for (String line : lines){
            String mispelledWord = line.split(":")[0];
            List<String> corrections = new ArrayList<>();
            String correctionsAsString = null;

            if (line.trim().split(":").length > 1){
                correctionsAsString = line.trim().split(":")[1].trim();
            }

            if (correctionsAsString != null && !correctionsAsString.isEmpty()){
                for (String correction : line.split(":")[1].split(",")){
                    corrections.add(correction.trim());
                }
            }

            output.put(mispelledWord, corrections);
        }

        return output;
    }

    public void runSpellCheckingOnGivenTestCase(String testCaseFolder){

        String testCaseFolderFullPath = resourcesDirectoryPath + "/" + testCaseFolder;
        String outputFile = "myMisspelledWords.txt";
        String referenceOutputFile = "MisspelledWords.txt";

        SpellChecker spellChecker = new SpellChecker(testCaseFolderFullPath, outputFile, referenceOutputFile);
        spellChecker.runSpellCheck();

        SpellChecker.verifyOutputEquality(
                readOutputFromFile(testCaseFolderFullPath, outputFile),
                readOutputFromFile(testCaseFolderFullPath, referenceOutputFile)
        );

        new File(testCaseFolderFullPath + "/" + outputFile).delete();
    }

    @Test
    public void testCase1(){
        runSpellCheckingOnGivenTestCase("testcase1");
    }

    @Test
    public void testCase2(){
        runSpellCheckingOnGivenTestCase("testcase2");
    }

    @Test
    public void testCase3(){
        runSpellCheckingOnGivenTestCase("testcase3");
    }

    @Test
    public void testCase4(){
        runSpellCheckingOnGivenTestCase("testcase4");
    }
}