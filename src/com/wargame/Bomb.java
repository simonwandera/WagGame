package com.wargame;

public class Bomb extends Weapon {
    private String bombNo;
    private boolean loaded = true;

    public Bomb(String bombNo) {
        this.bombNo = bombNo;
    }

    public void explode(){
        this.loaded = false;
    }

    public boolean isLoaded(){
        return loaded;
    }
}