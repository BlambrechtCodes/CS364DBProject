package com.example;

public class Game {
    private int gameId;
    private String round;
    private int scheduleId;
    private int team1Id;
    private int team2Id;
    private int bracketID; // Add this

    public Game(int gameId, String round, int scheduleId, int team1Id, int team2Id, int bracketID) { // Update constructor
        this.gameId = gameId;
        this.round = round;
        this.scheduleId = scheduleId;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.bracketID = bracketID; //add
    }

    // Getters
    public int getGameId() { return gameId; }
    public String getRound() { return round; }
    public int getScheduleId() { return scheduleId; }
    public int getTeam1Id() { return team1Id; }
    public int getTeam2Id() { return team2Id; }
    public int getBracketID() { return bracketID; } //add
}
