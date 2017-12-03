/**
 * Java 1. Homework 3
 *
 *@author Vladislav Marishin
 *@version dated Nov 30, 2017
 *
 */

import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class HomeWork3 {

    public static void main(String[] args) {
        checkValue();
        food();
    }

    public static void checkValue(){
        int x;
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        do {
            x = rand.nextInt(9);
            System.out.print("Угадайте число от 0 до 9 > ");
            for (int i = 0; i < 3; i++) {
                int a = sc.nextInt();
                if (a < x)  System.out.print("Угадываемое число больше.");
                if (a > x)  System.out.print("Угадываемое число меньше.");
                if (a == x) {
                    System.out.print("Вы угадали! ");
                    break;
                }
                if (i != 2) System.out.println(" Попробуйте еще раз >");
                else {
                    System.out.print(" Игра окончена. ");
                    break;
                }
            }
            System.out.println("Повторить игру? (1-да 0-нет) > ");
        }while(sc.nextInt()==1);
    }

    public static void food(){

        int x;
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);

        String str="";
        String try_word;
        String[] words;

        try{
            int b;
            FileReader file= new FileReader("text.txt");
            while((b = file.read()) !=-1)                           // Reading from file to string by symbols.
            str+=(char)b;                                           //
            file.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }

        words=str.split(" ");                               // Initializing of array of strings from string
                                                                  // with separate by spaces.
        x = rand.nextInt(words.length);                           // Random position in array.

        System.out.print("Отгадайте загаданное слово > ");
        try_word = sc.next();
        do {
            int min_length_word = Math.min(try_word.length(), words[x].length()); // Find shortest word

            for (int i = 0; i < min_length_word; i++) {    // comparison character-by-character
                if (try_word.charAt(i) == words[x].charAt(i)) System.out.print(try_word.charAt(i));
                else System.out.print("#");

            }
            for (int i = min_length_word; i < 15; i++)
                System.out.print("#");
            System.out.println();
            System.out.print("Попробуйте еще раз > ");
            try_word = sc.next();
        }while(!try_word.equalsIgnoreCase(words[x]));    // until not equels

        System.out.print("Поздравляем, вы угадали ! ");
    }
}
