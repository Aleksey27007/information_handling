package com.app.information_handling.reader.impl;

import com.app.information_handling.reader.AppReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AppReaderImpl implements AppReader {

    @Override
    public String fileRead(String fileName, String filePath) {
        if (filePath.isBlank() || fileName.isBlank()) {
            throw new NullPointerException("fileName or filePath must not be null or blank");
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
