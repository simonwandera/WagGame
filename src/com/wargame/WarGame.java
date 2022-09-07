package com.wargame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class WarGame {

    public static String gamesPath = "games.db";
    public static String playersPath = "players.db";
    public static void main(String[] args) throws InterruptedException {

        System.out.println("**************************************");
        System.out.println("\t*************  WELCOME TO WARGAME 1.0 ***************");
        System.out.println("**************************************");

        Scanner scanner = new Scanner(System.in);
        

        System.out.println("What is your name");
        String player = scanner.nextLine();

        System.out.println("Which country do you come from");

        String country = scanner.nextLine();

        String profile = player + "," + country;

        writeToFile(playersPath, profile);




        WarGameWorld game = new WarGameWorld(player);
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

    public static boolean playerExists(String player){
        for (int i = 0; i< readFile(playersPath).size(); i++){
            if (readFile(playersPath).get(i).get(0).equals(player)){
                return true;
            }
        }
        return false;
    }

    private static ArrayList<ArrayList<String>> readFile(String PATH){
        ArrayList<ArrayList<String>> allPlayers = new ArrayList();
        try {
            File myObj = new File(PATH);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                ArrayList<String> myList = new ArrayList<String>(Arrays.asList(data.trim().split(",")));
                allPlayers.add(myList);
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return allPlayers;
    }
}