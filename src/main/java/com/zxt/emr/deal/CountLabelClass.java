package com.zxt.emr.deal;

public class CountLabelClass {
    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getLabelNumber() {
        return labelNumber;
    }

    public void setLabelNumber(int labelNumber) {
        this.labelNumber = labelNumber;
    }

    private int number;
    private int labelNumber;
    public CountLabelClass(String className){
        this.className = className;
        number = 0;
        labelNumber = 0;
    }

    @Override
    public String toString() {
        return className+" --- "+number+" --- "+labelNumber;
    }
}
