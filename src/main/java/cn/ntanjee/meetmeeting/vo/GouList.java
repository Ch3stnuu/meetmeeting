package cn.ntanjee.meetmeeting.vo;

public class GouList {
    private int gid;
    private String title;

    public GouList(int gid, String title) {
        this.gid = gid;
        this.title = title;
    }

    public GouList() {
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "GouList{" +
                "gid=" + gid +
                ", title='" + title + '\'' +
                '}';
    }
}
