package cn.edu.ncu.java.service;

import cn.edu.ncu.java.dao.JDBCAdapter;
import cn.edu.ncu.java.dao.PlayerDAO;
import cn.edu.ncu.java.dao.PlayerDAOImpl;
import cn.edu.ncu.java.entity.Player;
import jxl.read.biff.BiffException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class PlayerManService {
    private PlayerDAO playerDAO;
    private JDBCAdapter jdbc;

    public PlayerManService() throws SQLException, IOException, BiffException {
        this.playerDAO = new PlayerDAOImpl();
        this.jdbc = new JDBCAdapter();
    }
    /**
     * @Description: 显示所有队员
     * @Name: showAllPlayer
     * @Param:[]
     * @Return: java.util.ArrayList<cn.edu.ncu.java.entity.Player>
     */
    public ArrayList<Player> showAllPlayer(){
        Vector table = playerDAO.showAllPlayer(this.jdbc);
        ArrayList<Player> data = new ArrayList<Player>();
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
                Player player = new Player(value[0], value[1], value[2], value[4],
                        value[5], value[3], value[6], value[7], value[8]);
                //System.out.println(player.toString());
                assert data != null;
                data.add(player);
            }
        }else {
            data = null;
        }
        return data;
    }
    /**
     * @Description: 查找队员信息
     * @Name: findPlayer
     * @Param: [team, numbers]
     * @Return: cn.edu.ncu.java.entity.Player
     */
    public Player findPlayer(String team, String numbers){
        Vector table = playerDAO.findPlayer(team,numbers,jdbc);
        Iterator<Vector> iterator = table.iterator();
        Player player = new Player();
        if(table.size() == 1) {
            Vector row = iterator.next();
            String[] value = new String[row.size()];
            int i = 0;
            Iterator<Object> it2 = row.iterator();
            while (it2.hasNext()) {
                value[i++] = it2.next() + "";
            }
            player = new Player(value[0], value[1], value[2], value[4],
                    value[5], value[3], value[6], value[7], value[8]);
            return player;
        }else {
            player = null;
        }
        return player;
    }
    /**
     * @Description: 删除队员信息
     * @Name: deletePlayer
     * @Param: [player]
     * @Return:void
     */
    public void deletePlayer(Player player){
        System.out.println(player.toString());
        playerDAO.deletePlayer(player,jdbc);
    }
    /**
     * @Description: 插入队员信息
     * @Name: insertPlayer
     * @Param: [player]
     * @Return:void
     */
    public void insertPlayer(Player player){
        System.out.println(player.toString());
        playerDAO.insertPlayer(player,jdbc);
    }

}
