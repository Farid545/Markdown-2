package org.example.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

    // REGEX
    public static String FILE_TYPE = "\\S\\.txt";

    // BOLD TEXT
    public static final String BOLD_TEXT_START = "(?<=\\s|\\.|\\,)\\*{1,2}(?=[^`]*?(?<!`)$)";
    public static final String BOLD_TEXT_END = "\\*{1,2}";

    // ITALIC TEXT
    public static String ITALIC_TEXT_START = "(?<=\\s|\\.|\\,)_{1,2}(?=[\\s\\S]*?(?<!`)$)";
    public static String ITALIC_TEXT_END = "_{1,2}";

    // MONO TEXT
    public static String MONO_TEXT_START = "(?<=\\s|\\.|\\,)(\\\\+)([\\s\\S]*?)(?<!`)";
    public static String MONO_TEXT_END = "(?<!`)\\\\+(\\s|\\.|\\,)";
    public static final String HTML_FORMAT = "html";
    public static final String ANSI_FORMAT = "escapeCodes";
    public static final String MARKDOWN_FORMAT = "markdown";

    // Magic strings
    public static final String VALID_TAGS_CONTENT = "<html><body></body></html>";
    public static final String HTML_FORMAT_CONTENT = "Some markdown with *bold* and _italic_ text";
    public static final String MISSING_CLOSING_TAGS_CONTENT = "<html><body></html>";
    public static final String WRONG_NESTING_TAGS_CONTENT = "<html><body></html></body>";
    public static final String PARAGRAPH_TEST_CONTENT = "This is the first paragraph.\n\nThis is the second paragraph.";
    public static final String PARAGRAPH_EXPECTED_OUTPUT = "<p>This is the first paragraph.</p>\n<p>This is the second paragraph.</p>\n";
    public static final String MULTIPLE_ANSI_CONTENT = "Some markdown with ***bold*** text";
    public static final String CONSOLE_OUTPUT_CONTENT = "Expected console output";
    public static final String EMPTY_STRING = "";
    public static final String INVALID_FILE_PATH = "example.pdf";

    // HTML tags
    public static final String BOLD_HTML_TAG = "<b>bold</b>";
    public static final String ITALIC_HTML_TAG = "<i>italic</i>";

    // ANSI escape codes
    public static final String BOLD_ANSI_START = "\u001B[1m";
    public static final String BOLD_ANSI_END = "\u001B[22m";
    public static final String ITALIC_ANSI_START = "\u001B[3m";
    public static final String ITALIC_ANSI_END = "\u001B[23m";

    // Console output
    public static final String EXPECTED_CONSOLE_OUTPUT = "Expected console output";

    // Test results
    public static final String VALID_TAGS_TEST_PASSED = "Test with valid tags passed.";
    public static final String VALID_TAGS_TEST_FAILED = "Test with valid tags failed: ";
    public static final String VALID_TAGS_EXCEPTION_NOT_EXPECTED = "Test with valid tags should not have thrown an exception.";
    public static final String HTML_FORMAT_TEST_PASSED = "HTML format processing passed.";
    public static final String HTML_FORMAT_TEST_FAILED = "HTML format processing failed. Expected: ";
    public static final String ANSI_FORMAT_TEST_PASSED = "ANSI format processing passed.";
    public static final String ANSI_FORMAT_TEST_FAILED = "ANSI format processing failed. Expected: ";
    public static final String PARAGRAPH_TEST_PASSED = "Test passed: ";
    public static final String PARAGRAPH_TEST_FAILED = "Test failed: Output did not match expected: ";
    public static final String INVALID_FILE_PATH_TEST_FAILED = "Test failed: No exception was thrown.";
    public static final String INVALID_FILE_PATH_TEST_PASSED = "Test passed: Exception was thrown.";
    public static final String CONSOLE_OUTPUT_TEST_PASSED = "Test passed: ";
    public static final String CONSOLE_OUTPUT_TEST_FAILED = "Test failed: Output did not match expected";
    public static final String MULTIPLE_ANSI_FORMAT_TEST_FAILED = "ANSI format processing failed. Expected:";
    public static final String MISSING_CLOSING_TAG_TEST_PASSED = "Test with missing closing tag passed: ";
    public static final String MISSING_CLOSING_TAG_TEST_FAILED = "Test with missing closing tag failed: Expected an exception.";
    public static final String MISSING_CLOSING_TAG_EXCEPTION_EXPECTED = "Test with missing closing tag should have thrown an exception.";
    public static final String WRONG_NESTING_TAG_TEST_PASSED = "Test with wrong nesting passed: ";
    public static final String WRONG_NESTING_TAG_EXCEPTION_EXPECTED = "Test with wrong nesting should have thrown an exception.";
    // PRE TEXT
    public static String PRE_TEXT_START = "(?<=\\s|\\.|\\,)```{1,2}(?=[\\s\\S]*?(?<!`)$)";
    public static String PRE_TEXT_END = "```{1,2}";

    public static String PRE = "<pre>(.*?)</pre>";

    // PARAGRAPH TEXT

    public static String P_TEXT = "(.*?)(?:\\R\\R|\\R$|$)";

    // KEY WORDS
    public static String EXIT = "exit";

}
