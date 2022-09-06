package com.wargame;

import java.util.ArrayList;
import java.util.Random;

public class Army {
//    private Soldier[] soldiers;
    private ArrayList<Soldier> soldiers;
//    private Gun[] guns;

    public Army() {
//        guns = new Gun[maxGuns];
    }

    public ArrayList<Soldier> getSoldiers() {
        return soldiers;
    }

    private void assignWeapon() {

    }

    public void setSoldiers(ArrayList<Soldier> soldiers) {
        this.soldiers = soldiers;
        this.assignWeapon();
    }

}
