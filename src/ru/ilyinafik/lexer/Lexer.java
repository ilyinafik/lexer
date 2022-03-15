package ru.ilyinafik.lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Lexer {

    private static class Token {

        private final String type;
        private final String value;

        private Token(String type, String value) {
            this.type = type;
            this.value = value;
        }

        @Override
        public String toString() {
            return type + ": " + value;
        }
    }

    private static final Map<String, String> LEXEMES = Map.of(
            "VAR", "^[a-z]+$",
            "DIGIT", "^0|[1-9][0-9]*$",
            "OPERATOR", "^[-|+|/|*]$",
            "ASSIGNMENT OPERATOR", "^=$"
    );

    private static List<Token> parseExpression(String value) {
        String[] parts = value.trim().split("\\s+");
        List<Token> result = new ArrayList<>(parts.length);
        for (String part : parts) {
            result.add(parseToken(part));
        }
        return result;
    }

    private static Token parseToken(String value) {
        for (String key : LEXEMES.keySet()) {
            if (value.matches(LEXEMES.get(key))) {
                return new Token(key, value);
            }
        }
        throw new IllegalArgumentException("Illegal value: " + value);
    }

    public static void main(String[] args) {
        String input = new Scanner(System.in).nextLine();
        List<Token> tokens = parseExpression(input);
        for (Token token : tokens) {
            System.out.println(token);
        }
    }

}
