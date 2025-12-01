package com.app.information_handling.reader.impl;

import com.app.information_handling.reader.AppReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AppReaderImpl implements AppReader {

    private final Path baseDirectory;

    public AppReaderImpl(Path baseDirectory) {
        if (baseDirectory == null) {
            throw new NullPointerException("baseDirectory must not be null");
        }
        this.baseDirectory = baseDirectory;
    }

    @Override
    public String fileRead(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            throw new NullPointerException("fileName must not be null or blank");
        }
        Path absolutePath = baseDirectory.resolve(fileName);
        final String content;
        try {
            content = Files.readString(absolutePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content;
    }
}
