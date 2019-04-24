package com.zxt.emr.utils;

import com.alibaba.fastjson.JSON;


import java.util.*;

public class AppResponse {
    private int flag;
    private int errCode;
    private List<JSON> params;
    private AppResponse(){}

    public int getFlag() {
        return flag;
    }

    public static AppResponse success(){
        return new AppResponse().setFlag(1).setErrCode(ErrCodeMap.NONE);
    }

    public static AppResponse failure(int errCode){
        return new AppResponse().setFlag(0).setErrCode(errCode);
    }

    public AppResponse setFlag(int flag) {
        this.flag = flag;
        return this;
    }

    public int getErrCode() {
        return errCode;
    }

    public AppResponse setErrCode(int errCode) {
        this.errCode = errCode;
        return this;
    }

    public List<JSON> getParams() {
        return params;
    }

    public AppResponse setParams(List<JSON> params) {
        if (params!=null) {
            this.params = params;
        }
        return this;
    }

    public AppResponse setParams(Object[] params){
        if (params != null){
            Arrays.stream(params).forEach(this::addParam);
        }
        return this;
    }

    public AppResponse addParam(JSON param){
        if (params == null){
            params = new ArrayList<>();
        }
        params.add(param);
        return this;
    }

    public AppResponse addParam(Map<String,String>param){
        return addParam((JSON)JSON.toJSON(param));
    }

    public AppResponse addParam(Object param) {
        return addParam((JSON) JSON.toJSON(param));
    }

    public JSON toJSON() {
        return (JSON) JSON.toJSON(this);
    }

    public AppResponse addParam(List list){
        if (list==null||list.isEmpty()){
            return this;
        }else {
            Iterator iterator = list.iterator();
            while(iterator.hasNext()){
                addParam(iterator.next());
            }
        }
        return this;
    }
}
