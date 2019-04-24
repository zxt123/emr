package com.zxt.emr.deal;

public class EntityStored {
    private String name;
    private int startPosition;
    private int endPosition;
    private String type;
    public EntityStored(String name, int startPosition, int endPosition, String type){
        this.name = name;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.type = type;
//        switch (type){
//            case "S": this.type = "自述症状";break;
//            case "P": this.type = "身体部位";break;
//            case "C": this.type = "时间";break;
//            case "D": this.type = "疾病";break;
//            case "E": this.type = "检查";break;
//            case "A": this.type = "异常检查结果";break;
//            case "T": this.type = "治疗";break;
//            default: this.type = "疾病诊断分类";
//        }
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(int endPosition) {
        this.endPosition = endPosition;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "EntityStored{" +
                "name='" + name + '\'' +
                ", startPosition=" + startPosition +
                ", endPosition=" + endPosition +
                ", type='" + type + '\'' +
                '}';
    }
}
