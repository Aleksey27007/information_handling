package com.app.information_handling.service;

import com.app.information_handling.composite.Sentence;
import com.app.information_handling.composite.Text;

import java.util.List;

public interface TextTaskService {

    // Task 1
    int findMaxSentenceCountWithSameWord(Text text);

    // Task 2
    List<Sentence> getSentencesSortedByLexemeCount(Text text);

    // Task 3
    Text swapFirstAndLastLexemeInEachSentence(Text text);
}


