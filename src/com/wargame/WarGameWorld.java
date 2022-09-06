package com.wargame;

import com.wargame.WarThreads.GunThread;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WarGameWorld {
    private static int maxSoldiers = 100;
    public static Army ally;
    public static Army enemy;

    public WarGameWorld() {
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
            if (army.getSoldiers().get(k).getWeapon().isActive())
                return false;
        return true;
    }

    private int deadSoldiers(Army army){
        int alive = 0;
        for (int k = 0; k < army.getSoldiers().size(); k++){
            if (army.getSoldiers().get(k).isAlive()) {
                alive = alive + 1;
            }
        }
        int deadSoldiers = army.getSoldiers().size() - alive;
        return deadSoldiers;
    }

    public void run() throws InterruptedException {
        // - Setup the game [Soldiers, Army (Ally, Enemy), Weapon Arsenal]
        // - Run the game [ Soldiers shoot at enemy, Control Weapons + Arsenal ]
        // - Control the game. Determine, when the game ends...
        // [1 - All soldiers are dead,
        // [2 - No weapon has bullets

        while (true) {

            if (allSoldiersAreDead(ally) || allSoldiersAreDead(enemy) || noWeaponHasBullets(ally) || noWeaponHasBullets(enemy)) {

                System.out.println(allSoldiersAreDead(ally) + " All soldiers are dead ally");
                System.out.println(allSoldiersAreDead(enemy) + " All soldiers are dead enemy");
                System.out.println(noWeaponHasBullets(ally) + " No weapon has bullets ally");
                System.out.println(noWeaponHasBullets(enemy) + " No weapon has bullets enemy");

                System.out.println("Exit...");
                break;

            }
            Thread t1 = new Thread(new GunThread("ally", Difficulty.EXTREME));
            Thread t2 = new Thread(new GunThread("enemy", Difficulty.EXTREME));

            t2.start();
            t1.start();
            t1.join();
            t2.join();
        }

    }
}