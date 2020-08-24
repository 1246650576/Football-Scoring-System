package cn.edu.ncu.java.dao;

import java.util.Vector;

public class ShooterListDAOImpl implements ShooterListDAO{
    /**
     * @Description: 显示射手榜
     * @Name: showAllShooterList
     * @Param: [selecting, jdbc]
     * @Return:java.util.Vector
     */
    @Override
    public Vector showAllShooterList(String selecting,JDBCAdapter jdbc) {
        Vector exist = jdbc.query(selecting);
        if(exist.size() == 0){
            System.out.println("查询信息不存在！");
            return null;
        }
        return exist;
    }
    /**
     * @Description: 显示女子组的射手榜
     * @Name: showAllShooterListForFemale
     * @Param: [jdbc]
     * @Return:java.util.Vector
     */
    @Override
    public Vector showAllShooterListForFemale(JDBCAdapter jdbc) {
        String selecting = "select * from shooterlist where teamname in(select distinct team from player where gender='女') order by ranking";
        return showAllShooterList(selecting,jdbc);
    }
    /**
     * @Description: 显示男子组的射手榜
     * @Name: showAllShooterListForMale
     * @Param: [jdbc]
     * @Return:java.util.Vector
     */
    @Override
    public Vector showAllShooterListForMale(JDBCAdapter jdbc) {
        String selecting = "select * from shooterlist where teamname not in(select name from team where college=NULL) and teamname not in(select distinct team from player where gender='女') order by ranking";
        return showAllShooterList(selecting,jdbc);
    }
    /**
     * @Description: 显示成年组的射手榜
     * @Name: showAllShooterListForAdult
     * @Param: [jdbc]
     * @Return:java.util.Vector
     */
    @Override
    public Vector showAllShooterListForAdult(JDBCAdapter jdbc) {
        String selecting = "select * from shooterlist where teamname in(select name from team where college=NULL) order by ranking";
        return showAllShooterList(selecting,jdbc);
    }
}
