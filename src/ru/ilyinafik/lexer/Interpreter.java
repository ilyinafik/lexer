package ru.ilyinafik.lexer;

import ru.ilyinafik.lexer.Tree.*;
import ru.ilyinafik.lexer.Tree.Number;

import java.util.HashMap;

public class Interpreter {
    //Мы получили массив узлом из парсера
    //Теперь мы проверяем класск каждого узла и выполняем соответствующее действие

    //Пример: если у нас поступил Унарный оператор(только печать), то мы выводим значение переменной или число, которе нужно
    //Как мы получаем значение переменной:
    //есть мап variables, в котором к каждому имени переменной сопоставляется её числовое значение
    //Чтобы его получить мы снова запускаем ф-ию checkClass и она понимает, что переменна принадлежит к классу
    //Variable, поэтому выдаёт её значение из variables
    //Если бы мы хотели получить значение числа, то его класс был бы Number, и мы бы просто взяли его значение из токена,
    //который есть, в классе Number
    public static HashMap<String,String> variables =new HashMap<>();
    public String checkClass(Node node){
        if (node.getClass()== UnaryOperation.class) {
            run((UnaryOperation) node);
        }
        if (node.getClass()== BinaryOperation.class) {
            return run((BinaryOperation)node);
        }
        if (node.getClass()== Variable.class) {
            return variables.get(((Variable) node).getVariable().value);
        }
        if (node.getClass()== Number.class) {
            return ((Number) node).getNumber().value;
        }
        if (node.getClass()== If.class){
            run((If) node);
        }
        if (node.getClass()== While.class){
            run((While) node);
        }
        if (node.getClass()== For.class) {
            run((For) node);
        }
        return "";
    }

    public void run(UnaryOperation node){
        if (node.getOp().type.typeName.equals("PRINT")) {
            System.out.println(this.checkClass(node.getValue()));
        }
    }
    public String run(BinaryOperation node){
        if (node.getOp().type.typeName.equals("ASSIGN")) {
            String res = this.checkClass(node.getrNode());
            Variable variable =(Variable) node.getlNode();
            variables.put(variable.getVariable().value, res);
            return res;
        }
        else
        {
            int left=Integer.parseInt(this.checkClass(node.getlNode()));
            int right=Integer.parseInt(this.checkClass(node.getrNode()));
            if (node.getOp().type.typeName.equals("PLUS")) {
                return String.valueOf(left + right);
            }
            if (node.getOp().type.typeName.equals("MINUS")) {
                return String.valueOf(left - right);
            }
            if (node.getOp().type.typeName.equals("MULT")) {
                return String.valueOf(left * right);
            }
            if (node.getOp().type.typeName.equals("DIV")) {
                return String.valueOf(left / right);
            }
            throw new Error("Недопустимая операция");
        }
    }
    public void run(If node){
        int left=Integer.parseInt(this.checkClass(node.getlNode()));
        int right=Integer.parseInt(this.checkClass(node.getrNode()));
        switch (node.getOp().type.typeName) {
            case "LESS":
                if (left < right) {
                    for (int i = 0; i < node.getThenOpArray().size(); i++)
                        this.checkClass(node.getThenOpArray().get(i));
                }
                else{
                    for (int i = 0; i < node.getElseOpArray().size(); i++)
                        this.checkClass(node.getElseOpArray().get(i));
                }
                break;
            case "MORE":
                if (left > right) {
                    for (int i = 0; i < node.getThenOpArray().size(); i++)
                        this.checkClass(node.getThenOpArray().get(i));
                }
                else{
                    for (int i = 0; i < node.getElseOpArray().size(); i++)
                        this.checkClass(node.getElseOpArray().get(i));
                }
                break;
            case "EQUAL":
                if (left == right) {
                    for (int i = 0; i < node.getThenOpArray().size(); i++)
                        this.checkClass(node.getThenOpArray().get(i));
                }
                else{
                    for (int i = 0; i < node.getElseOpArray().size(); i++)
                        this.checkClass(node.getElseOpArray().get(i));
                }
                break;
        }
    }
    public void run(While node){
        int left=Integer.parseInt(this.checkClass(node.getlNode()));
        int right=Integer.parseInt(this.checkClass(node.getrNode()));
        switch (node.getOp().type.typeName) {
            case "LESS":
                while (left < right) {
                    for (int i = 0; i < node.getOperationArray().size(); i++)
                        this.checkClass(node.getOperationArray().get(i));
                    left = Integer.parseInt(this.checkClass(node.getlNode()));
                    right = Integer.parseInt(this.checkClass(node.getrNode()));
                }
                break;
            case "MORE":
                while (left > right) {
                    for (int i = 0; i < node.getOperationArray().size(); i++)
                        this.checkClass(node.getOperationArray().get(i));
                    left = Integer.parseInt(this.checkClass(node.getlNode()));
                    right = Integer.parseInt(this.checkClass(node.getrNode()));
                }
                break;
            case "EQUAL":
                while (left == right) {
                    for (int i = 0; i < node.getOperationArray().size(); i++)
                        this.checkClass(node.getOperationArray().get(i));
                    left = Integer.parseInt(this.checkClass(node.getlNode()));
                    right = Integer.parseInt(this.checkClass(node.getrNode()));
                }
                break;
        }
    }
    public void run(For node){
        int left=Integer.parseInt(this.checkClass(node.getlNode()));
        int right=Integer.parseInt(this.checkClass(node.getrNode()));
        switch (node.getOp().type.typeName) {
            case "LESS":
                while (left < right) {
                    for (int i = 0; i < node.getOperationArray().size(); i++)
                        this.checkClass(node.getOperationArray().get(i));
                    this.checkClass(node.getAction());
                    left = Integer.parseInt(this.checkClass(node.getlNode()));
                    right = Integer.parseInt(this.checkClass(node.getrNode()));
                }
                break;
            case "MORE":
                while (left > right) {
                    for (int i = 0; i < node.getOperationArray().size(); i++)
                        this.checkClass(node.getOperationArray().get(i));
                    this.checkClass(node.getAction());
                    left = Integer.parseInt(this.checkClass(node.getlNode()));
                    right = Integer.parseInt(this.checkClass(node.getrNode()));
                }
                break;
            case "EQUAL":
                while (left == right) {
                    for (int i = 0; i < node.getOperationArray().size(); i++)
                        this.checkClass(node.getOperationArray().get(i));
                    this.checkClass(node.getAction());
                    left = Integer.parseInt(this.checkClass(node.getlNode()));
                    right = Integer.parseInt(this.checkClass(node.getrNode()));
                }
                break;
        }
    }
}
