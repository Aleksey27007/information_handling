package com.app.information_handling.parser.file;

public enum Regex {
    PARAGRAPH_REGEX(""),
    SENTENCE_REGEX(""),
    WORD_REGEX(""),
    SYMBOL_REGEX("");

    final String regex;

    Regex(String regex) {
        this.regex = regex;
    }
}
