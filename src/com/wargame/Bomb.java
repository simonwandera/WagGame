package com.wargame;

public class Bomb extends Weapon {
    private int bombNo;
    private boolean loaded = true;

    public Bomb(int bombNo) {
        this.bombNo = bombNo;
    }

    public void explode(){
        this.loaded = false;
    }

    public boolean isLoaded(){
        return loaded;
    }
}