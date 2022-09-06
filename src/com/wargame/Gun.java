package com.wargame;

public class Gun {
    int bullets;

    public Gun(String size, int bullets) {
        this.bullets = bullets;
    }
    public int getBullets() {
        return bullets;
    }

    public void setBullets(int bullets) {
        this.bullets = bullets;
    }
    
    public void shoot(){
        this.bullets--;
    }
}
