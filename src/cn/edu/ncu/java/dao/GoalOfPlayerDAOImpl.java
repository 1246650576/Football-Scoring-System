package cn.edu.ncu.java.dao;


import cn.edu.ncu.java.entity.GoalOfPlayer;

import java.util.Vector;

public class GoalOfPlayerDAOImpl implements GoalOfPlayerDAO{
    /**
     * @Description: 插入进球信息
     * @Name: insertGoalOfPlayer
     * @Param: [goalofplayer, jdbc]
     * @Return:void
     */
    @Override
    public void insertGoalOfPlayer(GoalOfPlayer goalofplayer, JDBCAdapter jdbc) {
        String selecting="select teamname from goalofplayer where goaltime='"+goalofplayer.getGoalTime()+"'";
        Vector exist=jdbc.query(selecting);
        if(exist.size()>0){
            System.out.println("该进球信息已经存在，请重新填写！");
            return;
        }
        String sql="insert into goalofplayer"+"(teamname,numbers,turn,goaltime) values" +
                "('"+goalofplayer.getTeamName()+"','"+goalofplayer.getNumbers()+"','"+goalofplayer.getTurn()+"','"
                + goalofplayer.getGoalTime()+"');";
        System.out.println(sql);
        try{
            jdbc.insert(sql);
        }
        catch(Exception ex){
            System.out.println( ex.getMessage());
        }
    }
    /**
     * @Description: 显示所有进球信息
     * @Name: showAllGoalOfPlayer
     * @Param: [jdbc]
     * @Return:java.util.Vector
     */
    @Override
    public Vector showAllGoalOfPlayer(JDBCAdapter jdbc) {
        String selecting = "select * from goalofplayer";
        Vector exist = jdbc.query(selecting);
        if(exist.size() == 0){
            System.out.println("查询信息不存在！");
            return null;
        }
        return exist;
    }
}
