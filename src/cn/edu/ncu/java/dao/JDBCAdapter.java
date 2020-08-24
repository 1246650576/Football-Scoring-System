package cn.edu.ncu.java.dao;

import jxl.read.biff.BiffException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.apache.tools.ant.types.EnumeratedAttribute;
import java.io.*;
import java.sql.*;
import java.util.Vector;

public class JDBCAdapter {
    private String user;            // 数据库用户名
    private String password;        // 数据库密码
    private String localhost;       //主机名
    private String DatabaseName;    //数据库名
    private Connection conn = null; // 注册驱动
    private Statement statement = null;
    private ResultSet res = null;
    private Vector rows;
    private ExcelReadDAO excelReadDAO = new ExcelReadDAOImpl();

    /**
     * @Description: 打开文件, 读取数据库初始化配置信息
     * @Name: getParams
     * @Param:[]
     * @Return:void
     */
    private void getParams(){
        try {
            System.out.println();
            File file = new File(this.getClass().getClassLoader().getResource("cn/edu/ncu/java/otherfiles").getPath()+"databaseInit.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            user = br.readLine();
            password = br.readLine();
            localhost = br.readLine();
            DatabaseName = br.readLine();
            br.close();
            fr.close();
       } catch (FileNotFoundException e) {
            System.err.println("未找到文件！");
       } catch (IOException e) {
            System.err.println("读取文件数据错误！");
        }
    }
    /**
     * @Description: 构造函数
     * @Name: JDBCAdapter
     * @Param:[]
     * @Return:
     */
    public JDBCAdapter() throws SQLException, IOException, BiffException {
        getParams();
        if(!createDatebase()) connector();
        if (tableIsEmplty()) {

            excelReadDAO.ReadExcel(conn, new File(this.getClass().getClassLoader().getResource("cn/edu/ncu/java/otherfiles").getPath()+"player.xls"), "insert player values(?,?,?,?,?,?,?,?,?);");
            excelReadDAO.ReadExcel(conn, new File(this.getClass().getClassLoader().getResource("cn/edu/ncu/java/otherfiles").getPath()+"team.xls"), "insert team values(?,?,?,?);");
            excelReadDAO.ReadExcel(conn, new File(this.getClass().getClassLoader().getResource("cn/edu/ncu/java/otherfiles").getPath()+"schedule.xls"), "insert schedule values(?,?,?,?,?,?,?);");
        }
    }
    /**
     * @Description:执行加载驱动程序、建立
     * @Name:connector
     * @Param:[]
     * @Return:void
     */
    public void connector(){
        try {
            java.sql.Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            String url = "jdbc:mysql://localhost:" + localhost + "/" + DatabaseName +
                        "?serverTimezone=UTC";
            conn = DriverManager.getConnection(url, user, password);
            if(conn!=null) {System.out.println("sucess connect to mysql");}
            statement = conn.createStatement();
        } catch (SQLException ex2) {
            ex2.printStackTrace();
            System.err.println("连接字符串错误，或者语句对象错误！");
        }
    }
    /**
     * @Description: 创建并连接数据库，注意加载数据库jar连接jar
     * @Name:createDatebase
     * @Param:[]
     * @Return:boolean
     * @return
     */
    public boolean tableIsEmplty(){
        Vector table1 = query("select * from schedule");
        Vector table2 = query("select * from player");
        Vector table3  = query("select * from team");
        return table1.isEmpty() & table2.isEmpty() & table3.isEmpty();
    }
    /**
     * @Description: 创建数据库
     * @Name:createDatebase
     * @Param:[]
     * @Return:boolean
     */
    public boolean createDatebase() throws SQLException {
        boolean result = false;
        try{
            Driver driver = new com.mysql.cj.jdbc.Driver();//获取数据库连接
            DriverManager.registerDriver(driver);
            String url = "jdbc:mysql://localhost:3306/football?serverTimezone=UTC";;
            conn = DriverManager.getConnection(url, user, password);
            statement = conn.createStatement();
            if(conn!=null) {System.out.println("sucess connect to mysql");}
            Vector exist = query("SELECT count(*) TABLES, table_schema FROM information_schema.TABLES where table_schema = '"+DatabaseName+"' GROUP BY table_schema;");
            if(exist.size() == 0){
                exeSQLSript(url,";",this.getClass().getClassLoader().getResource("cn/edu/ncu/java/otherfiles").getPath()+"footballsys.sql"); //执行脚本
                exeSQLSript(url,"$",this.getClass().getClassLoader().getResource("cn/edu/ncu/java/otherfiles").getPath()+"Trigger.sql");
                result = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }
    /**
     * @Description: 利用 Ant 的SQL Task来实现执行SQL 脚本的功能。ant 包中的 SQLExec类的扩展，此时需要将ant 包(ant.jar)导入
     * @Name: exeSQLSript
     * @Param: [url, delimiter, sqlAddress]
     * @Return:void
     */
    public void exeSQLSript(String url, String delimiter, String sqlAddress){
        SQLExec sqlExec = new SQLExec();
        String mysqlDriver = "com.mysql.cj.jdbc.Driver";
        sqlExec.setDriver(mysqlDriver);
        sqlExec.setUrl(url);
        sqlExec.setUserid(this.user);
        sqlExec.setPassword(this.password);
        sqlExec.setEncoding("UTF-8");
        sqlExec.setSrc(new File(sqlAddress));
        sqlExec.setOnerror((SQLExec.OnError)(EnumeratedAttribute.getInstance(SQLExec.OnError.class, "abort")));
        sqlExec.setPrint(true);
        sqlExec.setOutput(new File(this.getClass().getClassLoader().getResource("cn/edu/ncu/java/otherfiles").getPath()+"footballsys.out"));
        sqlExec.setDelimiter(delimiter);
        sqlExec.setProject(new Project());
        sqlExec.execute();
    }
    /**
     * @Description: 关闭连接对象
     * @Name: closeConnection
     * @Param:[]
     * @Return:void
     */
    public void closeConnection(){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }
    /**
     * @Description: 关闭语句对象
     * @Name: closeStatement
     * @Param:[]
     * @Return:void
     */
    public void closeStatement(){
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
            statement = null;
        }
    }
    /**
     * @Description: 执行insert操作
     * @Name: insert
     * @Param: [inserting]
     * @Return:void
     */
    public void insert(String inserting){
        if(conn == null || statement == null){
            System.out.println("没有建立数据库连接！");
        }
        try{
            statement.executeUpdate(inserting);
            System.out.println("插入成功！");
        }catch(Exception ex){
            System.err.println(ex);
            System.out.println("数据插入错误！");
        }
    }
    /**
     * @Description: 执行delete操作
     * @Name: delete
     * @Param: [deleting]
     * @Return:void
     */
    public void delete(String deleting){
        if(conn == null || statement == null){
            System.out.println("没有建立数据库连接！");
        }
        try{
            statement.executeUpdate(deleting);
            System.out.println("删除成功！");
        }catch(Exception ex){
            System.out.println("数据删除错误！");
        }
    }
    /**
     * @Description: 执行update操作
     * @Name: update
     * @Param: [updating]
     * @Return:void
     */
    public void update(String updating){
        if(conn == null || statement == null){
            System.out.println("没有建立数据库连接！");
        }
        try{
            statement.executeUpdate(updating);
            System.out.println("更新成功！");
        }catch(Exception ex){
            System.out.println("数据更新错误！");
        }
    }
    /**
     * @Description: 查询操作, 输入参数是SQL的字符串,返回结果是一个Vector的二维数组
     * @Name: query
     * @Param: [selecting]
     * @Return:java.util.Vector
     */
    public Vector query(String selecting){
        if (conn==null || statement==null){
            System.out.println("没有建立数据库连接！");
            return null;
        }
        ResultSetMetaData meta; //元数据，即数据字典,通过它能够得到结果集有多少字段，
        int colNum;             //记录结果集的列数
        String colName[];       //记录每个字段的名称
        try{
            res = statement.executeQuery(selecting);
            meta = res.getMetaData();
            colNum = meta.getColumnCount();
            colName = new String[colNum];
            for (int i=0; i<colNum; i++){
                colName[i]=meta.getColumnLabel(i+1);
            }
            rows = new Vector();
            while(res.next()){                  //将返回的结果集存入rows二维数组中
                Vector oneRow = new Vector();
                for(int i=1; i<=colNum; i++){   //每一行各个单元格内容加入到oneRow数组中
                    oneRow.addElement
                            ((Object)res.getObject(i));
                }
                rows.addElement(oneRow);
            }
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("数据查询错误！");
        }
        return rows;
    }
    /**
     * @Description: 关闭结果集对象
     * @Name: closeResultSet
     * @Param:[]
     * @Return:void
     */
    public  void closeResultSet() {
        if (res != null) {
            try {
                res.close();
                res=null;
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
    }
    /**
     * @Description: 关闭各个对象, 释放内存
     * @Name: close
     * @Param:[]
     * @Return:void
     */
    public void close(){
        this.closeResultSet();
        this.closeStatement();
        this.closeConnection();
    }
    /**
     * @Description: 事务的提交操作
     * @Name: commit
     * @Param:[]
     * @Return:void
     */
    public void commit() throws SQLException{
        conn.commit();
    }
    /**
     * @Description: 事务的回滚操作
     * @Name: rollback
     * @Param:[]
     * @Return:void
     */
    public void rollback() throws SQLException{
        conn.rollback();
    }
    /**
     * @Description: 专用于比赛过程插入,由于在界面中按下确定按钮之后,需要执行多次插入操作,每次都提示插入成功,并不合理,所以这里的插入操作成功之后不进行提示
     * @Name: insert2
     * @Param: [inserting]
     * @Return:void
     */
    public void insert2(String inserting){
        if(conn == null || statement == null){
            System.out.println("没有建立数据库连接！");
        }
        try{
            statement.executeUpdate(inserting);
        }catch(Exception ex){
        }
    }
    /**
     * @Description: 执行update操作
     * @Name: update2
     * @Param: [updating]
     * @Return:void
     */
    public void update2(String updating){
        if(conn == null || statement == null){
            System.out.println("没有建立数据库连接！");
        }
        try{
            statement.executeUpdate(updating);
        }catch(Exception ex){
            System.out.println("数据修改错误！");
        }
    }
    /**
     * @Description: 执行delete操作
     * @Name: delete2
     * @Param: [deleting]
     * @Return:void
     */
    public void delete2(String deleting){
        if(conn == null || statement == null){
            System.out.println("没有建立数据库连接！");
        }
        try{
            statement.executeUpdate(deleting);
        }catch(Exception ex){
            System.out.println("数据修改错误！");
        }
    }
}




