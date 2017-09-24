package spellchecker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by vagrant on 9/23/17.
 */
public class FileReader {

    public static List<String> fileToList(String fileName) {

        List<String> list = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stream.forEach(x -> list.add(x));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
