package com.app.information_handling.service.impl;

import com.app.information_handling.app_logger.AppLogger;
import com.app.information_handling.composite.*;
import com.app.information_handling.service.TextTaskService;

import java.util.*;

public class TextTaskServiceImpl implements TextTaskService {

    private static final AppLogger logger = AppLogger.getInstance();

    @Override
    public int findMaxSentenceCountWithSameWord(Text text) {
        logger.info("Calculating maximum sentence count sharing the same word");
        if (text == null || text.getChildren().isEmpty()) {
            logger.warn("Text is null or has no children, returning 0");
            return 0;
        }

        Map<String, Set<Sentence>> wordToSentences = new HashMap<>();
        List<Sentence> sentences = collectSentences(text);

        for (Sentence sentence : sentences) {
            Set<String> uniqueWordsInSentence = extractNormalizedWords(sentence);
            for (String word : uniqueWordsInSentence) {
                wordToSentences
                        .computeIfAbsent(word, k -> new HashSet<>())
                        .add(sentence);
            }
        }

        int max = wordToSentences.values().stream()
                .mapToInt(Set::size)
                .max()
                .orElse(0);

        logger.info("Max sentence count with same word: " + max);
        return max;
    }

    @Override
    public List<Sentence> getSentencesSortedByLexemeCount(Text text) {
        logger.info("Sorting sentences by lexeme count");
        List<Sentence> sentences = collectSentences(text);
        sentences.sort(Comparator.comparingInt(this::getLexemeCount));
        logger.info("Sentences sorted, total: " + sentences.size());
        return sentences;
    }

    @Override
    public Text swapFirstAndLastLexemeInEachSentence(Text text) {
        logger.info("Swapping first and last lexeme in each sentence");
        if (text == null) {
            logger.warn("Text is null, nothing to swap");
        }

        assert text != null;
        List<Paragraph> paragraphs = castList(text.getChildren(), Paragraph.class);
        for (Paragraph paragraph : paragraphs) {
            List<Sentence> sentences = castList(paragraph.getChildren(), Sentence.class);
            for (Sentence sentence : sentences) {
                swapFirstAndLastLexeme(sentence);
            }
        }

        logger.info("Swapping completed");
        return text;
    }

    // Helpers

    private List<Sentence> collectSentences(Text text) {
        List<Sentence> result = new ArrayList<>();
        if (text == null) {
            return result;
        }
        List<Paragraph> paragraphs = castList(text.getChildren(), Paragraph.class);
        for (Paragraph paragraph : paragraphs) {
            List<Sentence> sentences = castList(paragraph.getChildren(), Sentence.class);
            result.addAll(sentences);
        }
        return result;
    }

    private int getLexemeCount(Sentence sentence) {
        if (sentence == null) {
            return 0;
        }
        int count = 0;
        for (TextComponent component : sentence.getChildren()) {
            if (component instanceof Lexeme) {
                count++;
            }
        }
        return count;
    }

    private void swapFirstAndLastLexeme(Sentence sentence) {
        if (sentence == null) {
            return;
        }

        List<TextComponent> children = sentence.getLexemeList();
        int firstIndex = -1;
        int lastIndex = -1;

        for (int i = 0; i < children.size(); i++) {
            if (children.get(i) instanceof Lexeme) {
                firstIndex = i;
                break;
            }
        }

        for (int i = children.size() - 1; i >= 0; i--) {
            if (children.get(i) instanceof Lexeme) {
                lastIndex = i;
                break;
            }
        }

        if (firstIndex >= 0 && lastIndex >= 0 && firstIndex != lastIndex) {
            TextComponent first = children.get(firstIndex);
            TextComponent last = children.get(lastIndex);
            children.set(firstIndex, last);
            children.set(lastIndex, first);
        }
    }

    private Set<String> extractNormalizedWords(Sentence sentence) {
        Set<String> result = new HashSet<>();
        if (sentence == null) {
            return result;
        }

        List<Lexeme> lexemes = castList(sentence.getChildren(), Lexeme.class);
        for (Lexeme lexeme : lexemes) {
            List<Word> words = castList(lexeme.getChildren(), Word.class);
            for (Word word : words) {
                String normalized = normalizeWord(word.getText());
                if (!normalized.isBlank()) {
                    result.add(normalized);
                }
            }
        }

        return result;
    }

    private String normalizeWord(String word) {
        if (word == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (char c : word.toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> castList(List<TextComponent> components, Class<T> type) {
        List<T> result = new ArrayList<>();
        for (TextComponent component : components) {
            if (type.isInstance(component)) {
                result.add((T) component);
            }
        }
        return result;
    }
}

