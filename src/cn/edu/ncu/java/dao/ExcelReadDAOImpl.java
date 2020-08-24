package cn.edu.ncu.java.dao;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class ExcelReadDAOImpl implements ExcelReadDAO {
    /**
     * @Description: 读取excel数据表
     * @Name: ReadExcel
     * @Param: [con, path, inserting]
     * @Return:void
     */
    @Override
    public void ReadExcel(Connection con, File path,String inserting) throws IOException, BiffException {
        PreparedStatement pstatement;
        Workbook workbook = Workbook.getWorkbook(path);
        Sheet[] sheets = workbook.getSheets();
        if(sheets!=null) {
            for(Sheet sheet:sheets) {
                int rows = sheet.getRows();         //获取行数
                System.out.println(rows);
                int cols = sheet.getColumns();      //获取列数
                System.out.println(cols);
                for(int row = 1;row<rows;row++) {   //读取数据
                    String values[] = new String[cols];
                    for(int col=0;col<cols;col++) {
                        values[col] = sheet.getCell(col,row).getContents(); //将每行不同列的内容放入数组
                    }
                    try {
                        pstatement = con.prepareStatement(inserting);       //将读取出来的内容写入mysql数据库
                        for(int col=0;col<cols;col++) {
                            pstatement.setNString(col+1, values[col]);
                        }
                        pstatement.executeUpdate();                         //执行sql语句插入内容
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
        workbook.close();
    }
}


