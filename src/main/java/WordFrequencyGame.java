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
    public static final String SPACE = " ";

    public String getResult(String sentence) {
        try {
            List<WordFrequency> wordFrequencyList = initWordFrequencies(sentence);

            wordFrequencyList = calculateFrequencyByWord(wordFrequencyList);

            return formatWordFrequencies(wordFrequencyList);
        } catch (Exception e) {
            return ERROR_MESSAGE;
        }
    }

    private static String formatWordFrequencies(List<WordFrequency> wordFrequencyList) {
        return wordFrequencyList.stream()
                .map(wordFrequency -> wordFrequency.getWord() + SPACE + wordFrequency.getWordCount())
                .collect(Collectors.joining(LINE_BREAK));
    }

    private List<WordFrequency> calculateFrequencyByWord(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> wordToWordFrequenciesMap = getListMap(wordFrequencyList);

        return wordToWordFrequenciesMap.entrySet().stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                .sorted((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount())
                .toList();
    }

    private static List<WordFrequency> initWordFrequencies(String sentence) {
        String[] words = sentence.split(REGEX_BLANK);
        return Arrays.stream(words).map(word -> new WordFrequency(word, 1)).toList();
    }


    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencies) {
        return wordFrequencies.stream()
                .collect(Collectors.groupingBy(WordFrequency::getWord));
    }


}
