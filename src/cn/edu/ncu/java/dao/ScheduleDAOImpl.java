package cn.edu.ncu.java.dao;

import cn.edu.ncu.java.entity.Schedule;

import java.util.Vector;

public class ScheduleDAOImpl implements ScheduleDAO{
    @Override
    public void insertSchedule(Schedule schedule, JDBCAdapter jdbc) { }
    @Override
    public void updateSchedule(Schedule schedule, JDBCAdapter jdbc) { }
    @Override
    public void deleteSchedule(Schedule schedule, JDBCAdapter jdbc) { }
    /**
     * @Description: 显示所有赛程信息
     * @Name: showAllSchedule
     * @Param: [jdbc]
     * @Return:java.util.Vector
     */
    @Override
    public Vector showAllSchedule(JDBCAdapter jdbc) {
        String selecting = "select * from schedule";
        Vector exist = jdbc.query(selecting);
        if(exist.size() == 0){
            System.out.println("查询信息不存在！");
            return null;
        }
        return exist;
    }
}
