package com.wargame;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class WarGame {

    public static String PATH = "players.db";
    public static void main(String[] args) throws InterruptedException {

        System.out.println("**************************************");
        System.out.println("\t*************  WELCOME TO WARGAME 1.0 ***************");
        System.out.println("**************************************");

        Scanner scanner = new Scanner(System.in);

        System.out.println("What is your name");


        WarGameWorld game = new WarGameWorld("simon");
//        game.run();

        System.out.println("Winner " + game.run());

    }


    public static void writeToFile(String PATH, String str) {
        try {
            FileWriter myWriter = new FileWriter(PATH, true);
            myWriter.write(str);
            myWriter.flush();
            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}