package cn.ntanjee.meetmeeting.vo;

import java.time.LocalDateTime;

public class MessInfo {
    private String message;
    private int senderId;
    private String timeStr;
    private int isRequest;

    public MessInfo(String message, int senderId, String timeStr, int isRequest) {
        this.message = message;
        this.senderId = senderId;
        this.timeStr = timeStr;
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

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
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
                ", timeStr='" + timeStr + '\'' +
                ", isRequest=" + isRequest +
                '}';
    }
}
