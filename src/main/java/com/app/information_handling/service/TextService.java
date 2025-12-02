package com.app.information_handling.service;

import com.app.information_handling.composite.Text;

public interface TextService {
    Text parseText(String text);
    String restoreText(Text text);
}
