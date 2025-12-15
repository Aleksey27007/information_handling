package com.app.information_handling.composite;

import java.util.List;

public interface TextComponent {
    
    void add(TextComponent textComponent);
    void remove(TextComponent textComponent);
    List<TextComponent> getComponents;
    String toString;
}