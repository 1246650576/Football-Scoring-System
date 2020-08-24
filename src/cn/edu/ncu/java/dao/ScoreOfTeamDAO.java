package cn.edu.ncu.java.dao;

import java.util.Vector;

public interface ScoreOfTeamDAO {
    public Vector showAllScoreOfTeamForFemale(JDBCAdapter jdbc);
    public Vector showAllScoreOfTeamForMale(JDBCAdapter jdbc);
    public Vector showAllScoreOfTeamForAdult(JDBCAdapter jdbc);
    public Vector showAllScoreOfTeam(String selecting,JDBCAdapter jdbc);
}
