package exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static boolean scrabble(String letters, String word) {
        word = word.toLowerCase();
        String[] letter = letters.split("");
        String[] rightWord = word.split("");

        List<String> listLetter = new ArrayList<>(Arrays.asList(letter));
        List<String> listRightWord = new ArrayList<>(Arrays.asList(rightWord));

        for (String a : listLetter) {
            if (listRightWord.contains(a)) {
                listRightWord.remove(a);
            }
        }
            return listRightWord.isEmpty();
        }
    }

