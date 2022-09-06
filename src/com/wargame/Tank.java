package com.wargame;

public class Tank extends Weapon{

    private int tankNo;
    private int bullets;

    public Tank(int tankNo, int bullets) {
        this.tankNo = tankNo;
        this.bullets = bullets;
    }

    public void shoot(){
        this.bullets--;
        System.out.println("Gun " + this.tankNo + " shooting" );

    }

    public boolean isLoaded(){
        if(this.bullets > 0)
            return true;
        else;
            return false;
    }
}
