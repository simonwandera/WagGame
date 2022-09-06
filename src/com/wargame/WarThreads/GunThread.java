package com.wargame.WarThreads;

import com.wargame.Army;
import com.wargame.Gun;
import com.wargame.Soldier;
import com.wargame.WarGameWorld;

import java.util.ArrayList;

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

    public void startGunFire(){
        
    }

    @Override
    public void run() {
        getSoldiersWithGuns();


    }
}
