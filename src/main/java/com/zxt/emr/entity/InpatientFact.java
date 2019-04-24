package com.zxt.emr.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "inpatient_fact", schema = "emr", catalog = "")
public class InpatientFact {
    private int id;
    private String chiefComplaint;
    private String historyOfPresentIllness;
    private String pastHistory;
    private String personalHistory;
    private String maritalHistory;
    private String familyHistory;
    private String physicalExamination;
    private String generalAppearance;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "chief_complaint")
    public String getChiefComplaint() {
        return chiefComplaint;
    }

    public void setChiefComplaint(String chiefComplaint) {
        this.chiefComplaint = chiefComplaint;
    }

    @Basic
    @Column(name = "history_of_present_illness")
    public String getHistoryOfPresentIllness() {
        return historyOfPresentIllness;
    }

    public void setHistoryOfPresentIllness(String historyOfPresentIllness) {
        this.historyOfPresentIllness = historyOfPresentIllness;
    }

    @Basic
    @Column(name = "past_history")
    public String getPastHistory() {
        return pastHistory;
    }

    public void setPastHistory(String pastHistory) {
        this.pastHistory = pastHistory;
    }

    @Basic
    @Column(name = "personal_history")
    public String getPersonalHistory() {
        return personalHistory;
    }

    public void setPersonalHistory(String personalHistory) {
        this.personalHistory = personalHistory;
    }

    @Basic
    @Column(name = "marital_history")
    public String getMaritalHistory() {
        return maritalHistory;
    }

    public void setMaritalHistory(String maritalHistory) {
        this.maritalHistory = maritalHistory;
    }

    @Basic
    @Column(name = "family_history")
    public String getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(String familyHistory) {
        this.familyHistory = familyHistory;
    }

    @Basic
    @Column(name = "physical_examination")
    public String getPhysicalExamination() {
        return physicalExamination;
    }

    public void setPhysicalExamination(String physicalExamination) {
        this.physicalExamination = physicalExamination;
    }

    @Basic
    @Column(name = "general_appearance")
    public String getGeneralAppearance() {
        return generalAppearance;
    }

    public void setGeneralAppearance(String generalAppearance) {
        this.generalAppearance = generalAppearance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InpatientFact that = (InpatientFact) o;
        return id == that.id &&
                Objects.equals(chiefComplaint, that.chiefComplaint) &&
                Objects.equals(historyOfPresentIllness, that.historyOfPresentIllness) &&
                Objects.equals(pastHistory, that.pastHistory) &&
                Objects.equals(personalHistory, that.personalHistory) &&
                Objects.equals(maritalHistory, that.maritalHistory) &&
                Objects.equals(familyHistory, that.familyHistory) &&
                Objects.equals(physicalExamination, that.physicalExamination) &&
                Objects.equals(generalAppearance, that.generalAppearance);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, chiefComplaint, historyOfPresentIllness, pastHistory, personalHistory, maritalHistory, familyHistory, physicalExamination, generalAppearance);
    }
}
