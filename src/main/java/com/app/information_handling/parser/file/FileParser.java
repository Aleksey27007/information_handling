package com.app.information_handling.parser.file;

import java.util.List;

public interface FileParser { // Chain of responsibility
    List<String> parse(String text);
}
