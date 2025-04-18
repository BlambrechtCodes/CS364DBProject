public class Bracket {
    private int bracketID;
    private String bracketName;
    private int championTeamID;

    public Bracket(int bracketID, String bracketName, int championTeamID) {
        this.bracketID = bracketID;
        this.bracketName = bracketName;
        this.championTeamID = championTeamID;
    }

   // Getters and Setters
   public int getBracketID() { return bracketID;}
   public void setBracketID(int bracketID) { this.bracketID = bracketID; }

   public String getBracketName() { return bracketName; }
   public void setBracketName(String bracketName) { this.bracketName = bracketName; }

   public int getChampionTeamID() { return championTeamID; }
   public void setChampionTeamID(int championTeamID) { this.championTeamID = championTeamID; }
}
