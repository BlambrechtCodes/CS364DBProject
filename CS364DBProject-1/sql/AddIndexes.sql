-- Team Table
ALTER TABLE Team ADD INDEX idx_team_name (Name);

-- Player Table
ALTER TABLE Player ADD INDEX idx_player_name (Name);
ALTER TABLE Player ADD INDEX idx_player_teamID (TeamID);

-- Bracket Table
ALTER TABLE Bracket ADD INDEX idx_bracket_champion (Champion);

-- Game Table
ALTER TABLE Game ADD INDEX idx_game_scheduleID (ScheduleID);
ALTER TABLE Game ADD INDEX idx_game_round (Round);

-- Schedule Table
ALTER TABLE Schedule ADD INDEX idx_schedule_date (Date);

-- GameParticipation Table
ALTER TABLE GameParticipation ADD INDEX idx_gameparticipation_playerID (PlayerID);
ALTER TABLE GameParticipation ADD INDEX idx_gameparticipation_gameID (GameID);

-- PlayerGameStats Table
ALTER TABLE PlayerGameStats ADD INDEX idx_playergamestats_playerID (PlayerID);
ALTER TABLE PlayerGameStats ADD INDEX idx_playergamestats_gameID (GameID);

-- BelongTo Table
ALTER TABLE BelongTo ADD INDEX idx_belongto_playerID (PlayerID);
ALTER TABLE BelongTo ADD INDEX idx_belongto_teamID (TeamID);

-- TeamGameStats Table
ALTER TABLE TeamGameStats ADD INDEX idx_teamgamestats_teamID (TeamID);
ALTER TABLE TeamGameStats ADD INDEX idx_teamgamestats_gameID (GameID);

--

-- GamesinBracket Table
ALTER TABLE GameInBracket ADD INDEX idx_gamesinbracket_gameID (GameID);
ALTER TABLE GameInBracket ADD INDEX idx_gamesinbracket_bracketID (BracketID);


-- ScheduledGame Table
ALTER TABLE ScheduledGame ADD INDEX idx_scheduledgame_gameID (GameID);
ALTER TABLE ScheduledGame ADD INDEX idx_scheduledgame_scheduleID (ScheduleID);

ALTER TABLE TeamBracket
CHANGE COLUMN TeamId TeamID INT;

-- TeamBracket Table (Assuming it already exists)
ALTER TABLE TeamBracket ADD INDEX idx_teambracket_teamID (TeamID);
ALTER TABLE TeamBracket ADD INDEX idx_teambracket_bracketID (BracketID);