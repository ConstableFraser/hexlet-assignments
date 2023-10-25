package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// BEGIN
public class App {
    public static boolean scrabble(String text1, String text2) {
        String arraySymbols = text1.toLowerCase();
        String word = text2.toLowerCase();

        List<String> list1 = new ArrayList<>(Arrays.asList(arraySymbols.split("")));
        List<String> list2 = new ArrayList<>(Arrays.asList(word.split("")));

        for (var item : list2) {
            int count = Collections.frequency(list2, item);
            if (count > Collections.frequency(list1, item)) {
                return false;
            }
        }
        return true;
    }
}
//END
