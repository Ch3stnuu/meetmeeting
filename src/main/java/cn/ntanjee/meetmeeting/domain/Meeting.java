package cn.ntanjee.meetmeeting.domain;

import java.time.LocalDate;
import java.util.ArrayList;

public class Meeting {
    private int m_id;
    private int u_id;
    private String title;
    private String content;
    private LocalDate date;
    private String site;
    private int ispublic;
    private int restrict;
    private ArrayList label;
    private int status;

    public Meeting(int m_id, int u_id, String title, String content, LocalDate date, String site, int ispublic, int restrict, ArrayList label, int status) {
        this.m_id = m_id;
        this.u_id = u_id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.site = site;
        this.ispublic = ispublic;
        this.restrict = restrict;
        this.label = label;
        this.status = status;
    }

    public Meeting() {
    }

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getIspublic() {
        return ispublic;
    }

    public void setIspublic(int ispublic) {
        this.ispublic = ispublic;
    }

    public int getRestrict() {
        return restrict;
    }

    public void setRestrict(int restrict) {
        this.restrict = restrict;
    }

    public ArrayList getLabel() {
        return label;
    }

    public void setLabel(ArrayList label) {
        this.label = label;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "m_id=" + m_id +
                ", u_id=" + u_id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", site='" + site + '\'' +
                ", ispublic=" + ispublic +
                ", restrict=" + restrict +
                ", label=" + label +
                ", status=" + status +
                '}';
    }
}
