package ru.ilyinafik.lexer;

import ru.ilyinafik.lexer.Tree.Root;

import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static String checkFile(){
        String code="";
        try(FileReader reader = new FileReader("Code.txt"))
        {
            int c;
            while((c=reader.read())!=-1){

                code=code.concat(String.valueOf((char)c));
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        return code;
    }

    public static void main(String[] args) {
        //мейн, читаем файл Code, создаём лексер, парсер и интерпретатор
        String s=checkFile();
        System.out.println(s);
        Lexer lexer=new Lexer(s);
        Parser parser=new Parser(lexer.start());
        Root root=parser.parseArray();
        Interpreter interpreter =new Interpreter();
        for(int i = 0; i< root.getNodes().size(); i++) {
            interpreter.checkClass(root.getNodes().get(i));
        }
    }
}
