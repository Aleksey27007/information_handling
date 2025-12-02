package com.app.information_handling.parser.impl;

import com.app.information_handling.parser.FileParser;
import com.app.information_handling.parser.Regex;

import java.util.Arrays;
import java.util.List;

public class IntoLexemeParserImpl implements FileParser {
    private FileParser nextParser;
    private List<String> lexemes;

    @Override
    public List<String> parse(String text) {
        lexemes = Arrays.stream(text.split(Regex.LEXEME_REGEX.getRegex())).toList();
        return lexemes;
    }

    @Override
    public FileParser getNextParser() {
        return nextParser;
    }

    @Override
    public void setNextParser(FileParser nextParser) {
        this.nextParser = nextParser;
    }

    public List<String> getLexemes() {
        return lexemes;
    }
}

