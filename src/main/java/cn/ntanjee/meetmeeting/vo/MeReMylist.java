package cn.ntanjee.meetmeeting.vo;

public class MeReMylist {
    private int mid;
    private String title;
    private int isPass;

    public MeReMylist(int mid, String title, int isPass) {
        this.mid = mid;
        this.title = title;
        this.isPass = isPass;
    }

    public MeReMylist() {
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIsPass() {
        return isPass;
    }

    public void setIsPass(int isPass) {
        this.isPass = isPass;
    }

    @Override
    public String toString() {
        return "MeReMylist{" +
                "mid=" + mid +
                ", title='" + title + '\'' +
                ", isPass=" + isPass +
                '}';
    }
}
