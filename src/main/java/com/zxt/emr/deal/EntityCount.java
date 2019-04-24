package com.zxt.emr.deal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class EntityCount {
    private static int num_s=0;
    private static int num_p=0;
    private static int num_t=0;
    private static int num_d=0;
    private static int num_e=0;
    private static int label_s=0;
    private static int label_p=0;
    private static int label_t=0;
    private static int label_d=0;
    private static int label_e=0;
    public void countEntity(String fileName){
        try {
            File filename = new File(fileName);
            if (!filename.exists()){
                return ;
            }
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            String line ;
            String arr[];
            String name;
            String type;
            int num_s1=0;
            int num_p1=0;
            int num_t1=0;
            int num_d1=0;
            int num_e1=0;
            int label_s1=0;
            int label_p1=0;
            int label_t1=0;
            int label_d1=0;
            int label_e1=0;
            while ((line = br.readLine()) != null) {
                arr = line.split("\\s+");
                name = arr[0]==null?"":arr[0];
                type = arr[3]==null?"":arr[3];
                if (type.equals("身体部位")){
                    num_p1++;
                    label_p1+=name.length();
                }else if (type.equals("症状和体征")){
                    num_s1++;
                    label_s1+=name.length();
                }else if (type.equals("疾病和诊断")){
                    num_d1++;
                    label_d1+=name.length();
                    System.out.println(name);
                }else if (type.equals("检查和检验")){
                    num_e1++;
                    label_e1+=name.length();
                }else {
                    num_t1++;
                    label_t1+=name.length();
                }
            }
            num_d+=num_d1;
            num_e+=num_e1;
            num_p+=num_p1;
            num_s+=num_s1;
            num_t+=num_t1;
            label_d+=label_d1;
            label_e+=label_e1;
            label_p+=label_p1;
            label_s+=label_s1;
            label_t+=label_t1;
            br.close();
            reader.close();
//            System.out.println("the number of 疾病："+num_d1);
//            System.out.println("the number of label 疾病："+label_d1);
//            System.out.println("the number of 检查和检验："+num_e1);
//            System.out.println("the number of label 检查和检验："+label_e1);
//            System.out.println("the number of 身体部位："+num_p1);
//            System.out.println("the number of label 身体部位："+label_p1);
//            System.out.println("the number of 症状和体征："+num_s1);
//            System.out.println("the number of label 症状和体征："+label_s1);
//            System.out.println("the number of 治疗方式："+num_t1);
//            System.out.println("the number of label 治疗方式："+label_t1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
        public static void main(String[] args){
            EntityCount entityCount = new EntityCount();
//            for (int num =1;num<201;num++){
//                String path1 = "C:\\Users\\zxt\\Desktop\\6.15整理大礼包\\毕设\\毕设数据\\ccks2017CNER\\training dataset 1-200\\training dataset 1-200\\01-一般项目-1-200\\一般项目-"+num+".txt";
//                String path2 = "C:\\Users\\zxt\\Desktop\\6.15整理大礼包\\毕设\\毕设数据\\ccks2017CNER\\training dataset 1-200\\training dataset 1-200\\02-病史特点-1-200\\病史特点-"+num+".txt";
//                String path3 = "C:\\Users\\zxt\\Desktop\\6.15整理大礼包\\毕设\\毕设数据\\ccks2017CNER\\training dataset 1-200\\training dataset 1-200\\04-诊疗经过-1-200\\诊疗经过-"+num+".txt";
//                String path4 = "C:\\Users\\zxt\\Desktop\\6.15整理大礼包\\毕设\\毕设数据\\ccks2017CNER\\training dataset 1-200\\training dataset 1-200\\05-出院情况-1-200\\出院情况-"+num+".txt";
//                entityCount.countEntity(path4);
//            }
            for (int num =1;num<201;num++){
                String path1 = "C:\\Users\\zxt\\Desktop\\6.15整理大礼包\\毕设\\毕设数据\\ccks2017CNER\\training dataset 1-200\\training dataset 1-200\\01-一般项目-1-200\\一般项目-"+num+".txt";
                entityCount.countEntity(path1);
            }
            for (int num =1;num<201;num++){
                String path2 = "C:\\Users\\zxt\\Desktop\\6.15整理大礼包\\毕设\\毕设数据\\ccks2017CNER\\training dataset 1-200\\training dataset 1-200\\02-病史特点-1-200\\病史特点-"+num+".txt";
                entityCount.countEntity(path2);
            }
            for (int num =1;num<201;num++){
                String path3 = "C:\\Users\\zxt\\Desktop\\6.15整理大礼包\\毕设\\毕设数据\\ccks2017CNER\\training dataset 1-200\\training dataset 1-200\\04-诊疗经过-1-200\\诊疗经过-"+num+".txt";
                entityCount.countEntity(path3);
            }
            for (int num =1;num<201;num++){
                String path4 = "C:\\Users\\zxt\\Desktop\\6.15整理大礼包\\毕设\\毕设数据\\ccks2017CNER\\training dataset 1-200\\training dataset 1-200\\05-出院情况-1-200\\出院情况-"+num+".txt";
                entityCount.countEntity(path4);
            }
            System.out.println("------------------------------------");
            System.out.println("the number of 疾病："+num_d);
            System.out.println("the number of label 疾病："+label_d);
            System.out.println("the number of 检查和检验："+num_e);
            System.out.println("the number of label 检查和检验："+label_e);
            System.out.println("the number of 身体部位："+num_p);
            System.out.println("the number of label 身体部位："+label_p);
            System.out.println("the number of 症状和体征："+num_s);
            System.out.println("the number of label 症状和体征："+label_s);
            System.out.println("the number of 治疗方式："+num_t);
            System.out.println("the number of label 治疗方式："+label_t);
        }

}
