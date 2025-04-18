public class Game {
    private int gameId;
    private String round;
    private int scheduleId;
    private int team1Id;
    private int team2Id;

    public Game(int gameId, String round, int scheduleId, int team1Id, int team2Id) {
        this.gameId = gameId;
        this.round = round;
        this.scheduleId = scheduleId;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
    }

    // Getters and Setters
    public int getGameId() { return gameId; }
    public void setGameId(int gameId) { this.gameId = gameId; }

    public String getRound() { return round; }
    public void setRound(String round) { this.round = round; }

    public int getScheduleId() { return scheduleId; }
    public void setScheduleId(int scheduleId) { this.scheduleId = scheduleId; }

    public int getTeam1Id() { return team1Id; }
    public void setTeam1Id(int team1Id) { this.team1Id = team1Id; }

    public int getTeam2Id() { return team2Id; }
    public void setTeam2Id(int team2ID) { this.team2Id = team2ID; }
}
