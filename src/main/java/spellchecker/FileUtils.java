package spellchecker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dfingerman on 9/23/17.
 */
public class FileUtils {

    public static List<String> fileToList(String fileName){

        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;

            List<String> lines = new ArrayList<String>();
            while((line = bufferedReader.readLine()) != null){
                lines.add(line.toLowerCase());
            }

            return lines;

        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    public static void createOutputFile(String fileName, Map<String, List<String>> outputMap){

        List<String> outputList = new ArrayList<>();

        for (Map.Entry<String, List<String>> entry : outputMap.entrySet()){
            String outputLine = entry.getKey() + ":" ;

            for (int i=0; i<entry.getValue().size(); i++){

                if (i>0){
                    outputLine = outputLine + ", ";
                }

                outputLine = outputLine + entry.getValue().get(i);
            }

            outputList.add(outputLine);
        }

        if (outputList.size() == 0){
            outputList.add("0");
        }

        try {
            Files.write(Paths.get(fileName), outputList, Charset.forName("UTF-8"), StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
