package com.app.information_handling.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Word extends TextComponent{
    private List<TextComponent> symbols = new ArrayList<>();
    private String word;

    public Word(String symbol) {
        this.word = symbol;
    }

    @Override
    public String getText() {
        return word;
    }

    public void setItemComponents() {
        String[] result = word.split("");
        for (int i = 0; i < result.length; i++) {
            symbols.add(new Symbol(result[i]));
        }
    }
    @Override
    public void print() {
        setItemComponents();
        Iterator<TextComponent> iterator = symbols.iterator();
        symbols.stream()
                .takeWhile(x -> iterator.hasNext())
                .map(n -> iterator.next())
                .forEach(TextComponent::print);
        System.out.print(" ");
    }
}
