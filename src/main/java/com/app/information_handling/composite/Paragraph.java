package com.app.information_handling.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Paragraph extends TextComponent{
    private List<TextComponent> sentences = new ArrayList<>(); // содержит предложения
    private String paragraph;

    public Paragraph(String paragraph) {
        this.paragraph = paragraph;
    }

    @Override
    public void add(TextComponent paragraph) {
        sentences.add(paragraph);
    }

    @Override
    public String getText() {
        return paragraph;
    }

    public void setItemComponents() {
        String[] result = paragraph.split("\\.\\\\!\\?");
        for (int i = 0; i < result.length; i++) {
            sentences.add(new Sentence(result[i]));
        }
    }

    @Override
    public void print() {
        setItemComponents();
        Iterator<TextComponent> iterator = sentences.iterator();
        sentences.stream()
                .takeWhile(x -> iterator.hasNext())
                .map(n -> iterator.next())
                .forEach(TextComponent::print);
        System.out.print("\n");
    }
}
