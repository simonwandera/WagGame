package com.wargame;

public abstract class Weapon {
    String weaponNo;
    int bullets;
    boolean active;
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
}
