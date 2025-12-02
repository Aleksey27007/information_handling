package com.app.information_handling.composite;

import java.util.ArrayList;
import java.util.List;

public class Sentence extends TextComponent {
    private List<TextComponent> lexemes = new ArrayList<>();

    public Sentence() {
    }

    @Override
    public String getText() {
        StringBuilder result = new StringBuilder();
        for (TextComponent lexeme : lexemes) {
            result.append(lexeme.getText());
        }
        return result.toString();
    }
    
    @Override
    public void add(TextComponent component) {
        lexemes.add(component);
    }
    
    @Override
    public void remove(TextComponent component) {
        lexemes.remove(component);
    }
    
    @Override
    public List<TextComponent> getChildren() {
        return new ArrayList<>(lexemes);
    }
}