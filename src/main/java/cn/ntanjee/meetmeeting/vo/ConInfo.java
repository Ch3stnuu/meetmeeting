package cn.ntanjee.meetmeeting.vo;

public class ConInfo {
    private int cid;
    private String username;
    private int isFriend;
    private String authorization;

    public ConInfo(int cid, String username, int isFriend, String authorization) {
        this.cid = cid;
        this.username = username;
        this.isFriend = isFriend;
        this.authorization = authorization;
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

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    @Override
    public String toString() {
        return "ConInfo{" +
                "cid=" + cid +
                ", username='" + username + '\'' +
                ", isFriend=" + isFriend +
                ", authorization='" + authorization + '\'' +
                '}';
    }
}
