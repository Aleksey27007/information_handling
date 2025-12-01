package com.app.information_handling.composite;

import java.util.ArrayList;
import java.util.List;

public class Paragraph extends TextComponent {
    private List<TextComponent> sentences = new ArrayList<>();

    public Paragraph() {
    }

    @Override
    public String getText() {
        StringBuilder result = new StringBuilder();
        for (TextComponent component : sentences) {
            result.append(component.getText());
        }
        return result.toString();
    }
    
    @Override
    public void add(TextComponent component) {
        sentences.add(component);
    }
    
    @Override
    public void remove(TextComponent component) {
        sentences.remove(component);
    }
    
    @Override
    public List<TextComponent> getChildren() {
        return new ArrayList<>(sentences);
    }
}