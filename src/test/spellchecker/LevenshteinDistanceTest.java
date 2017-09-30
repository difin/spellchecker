package spellchecker;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vagrant on 9/23/17.
 */
public class LevenshteinDistanceTest {

    @Test
    public void whenTwoWordsAreItenticalThenSimpleDistanceIsZero(){

        String word1 = "some word";
        String word2 = "some word";

        int result = LevenshteinDistance.distance(word1, word2);

        assertEquals(0, result);
    }

    @Test
    public void whenFirstWordIsPrefixOfSecondWordThenResultIsLengthDifference(){

        String word1 = "some word";
        String word2 = "some word abcd";

        int result = LevenshteinDistance.distance(word1, word2);

        assertEquals(5, result);
    }

    @Test
    public void whenSecondWordIsPrefixOfFirstWordThenResultIsLengthDifference(){

        String word1 = "some word 234567";
        String word2 = "some word";

        int result = LevenshteinDistance.distance(word1, word2);

        assertEquals(7, result);
    }

    @Test
    public void whenWordsHaveSameLengthAndSomeDifferencesThenTheResultIsNumberOfDifferences(){

        String word1 = "some word";
        String word2 = "some abcd";

        int result = LevenshteinDistance.distance(word1, word2);

        assertEquals(3, result);
    }

    @Test
    public void whenWordsHaveDifferenctLengthAndSomeDifferencesThenTheResultIsNumberOfDifferences(){

        String word1 = "some word 1234";
        String word2 = "some abcd";

        int result = LevenshteinDistance.distance(word1, word2);

        assertEquals(8, result);
    }

    @Test
    public void whenBeginningOfWordsIsDifferentThenResultIsCorrect(){

        String word1 = "1234 some word";
        String word2 = "     some word";

        int result = LevenshteinDistance.distance(word1, word2);

        assertEquals(4, result);
    }

    @Test
    public void whenWordsAreCompletelyDifferentThenResultIsCorrect(){

        String word1 = "abcdefgh";
        String word2 = "12345678";

        int result = LevenshteinDistance.distance(word1, word2);

        assertEquals(8, result);
    }

    @Test
    public void whenBeginningAndEndOfWordsIsDifferentThenResultIsCorrect(){

        String word1 = "1234 some word";
        String word2 = "     some word 1234";

        int result = LevenshteinDistance.distance(word1, word2);

        assertEquals(9, result);
    }

    @Test
    public void whenAdditionsAndSubstitutionsAreNeddedThenResultIsCorrect(){

        String word1 = "GAMBOL";
        String word2 = "GUMBO";

        int result = LevenshteinDistance.distance(word1, word2);

        assertEquals(2, result);
    }
}