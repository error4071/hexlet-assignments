package exercise;

import java.util.Map;
import java.util.HashMap;

public class App {
    public static Map<String, Integer> getWordCount(String sentence) {
        String[] splitSentence = sentence.split(" ");
        Map<String, Integer> wordCount = new HashMap<>();

        for (String key : splitSentence) {
            if (sentence.isEmpty()) {
                return new HashMap<>();
            } else if (!wordCount.containsKey(key)) {
                wordCount.put(key, 0);
            }
            wordCount.put(key, wordCount.get(key) + 1);
        }
        return wordCount;
    }

        public static String toString(Map<String, Integer> wordCount) {

            if (wordCount.isEmpty()) {
            return "{}";
                }
            StringBuilder result = new StringBuilder();
            for (Map.Entry<String, Integer> entry : wordCount.entrySet())
                result.append("\n" + "  ").append(entry.getKey()).append(": ").append(entry.getValue().toString());
            return "{" + result + "\n}";
        }
    }
