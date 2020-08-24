package cn.edu.ncu.java.dao;


import cn.edu.ncu.java.entity.GoalOfPlayer;

import java.util.Vector;

public interface GoalOfPlayerDAO {
    public void insertGoalOfPlayer(GoalOfPlayer goalofplayer, JDBCAdapter jdbc);
    public Vector showAllGoalOfPlayer(JDBCAdapter jdbc);
}
