package cn.edu.ncu.java.service;

import cn.edu.ncu.java.dao.JDBCAdapter;
import cn.edu.ncu.java.dao.ScheduleDAO;
import cn.edu.ncu.java.dao.ScheduleDAOImpl;
import cn.edu.ncu.java.entity.Schedule;
import jxl.read.biff.BiffException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class ScheduleService {
    private ScheduleDAO scheduleDAO;
    private JDBCAdapter jdbc;

    public ScheduleService() throws SQLException, IOException, BiffException {
        this.scheduleDAO = new ScheduleDAOImpl();
        this.jdbc = new JDBCAdapter();
    }
    /**
     * @Description: 显示所有赛程信息
     * @Name: showAllSchedule
     * @Param:[]
     * @Return: java.util.ArrayList<cn.edu.ncu.java.entity.Schedule>
     */
    public ArrayList<Schedule> showAllSchedule(){
        Vector table = scheduleDAO.showAllSchedule(this.jdbc);
        ArrayList<Schedule> data = new ArrayList<Schedule>();
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
                Schedule schedule = new Schedule(value[0], value[1], value[2], value[3],
                        value[4], value[5], value[6]);
                //System.out.println(schedule.toString());
                assert data != null;
                data.add(schedule);
            }
        }else {
            data=null;
        }
        return data;
    }
}
