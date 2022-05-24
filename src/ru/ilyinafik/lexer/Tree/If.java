package ru.ilyinafik.lexer.Tree;

import ru.ilyinafik.lexer.Token;

import java.util.ArrayList;

public class If extends Node {
    //Почти то же самое, что и for - 2 узла, знак между ними
    //Если условия выполнено, то выполняются операции из thenOpArray, иначе из else
    Token op;
    Node lNode,rNode;
    ArrayList<Node> thenOpArray = new ArrayList<>();
    ArrayList<Node> elseOpArray = new ArrayList<>();
    public If(Token op, Node lNode, Node rNode) {
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

    public ArrayList<Node> getThenOpArray() {
        return thenOpArray;
    }

    public ArrayList<Node> getElseOpArray() {
        return elseOpArray;
    }

    public void setThenOpArray(ArrayList<Node> thenOpArray) {
        this.thenOpArray = thenOpArray;
    }

    public void setElseOpArray(ArrayList<Node> elseOpArray) {
        this.elseOpArray = elseOpArray;
    }
}
