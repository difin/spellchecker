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
public class SearchWordsLengthTests {

    private String resourcesDirectoryPath = new File("src/test/resources").getAbsolutePath();

    public void runTest(String testCaseFolder){

        String testCaseFolderFullPath = resourcesDirectoryPath + "/" + testCaseFolder;
        String outputFile = "MisspelledWords.txt";

        File outpuFileOnDisk = new File(testCaseFolderFullPath + "/" + outputFile);
        outpuFileOnDisk.delete();

        SpellChecker spellChecker = new SpellChecker(testCaseFolderFullPath, outputFile);
        spellChecker.runSpellCheck();
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
    public void shortSearchWords(){
        runTest("short_search_words");
    }

    @Test
    public void mediumSearchWords(){
        runTest("medium_search_words");
    }

    @Test
    public void longSearchWords(){
        runTest("long_search_words");
    }
}