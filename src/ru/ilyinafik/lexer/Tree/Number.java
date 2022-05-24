package ru.ilyinafik.lexer.Tree;

import ru.ilyinafik.lexer.Token;

public class Number extends Node {
    //класс чисел, ничего не может, просто хранит в себе токен с числом
    Token number;

    public Number(Token number) {
        this.number = number;
    }

    public Token getNumber() {
        return number;
    }
}
