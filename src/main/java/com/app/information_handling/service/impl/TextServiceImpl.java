package com.app.information_handling.service.impl;

import com.app.information_handling.composite.*;
import com.app.information_handling.parser.FileParser;
import com.app.information_handling.parser.impl.IntoParagraphParserImpl;
import com.app.information_handling.parser.impl.IntoSentenceParserImpl;
import com.app.information_handling.parser.impl.IntoWordParser;
import com.app.information_handling.parser.impl.IntoSymbolParserImpl;
import com.app.information_handling.parser.impl.IntoLineParserImpl;
import com.app.information_handling.service.TextService;

import java.util.List;

public class TextServiceImpl implements TextService {

    private IntoParagraphParserImpl paragraphParser;
    private IntoSentenceParserImpl sentenceParser;
    private IntoWordParser wordParser;
    private IntoSymbolParserImpl symbolParser;
    private IntoLineParserImpl lineParser;

    public Text parseText(String text) {
        if (text == null || text.trim().isEmpty()) {
            return new Text();
        }

        initializeParsers();
        List<String> paragraphs = splitIntoParagraphs(text);
        return buildTextFromParagraphs(paragraphs);
    }

    private void initializeParsers() {
        paragraphParser = new IntoParagraphParserImpl();
        sentenceParser = new IntoSentenceParserImpl();
        wordParser = new IntoWordParser();
        symbolParser = new IntoSymbolParserImpl();
        lineParser = new IntoLineParserImpl();

        paragraphParser.setNextParser(sentenceParser);
        sentenceParser.setNextParser(wordParser);
        wordParser.setNextParser(symbolParser);
    }

    private List<String> splitIntoParagraphs(String text) {
        if (!text.startsWith("\t")) {
            text = "\t" + text;
        }
        return paragraphParser.parse(text);
    }

    private Text buildTextFromParagraphs(List<String> paragraphs) {
        Text textComponent = new Text();

        for (String paragraphStr : paragraphs) {
            if (paragraphStr != null && !paragraphStr.trim().isEmpty()) {
                Paragraph paragraph = buildParagraph(paragraphStr, sentenceParser);
                textComponent.add(paragraph);
            }
        }

        return textComponent;
    }

    private Paragraph buildParagraph(String paragraphStr, FileParser sentenceParser) {
        Paragraph paragraph = new Paragraph();

        if (paragraphStr.startsWith("\t")) {
            paragraph.add(new Symbol('\t'));
            paragraphStr = paragraphStr.substring(1);
        }

        List<String> lines = lineParser.parse(paragraphStr);
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);

            if (!line.trim().isEmpty()) {
                List<String> sentences = sentenceParser.parse(line);

                for (String sentenceStr : sentences) {
                    if (sentenceStr != null && !sentenceStr.trim().isEmpty()) {
                        Sentence sentence = buildSentence(sentenceStr.trim(), sentenceParser.getNextParser());
                        paragraph.add(sentence);
                    }
                }
            }

            if (i < lines.size() - 1) {
                paragraph.add(new Symbol('\n'));
            }
        }

        return paragraph;
    }

    private Sentence buildSentence(String sentenceStr, FileParser wordParser) {
        Sentence sentence = new Sentence();

        List<String> words = wordParser.parse(sentenceStr);

        for (String wordStr : words) {
            if (wordStr != null && !wordStr.trim().isEmpty()) {
                Word word = buildWord(wordStr.trim(), wordParser.getNextParser());
                sentence.add(word);
            }
        }

        return sentence;
    }

    private Word buildWord(String wordStr, FileParser symbolParser) {
        Word word = new Word();

        if (wordStr != null && !wordStr.isEmpty()) {
            for (char c : wordStr.toCharArray()) {
                Symbol symbol = new Symbol(c);
                word.add(symbol);
            }
        }

        return word;
    }
}
