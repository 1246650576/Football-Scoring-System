package cn.edu.ncu.java.dao;

import cn.edu.ncu.java.entity.Player;
import cn.edu.ncu.java.utils.DataConvertor;

import java.awt.*;
import java.util.Vector;

public class PlayerDAOImpl extends Component implements PlayerDAO {
    /**
     * @Description: 插入队员信息
     * @Name: insertPlayer
     * @Param: [player, jdbc]
     * @Return:void
     */
    @Override
    public void insertPlayer(Player player,JDBCAdapter jdbc) {
        String selecting="select name from player where team ='"+player.getTeam()+"'and numbers ='"+ player.getNumber()+"'";
        Vector exist=jdbc.query(selecting);
        if(exist.size()>0){
            System.out.println("该球员信息已经存在，请重新填写！");
            return;
        }
        String sql="insert into player"+"(name,age,team,numbers,gender,position,height,weight,birthdate) values" +
                "('"+player.getName()+"','"+player.getAge()+"','"+player.getTeam()+"','"+player.getNumber()
                +"','"+player.getGender()+"','"+player.getPosition()
                +"','"+player.getHeight()+"','"+player.getWeight()+"','"
                +player.getBirthdate()+"');";
                //+ DataConvertor.converFromLocal(player.getBirthdate())+"')";
        System.out.println(sql);
        try{
            jdbc.insert(sql);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    /**
     * @Description: 删除某一队员信息
     * @Name: deletePlayer
     * @Param: [player, jdbc]
     * @Return:void
     */
    @Override
    public void deletePlayer(Player player,JDBCAdapter jdbc) {
        String sql = "delete from player where team ='"+player.getTeam()+"'and numbers ='"+ player.getNumber()+"'";
        System.out.println(sql);
        try{
            jdbc.delete(sql);
        }catch(Exception ex){
            System.out.println( ex.getMessage());
        }
    }
    /**
     * @Description: 更新队员信息
     * @Name: updatePlayer
     * @Param: [newteamname, newnumber, player, jdbc]
     * @Return:void
     */
    @Override
    public void updatePlayer(String newteamname,String newnumber,Player player,JDBCAdapter jdbc) {
        if(! newteamname.equals(player.getTeam()) && newnumber.equals(player.getNumber())){
            String selecting = "select * from player where team = '"+newteamname+"'and numbers ='"+newnumber+"'";
            Vector exist = jdbc.query(selecting);
            if(exist.size()>0){
                System.out.println("该球员已存在，请重新填写");
                return;
            }
            String sql="insert into player"+"(name,age,team,numbers,gender,position,height,weight,birthdate) values" +
                    "('"+player.getName()+"','"+player.getAge()+"','"+newteamname+"','"+newnumber
                    +"','"+player.getGender()+"','"+player.getPosition()
                    +"','"+player.getHeight()+"','"+player.getWeight()+"','"
                    +player.getBirthdate()+"')";
                //    + DataConvertor.converFromLocal(player.getBirthdate())+"')";
            System.out.println(sql);
            try{
                jdbc.insert(sql);
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }
    /**
     * @Description: 查询队员信息
     * @Name: findPlayer
     * @Param: [team, numbers, jdbc]
     * @Return:java.util.Vector
     */
    @Override
    public Vector findPlayer(String team, String numbers,JDBCAdapter jdbc) {
        String selecting = "select * from player where team='"+team+"' and numbers='"+numbers+"'";
        System.out.println(selecting);
        return jdbc.query(selecting);
    }
    /**
     * @Description: 显示所有队员信息
     * @Name: showAllPlayer
     * @Param: [jdbc]
     * @Return:java.util.Vector
     */
    @Override
    public Vector showAllPlayer(JDBCAdapter jdbc) {
        String selecting = "select * from player";
        Vector exist = jdbc.query(selecting);
        if(exist.size() == 0){
            System.out.println("查询信息不存在！");
            return null;
        }
        return exist;
    }
}
