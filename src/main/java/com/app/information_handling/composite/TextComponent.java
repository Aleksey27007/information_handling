package com.app.information_handling.composite;

import java.util.List;

public abstract class TextComponent {
    
    public abstract String getText();
    
    public void add(TextComponent component) {
        throw new UnsupportedOperationException("Method add is not supported for this component");
    }
    
    public void remove(TextComponent component) {
        throw new UnsupportedOperationException("Method remove is not supported for this component");
    }
    
    public List<TextComponent> getChildren() {
        throw new UnsupportedOperationException("Method getChildren is not supported for this component");
    }
}