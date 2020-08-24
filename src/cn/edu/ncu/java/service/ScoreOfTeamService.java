package cn.edu.ncu.java.service;

import cn.edu.ncu.java.dao.*;
import cn.edu.ncu.java.entity.ScoreOfTeam;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class ScoreOfTeamService {
    private ScoreOfTeamDAO scoreOfTeamDAO;
    private JDBCAdapter jdbc;
    /**
     * @Description: 构造方法
     * @Name: ScoreOfTeamService
     * @Param:[]
     * @Return:
     */
    public ScoreOfTeamService() throws SQLException, IOException, BiffException {
        this.scoreOfTeamDAO = new ScoreOfTeamDAOImpl();
        this.jdbc = new JDBCAdapter();
    }
    /**
     * @Description: 显示女子组的球队积分榜
     * @Name: showAllScoreOfTeamForFemale
     * @Param:[]
     * @Return: java.util.ArrayList<cn.edu.ncu.java.entity.ScoreOfTeam>
     */
    public ArrayList<ScoreOfTeam> showAllScoreOfTeamForFemale(){
        Vector table = scoreOfTeamDAO.showAllScoreOfTeamForFemale(jdbc);
        return showAllScoreOfTeam(table);
    }
    /**
     * @Description: 显示成年组的球队积分榜
     * @Name: showAllScoreOfTeamForAdult
     * @Param:[]
     * @Return: java.util.ArrayList<cn.edu.ncu.java.entity.ScoreOfTeam>
     */
    public ArrayList<ScoreOfTeam> showAllScoreOfTeamForAdult(){
        Vector table = scoreOfTeamDAO.showAllScoreOfTeamForAdult(jdbc);
        return showAllScoreOfTeam(table);
    }
    /**
     * @Description: 显示男子组（包括男子甲组和男子乙组）的球队积分榜
     * @Name: showAllScoreOfTeamForMale
     * @Param:[]
     * @Return: java.util.ArrayList<cn.edu.ncu.java.entity.ScoreOfTeam>
     */
    public ArrayList<ScoreOfTeam> showAllScoreOfTeamForMale(){
        Vector table = scoreOfTeamDAO.showAllScoreOfTeamForMale(jdbc);
        return showAllScoreOfTeam(table);
    }
    /**
     * @Description: 被方法showAllScoreOfTeamForAdult()、showAllScoreOfTeamForMale()和
     *               showAllScoreOfTeamForFemale()调用
     * @Name: showAllScoreOfTeam
     * @Param: [table]
     * @Return: java.util.ArrayList<cn.edu.ncu.java.entity.ScoreOfTeam>
     */
    private ArrayList<ScoreOfTeam> showAllScoreOfTeam(Vector table){
        ArrayList<ScoreOfTeam> data = new ArrayList<ScoreOfTeam>();
        if(table!=null) {
            Iterator<Vector> it = table.iterator();
            while (it.hasNext()) {
                Vector row = it.next();
                String[] value = new String[row.size()];
                int i = 0;
                Iterator<Object> it2 = row.iterator();
                while (it2.hasNext()) {
                    value[i++] = it2.next() + "";
                }
                ScoreOfTeam scoreOfTeam = new ScoreOfTeam(value[0], value[1], value[2], value[3],
                        value[4], value[5], value[6], value[7], value[8], value[9], value[10], value[11], value[12], value[13]);
                //System.out.println(scoreOfTeam.toString());
                assert data != null;
                data.add(scoreOfTeam);
            }
        }else {
            data = null;
        }
        return data;
    }
}
