package com.app.information_handling.parser;

public enum Regex {
    PARAGRAPH_REGEX("(?=\\n\\t)"),
    LINE_REGEX("\\n"),
    SENTENCE_REGEX("(?<=[.!?])\\s+"),
    WORD_REGEX("\\s+"),
    SYMBOL_REGEX("");

    final String regex;

    Regex(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
