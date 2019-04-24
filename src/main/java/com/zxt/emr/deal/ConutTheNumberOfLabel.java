package com.zxt.emr.deal;
import javax.xml.stream.events.StartElement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class ConutTheNumberOfLabel {
    public static void main(String[] args)throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String jdbc = "jdbc:mysql://211.83.111.224:3306/emr?characterEncoding=UTF-8";
        Connection connection = DriverManager.getConnection(jdbc,"root","Asdfghjkl123");
        Statement statement = connection.createStatement();
        String sql = "select DISTINCT (category) from mention_dim_history_of_present_illness";
        ResultSet resultSet = statement.executeQuery(sql);
        HashMap<String,CountLabelClass> results = new HashMap<>();
        while (resultSet.next()){
            CountLabelClass countLabelClass = new CountLabelClass(resultSet.getString(1));
            results.put(resultSet.getString(1),countLabelClass);
        }
        for (String key : results.keySet()){
            CountLabelClass clc = results.get(key);
            String sqlclc = "select mention from mention_dim_history_of_present_illness where category = '"+key+"'";
//            System.out.println(sqlclc);
            resultSet = statement.executeQuery(sqlclc);
            while (resultSet.next()){
//                System.out.println(resultSet.getString(1));
                clc.setNumber(clc.getNumber()+1);
                clc.setLabelNumber(clc.getLabelNumber()+resultSet.getString(1).length());
            }
            System.out.println(clc);
        }
//        String sql1 = "select mention from mention_dim_history_of_present_illness where category = '治疗'";
//        String sql2 = "select mention from mention_dim_history_of_present_illness where editUser = 'zengyt'";
//        ResultSet resultSet = statement.executeQuery(sql1);
//        while (resultSet.next()){
//            System.out.println(resultSet.getString(1));
//        }
        connection.close();
    }
}
