package com.wargame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WarGameWorld {
    private static int maxSoldiers = 16;
    private Army ally;
    private Army enemy;
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

    private void runGame() {
        // randomize enemy or ally
        int choice = new Random().nextInt(20);
        if (choice % 2 == 0) {
            // enemy
            for (int k = 0; k < 10; k ++) {
                int soldierIndex = new Random().nextInt(enemy.getSoldiers().size());
                if (enemy.getSoldiers().get(soldierIndex).getBullets() > 0 && enemy.getSoldiers().get(soldierIndex).isAlive())
                    enemy.getSoldiers().get(soldierIndex).shoot();
                else
                    enemy.getSoldiers().get(soldierIndex).setLife(false);
            }
            // ally
            for (int k = 0; k < 10; k ++) {
                int soldierIndex = new Random().nextInt(ally.getSoldiers().size());
                choice = new Random().nextInt(5);

                if (choice % 2 == 0 && ally.getSoldiers().get(soldierIndex).isAlive())
                    ally.getSoldiers().get(soldierIndex).shot();
            }
        }
        else {
            for (int k = 0; k < 10; k ++) {
                int soldierIndex = new Random().nextInt(ally.getSoldiers().size() - 1);
                if (ally.getSoldiers().get(soldierIndex).getBullets() > 0 && ally.getSoldiers().get(soldierIndex).isAlive())
                    ally.getSoldiers().get(soldierIndex).shoot();
                else
                    ally.getSoldiers().get(soldierIndex).setLife(false);
            }
            // ally
            for (int k = 0; k < 10; k ++) {
                int soldierIndex = new Random().nextInt(enemy.getSoldiers().size() - 1);
                choice = new Random().nextInt(10);
                if (choice % 2 == 0 && enemy.getSoldiers().get(soldierIndex).isAlive())
                    enemy.getSoldiers().get(soldierIndex).shot();
            }
        }
    }

    private boolean allSoldiersAreDead(Army army) {
        for (int k = 0; k < army.getSoldiers().size(); k ++)
            if (army.getSoldiers().get(k).isAlive())
                return false;
        return true;
    }

    private boolean noWeaponHasBullets(Army army) {

        for (int k = 0; k < army.getSoldiers().size(); k++) {
            if (army.getSoldiers().get(k).getWeapon().isActive())
                return false;
            else
                return true;
        }
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

                System.out.println("Exit...");
                break;
            }

            this.runGame();

            Scanner sc = new Scanner(System.in);

            Thread.sleep(2000);
        }
    }
}