package com.wargame;

import java.util.ArrayList;
import java.util.Random;

public class Army {
    private ArrayList<Soldier> soldiers;

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
