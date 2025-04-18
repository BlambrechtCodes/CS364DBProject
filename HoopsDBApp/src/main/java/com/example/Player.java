package com.example;

public class Player {
    private int playerId;
    private int teamId;
    private String name;
    private String position;
    private double height;

    public Player(int playerId, int teamId, String name, String position, double height) {
        this.playerId = playerId;
        this.teamId = teamId;
        this.name = name;
        this.position = position;
        this.height = height;
    }

    // Getters and Setters
    public int getPlayerId() { return playerId; }
    public void setPlayerId(int playerId) { this.playerId = playerId; }

    public int getTeamId() { return teamId; }
    public void setTeamId(int teamId) { this.teamId = teamId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }
}
