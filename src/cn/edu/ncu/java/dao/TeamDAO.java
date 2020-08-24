package cn.edu.ncu.java.dao;


import cn.edu.ncu.java.entity.Team;

import java.util.Vector;

public interface TeamDAO {
    public void insertTeam(Team team,JDBCAdapter jdbc);
    public void deleteTeam(Team team,JDBCAdapter jdbc);
    public int updateTeam(String newTeamName,Team team,JDBCAdapter jdbc);
    public Vector findTeam(String teamname, JDBCAdapter jdbc);
    public Vector showAllTeam(JDBCAdapter jdbc);
}
