package org.example.test;

import org.example.constant.Constants;
import org.example.exception.InvalidFileFormatException;
import org.example.exception.InvalidTextFormatException;
import org.example.filework.FileProceed;
import org.example.filework.Input;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

public class TestFileProceed {

    public static void testValidTags_validateTags() {
        FileProceed fileProceed = new FileProceed();
        fileProceed.setContent(Constants.VALID_TAGS_CONTENT);
        try {
            FileProceed.validateTags();
            System.out.println(Constants.VALID_TAGS_TEST_PASSED);
        } catch (IllegalArgumentException e) {
            System.out.println(Constants.VALID_TAGS_TEST_FAILED + e.getMessage());
            assert false : Constants.VALID_TAGS_EXCEPTION_NOT_EXPECTED;
        }
    }

    public static void testHtmlFormatProcessing_fileProcessing() throws InvalidTextFormatException {
        FileProceed fileProceed = new FileProceed();
        fileProceed.setContent(Constants.HTML_FORMAT_CONTENT);
        FileProceed.fileProcessing(Constants.EMPTY_STRING, Constants.HTML_FORMAT);
        boolean passed = fileProceed.getContent().contains(Constants.BOLD_HTML_TAG) &&
                fileProceed.getContent().contains(Constants.ITALIC_HTML_TAG);
        assert passed : Constants.HTML_FORMAT_TEST_FAILED +
                Constants.BOLD_HTML_TAG + Constants.ITALIC_HTML_TAG + "Expected HTML: " +
                fileProceed.getContent();
        System.out.println("\n" +Constants.HTML_FORMAT_TEST_PASSED);
    }

    public static void testANSIFormatProcessing_fileProcessing() throws InvalidTextFormatException {
        FileProceed fileProceed = new FileProceed();
        fileProceed.setContent(Constants.HTML_FORMAT_CONTENT);
        FileProceed.fileProcessing(Constants.EMPTY_STRING, Constants.ANSI_FORMAT);
        boolean passed = fileProceed.getContent().contains(Constants.BOLD_ANSI_START) &&
                fileProceed.getContent().contains(Constants.ITALIC_ANSI_START);
        assert passed : Constants.ANSI_FORMAT_TEST_FAILED +
                Constants.BOLD_ANSI_START + Constants.ITALIC_ANSI_START + "Got: " +
                fileProceed.getContent();
        System.out.println("\n" +Constants.ANSI_FORMAT_TEST_PASSED);
    }

    public static void testReadParagraph_readParagraph() {
        FileProceed fileProceed = new FileProceed();
        String testContent = Constants.PARAGRAPH_TEST_CONTENT;
        fileProceed.setContent(testContent);

        String expectedOutput = Constants.PARAGRAPH_EXPECTED_OUTPUT;

        FileProceed.readParagraph(Constants.P_TEXT);

        String resultContent = fileProceed.getContent();

        assert expectedOutput.equals(resultContent) : Constants.PARAGRAPH_TEST_FAILED + resultContent;

        System.out.println("\n" +Constants.PARAGRAPH_TEST_PASSED + resultContent);
    }

    public static void testMarkdownPath_InvalidFilePath() {

        String format = Constants.MARKDOWN_FORMAT;
        String filePath = Constants.INVALID_FILE_PATH;
        boolean exceptionThrown = false;

        InputStream originalSystemIn = System.in;

        ByteArrayInputStream inputStream = new ByteArrayInputStream(filePath.getBytes());
        System.setIn(inputStream);

        try {
            Input.markdownPath(format);
        } catch (InvalidTextFormatException | InvalidFileFormatException | NoSuchElementException e) {
            exceptionThrown = true;
        } finally {
            System.setIn(originalSystemIn);
        }

        assert !exceptionThrown : Constants.INVALID_FILE_PATH_TEST_FAILED;
        System.out.println("\n" +Constants.INVALID_FILE_PATH_TEST_PASSED);
    }

    public static void testConsoleOutput_fileProcessing() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        String testFilePath = Constants.EMPTY_STRING;
        String testFormat = Constants.HTML_FORMAT;
        FileProceed fileProceed = new FileProceed();
        fileProceed.setContent(Constants.CONSOLE_OUTPUT_CONTENT);
        try {
            FileProceed.fileProcessing(testFilePath, testFormat);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.setOut(originalOut);
        }
        String output = bos.toString();
        String expectedOutput = Constants.EXPECTED_CONSOLE_OUTPUT;
        assert output.contains(expectedOutput) : Constants.CONSOLE_OUTPUT_TEST_FAILED;

        System.out.println("\n" +Constants.CONSOLE_OUTPUT_TEST_PASSED + output);
    }

    public static void testMultipleANSIFormatProcessing_fileProcessing() throws InvalidTextFormatException {
        FileProceed fileProceed = new FileProceed();
        fileProceed.setContent(Constants.MULTIPLE_ANSI_CONTENT);
        FileProceed.fileProcessing(Constants.EMPTY_STRING, Constants.ANSI_FORMAT);
        boolean passed = fileProceed.getContent().contains(Constants.BOLD_ANSI_START);
        assert passed : Constants.MULTIPLE_ANSI_FORMAT_TEST_FAILED + fileProceed.getContent();
        System.out.println("Test passed: testMultipleANSIFormatProcessing");
    }

    public static void testInvalidTagsMissingClosing_validateTags() {
        FileProceed fileProceed = new FileProceed();
        fileProceed.setContent(Constants.MISSING_CLOSING_TAGS_CONTENT);
        try {
            FileProceed.validateTags();
            System.out.println(Constants.MISSING_CLOSING_TAG_TEST_FAILED);
            assert false : Constants.MISSING_CLOSING_TAG_EXCEPTION_EXPECTED;
        } catch (IllegalArgumentException e) {
            System.out.println("\n" +Constants.MISSING_CLOSING_TAG_TEST_PASSED + e.getMessage());
        }
    }

    public static void testInvalidTagsWrongNesting_validateTags() {
        FileProceed fileProceed = new FileProceed();
        fileProceed.setContent(Constants.WRONG_NESTING_TAGS_CONTENT);
        try {
            FileProceed.validateTags();
            assert false : Constants.WRONG_NESTING_TAG_EXCEPTION_EXPECTED;
        } catch (IllegalArgumentException e) {
            System.out.println("\n" +Constants.WRONG_NESTING_TAG_TEST_PASSED + e.getMessage());
        }
    }

    public static void main(String[] args) throws InvalidTextFormatException {
        testValidTags_validateTags();
        testInvalidTagsMissingClosing_validateTags();
        testInvalidTagsWrongNesting_validateTags();
        testHtmlFormatProcessing_fileProcessing();
        testANSIFormatProcessing_fileProcessing();
        testMultipleANSIFormatProcessing_fileProcessing();
        testConsoleOutput_fileProcessing();
        testReadParagraph_readParagraph();
        testMarkdownPath_InvalidFilePath();
    }
}
