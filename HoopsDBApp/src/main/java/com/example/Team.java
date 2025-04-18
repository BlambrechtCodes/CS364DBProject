package com.example;

public class Team {
    private int teamId;
    private String name;
    private String region;

    public Team(int teamId, String name, String region) {
        this.teamId = teamId;
        this.name = name;
        this.region = region;
    }

    // Getters and Setters
    public int getTeamId() { return teamId; }
    public void setTeamId(int teamId) { this.teamId = teamId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
}
