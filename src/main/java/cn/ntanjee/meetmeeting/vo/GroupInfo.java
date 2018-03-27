package cn.ntanjee.meetmeeting.vo;

import cn.ntanjee.meetmeeting.model.User;

import java.util.List;

public class GroupInfo {
    private int mid;
    private User admin;
    private List<User> conferee;
    private int isAdmin;
    private String authorization;

    public GroupInfo(int mid, User admin, List<User> conferee, int isAdmin, String authorization) {
        this.mid = mid;
        this.admin = admin;
        this.conferee = conferee;
        this.isAdmin = isAdmin;
        this.authorization = authorization;
    }

    public GroupInfo() {
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public List<User> getConferee() {
        return conferee;
    }

    public void setConferee(List<User> conferee) {
        this.conferee = conferee;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    @Override
    public String toString() {
        return "GroupInfo{" +
                "mid=" + mid +
                ", admin=" + admin +
                ", conferee=" + conferee +
                ", isAdmin=" + isAdmin +
                ", authorization='" + authorization + '\'' +
                '}';
    }
}
