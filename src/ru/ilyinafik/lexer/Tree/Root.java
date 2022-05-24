package ru.ilyinafik.lexer.Tree;

import java.util.ArrayList;

public class Root extends Node {
    //корневой узел, в котором содержатся все остальные узлы (в мейне мы будем проходить по массиву nodes)
    ArrayList<Node> nodes =new ArrayList<>();
    public void addNode(Node node){
        nodes.add(node);
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }
}
