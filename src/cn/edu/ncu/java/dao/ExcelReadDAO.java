package cn.edu.ncu.java.dao;

import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

public interface ExcelReadDAO {
    public void ReadExcel(Connection con, File path,String inserting) throws IOException, BiffException;
}
