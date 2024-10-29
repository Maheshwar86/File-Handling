package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    private static final String url = "jdbc:mysql://localhost:3306/Login";
    private static final String User = "root";
    private static final String Pass = "//JOKER//";
    private static final String File_Path = "E:\\Employee_Login_Details.xlsx";
    public static void main(String[] args) throws IOException , SQLException {
        FileInputStream file = new FileInputStream(File_Path);
        XSSFWorkbook FileData = new XSSFWorkbook(file);
        Connection conn = DriverManager.getConnection(url,User,Pass);
        XSSFSheet sheet = FileData.getSheetAt(0);
        String sql = "insert into Employee_Login_Details values(?,?)";
        PreparedStatement pre = conn.prepareStatement(sql);
        for(Row row : sheet){
            if (row.getRowNum()==0){
                continue;
            }
            Cell usernameCell = row.getCell(0);
            Cell passwordCell = row.getCell(1);
            String Name = usernameCell.getStringCellValue();
            String Pass = passwordCell.getStringCellValue();

            pre.setString(1,Name);
            pre.setString(2,Pass);
            pre.execute();
        }
        file.close();
        conn.close();
        System.out.println("Data Inserted Sucesfully");

    }
}