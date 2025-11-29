package com.app.information_handling.composite;

import java.util.List;

public abstract class TextComponent {

    public void add(TextComponent textComponent) {
        throw new UnsupportedOperationException();
    }

    public abstract String getText();

    public void print() {
        throw new UnsupportedOperationException();
    }
}
