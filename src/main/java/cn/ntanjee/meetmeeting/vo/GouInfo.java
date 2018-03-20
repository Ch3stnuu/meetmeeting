package cn.ntanjee.meetmeeting.vo;


import cn.ntanjee.meetmeeting.domain.User;

import java.util.List;

public class GouInfo {
    private User admin;
    private int mid;
    private List confree;
    private int isAdmin;

    public GouInfo(User admin, int mid, List confree, int isAdmin) {
        this.admin = admin;
        this.mid = mid;
        this.confree = confree;
        this.isAdmin = isAdmin;
    }

    public GouInfo() {
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public List getConfree() {
        return confree;
    }

    public void setConfree(List confree) {
        this.confree = confree;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "GouInfo{" +
                "admin=" + admin +
                ", mid=" + mid +
                ", confree=" + confree +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
