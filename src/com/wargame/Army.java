package com.wargame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Army {
    private ArrayList<Soldier> soldiers;

    public ArrayList<Soldier> getSoldiers() {
        return soldiers;
    }

    private void assignWeapon() {
        for(int i=0; i< (soldiers.size()/2); i++) {
            soldiers.get(i).assignWeapon(new Gun("gun" + i, 50, "Gun"));
        }

        for (int i=(soldiers.size()/2); i< (soldiers.size() * 3/4); i++){
            soldiers.get(i).assignWeapon(new Jet("jet" + i, 10, "Jet"));
        }

        for (int i=(soldiers.size() * 3/4); i< soldiers.size(); i++){
            soldiers.get(i).assignWeapon(new Bomb("bomb" + i));
        }
    }

    public void setSoldiers(ArrayList<Soldier> soldiers) {
        this.soldiers = soldiers;
        this.assignWeapon();
    }

}
