package ru.ilyinafik.lexer;

public class TokenType {
    //массив возможных токенов с их регулярными выражениями
    String typeName;
    String reg;
    public static TokenType[] tokenTypeList={
            new TokenType("NUM", "^0|[1-9][0-9]*"), new TokenType("SPACE", "\\ "),
            new TokenType("EndL", "[\\n]"), new TokenType("Karetka", "[\\r]"),
            new TokenType("EQUAL", "(?i)=="), new TokenType("LESS", "[<]"),
            new TokenType("MORE", "[>]"), new TokenType("ASSIGN", "[=]"),
            new TokenType("PLUS", "[+]"), new TokenType("MINUS", "[-]"),
            new TokenType("MULT", "[*]"), new TokenType("DIV", "[/]"),
            new TokenType("PRINT", "print"), new TokenType("IF","if"),
            new TokenType("ELSE","else"), new TokenType("FOR", "for"),
            new TokenType("WHILE","while"), new TokenType("END", "[;]"),
            new TokenType("LPAR", "[(]"), new TokenType("RPAR", "[)]"),
            new TokenType("LRectPar", "[{]"), new TokenType("RRectPAR", "[}]"),
            new TokenType("VAR", "[a-z][a-z]*"),
    };
    public TokenType(String typeName, String reg) {
        this.typeName = typeName;
        this.reg = reg;
    }
}
