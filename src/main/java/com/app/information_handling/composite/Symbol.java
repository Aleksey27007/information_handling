package com.app.information_handling.composite;

public class Symbol extends TextComponent{
    private String symbol;

    public Symbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getText() {
        return symbol;
    }

    @Override
    public void print() {
        System.out.print(symbol);
    }
}
