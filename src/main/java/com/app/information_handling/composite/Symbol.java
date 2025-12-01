package com.app.information_handling.composite;

public class Symbol extends TextComponent {
    private char symbol;

    public Symbol(char symbol) {
        this.symbol = symbol;
    }
    
    public Symbol(String symbol) {
        if (symbol != null && !symbol.isEmpty()) {
            this.symbol = symbol.charAt(0);
        } else {
            this.symbol = ' ';
        }
    }

    @Override
    public String getText() {
        return String.valueOf(symbol);
    }
    
    public char getSymbol() {
        return symbol;
    }
}