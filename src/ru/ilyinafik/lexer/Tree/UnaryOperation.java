package ru.ilyinafik.lexer.Tree;

import ru.ilyinafik.lexer.Token;

public class UnaryOperation extends Node {
    //класс унарной операции (в данном случае только print)
    //соответственно op - действие, которое мы хотим сделать(print)
    //value - с чем хотим сделать(переменная или число)
    Token op;
    Node value;

    public UnaryOperation(Token op, Node value) {
        this.op = op;
        this.value = value;
    }
    public Token getOp() {
        return op;
    }
    public Node getValue() {
        return value;
    }
}
