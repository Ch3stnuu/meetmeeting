package cn.ntanjee.meetmeeting.vo;

public class Contact {
    private int cid;
    private String contactName;

    public Contact(int cid, String contactName) {
        this.cid = cid;
        this.contactName = contactName;
    }

    public Contact() {
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "cid=" + cid +
                ", contactName='" + contactName + '\'' +
                '}';
    }
}
