public class PlayerGameStat {
    private int playerID;
    private int gameID;
    private int stl;
    private int blk;
    private int min;
    private double fgPercentage;
    private double threePPercenage;
    private double ftPercentage;
    private int reb;
    private int pf;
    private int ast;
    private int turnover;
    private int pts;

    public PlayerGameStat(int playerID, int gameID, int stl, int blk, int min, 
                         double fgPercentage, double threePPercenage, double ftPercentage,
                         int reb, int pf, int ast, int turnover, int pts) {
        this.playerID = playerID;
        this.gameID = gameID;
        this.stl = stl;
        this.blk = blk;
        this.min = min;
        this.fgPercentage = fgPercentage;
        this.threePPercenage = threePPercenage;
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
    public int getSTL() { return stl; }
    public int getBLK() { return blk; }
    public int getMIN() { return min; }
    public double getFG_Percentage() { return fgPercentage; }
    public double get3P_Percentage() { return threePPercenage; }
    public double getFT_Percentage() { return ftPercentage; }
    public int getREB() { return reb; }
    public int getPF() { return pf; }
    public int getAST() { return ast; }
    public int getTurnover() { return turnover; }
    public int getPTS() { return pts; }
}
