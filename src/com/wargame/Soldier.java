package com.wargame;
public class Soldier {
    private String militaryId;
    private boolean alive;
    private Weapon weapon;
    public Soldier(String militaryId) {
        this.militaryId = militaryId;
        this.alive = true;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public boolean isAlive() {
        return alive;
    }

    public void assignWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }
    public void shot() {
        this.alive = false;
        System.out.println(this.militaryId + " Was just killed");
    }
}
