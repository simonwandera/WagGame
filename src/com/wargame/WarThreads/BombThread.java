package com.wargame.WarThreads;

import com.wargame.*;

import java.util.ArrayList;

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

    @Override
    public void run() {

    }
}
