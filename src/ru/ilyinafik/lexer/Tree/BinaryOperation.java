package ru.ilyinafik.lexer.Tree;

import ru.ilyinafik.lexer.Token;

public class BinaryOperation extends Node {
    //Класс бинарной операции, к ним будут относиться всякие сложения,умножения,вычитания, а также сравнения - больше, меньше, равно
    //Тут есть левый узел и правый узел - ими могут быть либо числа(класс Number), либо переменные (класс Var)
    //op - оператор, который стоит между узлами
    Token op;
    Node lNode;
    Node rNode;
    public BinaryOperation(Token op, Node lNode, Node rNode) {
        super();
        this.op = op;
        this.lNode = lNode;
        this.rNode = rNode;
    }

    public Token getOp() {
        return op;
    }
    public Node getlNode() {
        return lNode;
    }
    public Node getrNode() {
        return rNode;
    }

}
