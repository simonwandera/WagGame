package com.wargame;

public abstract class Weapon {
    String weaponNo;
    private int bullets;
    private boolean active = true;
    private String type;
    public Weapon(String weaponNo, int bullets, String type) {
        this.weaponNo = weaponNo;
        this.bullets = bullets;
        this.type = type;

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
