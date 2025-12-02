package com.app.information_handling.parser.impl;

import com.app.information_handling.parser.FileParser;
import com.app.information_handling.parser.Regex;

import java.util.Arrays;
import java.util.List;

public class IntoSentenceParserImpl implements FileParser {
    private FileParser nextParser;
    private int countSentence;
    private List<String> sentences;

    @Override
    public List<String> parse(String text) {
        sentences = Arrays.stream(text.split(Regex.SENTENCE_REGEX.getRegex())).toList();
        return sentences;
    }

    @Override
    public FileParser getNextParser() {
        return nextParser;
    }

    @Override
    public void setNextParser(FileParser nextParser) {
        this.nextParser = nextParser;
    }

    public int getCountSentence() {
        return countSentence + sentences.size() - 1;
    }

    public void setCountSentence(int countSentence) {
        this.countSentence = countSentence;
    }

    public List<String> getSentences() {
        return sentences;
    }

    public void setSentences(List<String> sentences) {
        this.sentences = sentences;
    }
}
