package cn.ntanjee.meetmeeting.model;

import com.jfinal.plugin.activerecord.Model;

public class Request extends Model<Request> {

    public static final Request dao = new Request().dao();

    public String getTitle(){
        return Meeting.dao.findById(getInt("mid")).getStr("title");
    }
}
