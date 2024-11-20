import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    // todo extract method
    public String getResult(String sentence) {
        // todo magic value
        if (sentence.split("\\s+").length == 1) {
            return sentence + " 1";
        }
        // todo
        else {
            // todo large try...catch
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split("\\s+");

                List<WordFrequency> wordFrequencyList = new ArrayList<>();
                // todo stream
                for (String word : words) {
                    WordFrequency wordFrequency = new WordFrequency(word, 1);
                    wordFrequencyList.add(wordFrequency);
                }

                //get the map for the next step of sizing the same word
                // todo name
                Map<String, List<WordFrequency>> wordToWordFrequenciesMap = getListMap(wordFrequencyList);

                // todo tempory variable
                List<WordFrequency> wordFrequencies = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequency>> entry : wordToWordFrequenciesMap.entrySet()) {
                    WordFrequency wordFrequency = new WordFrequency(entry.getKey(), entry.getValue().size());
                    wordFrequencies.add(wordFrequency);
                }
                wordFrequencyList = wordFrequencies;

                wordFrequencyList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordFrequency wordFrequency : wordFrequencyList) {
                    String outputLine = wordFrequency.getWord() + " " + wordFrequency.getWordCount();
                    joiner.add(outputLine);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }


    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencies) {
        Map<String, List<WordFrequency>> wordToWordFrequenciesMap = new HashMap<>();
        // todo stream
        for (WordFrequency wordFrequency : wordFrequencies) {
            // todo no use
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            // todo duplicate
            if (!wordToWordFrequenciesMap.containsKey(wordFrequency.getWord())) {
                ArrayList wordFrequencyWithSameWord = new ArrayList<>();
                wordFrequencyWithSameWord.add(wordFrequency);
                wordToWordFrequenciesMap.put(wordFrequency.getWord(), wordFrequencyWithSameWord);
            } else {
                wordToWordFrequenciesMap.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }

        return wordToWordFrequenciesMap;
    }


}
