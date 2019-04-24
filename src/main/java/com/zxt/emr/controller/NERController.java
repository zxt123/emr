package com.zxt.emr.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zxt.emr.deal.ElectronicMedicalRecord;
import com.zxt.emr.deal.EntityStored;
import com.zxt.emr.utils.AppResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "ner")
@CrossOrigin
public class NERController {
    @RequestMapping(value = "visual")
    public AppResponse visual(@RequestBody JSONObject request){
        String content = (String) request.get("content");
        if (null == content||content.length()==0){
            return AppResponse.failure(16);
        }
        String complete = request.getString("complete");
        boolean flag = false;
        if (complete.equals("true")){
            flag = true;
        }
        String res = remoteCall(content);
        JSONObject jsonObject = JSON.parseObject(res);
        JSONArray entities = jsonObject.getJSONArray("entities");
        return parseContent(content,entities,flag);
    }
    @RequestMapping(value = "test")
    public AppResponse test(){
        return AppResponse.success();
    }
    private String remoteCall(String content){
        JSONObject  jsonObject = new JSONObject();
        jsonObject.put("content", content);
        String str = jsonObject.toJSONString();
        // 访问服务进程的套接字
        Socket socket = null;
        StringBuilder sb = new StringBuilder();
        try {
            // 初始化套接字，设置访问服务的主机和进程端口号，HOST是访问python进程的主机名称，可以是IP地址或者域名，PORT是python进程绑定的端口号
            socket = new Socket("192.168.1.119",12345);
            // 获取输出流对象
            OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os);
            // 发送内容
            out.print(str);
            // 告诉服务进程，内容发送完毕，可以开始处理
            out.print("over");
            // 获取服务进程的输入流
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
            String tmp = null;
            // 读取内容
            while((tmp=br.readLine())!=null)
                sb.append(tmp).append('\n');
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {if(socket!=null) socket.close();} catch (IOException e) {}
        }
        return sb.toString();
    }

    private AppResponse parseContent(String content,JSONArray entities,boolean complete){
        AppResponse appResponse = AppResponse.success();
        List<EntityStored> list = new ArrayList<>();
        for (Object o:entities){
            JSONObject jo = (JSONObject)o;
            EntityStored entityStored = new EntityStored(jo.getString("word"),jo.getInteger("start"),jo.getInteger("end"),jo.getString("type"));
            list.add(entityStored);
        }
        if (!complete){
            return appResponse.addParam(list);
        }
        ElectronicMedicalRecord emr = new ElectronicMedicalRecord();
        String startDate = content.substring(content.indexOf("入院日期")+5,content.indexOf("记录日期")).trim();
        String recordDate = content.substring(content.indexOf("记录日期")+5,content.indexOf("主诉")).trim();
        emr.setEnterDate(startDate);
        emr.setRecordDate(recordDate);
        List<EntityStored> chief = selectEntity(content.indexOf("主诉"),content.indexOf("现病史"),list);
        emr.setChiefComplaint(chief);
        List<EntityStored> present = selectEntity(content.indexOf("现病史"),content.indexOf("既往史"),list);
        emr.setCurrent(present);
        List<EntityStored> his = selectEntity(content.indexOf("既往史"),content.indexOf("个人史"),list);
        emr.setPast(his);
        List<EntityStored> personal = selectEntity(content.indexOf("个人史"),content.indexOf("婚育史"),list);
        emr.setPersonal(personal);
        List<EntityStored> mar = selectEntity(content.indexOf("婚育史"),content.indexOf("家族史"),list);
        emr.setMarriage(mar);
        List<EntityStored> fam = selectEntity(content.indexOf("家族史"),content.indexOf("体 格 检 查"),list);
        emr.setFamily(fam);
        String exam = content.substring(content.indexOf("体 格 检 查")+7).trim();
        String[] exams = exam.split("\\s+");
        List<String> ex = new ArrayList<>();
        for (String s:exams){
            ex.add(s.trim());
        }
        emr.setExamination(ex);
        return appResponse.addParam(emr);
    }
    private List<EntityStored> selectEntity(int start,int end,List<EntityStored> entities){
        List<EntityStored> res;
        res = entities.stream().filter((e) -> {
            if (e.getStartPosition() > start && e.getStartPosition() < end)
                return true;
            return false;
        }).collect(Collectors.toList());
        return res;
    }
}
