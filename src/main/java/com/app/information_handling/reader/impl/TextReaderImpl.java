package com.app.information_handling.reader.impl;

import com.app.information_handling.reader.TextReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextReaderImpl implements TextReader {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String fileRead(String fileName, String filePath) {
        if (filePath.isBlank() || fileName.isBlank()) {
            logger.warn("fileName or filePath must not be null or blank");
        }
        final String content;
        String absolutePath = filePath + "\\" + fileName;
        try {
            content = Files.readString(Path.of(absolutePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content;
    }
}
