package com.zxt.emr.deal;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class NewPreTreat {
    private LinkedList<EntityStored> entities;
    private static String nonEn = " O\r\n";
    private static String b_p = " B-P\r\n";
    private static String i_p = " I-P\r\n";
    private static String b_s = " B-S\r\n";
    private static String i_s = " I-S\r\n";
    private static String b_d = " B-D\r\n";
    private static String i_d = " I-D\r\n";
    private static String b_e = " B-E\r\n";
    private static String i_e = " I-E\r\n";
    private static String b_t = " B-T\r\n";
    private static String i_t = " I-T\r\n";
    private static String b_c = " B-C\r\n";
    private static String i_c = " I-C\r\n";
    private static String b_a = " B-A\r\n";
    private static String i_a = " I-A\r\n";
    private static String b_f = " B-F\r\n";
    private static String i_f = " I-F\r\n";
    public boolean produceSingle(LinkedList<EntityStored> list,String originalPath,String targetPath){
        if (list==null){
            return false;
        }
        try {
            File original = new File(originalPath);
            if (!original.exists()){
                return false;
            }
            File target = new File(targetPath);
            target.createNewFile();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target)));
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(original)));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = br.readLine())!=null){
                result.append(line);
            }
            char[] cs = result.toString().toCharArray();
            int start = 0;
            EntityStored entityStored;
            char[] name ;
            int length;
            while (!list.isEmpty()){
                entityStored = list.pollFirst();
                name = entityStored.getName().toCharArray();
                length = name.length;
                boolean mathch = false;
                while (!mathch&&start<cs.length){
                    if (cs[start]==' '){
                        start++;
                    }else if(cs[start]=='。'){
                        bw.write(cs[start]+nonEn);
                        bw.write("\r\n");
                        start++;
                    }else if(cs[start]!= name[0]){
                        bw.write(cs[start]+nonEn);
                        start++;
                    }else{
                        boolean peidui=true;
                        for (int k=1;k<length;k++){
                            if (cs[start+k]!=name[k]){
                                bw.write(cs[start]+nonEn);
                                start++;
                                peidui = false;
                                break;
                            }
                        }
                        if (peidui){
                            if (entityStored.getType().equals("身体部位")){
                                bw.write(cs[start]+b_p);
                                for (int k=1;k<length;k++){
                                    bw.write(cs[start+k]+i_p);
                                }
                                mathch = true;
                                start = start+length;
                            }else if (entityStored.getType().equals("自述症状")){
                                bw.write(cs[start]+b_s);
                                for (int k=1;k<length;k++){
                                    bw.write(cs[start+k]+i_s);
                                }
                                mathch = true;
                                start = start+length;
                            }else if (entityStored.getType().equals("疾病")){
                                bw.write(cs[start]+b_d);
                                for (int k=1;k<length;k++){
                                    bw.write(cs[start+k]+i_d);
                                }
                                mathch = true;
                                start = start+length;
                            }else if (entityStored.getType().equals("检查")){
                                bw.write(cs[start]+b_e);
                                for (int k=1;k<length;k++){
                                    bw.write(cs[start+k]+i_e);
                                }
                                mathch = true;
                                start = start+length;
                            }else if(entityStored.getType().equals("治疗")){
                                bw.write(cs[start]+b_t);
                                for (int k=1;k<length;k++){
                                    bw.write(cs[start+k]+i_t);
                                }
                                mathch = true;
                                start = start+length;
                            }else if (entityStored.getType().equals("时间")){
                                bw.write(cs[start]+b_c);
                                for (int k=1;k<length;k++){
                                    bw.write(cs[start+k]+i_c);
                                }
                                mathch = true;
                                start = start+length;
                            }else if (entityStored.getType().equals("异常检查结果")){
                                bw.write(cs[start]+b_a);
                                for (int k=1;k<length;k++){
                                    bw.write(cs[start+k]+i_a);
                                }
                                mathch = true;
                                start = start+length;
                            }else {
                                bw.write(cs[start]+b_f);
                                for (int k=1;k<length;k++){
                                    bw.write(cs[start+k]+i_f);
                                }
                                mathch = true;
                                start = start+length;
                            }
                        }
                    }
                }
            }
            while (start<cs.length){
                if (cs[start]==' '){
                    start++;
                }else if(cs[start]=='。'){
                    bw.write(cs[start]+nonEn);
                    bw.write("\r\n");
                    start++;
                }else {
                    bw.write(cs[start]+nonEn);
                    start++;
                }
            }
            bw.close();
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
        return  true;
    }
    public static void main(String[] args){
        try {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String jdbc = "jdbc:mysql://211.83.111.224:3306/emr?characterEncoding=UTF-8";
        Connection connection = DriverManager.getConnection(jdbc, "root", "Asdfghjkl123");
        Statement statement = connection.createStatement();
        for(int i=34;i<52;i++){
            String sql = "select mention,pos_b,pos_e,category from mention_dim_history_of_present_illness where editUser = 'zengyt' AND content_id = "+i;
            LinkedList<EntityStored> list = new LinkedList<>();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                EntityStored entityStored = new EntityStored(resultSet.getString(1),Integer.parseInt(resultSet.getString(2)),Integer.parseInt(resultSet.getString(3)),resultSet.getString(4));
                list.add(entityStored);
            }
            Collections.sort(list, Comparator.comparing(EntityStored::getStartPosition));
            String orginalTxt = "C:\\Users\\zxt\\Desktop\\DataLabelMyself\\original\\original-"+i+".txt";
            String targetTxt = "C:\\Users\\zxt\\Desktop\\DataLabelMyself\\label\\label-"+i+".txt";
            NewPreTreat newPreTreat = new NewPreTreat();
            newPreTreat.produceSingle(list,orginalTxt,targetTxt);
        }

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("end!");
    }
}
