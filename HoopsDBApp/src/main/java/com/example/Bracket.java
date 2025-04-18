package com.example;

public class Bracket {
    private int bracketID;
    private String bracketName;
    private int champion;

    public Bracket(int bracketID, String bracketName, int champion) {
        this.bracketID = bracketID;
        this.bracketName = bracketName;
        this.champion = champion;
    }

   // Getters and Setters
   public int getBracketID() { return bracketID;}
   public void setBracketID(int bracketID) { this.bracketID = bracketID; }

   public String getBracketName() { return bracketName; }
   public void setBracketName(String bracketName) { this.bracketName = bracketName; }

   public int getChampion() { return champion; }

    public void setChampion(int champion) {
        this.champion = champion;
    }
}
