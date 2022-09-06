package com.wargame;

public class Gun extends Weapon {
    int bullets;
    String gunNumber;

    public Gun(String gunNumber, int bullets) {
        super(gunNumber, bullets);

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

    public void shoot(){
        this.bullets--;
        System.out.println("Gun " + this.gunNumber + " shooting" );
    }
}
