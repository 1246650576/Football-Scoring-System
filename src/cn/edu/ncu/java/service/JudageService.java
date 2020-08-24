package cn.edu.ncu.java.service;

import cn.edu.ncu.java.dao.JDBCAdapter;
import cn.edu.ncu.java.dao.JudgeDAO;
import cn.edu.ncu.java.dao.JudgeDAOImpl;
import cn.edu.ncu.java.entity.Judage;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class JudageService {
    private JudgeDAO judgeDAO;
    private JDBCAdapter jdbc;

    public JudageService() throws SQLException, IOException, BiffException {
        judgeDAO = new JudgeDAOImpl();
        this.jdbc = new JDBCAdapter();
    }
    /**
     * @Description: 插入罚单信息
     * @Name: insertJudge
     * @Param: [judage]
     * @Return:void
     */
    public void insertJudge(Judage judage){
        System.out.println(judage.toString());
        judgeDAO.insertJudge(judage,jdbc);
    }
    public ArrayList<Judage> showAllJudge(){
        Vector table = judgeDAO.showAllJudge(jdbc);
        ArrayList<Judage> data = new ArrayList<Judage>();
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
                Judage judage = new Judage(value[0], value[1], value[2], value[3], value[4], value[5]);
                System.out.println(judage.toString());
                assert data != null;
                data.add(judage);
            }
        }else{
            data = null;
        }
        return data;
    }
}
