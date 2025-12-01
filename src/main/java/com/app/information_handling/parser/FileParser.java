package com.app.information_handling.parser;

import java.util.List;

public interface FileParser { // Chain of responsibility
    List<String> parse(String text);
}
