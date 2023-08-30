CREATE DATABASE FIFA;

--ATTACH DATABASE 'FIFA.db' AS FIFA;

CREATE TABLE IF NOT EXISTS FIFA.Teams (
    TeamID INTEGER PRIMARY KEY,
    TeamName TEXT,
    Country TEXT,
    Coach TEXT,
    FoundationYear INTEGER
);

CREATE TABLE IF NOT EXISTS FIFA.Players (
    PlayerID INTEGER PRIMARY KEY,
    TeamID INTEGER,
    PlayerName TEXT,
    Age INTEGER,
    Nationality TEXT,
    Position TEXT,
    ShirtNumber INTEGER,
    FOREIGN KEY (TeamID) REFERENCES Teams(TeamID)
);

CREATE TABLE IF NOT EXISTS FIFA.Matches (
    MatchID INTEGER PRIMARY KEY,
    HomeTeamID INTEGER,
    AwayTeamID INTEGER,
    MatchDate DATE,
    Stadium TEXT,
    ScoreHome INTEGER,
    ScoreAway INTEGER,
    FOREIGN KEY (HomeTeamID) REFERENCES Teams(TeamID),
    FOREIGN KEY (AwayTeamID) REFERENCES Teams(TeamID)
);

CREATE TABLE IF NOT EXISTS FIFA.Goals (
    GoalID INTEGER PRIMARY KEY,
    MatchID INTEGER,
    PlayerID INTEGER,
    Minute INTEGER,
    FOREIGN KEY (MatchID) REFERENCES Matches(MatchID),
    FOREIGN KEY (PlayerID) REFERENCES Players(PlayerID)
);

INSERT INTO Teams (TeamName, Country, Coach, FoundationYear)
VALUES
    ('Brazil', 'Brazil', 'Tite', 1914),
    ('Germany', 'Germany', 'Joachim Löw', 1900),
    ('France', 'France', 'Didier Deschamps', 1904),
    ('Argentina', 'Argentina', 'Lionel Scaloni', 1901);

INSERT INTO Players (TeamID, PlayerName, Age, Nationality, Position, ShirtNumber)
VALUES
    (1, 'Neymar Jr.', 29, 'Brazil', 'Forward', 10),
    (2, 'Toni Kroos', 31, 'Germany', 'Midfielder', 8),
    (3, 'Kylian Mbappé', 23, 'France', 'Forward', 7),
    (4, 'Lionel Messi', 34, 'Argentina', 'Forward', 10);

INSERT INTO Matches (HomeTeamID, AwayTeamID, MatchDate, Stadium, ScoreHome, ScoreAway)
VALUES
    (1, 2, '2018-06-14', 'Luzhniki Stadium', 2, 1),
    (3, 4, '2018-06-15', 'Kazan Arena', 2, 0),
    (2, 3, '2018-06-16', 'Fisht Olympic Stadium', 1, 2),
    (4, 1, '2018-06-17', 'Otkritie Arena', 0, 3);

INSERT INTO Goals (MatchID, PlayerID, Minute)
VALUES
    (1, 1, 20),
    (1, 2, 48),
    (2, 3, 65),
    (3, 3, 70),
    (3, 4, 81),
    (4, 1, 32);


SELECT TeamName, Coach FROM Teams;

SELECT PlayerName FROM Players WHERE Position = 'Forward';

SELECT * FROM Matches WHERE ScoreHome > 1;

SELECT P.PlayerName, COUNT(G.GoalID) AS GoalsScored FROM Players P JOIN Goals G ON P.PlayerID = G.PlayerID GROUP BY P.PlayerName ORDER BY GoalsScored DESC
LIMIT 3;

SELECT T.TeamName, M.MatchDate FROM Teams T LEFT JOIN Matches M ON T.TeamID = M.HomeTeamID OR T.TeamID = M.AwayTeamID LEFT JOIN Goals G ON M.MatchID = G.MatchID
WHERE G.GoalID IS NULL;

SELECT T.TeamName, AVG(P.Age) AS AverageAge FROM Teams T JOIN Players P ON T.TeamID = P.TeamID GROUP BY T.TeamName;

SELECT M.MatchDate, T1.TeamName AS HomeTeam, T2.TeamName AS AwayTeam, M.ScoreHome, M.ScoreAway
FROM Matches M JOIN Teams T1 ON M.HomeTeamID = T1.TeamID JOIN Teams T2 ON M.AwayTeamID = T2.TeamID WHERE M.ScoreHome > 0 AND M.ScoreAway > 0;

