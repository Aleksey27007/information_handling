package com.app.information_handling.composite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TextLeaf implements TextComponent {
    private static final Logger logger = LogManager.getLogger();
    private final char symbol;
    private final TextComponentType type;

    public TextLeaf(char symbol, TextComponentType type) {
        this.symbol = symbol;
        this.type = type;
    }

    @Override
    public void add(TextComponent component) {
        logger.error("Leaf can't contain children");
        throw new UnsupportedOperationException("Leaf can't contain children");
    }

    @Override
    public void remove(TextComponent component) {
        logger.error("Leaf can't contain children");
        throw new UnsupportedOperationException("Leaf can't contain children");
    }

    @Override
    public int count() {
        return 1;
    }

    @Override
    public List<TextComponent> getComponents() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
