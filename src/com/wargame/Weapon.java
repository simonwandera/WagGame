package com.wargame;

public abstract class Weapon {
    String weaponNo;
    private int bullets;
    private boolean active;
    public Weapon(String weaponNo, int bullets) {
        this.weaponNo = weaponNo;
        this.bullets = bullets;

        if(bullets < 1)
            this.setActive(false);
    }

    public Weapon(String weaponNo) {
        this.weaponNo = weaponNo;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void gunShoot(){
        this.bullets--;
        System.out.println("Gun " + this.weaponNo + " shooting" );
    }

    public void fireMissiles(){
        if (this.bullets > 0){
            System.out.println(this.weaponNo + " Firing ==============(**)==============");
            this.bullets--;
        }
    }

    public void explode(){
        this.setActive(false);
    }
}
