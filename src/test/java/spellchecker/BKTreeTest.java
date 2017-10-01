package spellchecker;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dfingerman on 9/29/17.
 */
public class BKTreeTest {

    private BKTree tree;

    @Before
    public void createTree(){
        tree = new BKTree("nice");
        tree.insert("ice");
        tree.insert("vise");
        tree.insert("night");
        tree.insert("mise");
        tree.insert("biker");
        tree.insert("viser");
    }

    @Test
    public void queryWithToleranceOneAndExpectOneMatch(){

        List<Pair<Integer, String>> matches = tree.query("bike", 1);

        Assert.assertTrue(matches.size() == 1);
        Assert.assertTrue(matches.iterator().next().getRight().equals("biker"));
    }

    @Test
    public void queryWithToleranceTwoAndExpectOneMatch(){

        List<Pair<Integer, String>> matches = tree.query("bright", 2);

        Assert.assertTrue(matches.size() == 1);
        Assert.assertTrue(matches.iterator().next().getRight().equals("night"));
    }

    @Test
    public void queryWithToleranceTwoAndExpectThreeMatch(){

        List<Pair<Integer, String>> matches = tree.query("rice", 2);
        List<String> wordCorrectionsList = new ArrayList<>();

        for (Pair<Integer, String> pair : matches){
            wordCorrectionsList.add(pair.getRight());
        }

        Assert.assertTrue(matches.size() == 4);
        Assert.assertTrue(matches.contains("nice"));
        Assert.assertTrue(matches.contains("vise"));
        Assert.assertTrue(matches.contains("mise"));
        Assert.assertTrue(matches.contains("ice"));
    }
}