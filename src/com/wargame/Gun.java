package com.wargame;

public class Gun extends Weapon {
    int bullets;
    String gunNumber;

    public Gun(String gunNumber, int bullets, String type) {
        super(gunNumber, bullets, type);

    }
    public int getBullets() {
        return bullets;
    }

    public void setBullets(int bullets) {
        this.bullets = bullets;
    }

    @Override
    public boolean isActive() {
        return super.isActive();
    }


}
