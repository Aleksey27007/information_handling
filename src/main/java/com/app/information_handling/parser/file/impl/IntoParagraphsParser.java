package com.app.information_handling.parser.file.impl;

import com.app.information_handling.parser.file.FileParser;

import java.util.List;

public class IntoParagraphsParser implements FileParser {
    private final String PARAGRAPH_REGEX = "";
    private FileParser nextParser;

    @Override
    public List<String> parse(String text) {
        return null;
    }

    public FileParser getNextParser() {
        return nextParser;
    }

    public void setNextParser(FileParser nextParser) {
        this.nextParser = nextParser;
    }
}
