package com.zxt.emr.deal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class WriteTxtFromMysql {
    public static void main(String[] args)throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String jdbc = "jdbc:mysql://211.83.111.224:3306/emr?characterEncoding=UTF-8";
        Connection connection = DriverManager.getConnection(jdbc, "root", "Asdfghjkl123");
        Statement statement = connection.createStatement();
        String sql1 = "select DISTINCT(content_id) from mention_dim_history_of_present_illness where editUser = 'zengyt'";
        ResultSet resultSet1 = statement.executeQuery(sql1);
        List<Integer> lists = new LinkedList<>();
        while(resultSet1.next()){
            String txtName = "C:\\Users\\zxt\\Desktop\\DataLabelMyself\\original\\original-"+resultSet1.getString(1)+".txt";
            String sql2 = "select history_of_present_illness from inpatient_fact WHERE id = "+resultSet1.getString(1);
            Statement statement1 = connection.createStatement();
            ResultSet resultSet2 = statement1.executeQuery(sql2);
            while (resultSet2.next()){
                write2txt(txtName,resultSet2.getString(1));
            }
        }
        connection.close();
    }

    public static void write2txt(String txtName,String content) throws Exception{
        File file = new File(txtName);
        if (!file.exists()){
            file.createNewFile();
        }
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(content);
        out.flush();
        out.close();
    }
}
