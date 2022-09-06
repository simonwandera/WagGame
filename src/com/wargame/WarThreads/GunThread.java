package com.wargame.WarThreads;

import com.wargame.*;

import java.util.ArrayList;
import java.util.Random;

public class GunThread implements Runnable{

    ArrayList<Soldier> allyWithGuns = new ArrayList<>();
    ArrayList<Soldier> enemyWithGuns = new ArrayList<>();
    String shooter;
    Difficulty difficulty;

    public GunThread(String shooter, Difficulty difficulty) {
        this.shooter = shooter;
        this.difficulty = difficulty;
    }

    public synchronized void getSoldiersWithGuns(){
        WarGameWorld.ally.getSoldiers().forEach(item ->{
            Soldier soldier = (Soldier) item;
            if (item.getWeapon() instanceof Gun){
                allyWithGuns.add(item);
            }
        });

        WarGameWorld.enemy.getSoldiers().forEach(item ->{
            Soldier soldier = (Soldier) item;
            if (item.getWeapon() instanceof Gun){
                enemyWithGuns.add(item);
            }
        });
    }

    public boolean noGunHasBullets( ArrayList<Soldier> allyWithGuns) {

        for (int k = 0; k < allyWithGuns.size(); k++)
            if (allyWithGuns.get(k).getWeapon().isActive() && allyWithGuns.get(k).isAlive())
                return false;
        return true;
    }

    private synchronized void startFiring() {
        // randomize enemy or ally
        int choice = new Random().nextInt(20);
        if (this.shooter.equals("enemy")) {

            // enemy shooting
            for (int k = 0; k < 4; k++) {
                int soldierIndex = new Random().nextInt(enemyWithGuns.size());
                if (enemyWithGuns.get(soldierIndex).getWeapon().isActive() && enemyWithGuns.get(soldierIndex).isAlive()) {
                    enemyWithGuns.get(soldierIndex).shoot();

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
                else
                    enemyWithGuns.get(soldierIndex).setAlive(false);
            }
            // ally shooting

        }

        else if(this.shooter.equals("ally")) {
            for (int k = 0; k < 4; k ++) {
                int soldierIndex = new Random().nextInt(allyWithGuns.size());
                if (allyWithGuns.get(soldierIndex).getWeapon().isActive() && allyWithGuns.get(soldierIndex).isAlive()) {
                    allyWithGuns.get(soldierIndex).shoot();

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
                else
                    allyWithGuns.get(soldierIndex).setAlive(false);
            }
            // ally
        }
    }

    @Override
    public void run() {
        getSoldiersWithGuns();
        if (noGunHasBullets(allyWithGuns) || noGunHasBullets(enemyWithGuns)){
            Thread.currentThread().stop();
        }else {
            startFiring();
        }
    }
}
