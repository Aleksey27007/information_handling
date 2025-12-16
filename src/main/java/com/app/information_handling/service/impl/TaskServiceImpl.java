package com.app.information_handling.service.impl;

import com.app.information_handling.composite.TextComponent;
import com.app.information_handling.composite.TextComposite;
import com.app.information_handling.service.TaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class TaskServiceImpl implements TaskService {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public void swapFirstAndLastLexemes(TextComponent textComponent) {
        logger.info("Start swapFirstAndLastLexemes");
        for (TextComponent paragraph : textComponent.getComponents()) {

            for (TextComponent sentenceComp : paragraph.getComponents()) {
                TextComposite sentence = (TextComposite) sentenceComp;
                List<TextComponent> words = sentence.getComponents();

                if (words.size() < 2) continue;

                TextComponent first = words.get(0);
                TextComponent last = words.get(words.size() - 1);

                words.set(0, last);
                words.set(words.size() - 1, first);
            }
        }
    }

    @Override
    public int findMaxSentenceCountWithSameWords(TextComponent textComponent) {
        logger.info("Start findMaxSentenceCountWithSameWords");
        if (!(textComponent instanceof TextComposite)) return 0;

        Map<String, Set<Integer>> wordToSentences = new HashMap<>();
        int sentenceIndex = 0;

        for (TextComponent paragraph : textComponent.getComponents()) {
            for (TextComponent sentence : paragraph.getComponents()) {

                Set<String> wordsInSentence = extractWords(sentence);

                for (String word : wordsInSentence) {
                    wordToSentences
                            .computeIfAbsent(word.toLowerCase(), k -> new HashSet<>())
                            .add(sentenceIndex);
                }

                sentenceIndex++;
            }
        }

        return wordToSentences.values()
                .stream()
                .mapToInt(Set::size)
                .max()
                .orElse(0);
    }

    private Set<String> extractWords(TextComponent sentence) {
        Set<String> words = new HashSet<>();

        for (TextComponent wordComp : sentence.getComponents()) {
            words.add(wordComp.toString());
        }
        return words;
    }

    @Override
    public List<String> sortSentencesByLexemeCount(TextComponent textComponent) {
        logger.info("Start sortSentencesByLexemeCount");
        List<TextComponent> sentences = new ArrayList<>();

        for (TextComponent paragraph : textComponent.getComponents()) {
            sentences.addAll(paragraph.getComponents());
        }

        sentences.sort(Comparator.comparingInt(this::lexemeCount));

        List<String> result = new ArrayList<>();
        for (TextComponent s : sentences) {
            result.add(s.toString());
        }
        return result;
    }

    private int lexemeCount(TextComponent sentence) {
        return sentence.getComponents().size();
    }
}
