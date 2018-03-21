package cn.ntanjee.meetmeeting.controller.meeting;

import cn.ntanjee.meetmeeting.domain.Meeting;
import cn.ntanjee.meetmeeting.domain.MeetingRequest;
import cn.ntanjee.meetmeeting.model.Request;
import cn.ntanjee.meetmeeting.service.MeetingService;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MeetingRequestController extends Controller{
    private JSONObject jsonObject = new JSONObject();

    public void index(){
        int rid = getParaToInt("rid");
        int auth = getParaToInt("auth");
        String token = getPara("token");


        jsonObject.put("authorization", "T000");

        renderJson(jsonObject);
    }

    public void put(){
        String token = getPara("token");
        int mid = getParaToInt("mid");
        String name = getPara("name");
        String phone = getPara("phone");
        String remark = getPara("remark");

        int i = -1;
        i = MeetingService.getInstance().createRequest(1, mid, name, phone, remark);

        if (i < 0){
            jsonObject.put("authorization", "T001");
        } else {
            jsonObject.put("mid", mid);
        }

        renderJson(jsonObject);
    }

    public void list(){
        String token = getPara("token");

        List<Request> requestList = MeetingService.getInstance().getRequestByUid(1);

        jsonObject.put("requestList", requestList);

        renderJson(jsonObject);
    }

    public void info(){
        int rid = getParaToInt("rid");
        String token = getPara("token");

        MeetingRequest m = new MeetingRequest(1, "还有这种操作...", "黄猪", "12345678769", "我是一只小萌新");
        String authorization = "T000";

        jsonObject.put("r_id", m.getRid());
        jsonObject.put("title", m.getTitle());
        jsonObject.put("name", m.getName());
        jsonObject.put("tel", m.getTel());
        jsonObject.put("remark", m.getRemark());
        jsonObject.put("authorization", authorization);

        renderJson(jsonObject);
    }

    public void mylist(){
        String token = getPara("token");
        int status = getParaToInt("status");

        ArrayList<String> label = new ArrayList();
        label.add("公司年会");
        label.add("会议");

        LocalDate localDate = LocalDate.now();
        Meeting meeting = new Meeting(1, 1, "震惊！", "balabalabala...", localDate, "北京天安门", 1, 0, label, 0);
        Meeting meeting2 = new Meeting(1, 1, "震惊！", "balabalabala...", localDate, "北京天安门", 1, 0, label, 0);

        List list = new LinkedList();
        list.add(meeting);
        list.add(meeting2);

        jsonObject.put("meetingList", list);

        renderJson(jsonObject);
    }
}
