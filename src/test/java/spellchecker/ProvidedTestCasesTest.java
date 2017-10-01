package spellchecker;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dfingerman on 9/30/17.
 */
public class ProvidedTestCasesTest {

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

    public void runTest(String testCaseFolder){

        String testCaseFolderFullPath = resourcesDirectoryPath + "/" + testCaseFolder;
        String outputFile = "myMisspelledWords.txt";
        String referenceOutputFile = "MisspelledWords.txt";

        File outpuFileOnDisk = new File(testCaseFolderFullPath + "/" + outputFile);
        outpuFileOnDisk.delete();

        SpellChecker spellChecker = new SpellChecker(testCaseFolderFullPath, outputFile);
        spellChecker.runSpellCheck();

        SpellChecker.verifyOutputEquality(
                readOutputFromFile(testCaseFolderFullPath, outputFile),
                readOutputFromFile(testCaseFolderFullPath, referenceOutputFile)
        );

        outpuFileOnDisk.delete();
    }

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            System.out.println("-------------------------------------------------------");
            System.out.println("\tStarting test: " + description.getMethodName());
            System.out.println("-------------------------------------------------------");
        }
    };

    @Test
    public void testCase1(){
        runTest("testcase1");
    }

    @Test
    public void testCase2(){
        runTest("testcase2");
    }

    @Test
    public void testCase3(){
        runTest("testcase3");
    }

    @Test
    public void testCase4(){
        runTest("testcase4");
    }
}