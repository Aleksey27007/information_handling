package com.app.information_handling.parser;

import com.app.information_handling.composite.TextComponent;
import com.app.information_handling.composite.TextComponentType;
import com.app.information_handling.composite.TextComposite;

public class SentenceParser extends TextParser {
    private static final String PARAGRAPH_SEPARATOR_INTO_SENTENCE = "(?<=[.!?])\\s+";

    @Override
    public TextComponent parse(String paragraph) {
        TextComposite paragraphComposite = new TextComposite(TextComponentType.SENTENCE);

        String[] sentences = paragraph.split(PARAGRAPH_SEPARATOR_INTO_SENTENCE);
        for (String sentence : sentences) {
            if (nextParser != null) {
                TextComponent sentenceComponent = nextParser.parse(sentence.strip());
                paragraphComposite.add(sentenceComponent);
            }
        }
        return paragraphComposite;
    }
}
