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

    public boolean allSoldiersAreDead(ArrayList<Soldier> army) {
        for (int k = 0; k < army.size(); k ++)
            if (army.get(k).isAlive())
                return false;
        return true;
    }

    public boolean noJetHasMissiles( ArrayList<Soldier> soldierWithJet) {

        for (int k = 0; k < soldierWithJet.size(); k++)
            if (soldierWithJet.get(k).getWeapon().isActive() && soldierWithJet.get(k).isAlive())
                return false;
        return true;
    }

    private synchronized void fireMissile() {
        // randomize enemy or ally
        int choice = new Random().nextInt(20);
        if (this.soldierType.equals("enemy")) {

            // select enemy to bomb

            int soldierIndex = new Random().nextInt(enemyWithJet.size());
            if (enemyWithJet.get(soldierIndex).getWeapon().isActive() && enemyWithJet.get(soldierIndex).isAlive()) {
                enemyWithJet.get(soldierIndex).shoot();


                for (int i = (WarGameWorld.ally.getSoldiers().size() * 7/8); i< WarGameWorld.ally.getSoldiers().size(); i++) {
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
                enemyWithJet.get(soldierIndex).setAlive(false);

        }

        else if(this.soldierType.equals("ally")) {

            int soldierIndex = new Random().nextInt(allyWithJet.size());
            if (allyWithJet.get(soldierIndex).getWeapon().isActive() && allyWithJet.get(soldierIndex).isAlive()) {
                allyWithJet.get(soldierIndex).shoot();

                for (int i = (WarGameWorld.enemy.getSoldiers().size() * 7/8); i< WarGameWorld.enemy.getSoldiers().size(); i++) {
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
                allyWithJet.get(soldierIndex).setAlive(false);
        }
    }

    @Override
    public void run() {
        getSoldiersWithJet();
        while (true) {
            if (noJetHasMissiles(allyWithJet) || noJetHasMissiles(enemyWithJet) || allSoldiersAreDead(WarGameWorld.enemy.getSoldiers()) || allSoldiersAreDead(WarGameWorld.ally.getSoldiers())) {

                if(noJetHasMissiles((allyWithJet)) || allSoldiersAreDead(WarGameWorld.ally.getSoldiers())){
                    WarGameWorld.jetWinner = "enemy";

                }else if(noJetHasMissiles((allyWithJet)) || allSoldiersAreDead(WarGameWorld.enemy.getSoldiers())){
                    WarGameWorld.jetWinner = "ally";
                }
                Thread.currentThread().stop();
                break;
            } else {
                fireMissile();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
