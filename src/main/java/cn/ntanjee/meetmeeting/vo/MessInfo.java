package cn.ntanjee.meetmeeting.vo;

import java.time.LocalDate;

public class MessInfo {
    private String message;
    private int senderId;
    private LocalDate date;
    private int isRequest;

    public MessInfo(String message, int senderId, LocalDate date, int isRequest) {
        this.message = message;
        this.senderId = senderId;
        this.date = date;
        this.isRequest = isRequest;
    }

    public MessInfo() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getIsRequest() {
        return isRequest;
    }

    public void setIsRequest(int isRequest) {
        this.isRequest = isRequest;
    }

    @Override
    public String toString() {
        return "MessInfo{" +
                "message='" + message + '\'' +
                ", senderId=" + senderId +
                ", date=" + date +
                ", isRequest=" + isRequest +
                '}';
    }
}
