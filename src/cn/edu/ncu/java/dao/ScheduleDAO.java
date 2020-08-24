package cn.edu.ncu.java.dao;

import cn.edu.ncu.java.entity.Schedule;

import java.util.Vector;

public interface ScheduleDAO {
    public void insertSchedule(Schedule schedule, JDBCAdapter jdbc);
    public void updateSchedule(Schedule schedule, JDBCAdapter jdbc);
    public void deleteSchedule(Schedule schedule, JDBCAdapter jdbc);
    public Vector showAllSchedule(JDBCAdapter jdbc);
}
