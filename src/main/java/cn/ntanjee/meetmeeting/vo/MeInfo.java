package cn.ntanjee.meetmeeting.vo;

import java.time.LocalDate;

public class MeInfo {
    private String title;
    private String content;
    private int mid;
    private LocalDate date;
    private String site;
    private int isPass;

    public MeInfo(String title, String content, int mid, LocalDate date, String site, int isPass) {
        this.title = title;
        this.content = content;
        this.mid = mid;
        this.date = date;
        this.site = site;
        this.isPass = isPass;
    }

    public MeInfo() {
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

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getIsPass() {
        return isPass;
    }

    public void setIsPass(int isPass) {
        this.isPass = isPass;
    }

    @Override
    public String toString() {
        return "MeInfo{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", mid=" + mid +
                ", date=" + date +
                ", site='" + site + '\'' +
                ", isPass=" + isPass +
                '}';
    }
}
