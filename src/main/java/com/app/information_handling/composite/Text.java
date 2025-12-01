package com.app.information_handling.composite;

import java.util.ArrayList;
import java.util.List;

public class Text extends TextComponent {
    private List<TextComponent> paragraphs = new ArrayList<>();

    public Text() {
    }

    @Override
    public String getText() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < paragraphs.size(); i++) {
            result.append(paragraphs.get(i).getText());
            if (i < paragraphs.size() - 1) {
                result.append("\n");
            }
        }
        return result.toString();
    }
    
    @Override
    public void add(TextComponent component) {
        paragraphs.add(component);
    }
    
    @Override
    public void remove(TextComponent component) {
        paragraphs.remove(component);
    }
    
    @Override
    public List<TextComponent> getChildren() {
        return new ArrayList<>(paragraphs);
    }
}