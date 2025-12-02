package com.app.information_handling.composite;

import java.util.ArrayList;
import java.util.List;

public class Lexeme extends TextComponent {
    private List<TextComponent> words = new ArrayList<>();

    public Lexeme() {
    }

    @Override
    public String getText() {
        StringBuilder result = new StringBuilder();
        for (TextComponent word : words) {
            result.append(word.getText());
        }
        return result.toString();
    }
    
    @Override
    public void add(TextComponent component) {
        words.add(component);
    }
    
    @Override
    public void remove(TextComponent component) {
        words.remove(component);
    }
    
    @Override
    public List<TextComponent> getChildren() {
        return new ArrayList<>(words);
    }
}

