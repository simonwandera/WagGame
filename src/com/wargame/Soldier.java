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

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void assignWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void shoot(){
        if (weapon instanceof Gun) {
            System.out.println(this.militaryId + " Shooting \uD83D\uDD2B");
            weapon.gunShoot();
        }
        else if (weapon instanceof Jet) {
            System.out.println(this.militaryId + " Firing \uD83D\uDE80");
            weapon.fireMissiles();
        }
        else if ( weapon instanceof Bomb)
            System.out.println(this.militaryId + " Bombing \uD83D\uDCA3");
            weapon.explode();
    }
    public void shot() {
        setAlive(false);
        System.out.println(this.militaryId + " Was just killed \uD83D\uDC7E");
    }

}
