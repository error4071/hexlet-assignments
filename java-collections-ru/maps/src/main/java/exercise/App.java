package exercise;

public class App {
    public static Map<String, Integer> getWordCount(String sentence) {

        String[] splitSentence = sentence.split("");

        Map<String, Integer> wordsCount = new HashMap<>();

        for (String x : splitSentence) {
            if (!wordsCount.containsKey(x)) {
                wordsCount.put(x, 0);
            }
            return wordsCount;
        }
    }

        private static String toString(Map<String, Integer> wordsCount) {
            for (Map.Entry<String, Integer> entry: wordsCount.entrySet()) {
                System.out.print("{  " + entry.getValue() + ":  " + entry.getKey() + "  }");
            }
        }
    }
