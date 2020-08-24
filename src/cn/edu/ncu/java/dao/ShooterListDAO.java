package cn.edu.ncu.java.dao;

import java.util.Vector;

public interface ShooterListDAO {
    public Vector showAllShooterListForFemale(JDBCAdapter jdbc);
    public Vector showAllShooterListForMale(JDBCAdapter jdbc);
    public Vector showAllShooterListForAdult(JDBCAdapter jdbc);
    public Vector showAllShooterList(String selecting,JDBCAdapter jdbc);
}
