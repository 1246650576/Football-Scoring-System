package cn.edu.ncu.java.dao;

import cn.edu.ncu.java.entity.Judage;

import java.util.Vector;

public class JudgeDAOImpl implements JudgeDAO{
    /**
     * @Description: 插入惩罚信息
     * @Name: insertJudge
     * @Param: [judage, jdbc]
     * @Return:void
     */
    @Override
    public void insertJudge(Judage judage, JDBCAdapter jdbc) {
        String selecting="select teamname from judge where judgetime='"+judage.getJudgeTime()+"'";
        Vector exist=jdbc.query(selecting);
        if(exist.size()>0){
            System.out.println("该进球信息已经存在，请重新填写！");
            return;
        }
        String sql="insert into judge"+"(teamname,numbers,redcard,yellowcard,turn,judgetime) values" +
                "('"+judage.getTeamName()+"','"+judage.getNumbers()+"','"+judage.getRedCard()+"','"
                + judage.getYellowCard()+"','"
                + judage.getTurn()+"','"
                + judage.getJudgeTime()+"')";
        System.out.println(sql);
        try{
            jdbc.insert(sql);
        }
        catch(Exception ex){
            System.out.println( ex.getMessage());
        }
    }
    /**
     * @Description: 显示所有罚单信息
     * @Name: showAllJudge
     * @Param: [jdbc]
     * @Return:java.util.Vector
     */
    @Override
    public Vector showAllJudge(JDBCAdapter jdbc) {
        String selecting = "select * from judge";
        Vector exist = jdbc.query(selecting);
        if(exist.size() == 0){
            System.out.println("查询信息不存在！");
            return null;
        }
        return exist;
    }
}
