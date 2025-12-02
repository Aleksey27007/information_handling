package com.app.information_handling.service.impl;

import com.app.information_handling.composite.*;
import com.app.information_handling.parser.FileParser;
import com.app.information_handling.parser.impl.IntoParagraphParserImpl;
import com.app.information_handling.parser.impl.IntoSentenceParserImpl;
import com.app.information_handling.parser.impl.IntoLexemeParserImpl;
import com.app.information_handling.parser.impl.IntoWordParser;
import com.app.information_handling.parser.impl.IntoSymbolParserImpl;
import com.app.information_handling.parser.impl.IntoLineParserImpl;
import com.app.information_handling.service.TextService;
import com.app.information_handling.app_logger.AppLogger;

import java.util.List;

public class TextServiceImpl implements TextService {

    private static final AppLogger logger = AppLogger.getInstance();
    
    private IntoParagraphParserImpl paragraphParser;
    private IntoSentenceParserImpl sentenceParser;
    private IntoLexemeParserImpl lexemeParser;
    private IntoWordParser wordParser;
    private IntoSymbolParserImpl symbolParser;
    private IntoLineParserImpl lineParser;

    @Override
    public Text parseText(String text) {
        logger.info("Starting text parsing");
        if (text == null || text.isBlank()) {
            logger.warn("Text is empty or null");
            return new Text();
        }

        initializeParsers();
        List<String> paragraphs = splitIntoParagraphs(text);
        Text result = buildTextFromParagraphs(paragraphs);
        logger.info("Text successfully parsed into " + result.getChildren().size() + " paragraphs");
        return result;
    }

    @Override
    public String restoreText(Text text) {
        logger.info("Starting text restoration");
        if (text == null) {
            logger.warn("Text is null, returning empty string");
            return "";
        }
        String restored = text.getText();
        logger.info("Text successfully restored, length: " + restored.length() + " characters");
        return restored;
    }

    private void initializeParsers() {
        if (paragraphParser == null) {
            paragraphParser = new IntoParagraphParserImpl();
            sentenceParser = new IntoSentenceParserImpl();
            lexemeParser = new IntoLexemeParserImpl();
            wordParser = new IntoWordParser();
            symbolParser = new IntoSymbolParserImpl();
            lineParser = new IntoLineParserImpl();

            paragraphParser.setNextParser(sentenceParser);
            sentenceParser.setNextParser(lexemeParser);
            lexemeParser.setNextParser(wordParser);
            wordParser.setNextParser(symbolParser);
            
            logger.info("Parsers initialized");
        }
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
            if (!paragraphStr.isBlank()) {
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

            if (!line.isBlank()) {
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

    private Sentence buildSentence(String sentenceStr, FileParser lexemeParser) {
        Sentence sentence = new Sentence();

        List<String> lexemes = lexemeParser.parse(sentenceStr);

        for (int i = 0; i < lexemes.size(); i++) {
            String lexemeStr = lexemes.get(i);
            if (!lexemeStr.isBlank()) {
                Lexeme lexeme = buildLexeme(lexemeStr.trim(), lexemeParser.getNextParser());
                sentence.add(lexeme);
                
                if (i < lexemes.size() - 1) {
                    sentence.add(new Symbol(' '));
                }
            }
        }

        return sentence;
    }
    
    private Lexeme buildLexeme(String lexemeStr, FileParser wordParser) {
        Lexeme lexeme = new Lexeme();

        List<String> words = wordParser.parse(lexemeStr);

        for (String wordStr : words) {
            if (!wordStr.isBlank()) {
                Word word = buildWord(wordStr.trim(), wordParser.getNextParser());
                lexeme.add(word);
            }
        }

        return lexeme;
    }

    private Word buildWord(String wordStr, FileParser symbolParser) {
        Word word = new Word();

        if (!wordStr.isBlank()) {
            for (char c : wordStr.toCharArray()) {
                Symbol symbol = new Symbol(c);
                word.add(symbol);
            }
        }

        return word;
    }
}
