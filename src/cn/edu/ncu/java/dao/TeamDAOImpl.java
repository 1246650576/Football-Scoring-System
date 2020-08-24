package cn.edu.ncu.java.dao;

import cn.edu.ncu.java.entity.Team;
import cn.edu.ncu.java.utils.DataConvertor;

import java.awt.*;
import java.util.Vector;

public class TeamDAOImpl extends Component implements TeamDAO{
    /**
     * @Description: 增加球队
     * @Name: insertTeam
     * @Param: [team, jdbc]
     * @Return:void
     */
    @Override
    public void insertTeam(Team team,JDBCAdapter jdbc) {
        String selecting = "select name from team where name='"+team.getName()+"'";
        Vector exist = jdbc.query(selecting);
        if(exist.size()>0){
            System.out.println("该球队已经存在，请重新填写！");
            return;
        }
        String sql="insert into team"+"(name,college,coach,setTime) " +
                "values('"+team.getName()+"','"+team.getCollege()+"','"+team.getCoach()+"','"+
                team.getSetTime()+"')";
              //  DataConvertor.converFromLocal(team.getSetTime())+"')";
        System.out.println(sql);
        try{
            jdbc.insert(sql);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    /**
     * @Description: 修改球队
     * @Name: deleteTeam
     * @Param: [teamname, jdbc]
     * @Return:void
     */
    @Override
    public void deleteTeam(Team team,JDBCAdapter jdbc) {
        String sql = "delete from team where name ='"+team.getName()+"'";
        System.out.println(sql);
        try{
            jdbc.delete(sql);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    /**
     * @Description: 修改球队信息
     * @Name: updateTeam
     * @Param: [newTeamName, team, jdbc]
     * @Return:int
     */
    @Override
    public int updateTeam(String newTeamName,Team team,JDBCAdapter jdbc) {
        if(!team.getName().equals(newTeamName)) {
            String selecting = "select name from team where name ='" + newTeamName + "'";
            Vector exist = jdbc.query(selecting);
            if (exist.size() > 0) {
                System.out.println("该球队已经存在，请重新填写！");
                return 0;
            }
        }
        String sql = "update team set name ='" + newTeamName + "',college ='" + team.getCollege()+
                     "',coach ='" + team.getCoach() + "',setTime ='" + team.getSetTime()
                     + "' where name ='" + team.getName() + "'";
        try{
            jdbc.update(sql);
        }catch(Exception  ex){
            System.out.println(ex.getMessage());
            return 0;
        }
        return 1;
    }
    /**
     * @Description: 查询队伍信息
     * @Name: findTeam
     * @Param: [teamname, jdbc]
     * @Return:java.util.Vector
     */
    @Override
    public Vector findTeam(String teamname, JDBCAdapter jdbc) {
        String selecting = "select * from team where name='"+teamname+"'";
        System.out.println(selecting);
        return jdbc.query(selecting);
    }

    /**
     * @Description: 显示所有球队信息
     * @Name: showAllTeam
     * @Param: [jdbc]
     * @Return:java.util.Vector
     */
    @Override
    public Vector showAllTeam(JDBCAdapter jdbc) {
        String selecting = "select * from team";
        Vector exist = jdbc.query(selecting);
        if(exist.size() == 0){
            System.out.println("查询信息不存在！");
            return null;
        }
        return exist;
    }
}
