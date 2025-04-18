package com.example;

public class PlayerGameStat {
    private int playerID;
    private int gameID;
    private int teamID;
    private int stl;
    private int blk;
    private int min;
    private double fgPercentage;
    private double threePPercentage;
    private double ftPercentage;
    private int reb;
    private int pf;
    private int ast;
    private int turnover;
    private int pts;

    public PlayerGameStat(int playerID, int gameID, int teamID, int stl, int blk, int min, 
                         double fgPercentage, double threePPercentage, double ftPercentage,
                         int reb, int pf, int ast, int turnover, int pts) {
        this.playerID = playerID;
        this.gameID = gameID;
        this.teamID = teamID;
        this.stl = stl;
        this.blk = blk;
        this.min = min;
        this.fgPercentage = fgPercentage;
        this.threePPercentage = threePPercentage;
        this.ftPercentage = ftPercentage;
        this.reb = reb;
        this.pf = pf;
        this.ast = ast;
        this.turnover = turnover;
        this.pts = pts;
    }

    // Getters
    public int getPlayerID() { return playerID; }
    public int getGameID() { return gameID; }
    public int getTeamID() { return teamID; }
    public int getStl() { return stl; }
    public int getBlk() { return blk; }
    public int getMin() { return min; }
    public double getFgPercentage() { return fgPercentage; }
    public double getThreePPercentage() { return threePPercentage; }
    public double getFtPercentage() { return ftPercentage; }
    public int getReb() { return reb; }
    public int getPf() { return pf; }
    public int getAst() { return ast; }
    public int getTurnover() { return turnover; }
    public int getPts() { return pts; }
}
