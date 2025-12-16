package com.app.information_handling.parser;

import com.app.information_handling.composite.TextComponent;
import com.app.information_handling.composite.TextComponentType;
import com.app.information_handling.composite.TextComposite;

public class ParagraphParser extends TextParser {
    private static final String TEXT_SEPARATOR_INTO_PARAGRAPHS = "(\\r?\\n\\s*){2,}";

    @Override
    public TextComponent parse(String text) {
        TextComposite textComposite = new TextComposite(TextComponentType.PARAGRAPH);

        String[] paragraphs = text.split(TEXT_SEPARATOR_INTO_PARAGRAPHS);
        for (String paragraph : paragraphs) {
            if (nextParser != null) {
                TextComponent paragraphComponent = nextParser.parse(paragraph.strip());
                textComposite.add(paragraphComponent);
            }
        }
        return textComposite;
    }
}
