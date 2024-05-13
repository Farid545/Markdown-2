package org.example.filework;

import org.example.constant.Constants;
import org.example.enums.MessageCodes;
import org.example.exception.InvalidTextFormatException;
import org.example.util.FileUtility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileProceed {

    private static String content = "";

    public String getContent() {
        return content;
    }

    public void setContent(String newContent) {
        content = newContent;
    }

    public static void fileProcessing(String filePath, String format) throws InvalidTextFormatException {
        if(content.equals(""))
        readFileToString(filePath);
        switch (format) {
            case "html":
                readMarkdownText(Constants.PRE_TEXT_START, Constants.PRE_TEXT_END,
                        " <pre>", "</pre> ");
                readMarkdownText(Constants.BOLD_TEXT_START, Constants.BOLD_TEXT_END,
                        " <b>", "</b> "); // BOLD
                readMarkdownText(Constants.ITALIC_TEXT_START, Constants.ITALIC_TEXT_END,
                        " <i>", "</i> "); // ITALIC
                readMarkdownText(Constants.MONO_TEXT_START, Constants.MONO_TEXT_END,
                        " <tt>", "</tt> "); // MONO
                readParagraph(Constants.P_TEXT);
                validateTags();
                break;
            case "escapeCodes":
                readMarkdownText(Constants.PRE_TEXT_START, Constants.PRE_TEXT_END, "\u001B[7m", "\u001B[27m");// PRE with inverse/reverse mode
                readMarkdownText(Constants.BOLD_TEXT_START, Constants.BOLD_TEXT_END,
                        "\u001B[1m", "\u001B[22m"); // BOLD
                readMarkdownText(Constants.ITALIC_TEXT_START, Constants.ITALIC_TEXT_END,
                        "\u001B[3m", "\u001B[23m"); // ITALIC
                readMarkdownText(Constants.MONO_TEXT_START, Constants.MONO_TEXT_END,
                        "\u001B[7m", "\u001B[27m "); // MONO with inverse/reverse mode
        }
        System.out.println(content);
    }

    public static boolean checkTagPositionInPRE(int tag) {
        Matcher matcher = FileUtility.getMatcherFromPattern(content, Constants.PRE);

        while (matcher.find()) {
            int startPreTag = matcher.start(); // Початкова позиція тегу <pre>
            int endPreTag = matcher.end(); // Кінцева позиція тегу <pre>
            int startEndPreTag = content.indexOf("</pre>", endPreTag); // Початкова позиція тегу </pre> після початку тексту

            if (tag > startPreTag && tag < startEndPreTag) {
                return true;
            }
        }
        return false;
    }

    public static void validateTags() {
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) == '<') {
                int closingTagIndex = content.indexOf('>', i);
                if (closingTagIndex == -1)
                    throw new IllegalArgumentException("Invalid tag structure.");

                String tag = content.substring(i, closingTagIndex + 1);

                if (tag.startsWith("</")) {
                    String openTag = stack.pop();
                    if (!tag.substring(2, tag.length() - 1).equals(openTag.substring(1, openTag.length() - 1)))
                        throw new IllegalArgumentException("Invalid nesting of tags: " + openTag + " and " + tag);
                } else {
                    stack.push(tag);
                }

                i = closingTagIndex;
            }
        }

        if (!stack.isEmpty())
            throw new IllegalArgumentException("Unclosed tags: " + stack);
    }

    public static void readFileToString(String filePath) {
        try {
            Path path = Paths.get(filePath);
            byte[] bytesContent = Files.readAllBytes(path);
            content = new String(bytesContent);
            content = content.concat(" ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void checkTheError(int startCounter, int endCounter, String regex, String replaceTo) throws InvalidTextFormatException {
        if (startCounter == endCounter)
            content = content.replaceAll(regex, replaceTo);
        else
            throw new InvalidTextFormatException(MessageCodes.INVALID_TEXT_FORMAT);
    }

    public static void readParagraph(String regex) {
        Matcher matcher = FileUtility.getMatcherFromPattern(content, regex);

        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            String paragraph = matcher.group().trim();
            if (!paragraph.isEmpty()) {
                result.append("<p>").append(paragraph).append("</p>").append("\n");
            }
        }

        content = result.toString();
    }


    public static void readMarkdownText(String regexStart, String regexEnd, String replaceStart, String replaceEnd) throws InvalidTextFormatException {
        Matcher matcher;

        // Для відкриття
        matcher = FileUtility.getMatcherFromPattern(content, regexStart);
        int startCounter = findReegax(matcher);

        // Для закриття
        matcher = FileUtility.getMatcherFromPattern(content, regexStart);
        int endCounter = findReegax(matcher);

        checkTheError(startCounter, endCounter, regexStart, replaceStart);
        checkTheError(startCounter, endCounter, regexEnd, replaceEnd);
    }

    public static int findReegax(Matcher matcher) {
        int counter = 0;
        while (matcher.find()) {
            if (!checkTagPositionInPRE(matcher.start()))
                counter++;
        }

        return counter;
    }
}
