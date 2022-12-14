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

    public boolean allSoldiersAreDead(ArrayList<Soldier> army) {
        for (int k = 0; k < army.size(); k ++)
            if (army.get(k).isAlive())
                return false;
        return true;
    }

    private synchronized void throwBomb() {
        // randomize enemy or ally
        int choice = new Random().nextInt(20);
        if (this.bomber.equals("enemy")) {

            // select enemy to bomb

            int soldierIndex = new Random().nextInt(enemyWithBomb.size());
            if (enemyWithBomb.get(soldierIndex).getWeapon().isActive() && enemyWithBomb.get(soldierIndex).isAlive()) {
                enemyWithBomb.get(soldierIndex).shoot();


                for (int i = (WarGameWorld.ally.getSoldiers().size() * 3/4); i< WarGameWorld.ally.getSoldiers().size(); i++) {
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

                for (int i = (WarGameWorld.enemy.getSoldiers().size()* 3/4); i< WarGameWorld.enemy.getSoldiers().size(); i++) {
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

    public boolean noBombIsActive( ArrayList<Soldier> soldierWithBomb) {

        for (int k = 0; k < soldierWithBomb.size(); k++)
            if (soldierWithBomb.get(k).getWeapon().isActive() && soldierWithBomb.get(k).isAlive())
                return false;
        return true;
    }

    @Override
    public void run() {

        getSoldiersWithBomb();
        while (true) {
            if (noBombIsActive(allyWithBomb) || noBombIsActive(enemyWithBomb) || allSoldiersAreDead(WarGameWorld.ally.getSoldiers()) || allSoldiersAreDead(WarGameWorld.enemy.getSoldiers())) {
                if(noBombIsActive((allyWithBomb)) || allSoldiersAreDead(WarGameWorld.ally.getSoldiers())){
                    WarGameWorld.winner.add("enemy");

                }else if(noBombIsActive((enemyWithBomb)) || allSoldiersAreDead(WarGameWorld.enemy.getSoldiers())){
                    WarGameWorld.winner.add("ally");
                }
                Thread.currentThread().stop();
                break;
            } else {
                throwBomb();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
