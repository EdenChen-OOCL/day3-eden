import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String REGEX_BLANK = "\\s+";
    public static final String LINE_BREAK = "\n";
    public static final String ERROR_MESSAGE = "Calculate Error";

    // todo extract method
    public String getResult(String sentence) {
        if (sentence.split(REGEX_BLANK).length == 1) {
            return sentence + " 1";
        }
        // todo
        else {
            // todo large try...catch
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split(REGEX_BLANK);

                List<WordFrequency> wordFrequencyList = Arrays.stream(words).map(word -> new WordFrequency(word, 1)).toList();

                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordToWordFrequenciesMap = getListMap(wordFrequencyList);

                // todo tempory variable
                List<WordFrequency> wordFrequencies = wordToWordFrequenciesMap.entrySet().stream()
                        .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                        .collect(Collectors.toList());
                wordFrequencyList = wordFrequencies;

                wordFrequencyList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

                return wordFrequencyList.stream()
                        .map(wordFrequency -> wordFrequency.getWord() + " " + wordFrequency.getWordCount())
                        .collect(Collectors.joining(LINE_BREAK));
            } catch (Exception e) {
                return ERROR_MESSAGE;
            }
        }
    }


    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencies) {
        return wordFrequencies.stream()
                .collect(Collectors.groupingBy(WordFrequency::getWord));
    }


}
