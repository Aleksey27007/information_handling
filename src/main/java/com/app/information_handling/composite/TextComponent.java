package com.app.information_handling.composite;

import java.util.List;

public abstract class TextComponent {
    
    public abstract String getText();
    
    public void add(TextComponent component) {
        throw new UnsupportedOperationException("Метод add не поддерживается для этого компонента");
    }
    
    public void remove(TextComponent component) {
        throw new UnsupportedOperationException("Метод remove не поддерживается для этого компонента");
    }
    
    public List<TextComponent> getChildren() {
        throw new UnsupportedOperationException("Метод getChildren не поддерживается для этого компонента");
    }
}