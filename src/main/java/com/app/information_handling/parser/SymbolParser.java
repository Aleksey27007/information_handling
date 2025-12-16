package com.app.information_handling.parser;

import com.app.information_handling.composite.TextComponent;
import com.app.information_handling.composite.TextComponentType;
import com.app.information_handling.composite.TextComposite;
import com.app.information_handling.composite.TextLeaf;

public class SymbolParser extends TextParser {
    @Override
    public TextComponent parse(String word) {
        TextComposite wordComposite = new TextComposite(TextComponentType.SYMBOL);

        for (char c : word.toCharArray()) {
            wordComposite.add(new TextLeaf(c, TextComponentType.SYMBOL));
        }

        return wordComposite;
    }
}
