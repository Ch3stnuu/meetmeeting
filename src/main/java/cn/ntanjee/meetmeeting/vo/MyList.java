package cn.ntanjee.meetmeeting.vo;

public class MyList {
    private int mid;
    private String title;
    private int status;

    public MyList() {
    }

    public MyList(int mid, String title, int status) {
        this.mid = mid;
        this.title = title;
        this.status = status;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MyList{" +
                "mid=" + mid +
                ", title='" + title + '\'' +
                ", status=" + status +
                '}';
    }
}
