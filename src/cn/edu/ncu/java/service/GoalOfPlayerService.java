package cn.edu.ncu.java.service;

import cn.edu.ncu.java.dao.GoalOfPlayerDAO;
import cn.edu.ncu.java.dao.GoalOfPlayerDAOImpl;
import cn.edu.ncu.java.dao.JDBCAdapter;
import cn.edu.ncu.java.entity.GoalOfPlayer;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class GoalOfPlayerService {
    private GoalOfPlayerDAO goalOfPlayerDAO;
    private JDBCAdapter jdbc;

    public GoalOfPlayerService() throws SQLException, IOException, BiffException {
        this.goalOfPlayerDAO = new GoalOfPlayerDAOImpl();
        this.jdbc = new JDBCAdapter();
    }
    /**
     * @Description: 插入进球信息
     * @Name: insertGoalOfPlayer
     * @Param: [goalOfPlayer]
     * @Return:void
     */
    public void insertGoalOfPlayer(GoalOfPlayer goalOfPlayer){
        System.out.println(goalOfPlayer.getGoalTime());
        goalOfPlayerDAO.insertGoalOfPlayer(goalOfPlayer,jdbc);
    }
    /**
     * @Description: 显示所有进球信息
     * @Name: showAllGoalOfPlayer
     * @Param:[]
     * @Return: java.util.ArrayList<cn.edu.ncu.java.entity.GoalOfPlayer>
     */
    public ArrayList<GoalOfPlayer> showAllGoalOfPlayer(){
        Vector table = goalOfPlayerDAO.showAllGoalOfPlayer(jdbc);
        ArrayList<GoalOfPlayer> data = new ArrayList<GoalOfPlayer>();
        if (table != null) {
            Iterator<Vector> it = table.iterator();
            while (it.hasNext()) {
                Vector row = it.next();
                String[] value = new String[row.size()];
                int i = 0;
                Iterator<Object> it2 = row.iterator();
                while (it2.hasNext()) {
                    value[i++] = it2.next() + "";
                }
                GoalOfPlayer goalOfPlayer = new GoalOfPlayer(value[0], value[1], value[2], value[3]);
                System.out.println(goalOfPlayer.toString());
                assert data != null;
                data.add(goalOfPlayer);
            }
        }else {
            data = null;
        }
        return data;
    }
}
