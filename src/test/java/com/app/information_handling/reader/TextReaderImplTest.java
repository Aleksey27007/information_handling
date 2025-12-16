package com.app.information_handling.reader;

import com.app.information_handling.reader.impl.TextReaderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextReaderImplTest {
    private static final String fileName = "text.txt";
    private static final String nonExistentName = "nonexistent.txt";
    private TextReader textReader;
    private String projectPath;

    @BeforeEach
    void setUp() {
        textReader = new TextReaderImpl();
        projectPath = System.getProperty("user.dir");
    }

    @Test
    void fileReadWithValidFileReturnsNonEmptyContent() {
        String content = textReader.fileRead(fileName, projectPath);
        
        assertNotNull(content);
        assertFalse(content.isBlank());
    }

    @Test
    void fileReadWithValidFileContainsExpectedText() {
        String content = textReader.fileRead(fileName, projectPath);
        
        assertTrue(content.contains("It has survived"));
        assertTrue(content.contains("centuries"));
        assertTrue(content.contains("typesetting"));
    }

    @Test
    void fileReadWithInvalidFileNameThrowsException() {
        assertThrows(RuntimeException.class, () -> textReader.fileRead(nonExistentName, projectPath));
    }

    @Test
    void fileRead_withBlankFileName_throwsException() {
        assertThrows(RuntimeException.class, () -> textReader.fileRead("", projectPath));
    }
}

