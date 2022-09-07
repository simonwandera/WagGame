package com.wargame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class WarGame {

    public static String gamesPath = "games.db";
    public static String playersPath = "players.db";
    public static void main(String[] args) throws InterruptedException {

        leaderBoard();
        System.out.println("**************************************");
        System.out.println("\t*************  WELCOME TO WARGAME 1.0 ***************");
        System.out.println("**************************************");

        Scanner scanner = new Scanner(System.in);

        System.out.println("What is your name");
        String player = scanner.nextLine();

        if(playerExists(player)){
            play(player);
        }else {

            System.out.println("\nPlease proceed with the registration");
            Thread.sleep(2000);
            System.out.println("Which country do you come from");

            String country = scanner.nextLine();
            String profile = player + "," + country + "\n";
            writeToFile(playersPath, profile);

            Thread.sleep(2000);
            System.out.println("Registered successfully");
            play(player);

        }
    }

    public static void play(String player) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome back " +player + "!");
        System.out.println("You have a total of " + getPoints(player) + " points");

        System.out.println("\nWould you like to play?");

        System.out.println("\n Y/N");
        String choice = scanner.nextLine();

        if(choice.toLowerCase().equals("y")){
            System.out.println("Select level \n S - Simple \t H - hard \t  E - extreme");
            String level = scanner.nextLine();
            WarGameWorld game = new WarGameWorld(player);

            if (level.toLowerCase().equals("s")){
                Difficulty mode = Difficulty.SIMPLE;
                if (game.run(mode).equals("ally"))
                    System.out.println("\nYou won");
                else
                    System.out.println("\n You lost");
            }else if(level.toLowerCase().equals("h")){
                Difficulty mode = Difficulty.HARD;
                if (game.run(mode).equals("ally"))
                    System.out.println("\nYou won");
                else
                    System.out.println("\n You lost");
            } else if (level.toLowerCase().equals("e")) {
                Difficulty mode = Difficulty.EXTREME;
                if (game.run(mode).equals("ally"))
                    System.out.println("\nYou won");
                else
                    System.out.println("\n You lost");
            }

        }else {
            System.out.println("Exiting...");
            System.exit(1000);
        }
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

    public static int getPoints(String player){
        int points = 0;
        for (int i = 0; i< readFile(gamesPath).size(); i++){
            if(readFile(gamesPath).get(i).get(0).equals(player)){
                points = points + Integer.parseInt(readFile(gamesPath).get(i).get(2).trim());
            }
        }
        return points;
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

    public static void leaderBoard(){
        ArrayList<ArrayList<String>> leaderboard = new ArrayList<>(readFile(gamesPath));
        HashSet<String> hashSet = new HashSet<>();

        for (int i=0; i< leaderboard.size(); i++){
            hashSet.add(leaderboard.get(i).get(0));
        }
        hashSet.add("enemy");

        ArrayList<String> scoresList = new ArrayList<>();
        scoresList.addAll(hashSet);

        HashMap<String, Integer> board = new HashMap<>();

        for (int i = 0; i< scoresList.size(); i++){
            int points = 0;
            for (int j =0; j< leaderboard.size(); j++){
                if (leaderboard.get(j).get(0).equals(scoresList.get(i))){
                    points = points + Integer.parseInt(leaderboard.get(j).get(2).trim());
                }
            }
            board.put(scoresList.get(i), points);
        }

        System.out.println(scoresList);
        System.out.println(leaderboard);
        System.out.println(board);
    }
}