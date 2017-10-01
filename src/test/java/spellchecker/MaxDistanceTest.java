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
public class MaxDistanceTest {

    private String resourcesDirectoryPath = new File("src/test/resources").getAbsolutePath();

    public void runTest(String testCaseFolder, int maxDistance){

        String testCaseFolderFullPath = resourcesDirectoryPath + "/" + testCaseFolder;
        String outputFile = "myMisspelledWords.txt";

        File outpuFileOnDisk = new File(testCaseFolderFullPath + "/" + outputFile);
        outpuFileOnDisk.delete();

        SpellChecker spellChecker = new SpellChecker(testCaseFolderFullPath, outputFile);
        spellChecker.overrideMaxDistance(maxDistance);
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
    public void maxDistance0(){
        runTest("medium_search_words", 0);
    }

    @Test
    public void maxDistance1(){
        runTest("medium_search_words", 1);
    }

    @Test
    public void maxDistance2(){
        runTest("medium_search_words", 2);
    }

    @Test
    public void maxDistance3(){
        runTest("medium_search_words", 3);
    }

    @Test
    public void maxDistance4(){
        runTest("medium_search_words", 4);
    }

    @Test
    public void maxDistance5(){
        runTest("medium_search_words", 5);
    }
}