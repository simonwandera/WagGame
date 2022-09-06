package com.wargame.WarThreads;

import com.wargame.*;

import java.util.ArrayList;
import java.util.Random;

public class JetThread implements Runnable {

    ArrayList<Soldier> allyWithJet = new ArrayList<>();
    ArrayList<Soldier> enemyWithJet = new ArrayList<>();
    String soldierType;
    Difficulty difficulty;

    public JetThread(String soldierType, Difficulty difficulty) {
        this.soldierType = soldierType;
        this.difficulty = difficulty;
    }

    public synchronized void getSoldiersWithJet() {
        WarGameWorld.ally.getSoldiers().forEach(item -> {
            Soldier soldier = (Soldier) item;
            if (item.getWeapon() instanceof Jet) {
                allyWithJet.add(item);
            }
        });

        WarGameWorld.enemy.getSoldiers().forEach(item -> {
            Soldier soldier = (Soldier) item;
            if (item.getWeapon() instanceof Bomb) {
                enemyWithJet.add(item);
            }
        });
    }

    private synchronized void fireMissile() {
        // randomize enemy or ally
        int choice = new Random().nextInt(20);
        if (this.soldierType.equals("enemy")) {

            // select enemy to bomb

            int soldierIndex = new Random().nextInt(enemyWithJet.size());
            if (enemyWithBomb.get(soldierIndex).getWeapon().isActive() && enemyWithBomb.get(soldierIndex).isAlive()) {
                enemyWithBomb.get(soldierIndex).shoot();


                for (int i = (WarGameWorld.ally.getSoldiers().size()/4); i< WarGameWorld.ally.getSoldiers().size(); i++) {
                    soldierIndex = new Random().nextInt(WarGameWorld.ally.getSoldiers().size());
                    choice = new Random().nextInt(10);

                    if (difficulty.equals(Difficulty.SIMPLE)) {
                        if (choice >= 0 && WarGameWorld.ally.getSoldiers().get(soldierIndex).isAlive())
                            WarGameWorld.ally.getSoldiers().get(soldierIndex).shot();
                    } else if (difficulty.equals(Difficulty.HARD)) {
                        if (choice >= 4 && WarGameWorld.ally.getSoldiers().get(soldierIndex).isAlive())
                            WarGameWorld.ally.getSoldiers().get(soldierIndex).shot();
                    } else if (difficulty.equals(Difficulty.EXTREME)) {
                        if (choice >= 7 && WarGameWorld.ally.getSoldiers().get(soldierIndex).isAlive())
                            WarGameWorld.ally.getSoldiers().get(soldierIndex).shot();
                    }
                }

            }
            else
                enemyWithBomb.get(soldierIndex).setAlive(false);

        }

        else if(this.bomber.equals("ally")) {

            int soldierIndex = new Random().nextInt(allyWithBomb.size());
            if (allyWithBomb.get(soldierIndex).getWeapon().isActive() && allyWithBomb.get(soldierIndex).isAlive()) {
                allyWithBomb.get(soldierIndex).shoot();

                for (int i = (WarGameWorld.enemy.getSoldiers().size()/4); i< WarGameWorld.enemy.getSoldiers().size(); i++) {
                    soldierIndex = new Random().nextInt(WarGameWorld.enemy.getSoldiers().size());
                    choice = new Random().nextInt(10);
                    if (difficulty.equals(Difficulty.SIMPLE)) {
                        if (choice >= 0 && WarGameWorld.enemy.getSoldiers().get(soldierIndex).isAlive())
                            WarGameWorld.enemy.getSoldiers().get(soldierIndex).shot();

                    } else if (difficulty.equals(Difficulty.HARD)) {
                        if (choice >= 4 && WarGameWorld.enemy.getSoldiers().get(soldierIndex).isAlive())
                            WarGameWorld.enemy.getSoldiers().get(soldierIndex).shot();

                    } else if (difficulty.equals(Difficulty.EXTREME)) {
                        if (choice >= 7 && WarGameWorld.enemy.getSoldiers().get(soldierIndex).isAlive())
                            WarGameWorld.enemy.getSoldiers().get(soldierIndex).shot();
                    }
                }
            }
            else
                allyWithBomb.get(soldierIndex).setAlive(false);
        }
    }

    @Override
    public void run() {


    }
}
