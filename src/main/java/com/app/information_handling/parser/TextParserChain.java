package com.app.information_handling.parser;

import com.app.information_handling.composite.TextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextParserChain {
    private static final Logger logger = LogManager.getLogger();
    private final TextParser firstParser;

    public TextParserChain() {
        ParagraphParser paragraphParser = new ParagraphParser();
        SentenceParser sentenceParser = new SentenceParser();
        WordParser wordParser = new WordParser();
        SymbolParser symbolParser = new SymbolParser();

        paragraphParser.setNextParser(sentenceParser);
        sentenceParser.setNextParser(wordParser);
        wordParser.setNextParser(symbolParser);

        this.firstParser = paragraphParser;
    }

    public TextComponent parse(String text) {
        if (text.isBlank()) {
            logger.warn("Text is blank.");
        }
        return firstParser.parse(text);
    }
}

