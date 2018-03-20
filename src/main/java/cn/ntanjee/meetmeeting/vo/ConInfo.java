package cn.ntanjee.meetmeeting.vo;

public class ConInfo {
    private int cid;
    private String username;
    private int isFriend;

    public ConInfo(int cid, String username, int isFriend) {
        this.cid = cid;
        this.username = username;
        this.isFriend = isFriend;
    }

    public ConInfo() {
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(int isFriend) {
        this.isFriend = isFriend;
    }

    @Override
    public String toString() {
        return "ConInfo{" +
                "cid=" + cid +
                ", username='" + username + '\'' +
                ", isFriend=" + isFriend +
                '}';
    }
}
