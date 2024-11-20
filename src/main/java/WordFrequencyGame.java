import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String REGEX_BLANK = "\\s+";
    public static final String LINE_BREAK = "\n";
    public static final String ERROR_MESSAGE = "Calculate Error";
    public static final String SPACE = " ";

    public String calculateWordFrequency(String sentence) {
        try {
            List<WordFrequency> wordFrequencyList = calculateFrequencyByWord(sentence);
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

    private List<WordFrequency> calculateFrequencyByWord(String sentence) {
        List<WordFrequency> wordFrequencyList = Arrays.stream(sentence.split(REGEX_BLANK)).map(word -> new WordFrequency(word, 1)).toList();
        Map<String, List<WordFrequency>> wordToWordFrequenciesMap = getListMap(wordFrequencyList);

        return wordToWordFrequenciesMap.entrySet().stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                .sorted((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount())
                .toList();
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencies) {
        return wordFrequencies.stream()
                .collect(Collectors.groupingBy(WordFrequency::getWord));
    }


}
