package com.wargame;

public class Bomb extends Weapon {
    private String bombNo;
    private boolean loaded = true;

    public Bomb(String bombNo) {
        super(bombNo);
    }

    public void explode(){
        super.setActive(false);
    }
}