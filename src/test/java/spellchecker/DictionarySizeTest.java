package spellchecker;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.File;

/**
 * Created by dfingerman on 9/30/17.
 */
public class DictionarySizeTest {

    private String resourcesDirectoryPath = new File("src/test/resources").getAbsolutePath();

    public void runTest(String testCaseFolder, int maxDictionarySize){

        String testCaseFolderFullPath = resourcesDirectoryPath + "/" + testCaseFolder;
        String outputFile = "myMisspelledWords.txt";

        File outpuFileOnDisk = new File(testCaseFolderFullPath + "/" + outputFile);
        outpuFileOnDisk.delete();

        SpellChecker spellChecker = new SpellChecker(testCaseFolderFullPath, outputFile);
        spellChecker.setMaxDictionaryLines(maxDictionarySize);
        spellChecker.runSpellCheck();

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
    public void maxDictionarySize50k(){
        runTest("medium_search_words", 50000);
    }

    @Test
    public void maxDictionarySize100k(){ runTest("medium_search_words", 100000); }

    @Test
    public void maxDictionarySize150k(){
        runTest("medium_search_words", 150000);
    }

    @Test
    public void maxDictionarySize200k(){
        runTest("medium_search_words", 200000);
    }

    @Test
    public void maxDictionarySize250k(){
        runTest("medium_search_words", 250000);
    }

    @Test
    public void maxDictionarySize300k(){
        runTest("medium_search_words", 300000);
    }

    @Test
    public void maxDictionarySize350k(){
        runTest("medium_search_words", 350000);
    }

    @Test
    public void maxDictionarySize400k(){
        runTest("medium_search_words", 400000);
    }
}