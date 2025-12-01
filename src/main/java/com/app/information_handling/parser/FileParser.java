package com.app.information_handling.parser;

import java.util.List;

public interface FileParser {
    List<String> parse(String text);
    FileParser getNextParser();
    void setNextParser(FileParser nextParser);
}