package spellchecker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by vagrant on 9/29/17.
 */
public class BKTree
{
    private Map<Integer, BKTree> children;
    private String word;

    public BKTree(String word){
        children = new HashMap<>();
        this.word = word;
    }

    public void insert(String neWword){

        int distance = LevenshteinDistance.distance(word, neWword);
        BKTree childTree = getChildTreeByDistance(distance);

        if (childTree == null){
            childTree = new BKTree(neWword);
            children.put(distance, childTree);
        }
        else{
            childTree.insert(neWword);
        }
    }

    private BKTree getChildTreeByDistance(int distance){
        return children.get(distance);
    }

    public Set<String> query(String wordInQuery, int tolerance){

        Set<String> matches = new HashSet<>();
        int distance = LevenshteinDistance.distance(word, wordInQuery);

        if (distance <= tolerance){
            matches.add(word);
        }

        for (Map.Entry<Integer, BKTree> entry : children.entrySet()){
            if (entry.getKey() >= (distance-tolerance) && entry.getKey() <= (distance+tolerance)){
                matches.addAll(entry.getValue().query(wordInQuery, tolerance));
            }
        }

        return matches;
    }
}
