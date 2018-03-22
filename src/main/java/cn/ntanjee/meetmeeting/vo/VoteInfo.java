package cn.ntanjee.meetmeeting.vo;

import java.util.Arrays;

public class VoteInfo {
    private String vname;
    private String[] item;
    private int isOver;
    private String authorization ;

    public VoteInfo(String vname, String[] item, int isOver, String authorization) {
        this.vname = vname;
        this.item = item;
        this.isOver = isOver;
        this.authorization = authorization;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String[] getItem() {
        return item;
    }

    public void setItem(String[] item) {
        this.item = item;
    }

    public int getIsOver() {
        return isOver;
    }

    public void setIsOver(int isOver) {
        this.isOver = isOver;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    @Override
    public String toString() {
        return "VoteInfo{" +
                "vname='" + vname + '\'' +
                ", item=" + Arrays.toString(item) +
                ", isOver=" + isOver +
                ", authorization='" + authorization + '\'' +
                '}';
    }
}
