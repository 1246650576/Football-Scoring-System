package cn.edu.ncu.java.dao;

import cn.edu.ncu.java.entity.Player;

import java.util.Vector;

public interface PlayerDAO {
    public void insertPlayer(Player player,JDBCAdapter jdbc);
    public void deletePlayer(Player player,JDBCAdapter jdbc);
    public void updatePlayer(String newteamname,String newnumber,Player player,JDBCAdapter jdbc);
    public Vector findPlayer(String team, String numbers,JDBCAdapter jdbc);
    public Vector showAllPlayer(JDBCAdapter jdbc);
}


