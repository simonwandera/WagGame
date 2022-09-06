package com.wargame;

public class Gun extends Weapon {
    int bullets;
    String gunNumber;

    public Gun(String size, int bullets, String gunNumber) {
        this.bullets = bullets;
        this.gunNumber = gunNumber;
    }
    public int getBullets() {
        return bullets;
    }

    public void setBullets(int bullets) {
        this.bullets = bullets;
    }

    public boolean isLoaded(){
        if(this.bullets > 0)
            return true;
        else;
        return false;
    }

    public void shoot(){
        this.bullets--;
        System.out.println("Gun " + this.gunNumber + " shooting" );
    }
}
