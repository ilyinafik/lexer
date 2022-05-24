package ru.ilyinafik.lexer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    String codeStrings;
    int position =0;
    ArrayList<Token> tokenArray =new ArrayList<>();

    public Lexer(String codeStrings) {
        this.codeStrings = codeStrings;
    }
    public ArrayList<Token> start(){
        //главная функция лексера, в ней запускаем функцию, которая ищет токены и потом выдаём массив токенов
        while(checkTokens()){}
        return this.tokenArray;
    }
    public boolean checkTokens(){
        //функция, которая ищет токены. В ней мы проходим по нашему списку токенов и,
        //если нашлось совпадение регулярного выржения,
        // и при этом это совпадение начинается с позиции, на которой мы остановились, то мы создаём такой токен, добавляем его в массив
        //и передвигаем позицию, иначе выдаём ошибку.
        //После того, как прошли по всей строке(коду, который в файле Code) массив токенов передаём парсеру
        TokenType[] tokenTypes=TokenType.tokenTypeList;
        if(this.position >= codeStrings.length())
            return false;
        for (int i=0;i<tokenTypes.length;i++){
            TokenType tokenType=tokenTypes[i];
            String regex=tokenType.reg;
            Matcher matcher=Pattern.compile(regex).matcher(codeStrings);
            if(matcher.find(this.position)&&matcher.start()==this.position)
            {
                String result=this.codeStrings.substring(this.position,this.position +matcher.group().length());
                Token token = new Token(tokenType, result, this.position);
                this.position += result.length();
                if(token.type!=TokenType.tokenTypeList[3]&&token.type!=TokenType.tokenTypeList[2]&&token.type!=TokenType.tokenTypeList[1])
                    tokenArray.add(token);
                return true;
            }
        }
        throw new Error("Ошибка на позиции: "+this.position);
    }

}