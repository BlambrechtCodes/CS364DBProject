USE lambrecht5083_CollegeHoopsDB;

-- Create the Team table
CREATE TABLE Team (
    TeamID INT AUTO_INCREMENT PRIMARY KEY,
    Region VARCHAR(100),
    Name VARCHAR(255)
);

-- Create the Player table
CREATE TABLE Player (
    PlayerID INT AUTO_INCREMENT PRIMARY KEY,
    TeamID INT,
    Height FLOAT,
    Name VARCHAR(255),
    Position VARCHAR(50),
    FOREIGN KEY (TeamID) REFERENCES Team(TeamID)
);

-- Create the Schedule table
CREATE TABLE Schedule (
    ScheduleID INT AUTO_INCREMENT PRIMARY KEY,
    Broadcasting VARCHAR(255),
    Date DATE,
    Tickets VARCHAR(255),
    Location VARCHAR(255)
);

-- Create the Game table
CREATE TABLE Game (
    GameID INT AUTO_INCREMENT PRIMARY KEY,
    Round VARCHAR(50),
    ScheduleID INT,
    FOREIGN KEY (ScheduleID) REFERENCES Schedule(ScheduleID)
);

-- Create the Bracket table
CREATE TABLE Bracket (
    BracketID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255),
    Champion INT,
    FOREIGN KEY (Champion) REFERENCES Team(TeamID)
);

-- I added the relationship tables below for you to look over. I have not implemented the below code in the actual database yet. 
-- Let me know what you think!

-- Create the PlayerGameStats relationship table (Many-to-Many between Player and Game)
-- CREATE TABLE PlayerGameStats (
--     PlayerID INT,
--     GameID INT,
--     STL INT,
--     BLK INT,
--     MIN INT,
--     FG_Percentage FLOAT,
--     Turnovers INT,
--     ThreePoint_Percentage FLOAT,
--     PointsScored INT,
--     FreeThrow_Percentage FLOAT,
--     Rebounds INT,
--     Assists INT,
--     PersonalFouls INT,
--     PRIMARY KEY (PlayerID, GameID),
--     FOREIGN KEY (PlayerID) REFERENCES Player(PlayerID),
--     FOREIGN KEY (GameID) REFERENCES Game(GameID)
-- );

-- Create the GameParticipation relationship table (Many-to-Many between Player and Game)
-- CREATE TABLE GameParticipation (
--     PlayerID INT,
--     GameID INT,
--     PRIMARY KEY (PlayerID, GameID),
--     FOREIGN KEY (PlayerID) REFERENCES Player(PlayerID),
--     FOREIGN KEY (GameID) REFERENCES Game(GameID)
-- );

-- Create the BelongTo relationship table (Many-to-Many between Player and Team)
-- CREATE TABLE BelongTo (
--     PlayerID INT,
--     TeamID INT,
--     PRIMARY KEY (PlayerID, TeamID),
--     FOREIGN KEY (PlayerID) REFERENCES Player(PlayerID),
--     FOREIGN KEY (TeamID) REFERENCES Team(TeamID)
-- );

-- Create the TeamGameStats relationship table (Many-to-Many between Team and Game)
-- CREATE TABLE TeamGameStats (
--     TeamID INT,
--     GameID INT,
--     PointsScored INT,
--     PRIMARY KEY (TeamID, GameID),
--     FOREIGN KEY (TeamID) REFERENCES Team(TeamID),
--     FOREIGN KEY (GameID) REFERENCES Game(GameID)
-- );

-- Create the GameInBracket relationship table (Many-to-One between Game and Bracket)
-- CREATE TABLE GameInBracket (
--     GameID INT,
--     BracketID INT,
--     PRIMARY KEY (GameID, BracketID),
--     FOREIGN KEY (GameID) REFERENCES Game(GameID),
--     FOREIGN KEY (BracketID) REFERENCES Bracket(BracketID)
-- );

-- Create the ScheduledGame relationship table (Many-to-One between Game and Schedule)
-- CREATE TABLE ScheduledGame (
--     GameID INT,
--     ScheduleID INT,
--     PRIMARY KEY (GameID, ScheduleID),
--     FOREIGN KEY (GameID) REFERENCES Game(GameID),
--     FOREIGN KEY (ScheduleID) REFERENCES Schedule(ScheduleID)
-- );

-- Create the TeamBracket relationship table (Many-to-Many between Team and Bracket)
-- CREATE TABLE TeamBracket (
--     TeamID INT,
--     BracketID INT,
--     Seed INT,
--     Stage VARCHAR(50),
--     PRIMARY KEY (TeamID, BracketID),
--     FOREIGN KEY (TeamID) REFERENCES Team(TeamID),
--     FOREIGN KEY (BracketID) REFERENCES Bracket(BracketID)
-- );

