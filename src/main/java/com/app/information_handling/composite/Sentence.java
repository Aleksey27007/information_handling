package com.app.information_handling.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Sentence extends TextComponent {
    private List<TextComponent> words = new ArrayList<>();
    private String sentence;

    public Sentence(String sentence) {
        this.sentence = sentence;
    }

    @Override
    public String getText() {
        return sentence;
    }

    public void setItemComponents() {
        String[] result = sentence.split(" ");
        for (int i = 0; i < result.length; i++) {
            words.add(new Word(result[i]));
        }
    }

    @Override
    public void print() {
        setItemComponents();
        Iterator<TextComponent> iterator = words.iterator();
        words.stream()
                .takeWhile(x -> iterator.hasNext())
                .map(n -> iterator.next())
                .forEach(TextComponent::print);
    }
}
