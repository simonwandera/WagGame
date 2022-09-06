package com.wargame.WarThreads;

import com.wargame.Gun;
import com.wargame.Soldier;
import com.wargame.WarGameWorld;

import java.util.ArrayList;
import java.util.Random;

public class GunThread implements Runnable{

    ArrayList<Soldier> allyWithGuns = new ArrayList<>();
    ArrayList<Soldier> enemyWithGuns = new ArrayList<>();

    String shooter;

    public GunThread(String shooter) {
        this.shooter = shooter;

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

    public boolean allSoldiersAreDead(ArrayList<Soldier> soldiers) {
        for (int k = 0; k < soldiers.size(); k ++)
            if (soldiers.get(k).isAlive())
                return false;
        return true;
    }

    public boolean noWeaponHasBullets(ArrayList<Soldier> soldiers) {

        for (int k = 0; k < soldiers.size(); k++)
            if (soldiers.get(k).getWeapon().isActive())
                return false;
        return true;
    }

    private void startFiring() {
        // randomize enemy or ally
        int choice = new Random().nextInt(20);
        if (this.shooter == "enemy") {
            // enemy shooting
            for (int k = 0; k < 5; k ++) {
                int soldierIndex = new Random().nextInt(enemyWithGuns.size());
                if (enemyWithGuns.get(soldierIndex).getWeapon().isActive() && enemyWithGuns.get(soldierIndex).isAlive())
                    enemyWithGuns.get(soldierIndex).shoot();
                else
                    enemyWithGuns.get(soldierIndex).setAlive(false);
            }
            // ally shooting
            for (int k = 0; k < 5; k ++) {
                int soldierIndex = new Random().nextInt(allyWithGuns.size());
                choice = new Random().nextInt(10);

                if (choice % 2 == 0 && allyWithGuns.get(soldierIndex).isAlive())
                    allyWithGuns.get(soldierIndex).shot();
            }
        }

        else if(this.shooter == "ally") {
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

        while (true) {
            if (allSoldiersAreDead(allyWithGuns) || allSoldiersAreDead(enemyWithGuns) || noWeaponHasBullets(enemyWithGuns) || noWeaponHasBullets(allyWithGuns)) {
                System.out.println("Exiting ...");
                break;
            }
            startFiring();
        }
    }
}
