package ru.ilyinafik.lexer.Tree;

import ru.ilyinafik.lexer.Token;

public class Variable extends Node {
    //класс переменной, просто хранит токен с переменной
    Token variable;

    public Variable(Token variable) {
        this.variable = variable;
    }
    public Token getVariable() {
        return variable;
    }
}
