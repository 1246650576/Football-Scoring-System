package cn.edu.ncu.java.dao;

import cn.edu.ncu.java.entity.Judage;

import java.util.Vector;

public interface JudgeDAO {
    public void insertJudge(Judage judage, JDBCAdapter jdbc);
    public Vector showAllJudge(JDBCAdapter jdbc);
}
