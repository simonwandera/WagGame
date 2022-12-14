package com.wargame;

import com.wargame.WarThreads.BombThread;
import com.wargame.WarThreads.GunThread;
import com.wargame.WarThreads.JetThread;

import java.text.CollationKey;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

public class WarGameWorld {
    private static int maxSoldiers = 100;
    public static Army ally;
    public static Army enemy;
    public static ArrayList<String> winner = new ArrayList<>();
    String player;

    public WarGameWorld(String player) {
        this.player = player;
        this.createArmy();
    }

    private void createArmy() {
        // Create 2 armies (Ally and Enemy)
        ally = new Army();
        enemy = new Army();
        // 1000 soldiers per army

        ArrayList<Soldier> allySoldiers = new ArrayList<>();
        ArrayList<Soldier> enemySoldiers = new ArrayList<>();

        for (int k = 0; k < maxSoldiers; k++) {

            allySoldiers.add(new Soldier("ALLY_00" + k));
            enemySoldiers.add(new Soldier("ENEMY_00" + k));
        }
        ally.setSoldiers(allySoldiers);
        enemy.setSoldiers(enemySoldiers);
    }

    public boolean allSoldiersAreDead(Army army) {
        for (int k = 0; k < army.getSoldiers().size(); k ++)
            if (army.getSoldiers().get(k).isAlive())
                return false;
        return true;
    }
    public boolean noWeaponHasBullets(Army army) {

        for (int k = 0; k < army.getSoldiers().size(); k++)
            if (army.getSoldiers().get(k).getWeapon().isActive() && army.getSoldiers().get(k).isAlive())
                return false;
        return true;
    }

    public String run(Difficulty difficulty) throws InterruptedException {
        // - Setup the game [Soldiers, Army (Ally, Enemy), Weapon Arsenal]
        // - Run the game [ Soldiers shoot at enemy, Control Weapons + Arsenal ]
        // - Control the game. Determine, when the game ends...
        // [1 - All soldiers are dead,
        // [2 - No weapon has bullets

        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        String today = LocalDate.now().format(myFormatObj);

        winner.clear();

        Thread gun1 = new Thread(new GunThread("ally", difficulty));
        Thread gun2 = new Thread(new GunThread("enemy", Difficulty.HARD));

        Thread bomb1 = new Thread(new BombThread("ally", difficulty));
        Thread bomb2 = new Thread(new BombThread("enemy", Difficulty.HARD));

        Thread jet1 = new Thread(new JetThread("ally", difficulty));
        Thread jet2 = new Thread(new JetThread("enemy", Difficulty.HARD));

        gun1.start();
        gun2.start();
        bomb1.start();
        bomb2.start();
        jet1.start();
        jet2.start();

        gun1.join();
        gun2.join();
        bomb1.join();
        bomb2.join();
        jet1.join();
        jet2.join();

        if(Collections.frequency(winner, "enemy") > Collections.frequency(winner, "ally")){
            String str = player + ", lost" + ", 0 " + "," + difficulty + "," + today + "\n";
            WarGame.writeToFile(WarGame.gamesPath, str);
            return "enemy";
        }else {
            String str = player + ", won" + ", 100" + "," + difficulty + "," + today + "\n";
            WarGame.writeToFile(WarGame.gamesPath, str);
            return "ally";
        }
    }
}