package ru.ilyinafik.lexer.Tree;

import ru.ilyinafik.lexer.Token;

import java.util.ArrayList;

public class For extends Node {
    //класс цикла for
    //Здесь как и в бинарной операции есть левый и правый узел и оператор между ними - условие, которое будет проверяться
    //operationArray - массив операций(таких же узлов), которые будут выполняться, после сравнения условия
    //action - действие, которое будет выполняться, после массива операций
    Token op;
    Node lNode,rNode,action;
    ArrayList<Node> operationArray = new ArrayList<>();

    public For(Token op, Node lNode, Node rNode, Node action) {
        this.op = op;
        this.lNode = lNode;
        this.rNode = rNode;
        this.action=action;
    }

    public ArrayList<Node> getOperationArray() {
        return operationArray;
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
    public Node getAction() {
        return action;
    }

    public void setOperationArray(ArrayList<Node> operationArray) {
        this.operationArray = operationArray;
    }
}
