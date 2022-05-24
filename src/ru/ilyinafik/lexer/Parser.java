package ru.ilyinafik.lexer;

import ru.ilyinafik.lexer.Tree.*;
import ru.ilyinafik.lexer.Tree.Number;

import java.util.ArrayList;

public class Parser {
    //штука, которая получает массив токенов, и составляет соответствующие узлы, которые потом передаёт в интерпретатор
    //как она составляет узлы: сравнивает тип токенов и на основаниее этого понимает, какой узел нужно создать
    // и что должно идти дальше
    //пример у нас есть токен for, тогда дальше обязательно должно идти условие, которое нужно для фора,
    // а после него должно быть действие
    //после действия должен идти список операций в фигурнах скобках
    //она всё это проверяет и если не так, то выдаёт ошибку
    ArrayList<Token> tokenArray;
    int position =0;

    public Parser(ArrayList<Token> tokenArray) {
        this.tokenArray = tokenArray;
    }
    public Root parseArray(){
        Root root=new Root();
        while (position < tokenArray.size()){
            Node codeStringNode= checkString();
            needInArray(new String[]{"END"});
            root.addNode(codeStringNode);
        }
        return root;
    }
    public Node checkString(){
        String value = tokenArray.get(position).type.typeName;
        if (value.equals("VAR" )) {
            Node varNode = getVarOrNum();
            Token operator = getFromArray(new String[]{"ASSIGN"});
            if (operator != null) {
                Node rightVal = getPlusOrMinus();
                return new BinaryOperation(operator, varNode, rightVal);
            }
            throw new Error("После переменной ожидается бинарный оператор на позиции:" + position);
        }
        if (value.equals("PRINT")) {
            position++;
            return new UnaryOperation(tokenArray.get(position - 1), this.getPlusOrMinus());
        }
        if (value.equals("IF")) {
            position++;
            return getIfNode();
        }
        if (value.equals("WHILE")) {
            position++;
            return getWhileNode();
        }
        if (value.equals("FOR")) {
            position++;
            return getForNode();
        }
        throw new Error("Ошибка на позиции: "+ position +". Ожидалось действие или переменная");
    }
    public Token getFromArray(String[] need){
        Token curToken;
        if (position < tokenArray.size()) {
            curToken = tokenArray.get(position);
            for (String tokenTypeName : need)
                if (tokenTypeName.equals(curToken.type.typeName)) {
                    position++;
                    return curToken;
                }
        }
        return null;
    }
    public void needInArray(String[] expected){
        Token token= getFromArray(expected);
        if(token==null){
            throw new Error("На позииции "+ position +" ожидается "+expected[0]);
        }
    }
    
    public Node getVarOrNum(){
        if (tokenArray.get(position).type.typeName.equals("NUM")){
            position++;
            return new Number(tokenArray.get(position -1));
        }
        if (tokenArray.get(position).type.typeName.equals("VAR")){
            position++;
            return new Variable(tokenArray.get(position -1));
        }
        throw new Error("Ожидается переменная или число на позиции: "+ position);
    }
    public Node getParentheses(){
        if (tokenArray.get(position).type.typeName.equals("LPAR")){
            position++;
            Node node = getPlusOrMinus();
            needInArray(new String[]{"RPAR"});
            return node;
        }
        else
            return getVarOrNum();
    }
    public Node getMulOrDiv(){
        Node leftVal= getParentheses();
        Token operator= getFromArray(new String[]{"MULT","DIV"});
        while (operator!=null){
            Node rightVal= getParentheses();
            leftVal=new BinaryOperation(operator,leftVal,rightVal);
            operator= getFromArray(new String[]{"MULT","DIV"});
        }
        return leftVal;
    }
    public Node getPlusOrMinus(){
        Node leftVal= getMulOrDiv();
        Token operator= getFromArray(new String[]{"PLUS","MINUS"});
        while (operator!=null){
            Node rightVal= getMulOrDiv();
            leftVal=new BinaryOperation(operator,leftVal,rightVal);
            operator= getFromArray(new String[]{"PLUS","MINUS"});
        }
        return leftVal;
    }
    public Node getIfNode(){
        Node leftVal= getPlusOrMinus();
        Token operator= getFromArray(new String[]{"LESS","MORE","EQUAL"});
        Node rightVal= getPlusOrMinus();
        If ifNode =new If(operator,leftVal,rightVal);
        needInArray(new String[]{"LRectPar"});
        ifNode.setThenOpArray(getArrayOfOperations());
        position++;
        needInArray(new String[]{"ELSE"});
        needInArray(new String[]{"LRectPar"});
        ifNode.setElseOpArray(getArrayOfOperations());
        position++;
        return ifNode;
    }
    public Node getForNode(){
        Node lNode= getPlusOrMinus();
        Token op= getFromArray(new String[]{"LESS","MORE","EQUAL"});
        Node rNode= getPlusOrMinus();
        needInArray(new String[]{"END"});
        Node varNode = getVarOrNum();
        Token assign = getFromArray(new String[]{"ASSIGN"});
        Node rightActVal = getPlusOrMinus();
        BinaryOperation action= new BinaryOperation(assign, varNode, rightActVal);
        if (assign == null)
            throw new Error("После переменной ожидается = на позиции:"+ position);
        For forNode = new For(op,lNode,rNode,action);
        needInArray(new String[]{"LRectPar"});
        forNode.setOperationArray(getArrayOfOperations());
        position++;
        return forNode;
    }
    public Node getWhileNode(){
        Node lNode= getPlusOrMinus();
        Token op= getFromArray(new String[]{"LESS","MORE","EQUAL"});
        Node rNode= getPlusOrMinus();
        While whileNode =new While(op,lNode,rNode);
        needInArray(new String[]{"LRectPar"});
        whileNode.setOperationArray(getArrayOfOperations());
        position++;
        return whileNode;
    }

    public ArrayList<Node> getArrayOfOperations(){
        ArrayList<Node> arrayList=new ArrayList<>();
        while(!tokenArray.get(position).type.typeName.equals("RRectPAR")) {
            arrayList.add(checkString());
            needInArray(new String[]{"END"});
            if (position == tokenArray.size())
                throw new Error("Ошибка, ожидалось }");
        }
        return arrayList;
    }
}
