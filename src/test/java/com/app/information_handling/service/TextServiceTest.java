package com.app.information_handling.service;

import com.app.information_handling.composite.Text;
import com.app.information_handling.reader.AppReader;
import com.app.information_handling.reader.impl.AppReaderImpl;
import com.app.information_handling.service.impl.TextServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextServiceTest {

    private static final String fileName = "text.txt";
    private TextService textService;
    private AppReader reader;
    private String projectPath;

    @BeforeEach
    void setUp() {
        textService = new TextServiceImpl();
        reader = new AppReaderImpl();
        projectPath = System.getProperty("user.dir");
    }

    @Test
    void parseTextWithFileContentReturnsNonEmptyText() {
        String input = reader.fileRead(fileName, projectPath);
        Text result = textService.parseText(input);

        assertNotNull(result);
        assertFalse(result.getChildren().isEmpty());
    }

    @Test
    void parseTextWithNullReturnsEmptyText() {
        Text result = textService.parseText(null);

        assertNotNull(result);
        assertTrue(result.getChildren().isEmpty());
    }

    @Test
    void parseTextWithEmptyStringReturnsEmptyText() {
        Text result = textService.parseText("");

        assertNotNull(result);
        assertTrue(result.getChildren().isEmpty());
    }

    @Test
    void restoreTextAfterParsingFileReturnsNonEmptyString() {
        String input = reader.fileRead(fileName, projectPath);
        Text parsed = textService.parseText(input);
        String restored = textService.restoreText(parsed);

        assertNotNull(restored);
        assertFalse(restored.isEmpty());
    }

    @Test
    void restoreTextWithNullReturnsEmptyString() {
        String result = textService.restoreText(null);

        assertEquals("", result);
    }

    @Test
    void parseAndRestoreWithFilePreservesKeyContent() {
        String input = reader.fileRead(fileName, projectPath);
        Text parsed = textService.parseText(input);
        String restored = textService.restoreText(parsed);

        assertNotNull(restored);

        assertTrue(restored.contains("It has survived"));
        assertTrue(restored.contains("Bye."));
    }
}
