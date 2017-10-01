package spellchecker;

import org.apache.commons.collections4.ComparatorUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by dfingerman on 01/10/17.
 */
public class VocabularyGenerator {

    private static char[] alphabet = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            '!','@','#','$','%','^','&','*','(',')','-','=', '<',',','>','.',';',':','{','[','}',']'
    };
    private static int maxWordLength = 20;
    private static int vocabularySize = 200000;

    private static List<String> outputList = new ArrayList<>();
    private static String outputFile = "vocab.generated.txt";

    public static void main(String[] args){

        for (int i=0; i<vocabularySize; i++){
            String word = generateWord();
            outputList.add(word);
            System.out.println(outputList.size());
        }

        outputList.sort(ComparatorUtils.naturalComparator());

        FileUtils.writeListToFile(outputFile, outputList);
    }

    private static String generateWord() {

        int wordLength = getRandomNumberInRange(0, maxWordLength);
        String word = "";
        boolean isNewWord = false;

        while (!isNewWord){
            for (int i=0; i<wordLength; i++){
                char letter = alphabet[getRandomNumberInRange(0,alphabet.length)];
                word = word + letter;
            }

            isNewWord = !outputList.contains(word);

            if (!isNewWord){
                wordLength = getRandomNumberInRange(0, maxWordLength);
                word = "";
            }
        }

        return word;
    }

    public static int getRandomNumberInRange(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
