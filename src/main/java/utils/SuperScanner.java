package utils;

import java.util.Scanner;

public class SuperScanner {
    private Scanner scanner;

    public SuperScanner(Scanner scanner){
        this.scanner = scanner;
    }

    public String nextLine(String message){
        System.out.print(message);
        return scanner.nextLine();
    }

    public int nextInt(String message){
        System.out.print(message);
        int input =  Integer.parseInt(scanner.nextLine());
        return input;
    }

    public int nextInt(String message, String errorMessage){
        while(true) {
            try {
                return nextInt(message);
            }catch (Exception e){
                System.out.println(errorMessage);
            }
        }
    }

    public int nextInt(String message, String errorMessage, int... options){
        while (true){
            int input = nextInt(message, errorMessage);
            for(int option : options){
                if(input == option){
                    return option;
                }
            }
            System.out.println(errorMessage);
        }
    }

    public char nextChar(String message){
        return nextLine(message).charAt(0);
    }

    public char nextChar(String message, String errorMessage, char... options){
        while (true){
            char input = nextLine(message).charAt(0);
            for(char c : options){
                if(input == c){
                    return c;
                }
            }
            System.out.println(errorMessage);
        }
    }


}
