package com.app.information_handling.parser.impl;

import com.app.information_handling.parser.FileParser;
import com.app.information_handling.parser.Regex;

import java.util.Arrays;
import java.util.List;

public class IntoSymbolParserImpl implements FileParser {
    private FileParser nextParser;
    private int countSymbol;
    private List<String> symbols;
    
    @Override
    public List<String> parse(String text) {
        symbols = Arrays.stream(text.split(Regex.SYMBOL_REGEX.getRegex())).toList();
        return symbols;
    }

    @Override
    public FileParser getNextParser() {
        return nextParser;
    }

    @Override
    public void setNextParser(FileParser nextParser) {
        this.nextParser = nextParser;
    }

    public int getCountSymbol() {
        return countSymbol + symbols.size() - 1;
    }

    public void setCountSymbol(int countSymbol) {
        this.countSymbol = countSymbol;
    }

    public List<String> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<String> symbols) {
        this.symbols = symbols;
    }
}