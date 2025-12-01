package com.app.information_handling.parser.impl;

import com.app.information_handling.parser.FileParser;
import com.app.information_handling.parser.Regex;

import java.util.Arrays;
import java.util.List;

public class IntoParagraphParserImpl implements FileParser {
    private FileParser nextParser;
    private int countParagraph;
    private List<String> paragraphs;

    @Override
    public List<String> parse(String text) {
        paragraphs = Arrays.stream(text.split(Regex.PARAGRAPH_REGEX.getRegex())).toList();
        return paragraphs;
    }

    @Override
    public FileParser getNextParser() {
        return nextParser;
    }

    @Override
    public void setNextParser(FileParser nextParser) {
        this.nextParser = nextParser;
    }

    public int getCountParagraph() {
        return countParagraph + paragraphs.size() - 1;
    }

    public void setCountParagraph(int countParagraph) {
        this.countParagraph = countParagraph;
    }

    public List<String> getParagraphs() {
        return paragraphs;
    }
}