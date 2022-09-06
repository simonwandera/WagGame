package com.wargame.WarThreads;

import com.wargame.*;

import java.util.ArrayList;
import java.util.Random;

public class BombThread implements Runnable{

    ArrayList<Soldier> allyWithBomb = new ArrayList<>();
    ArrayList<Soldier> enemyWithBomb = new ArrayList<>();
    String bomber;
    Difficulty difficulty;

    public BombThread(String bomber, Difficulty difficulty) {
        this.bomber = bomber;
        this.difficulty = difficulty;
    }
    public synchronized void getSoldiersWithBomb() {
        WarGameWorld.ally.getSoldiers().forEach(item -> {
            Soldier soldier = (Soldier) item;
            if (item.getWeapon() instanceof Bomb) {
                allyWithBomb.add(item);
            }
        });

        WarGameWorld.enemy.getSoldiers().forEach(item -> {
            Soldier soldier = (Soldier) item;
            if (item.getWeapon() instanceof Bomb) {
                enemyWithBomb.add(item);
            }
        });
    }

    private synchronized void throwBomb() {
        // randomize enemy or ally
        int choice = new Random().nextInt(20);
        if (this.bomber.equals("enemy")) {

            // select enemy to bomb

            int soldierIndex = new Random().nextInt(enemyWithBomb.size());
            if (enemyWithBomb.get(soldierIndex).getWeapon().isActive() && enemyWithBomb.get(soldierIndex).isAlive())
                enemyWithBomb.get(soldierIndex).shoot();
            else
                enemyWithBomb.get(soldierIndex).setAlive(false);

            // ally kill soldiers
            for (int k = 0; k < 4; k++) {
                soldierIndex = new Random().nextInt(WarGameWorld.ally.getSoldiers().size());
                choice = new Random().nextInt(10);

                if(difficulty.equals(Difficulty.SIMPLE)){
                    if (choice >= 0 && WarGameWorld.ally.getSoldiers().get(soldierIndex).isAlive())
                        WarGameWorld.ally.getSoldiers().get(soldierIndex).shot();
                }else if (difficulty.equals(Difficulty.HARD)){
                    if (choice >= 4 && WarGameWorld.ally.getSoldiers().get(soldierIndex).isAlive())
                        WarGameWorld.ally.getSoldiers().get(soldierIndex).shot();
                }else if (difficulty.equals(Difficulty.EXTREME)){
                    if (choice >= 7 && WarGameWorld.ally.getSoldiers().get(soldierIndex).isAlive())
                        WarGameWorld.ally.getSoldiers().get(soldierIndex).shot();
                }
            }

        }

        else if(this.bomber.equals("ally")) {

            int soldierIndex = new Random().nextInt(allyWithBomb.size());
            if (allyWithBomb.get(soldierIndex).getWeapon().isActive() && allyWithBomb.get(soldierIndex).isAlive())
                allyWithBomb.get(soldierIndex).shoot();
            else
                allyWithBomb.get(soldierIndex).setAlive(false);

            // ally
            for (int k = 0; k < 5; k ++) {
                soldierIndex = new Random().nextInt(WarGameWorld.enemy.getSoldiers().size());
                choice = new Random().nextInt(10);

                if(difficulty.equals(Difficulty.SIMPLE)){
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
    }

    @Override
    public void run() {

        getSoldiersWithBomb();

    }
}
