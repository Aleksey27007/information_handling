package com.app.information_handling.parser;

import com.app.information_handling.composite.TextComponent;

public abstract class TextParser {
    protected TextParser nextParser;
    public void setNextParser(TextParser nextParser) {
        this.nextParser = nextParser;
    }
    public abstract TextComponent parse(String text);
}
