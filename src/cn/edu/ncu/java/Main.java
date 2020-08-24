package cn.edu.ncu.java;


import cn.edu.ncu.java.dao.*;
import cn.edu.ncu.java.service.PlayerManService;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.sql.SQLException;


public class Main {
    private static TeamDAO teamDAO = new TeamDAOImpl();
    private static PlayerDAO playerDAO = new PlayerDAOImpl();

    public static void main(String[] args) throws IOException, SQLException, BiffException {

        JDBCAdapter jdbc = new JDBCAdapter();
        // Player player = new Player("于博",23,"北京现代","男","守门员",
                //"1","186","84",1985,7,17);
        //Team team = new Team("北京现代2","陈磊","软件学院",2005,7,17);

       // playerDAO.insertPlayer(player,jdbc);
        //teamDAO.insertTeam(team,jdbc);
        //PlayerManService playerManService = new PlayerManService();
        //playerManService.showAllPlayer();
    }

}
