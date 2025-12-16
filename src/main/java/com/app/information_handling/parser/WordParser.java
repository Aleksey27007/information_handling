package com.app.information_handling.parser;

import com.app.information_handling.composite.TextComponent;
import com.app.information_handling.composite.TextComponentType;
import com.app.information_handling.composite.TextComposite;

public class WordParser extends TextParser {
    public static final String SENTENCE_SEPARATOR_INTO_WORDS = "\\s+";

    @Override
    public TextComponent parse(String sentence) {
        TextComposite sentenceComposite = new TextComposite(TextComponentType.WORD);

        String[] words = sentence.split(SENTENCE_SEPARATOR_INTO_WORDS);
        for (String word : words) {
            if (nextParser != null) {
                TextComponent wordComponent = nextParser.parse(word);
                sentenceComposite.add(wordComponent);
            }
        }
        return sentenceComposite;
    }
}
