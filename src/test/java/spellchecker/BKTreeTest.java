package spellchecker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

        List<String> matches = tree.query("bike", 1);

        Assert.assertTrue(matches.size() == 1);
        Assert.assertTrue(matches.iterator().next().equals("biker"));
    }

    @Test
    public void queryWithToleranceTwoAndExpectOneMatch(){

        List<String> matches = tree.query("bright", 2);

        Assert.assertTrue(matches.size() == 1);
        Assert.assertTrue(matches.iterator().next().equals("night"));
    }

    @Test
    public void queryWithToleranceTwoAndExpectThreeMatch(){

        List<String> matches = tree.query("rice", 2);

        Assert.assertTrue(matches.size() == 4);
        Assert.assertTrue(matches.contains("nice"));
        Assert.assertTrue(matches.contains("vise"));
        Assert.assertTrue(matches.contains("mise"));
        Assert.assertTrue(matches.contains("ice"));
    }
}