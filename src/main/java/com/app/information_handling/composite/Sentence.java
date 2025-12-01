package com.app.information_handling.composite;

import java.util.ArrayList;
import java.util.List;

public class Sentence extends TextComponent {
    private List<TextComponent> words = new ArrayList<>();

    public Sentence() {
    }

    @Override
    public String getText() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            result.append(words.get(i).getText());
            if (i < words.size() - 1) {
                result.append(" ");
            }
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