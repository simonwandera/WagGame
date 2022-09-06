package com.wargame;

public class Gun {
    int bullets;
    int maxBullets;
    String gunSize;

    public String getGunSize() {
        return gunSize;
    }

    public void setGunSize(String gunSize) {
        this.gunSize = gunSize;
    }

    public void reloadBullets() {
        bullets = maxBullets;
    }


    public Gun(String size, int bullets) {
        this.maxBullets = 5;
        reloadBullets();
        this.gunSize = size;
        this.bullets = bullets;
    }

    public void shootMissiles() {
        if (bullets > 0)
        {
            if (this.gunSize.equals("small")){
                bullets --;
                System.out.println("------");
            }
            else if(this.gunSize.equals("medium")) {
                bullets --;
                System.out.println("---------------");
            } else if (this.gunSize.equals("large")) {
                bullets --;
                System.out.println("------------------------------");
                
            }
        }else {
            System.out.println("Reloadin ...");
            this.setBullets(15);
        }
    }
    public int getBullets() {
        return bullets;
    }

    public void setBullets(int bullets) {
        this.bullets = bullets;
    }

    public int getMaxBullets() {
        return maxBullets;
    }

    public void setMaxBullets(int maxBullets) {
        this.maxBullets = maxBullets;
    }
}
