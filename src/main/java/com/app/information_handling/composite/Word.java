package com.app.information_handling.composite;

import java.util.ArrayList;
import java.util.List;

public class Word extends TextComponent {
    private List<TextComponent> symbols = new ArrayList<>();

    public Word() {
    }

    @Override
    public String getText() {
        StringBuilder result = new StringBuilder();
        for (TextComponent symbol : symbols) {
            result.append(symbol.getText());
        }
        return result.toString();
    }
    
    @Override
    public void add(TextComponent component) {
        symbols.add(component);
    }
    
    @Override
    public void remove(TextComponent component) {
        symbols.remove(component);
    }
    
    @Override
    public List<TextComponent> getChildren() {
        return new ArrayList<>(symbols);
    }
}