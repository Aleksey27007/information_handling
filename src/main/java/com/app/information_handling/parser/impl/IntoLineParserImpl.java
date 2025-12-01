package com.app.information_handling.parser.impl;

import com.app.information_handling.parser.FileParser;
import com.app.information_handling.parser.Regex;

import java.util.Arrays;
import java.util.List;

public class IntoLineParserImpl implements FileParser {
    private FileParser nextParser;
    private List<String> lines;

    @Override
    public List<String> parse(String text) {
        lines = Arrays.stream(text.split(Regex.LINE_REGEX.getRegex())).toList();
        return lines;
    }

    @Override
    public FileParser getNextParser() {
        return nextParser;
    }

    @Override
    public void setNextParser(FileParser nextParser) {
        this.nextParser = nextParser;
    }

    public List<String> getLines() {
        return lines;
    }
}

