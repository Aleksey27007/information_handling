package com.app.information_handling.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Text extends TextComponent{
    private List<TextComponent> paragraphs = new ArrayList<>(); // количество обзацев
    private String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public void add(TextComponent paragraph) {
        paragraphs.add(paragraph);
    }

    @Override
    public String getText() {
        return text; // выводит весь текст
    }

    public void setItemComponents() {
        String[] result = text.split("\n");
        for (int i = 0; i < result.length; i++) {
            paragraphs.add(new Paragraph(result[i]));
        }
    }

    @Override
    public void print() {
        setItemComponents();
        Iterator<TextComponent> iterator = paragraphs.iterator();
        paragraphs.stream()
                .takeWhile(x -> iterator.hasNext())
                .map(n -> iterator.next())
                .forEach(TextComponent::print);
    }
}
