package cn.ntanjee.meetmeeting.controller.meeting;

import cn.ntanjee.meetmeeting.domain.Meeting;
import cn.ntanjee.meetmeeting.domain.MeetingRequest;
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

        String authorization = "T000";

        jsonObject.put("mid", mid);
        jsonObject.put("authorization", authorization);

        renderJson(jsonObject);
    }

    public void list(){
        String token = getPara("token");

        String authorization = "T000";
        HashMap<Object, Object> map = new HashMap();
        map.put("r_id", 1);
        map.put("name", "大黄");

        HashMap<Object, Object> map2 = new HashMap();
        map2.put("r_id", 2);
        map2.put("name", "君君");

        List<Object> list = new LinkedList();
        list.add(map);
        list.add(map2);


        jsonObject.put("requestList", list);
        jsonObject.put("authorization", authorization);

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
