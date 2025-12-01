package com.app.information_handling.parser.impl;

import com.app.information_handling.parser.FileParser;
import com.app.information_handling.parser.Regex;

import java.util.Arrays;
import java.util.List;

public class IntoWordParser implements FileParser {
    private FileParser nextParser;
    private int countWord;
    private List<String> words;
    @Override
    public List<String> parse(String text) {
        words = Arrays.stream(text.split(Regex.WORD_REGEX.getRegex())).toList();
        return words;
    }

    public int getCountWord() {
        return countWord + words.size() - 1;
    }

    public void setCountWord(int countWord) {
        this.countWord = countWord;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    @Override
    public FileParser getNextParser() {
        return nextParser;
    }

    @Override
    public void setNextParser(FileParser nextParser) {
        this.nextParser = nextParser;
    }
}