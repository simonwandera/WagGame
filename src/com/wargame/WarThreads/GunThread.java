package com.wargame.WarThreads;

import com.wargame.Gun;
import com.wargame.Soldier;
import com.wargame.WarGameWorld;

import java.util.ArrayList;
import java.util.Random;

public class GunThread implements Runnable{

    ArrayList<Soldier> allyWithGuns = new ArrayList<>();
    ArrayList<Soldier> enemyWithGuns = new ArrayList<>();
    public void getSoldiersWithGuns(){
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

    public boolean noSoldierhasWeapons(ArrayList<Soldier> soldiersWithGuns){

    }

    private void startFiring() {
        // randomize enemy or ally
        int choice = new Random().nextInt(20);
        if (choice % 2 == 0) {
            // enemy
            for (int k = 0; k < 5; k ++) {
                int soldierIndex = new Random().nextInt(enemyWithGuns.size());
                if (enemyWithGuns.get(soldierIndex).getWeapon().isActive() && enemyWithGuns.get(soldierIndex).isAlive())
                    enemyWithGuns.get(soldierIndex).shoot();
                else
                    enemyWithGuns.get(soldierIndex).setAlive(false);
            }
            // ally
            for (int k = 0; k < 5; k ++) {
                int soldierIndex = new Random().nextInt(allyWithGuns.size());
                choice = new Random().nextInt(10);

                if (choice % 2 == 0 && allyWithGuns.get(soldierIndex).isAlive())
                    allyWithGuns.get(soldierIndex).shot();
            }
        }

        else {
            for (int k = 0; k < 10; k ++) {
                int soldierIndex = new Random().nextInt(allyWithGuns.size());
                if (allyWithGuns.get(soldierIndex).getWeapon().isActive() && allyWithGuns.get(soldierIndex).isAlive())
                    allyWithGuns.get(soldierIndex).shoot();
                else
                    allyWithGuns.get(soldierIndex).setAlive(false);
            }
            // ally
            for (int k = 0; k < 10; k ++) {
                int soldierIndex = new Random().nextInt(enemyWithGuns.size());
                choice = new Random().nextInt(10);
                if (choice % 2 == 0 && enemyWithGuns.get(soldierIndex).isAlive())
                    enemyWithGuns.get(soldierIndex).shot();
            }
        }
    }

    @Override
    public void run() {
        getSoldiersWithGuns();
        startFiring();

    }
}
