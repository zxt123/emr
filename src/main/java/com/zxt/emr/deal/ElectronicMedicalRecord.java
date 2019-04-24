package com.zxt.emr.deal;

import java.util.ArrayList;
import java.util.List;

public class ElectronicMedicalRecord {
    private String enterDate;
    private String recordDate;
    private List<EntityStored> chiefComplaint = new ArrayList<>();
    private List<EntityStored> current= new ArrayList<>();
    private List<String> examination= new ArrayList<>();
    private List<EntityStored> general= new ArrayList<>();
    private List<EntityStored> past= new ArrayList<>();
    private List<EntityStored> personal= new ArrayList<>();
    private List<EntityStored> marriage= new ArrayList<>();
    private List<EntityStored> family= new ArrayList<>();


    public String getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(String enterDate) {
        this.enterDate = enterDate;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public List<EntityStored> getChiefComplaint() {
        return chiefComplaint;
    }

    public void setChiefComplaint(List<EntityStored> chiefComplaint) {
        this.chiefComplaint = chiefComplaint;
    }

    public List<EntityStored> getCurrent() {
        return current;
    }

    public void setCurrent(List<EntityStored> current) {
        this.current = current;
    }

    public List<String> getExamination() {
        return examination;
    }

    public void setExamination(List<String> examination) {
        this.examination = examination;
    }

    public List<EntityStored> getGeneral() {
        return general;
    }

    public void setGeneral(List<EntityStored> general) {
        this.general = general;
    }

    public List<EntityStored> getPast() {
        return past;
    }

    public void setPast(List<EntityStored> past) {
        this.past = past;
    }

    public List<EntityStored> getPersonal() {
        return personal;
    }

    public void setPersonal(List<EntityStored> personal) {
        this.personal = personal;
    }

    public List<EntityStored> getMarriage() {
        return marriage;
    }

    public void setMarriage(List<EntityStored> marriage) {
        this.marriage = marriage;
    }

    public List<EntityStored> getFamily() {
        return family;
    }

    public void setFamily(List<EntityStored> family) {
        this.family = family;
    }



}
