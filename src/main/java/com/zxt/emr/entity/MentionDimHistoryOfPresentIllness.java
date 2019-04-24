package com.zxt.emr.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "mention_dim_history_of_present_illness", schema = "emr", catalog = "")
public class MentionDimHistoryOfPresentIllness {
    private int id;
    private String mention;
    private String posB;
    private String posE;
    private String category;
    private String decoration;
    private String contentId;
    private String editUser;
    private String editTime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "mention")
    public String getMention() {
        return mention;
    }

    public void setMention(String mention) {
        this.mention = mention;
    }

    @Basic
    @Column(name = "pos_b")
    public String getPosB() {
        return posB;
    }

    public void setPosB(String posB) {
        this.posB = posB;
    }

    @Basic
    @Column(name = "pos_e")
    public String getPosE() {
        return posE;
    }

    public void setPosE(String posE) {
        this.posE = posE;
    }

    @Basic
    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "decoration")
    public String getDecoration() {
        return decoration;
    }

    public void setDecoration(String decoration) {
        this.decoration = decoration;
    }

    @Basic
    @Column(name = "content_id")
    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    @Basic
    @Column(name = "editUser")
    public String getEditUser() {
        return editUser;
    }

    public void setEditUser(String editUser) {
        this.editUser = editUser;
    }

    @Basic
    @Column(name = "editTime")
    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MentionDimHistoryOfPresentIllness that = (MentionDimHistoryOfPresentIllness) o;
        return id == that.id &&
                Objects.equals(mention, that.mention) &&
                Objects.equals(posB, that.posB) &&
                Objects.equals(posE, that.posE) &&
                Objects.equals(category, that.category) &&
                Objects.equals(decoration, that.decoration) &&
                Objects.equals(contentId, that.contentId) &&
                Objects.equals(editUser, that.editUser) &&
                Objects.equals(editTime, that.editTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, mention, posB, posE, category, decoration, contentId, editUser, editTime);
    }
}
