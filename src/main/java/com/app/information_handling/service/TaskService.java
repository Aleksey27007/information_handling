package com.app.information_handling.service;

import com.app.information_handling.composite.TextComponent;

import java.util.List;

public interface TaskService {
    //1 task
    int findMaxSentenceCountWithSameWords(TextComponent textComponent);
    //2 task
    List<String> sortSentencesByLexemeCount(TextComponent textComponent);
    //3 task
    void swapFirstAndLastLexemes(TextComponent textComponent);
}
