package com.wargame;

public class Jet extends Weapon {
    int missiles;
    String jetNo;

    public Jet(String jetNo, int missiles){
        this.missiles = missiles;
        this.jetNo = jetNo;
    }

    public int getMissiles() {
        return missiles;
    }

    public boolean isLoaded(){
        if(missiles > 0)
            return true;
        else
            return false;
    }

    public void fireMissiles(){
        if (this.missiles > 0){
            System.out.println(this.jetNo + " Firing ==============(**)==============");
            this.missiles--;
        }
    }
}