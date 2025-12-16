package com.app.information_handling.service;

import com.app.information_handling.composite.TextComponent;
import com.app.information_handling.parser.TextParserChain;
import com.app.information_handling.reader.TextReader;
import com.app.information_handling.reader.impl.TextReaderImpl;
import com.app.information_handling.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceImplTest {
    private TaskService taskService;
    private TextReader textReader;
    private TextParserChain parserChain;
    private TextComponent textComponent;
    private String projectPath;

    @BeforeEach
    void setUp() {
        taskService = new TaskServiceImpl();
        textReader = new TextReaderImpl();
        parserChain = new TextParserChain();
        projectPath = System.getProperty("user.dir");
        
        String text = textReader.fileRead("text.txt", projectPath);
        textComponent = parserChain.parse(text);
    }

    @Test
    void swapFirstAndLastLexemesWithValidTextPreservesLength() {
        String beforeSwap = textComponent.toString();
        int beforeLength = beforeSwap.length();
        
        taskService.swapFirstAndLastLexemes(textComponent);
        
        String afterSwap = textComponent.toString();
        int afterLength = afterSwap.length();
        
        assertEquals(beforeLength, afterLength);
    }

    @Test
    void findMaxSentenceCountWithSameWordsWithValidTextReturnsPositiveNumber() {
        int result = taskService.findMaxSentenceCountWithSameWords(textComponent);
        
        assertTrue(result > 0);
    }

    @Test
    void findMaxSentenceCountWithSameWordsWithValidTextReturnsExpectedValue() {
        int result = taskService.findMaxSentenceCountWithSameWords(textComponent);
        
        assertTrue(result >= 1);
        assertTrue(result <= 10);
    }

    @Test
    void sortSentencesByLexemeCountWithValidTextReturnsNonEmptyList() {
        List<String> result = taskService.sortSentencesByLexemeCount(textComponent);
        
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void sortSentencesByLexemeCountWithValidTextReturnsSortedList() {
        List<String> result = taskService.sortSentencesByLexemeCount(textComponent);
        
        if (result.size() > 1) {
            for (int i = 0; i < result.size() - 1; i++) {
                String current = result.get(i);
                String next = result.get(i + 1);
                
                int currentCount = countLexemes(current);
                int nextCount = countLexemes(next);
                
                assertTrue(currentCount <= nextCount, 
                    "Sentences should be sorted in non-decreasing order by lexeme count");
            }
        }
    }

    @Test
    void sortSentencesByLexemeCountWithValidTextPreservesAllSentences() {
        int totalSentencesBefore = countTotalSentences(textComponent);
        
        List<String> result = taskService.sortSentencesByLexemeCount(textComponent);
        
        assertEquals(totalSentencesBefore, result.size());
    }

    @Test
    void allMethodsWithTextFromFileWorkCorrectly() {
        assertNotNull(textComponent);
        
        String beforeSwap = textComponent.toString();
        taskService.swapFirstAndLastLexemes(textComponent);
        String afterSwap = textComponent.toString();
        assertNotEquals(beforeSwap, afterSwap);
        
        int maxCount = taskService.findMaxSentenceCountWithSameWords(textComponent);
        assertTrue(maxCount > 0);
        
        List<String> sorted = taskService.sortSentencesByLexemeCount(textComponent);
        assertFalse(sorted.isEmpty());
    }

    private int countLexemes(String sentence) {
        if (sentence == null || sentence.isBlank()) {
            return 0;
        }
        return sentence.trim().split("\\s+").length;
    }

    private int countTotalSentences(TextComponent textComponent) {
        if (textComponent == null) {
            return 0;
        }
        return textComponent.count();
    }
}

