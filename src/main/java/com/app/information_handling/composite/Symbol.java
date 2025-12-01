package com.app.information_handling.composite;

public class Symbol extends TextComponent{
    private String symbol;

    public Symbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getChild() {
        return symbol;
    }
}
