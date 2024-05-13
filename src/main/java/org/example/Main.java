package org.example;

import org.example.exception.InvalidFileFormatException;
import org.example.exception.InvalidTextFormatException;
import org.example.filework.Input;

public class Main {
    public static void main(String[] args) throws InvalidTextFormatException, InvalidFileFormatException {
        if (args.length > 0 && args[0].startsWith("--format=")) {
            String format = args[0].substring(9);
            try {
                Input.markdownPath(format);
            } catch (InvalidTextFormatException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Input.markdownPath("escapeCodes"); // Default format is ANSI Escape Codes
            } catch (InvalidTextFormatException e) {
                e.printStackTrace();
            }
        }
    }
}