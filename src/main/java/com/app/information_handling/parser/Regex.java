package com.app.information_handling.parser;

public enum Regex {
    PARAGRAPH_REGEX("\\t"),
    SENTENCE_REGEX("\\.\\\\!\\?"),
    WORD_REGEX("\\ "),
    SYMBOL_REGEX("\\D");

    final String regex;

    Regex(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
