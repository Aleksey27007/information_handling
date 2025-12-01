package com.app.information_handling.parser.impl;

import com.app.information_handling.parser.FileParser;
import com.app.information_handling.parser.Regex;

import java.util.Arrays;
import java.util.List;

public class IntoSymbolParserImpl implements FileParser {
    private int countSymbol;
    private List<String> symbols;
    @Override
    public List<String> parse(String text) {
        symbols = Arrays.stream(text.split(Regex.SYMBOL_REGEX.getRegex())).toList(); // делает с табуляциями
        return symbols;
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
