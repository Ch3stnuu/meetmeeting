package cn.ntanjee.meetmeeting.domain;

public class MeetingRequest {
    private int rid;
    private String title;
    private String name;
    private String tel;
    private String remark;

    public MeetingRequest(int rid, String title, String name, String tel, String remark) {
        this.rid = rid;
        this.title = title;
        this.name = name;
        this.tel = tel;
        this.remark = remark;
    }

    public MeetingRequest() {
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "MeetingRequest{" +
                "rid=" + rid +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
