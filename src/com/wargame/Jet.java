package com.wargame;

public class Jet {
    int missiles;
    String jetNo;

    public Jet(int missiles, String jetNo){
        this.missiles = missiles;
        this.jetNo = jetNo;
    }

    public int getMissiles() {
        return missiles;
    }

    public void fireMissiles(){
        if (this.missiles > 0){
            System.out.println(this.jetNo + " Firing ==============(**)==============");
            this.missiles--;
        }
    }
}