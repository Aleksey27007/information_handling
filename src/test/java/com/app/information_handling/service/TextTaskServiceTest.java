package com.app.information_handling.service;

import com.app.information_handling.composite.*;
import com.app.information_handling.reader.AppReader;
import com.app.information_handling.reader.impl.AppReaderImpl;
import com.app.information_handling.service.impl.TextServiceImpl;
import com.app.information_handling.service.impl.TextTaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextTaskServiceTest {

    private static final String fileName = "text.txt";
    private TextService textService;
    private TextTaskService textTaskService;
    private AppReader reader;
    private String projectPath;

    @BeforeEach
    void setUp() {
        textService = new TextServiceImpl();
        textTaskService = new TextTaskServiceImpl();
        reader = new AppReaderImpl();
        projectPath = System.getProperty("user.dir");
    }

    @Test
    void findMaxSentenceCountWithSameWord() {
        String input = reader.fileRead(fileName, projectPath);
        Text text = textService.parseText(input);

        int max = textTaskService.findMaxSentenceCountWithSameWord(text);

        assertEquals(6, max);
    }

    @Test
    void getSentencesSortedByLexemeCountAndIsNonDecreasing() {
        String input = reader.fileRead(fileName, projectPath);
        Text text = textService.parseText(input);

        List<Sentence> sorted = textTaskService.getSentencesSortedByLexemeCount(text);

        assertFalse(sorted.isEmpty());

        int prevLexemeCount = -1;
        for (Sentence sentence : sorted) {
            int currentCount = countLexemes(sentence);
            assertTrue(currentCount >= prevLexemeCount);
            prevLexemeCount = currentCount;
        }
    }

    @Test
    void swapFirstAndLastLexemeInEachSentencePreservesLengthAndChangesOrder() {
        String input = reader.fileRead(fileName, projectPath);

        Text original = textService.parseText(input);
        Text toModify = textService.parseText(input);

        String before = original.getText();
        Text modified = textTaskService.swapFirstAndLastLexemeInEachSentence(toModify);
        String after = modified.getText();
        System.out.println(before);
        System.out.println(after);

        assertEquals(before.length(), after.length());
        assertNotEquals(before, after);
    }

    private int countLexemes(Sentence sentence) {
        int count = 0;
        for (TextComponent component : sentence.getChildren()) {
            if (component instanceof Lexeme) {
                count++;
            }
        }
        return count;
    }
}


