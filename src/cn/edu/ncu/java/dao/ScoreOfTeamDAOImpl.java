package cn.edu.ncu.java.dao;

import java.util.Vector;

public class ScoreOfTeamDAOImpl implements ScoreOfTeamDAO{
    /**
     * @Description: 显示男子组的球队积分榜
     * @Name: showAllScoreOfTeamForFemale
     * @Param: [jdbc]
     * @Return:java.util.Vector
     */
    @Override
    public Vector showAllScoreOfTeamForFemale(JDBCAdapter jdbc) {
        String selecting = "select * from scoreofteam where teamname in(select distinct team from player where gender='女') order by ranking";
        return showAllScoreOfTeam(selecting,jdbc);
    }
    /**
     * @Description: 显示女子组的球队积分榜
     * @Name: showAllScoreOfTeamForMale
     * @Param: [jdbc]
     * @Return:java.util.Vector
     */
    @Override
    public Vector showAllScoreOfTeamForMale(JDBCAdapter jdbc) {
        String selecting = "select * from scoreofteam where teamname not in(select distinct team from player where gender='女') and teamname not in(select name from team where college='无') order by ranking";
        return showAllScoreOfTeam(selecting,jdbc);
    }
    /**
     * @Description: 显示成年组的球队积分榜
     * @Name: showAllScoreOfTeamForAdult
     * @Param: [jdbc]
     * @Return:java.util.Vector
     */
    @Override
    public Vector showAllScoreOfTeamForAdult(JDBCAdapter jdbc) {
        String selecting = "select * from scoreofteam where teamname in(select name from team where college='无') order by ranking";
        return showAllScoreOfTeam(selecting,jdbc);
    }
    /**
     * @Description: 显示球队积分榜
     * @Name: showAllScoreOfTeam
     * @Param: [selecting, jdbc]
     * @Return:java.util.Vector
     */
    @Override
    public Vector showAllScoreOfTeam(String selecting,JDBCAdapter jdbc) {
        Vector exist = jdbc.query(selecting);
        if(exist.size() == 0){
            System.out.println("查询信息不存在！");
            return null;
        }
        return exist;
    }
}
