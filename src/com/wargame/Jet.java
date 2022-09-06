package com.wargame;

public class Jet extends Weapon {
    private int missiles;
    private String jetNo;
    private String type;

    public Jet(String jetNo, int missiles, String type){
        super(jetNo, missiles, type);
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