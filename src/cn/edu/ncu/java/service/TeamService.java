package cn.edu.ncu.java.service;

import cn.edu.ncu.java.dao.*;
import cn.edu.ncu.java.entity.Team;
import jxl.read.biff.BiffException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class TeamService {
    private TeamDAO teamDAO;
    private JDBCAdapter jdbc;
    /**
     * @Description: 构造方法
     * @Name: TeamService
     * @Param:[]
     * @Return:
     */
    public TeamService() throws SQLException, IOException, BiffException {
        this.teamDAO = new TeamDAOImpl();
        this.jdbc = new JDBCAdapter();
    }
    /**
     * @Description: 显示所有队的信息
     * @Name: showAllTeam
     * @Param:[]
     * @Return: java.util.ArrayList<cn.edu.ncu.java.entity.Team>
     */
    public ArrayList<Team> showAllTeam(){
        Vector table = teamDAO.showAllTeam(this.jdbc);
        ArrayList<Team> data = new ArrayList<Team>();
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
                Team team = new Team(value[0], value[2], value[1], value[3]);
                //System.out.println(team.toString());
                assert data != null;
                data.add(team);
            }
        }else {
            data=null;
        }
        return data;
    }
    /**
     * @Description: 依据队名查找球队
     * @Name: findTeam
     * @Param: [teamname]
     * @Return: cn.edu.ncu.java.entity.Team
     */
    public Team findTeam(String teamname){
        Vector table = teamDAO.findTeam(teamname,jdbc);
        Iterator<Vector> iterator = table.iterator();
        Team team = new Team();
        if(table.size() == 1) {
            Vector row = iterator.next();
            String[] value = new String[row.size()];
            int i = 0;
            Iterator<Object> it2 = row.iterator();
            while (it2.hasNext()) {
                value[i++] = it2.next() + "";
            }
            team = new Team(value[0], value[2], value[1], value[3]);
            return team;
        }else {
            team = null;
        }
        return team;
    }
    /**
     * @Description: 删除指定球队的信息
     * @Name: deleteTeam
     * @Param: [team]
     * @Return:void
     */
    public void deleteTeam(Team team){
        System.out.println(team.toString());
        teamDAO.deleteTeam(team,jdbc);
    }
    /**
     * @Description: 插入球队信息
     * @Name: insertTeam
     * @Param: [team]
     * @Return:void
     */
    public void insertTeam(Team team){
        System.out.println(team.toString());
        teamDAO.insertTeam(team,jdbc);
    }
}
