package com.zxt.emr.deal;

import java.io.*;
import java.util.LinkedList;

public class PreTreat {
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
    public LinkedList<EntityStored> convertEntity2List(String fileName){
        try {
            File filename = new File(fileName);
            if (!filename.exists()){
                return null;
            }
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            String line ;
            String arr[];
            String name;
            int start,end;
            String type;
            int pre=-1;
            entities = new LinkedList<EntityStored>();
            while ((line = br.readLine()) != null) {
                    arr = line.split("\\s+");
                    name = arr[0]==null?"":arr[0];
                    start = arr[1]==null?-1:Integer.valueOf(arr[1]);
                    end = arr[2]==null?-1:Integer.valueOf(arr[2]);
                    type = arr[3]==null?"":arr[3];
                    if (pre<start){
                        pre = end;
                        EntityStored entityStored = new EntityStored(name,start,end,type);
                        entities.addLast(entityStored);
                    }
            }
            br.close();
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  entities;
    }

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
            System.out.println(result);
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
                            }else if (entityStored.getType().equals("症状和体征")){
                                bw.write(cs[start]+b_s);
                                for (int k=1;k<length;k++){
                                    bw.write(cs[start+k]+i_s);
                                }
                                mathch = true;
                                start = start+length;
                            }else if (entityStored.getType().equals("疾病和诊断")){
                                bw.write(cs[start]+b_d);
                                for (int k=1;k<length;k++){
                                    bw.write(cs[start+k]+i_d);
                                }
                                mathch = true;
                                start = start+length;
                            }else if (entityStored.getType().equals("检查和检验")){
                                bw.write(cs[start]+b_e);
                                for (int k=1;k<length;k++){
                                    bw.write(cs[start+k]+i_e);
                                }
                                mathch = true;
                                start = start+length;
                            }else {
                                bw.write(cs[start]+b_t);
                                for (int k=1;k<length;k++){
                                    bw.write(cs[start+k]+i_t);
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
    public boolean meige(String source,String targetPath){
        try {
            File original = new File(source);
            if (!original.exists()) {
                return false;
            }
            File target = new File(targetPath);
            if (!target.exists()) {
                target.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target)));
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(original)));
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static void main(String[] args){
        PreTreat preTreat = new PreTreat();
        for (int num =1;num<201;num++){
            String path = "D:\\文件\\毕设\\毕设\\毕设数据\\ccks2017CNER\\training dataset 1-200\\training dataset 1-200\\02-病史特点-1-200\\病史特点-"+num+".txt";
            String original = "D:\\文件\\毕设\\毕设\\毕设数据\\ccks2017CNER\\training dataset 1-200\\training dataset 1-200\\02-病史特点-1-200\\病史特点-"+num+".txtoriginal.txt";
            String target = "C:\\Users\\zxt\\Desktop\\target\\02-病史特点-"+num+"target.txt";
            LinkedList<EntityStored> entityStoreds = preTreat.convertEntity2List(path);
            System.out.println(entityStoreds.toString());
            System.out.println(preTreat.produceSingle(entityStoreds,original,target));
        }
//        String test="读出所有数据， 然后存起来！";
//        char[] cs = test.toCharArray();
//        System.out.println(Arrays.toString(cs));
    }
}
