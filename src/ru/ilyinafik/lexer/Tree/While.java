package ru.ilyinafik.lexer.Tree;

import ru.ilyinafik.lexer.Token;

import java.util.ArrayList;

public class While extends Node {
    //класс while, точно такой же, как и for, но без дополнительного действия action
    Token op;
    Node lNode,rNode;
    ArrayList<Node> operationArray = new ArrayList<>();

    public While(Token op, Node lNode, Node rNode) {
        this.op = op;
        this.lNode = lNode;
        this.rNode = rNode;
    }

    public void setOperationArray(ArrayList<Node> operationArray) {
        this.operationArray = operationArray;
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
    public ArrayList<Node> getOperationArray() {
        return operationArray;
    }
}
