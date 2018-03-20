package cn.ntanjee.meetmeeting.controller.meeting;

import cn.ntanjee.meetmeeting.domain.Meeting;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;

import java.time.LocalDate;
import java.util.*;

public class MeetingController extends Controller {
    private JSONObject jsonObject = new JSONObject();

    public void index() {
        String token = getPara("token");
        String label = getPara("label");

        String authorization = "T000";
        HashMap<String, java.io.Serializable> map = new HashMap<String, java.io.Serializable>();
        map.put("mid", 2);
        map.put("title", "安卓研讨会");

        HashMap<String, java.io.Serializable> map1 = new HashMap<String, java.io.Serializable>();
        map1.put("mid", 2);
        map1.put("title", "安卓研讨会");

        List<HashMap<String, java.io.Serializable>> list = new LinkedList<HashMap<String, java.io.Serializable>>();
        list.add(map);
        list.add(map1);

        jsonObject.put("meetingList", list);
        jsonObject.put("authorization", authorization);

        renderJson(jsonObject);
    }

    public void info(){
        String token = getPara("token");
        String mid = getPara("mid");

        ArrayList<String> label = new ArrayList<>();
        label.add("公司年会");
        label.add("会议");
        LocalDate localDate = LocalDate.now();
        Meeting meeting = new Meeting(1, 1, "震惊！", "balabalabala...", localDate, "北京天安门", 1, 0, label, 0);
        int isPass = 1;
        String authorization = "T000";

        renderJson(meeting);

    }

    public void release() throws Exception {
        String title = getPara("title");
        String content = getPara("content");
        Date date = getParaToDate("date");
        String site = getPara("site");
        String label = getPara("label");
        String restrict = getPara("restrict");
        List<String> arrayLists = JSON.parseArray(label, String.class);
        String authorization = "T000";

        Boolean insert = false;

        if(insert){
            jsonObject.put("mid", "2");
            jsonObject.put("code", "R001");
            jsonObject.put("authorization", authorization);
        }else {
            jsonObject.put("code","R000");
        }

        renderJson(jsonObject);
    }

    public void list(){
        String token = getPara("token");

        String authorization = "T000";
        HashMap<String, java.io.Serializable> map = new HashMap<>();
        map.put("mid", 1);
        map.put("title", "Java 研讨会");

        HashMap<String, java.io.Serializable> map1 = new HashMap<String, java.io.Serializable>();
        map1.put("mid", 2);
        map1.put("title", "安卓研讨会");

        List<HashMap<String, java.io.Serializable>> list = new LinkedList<HashMap<String, java.io.Serializable>>();
        list.add(map);
        list.add(map1);

        jsonObject.put("meetingList", list);
        jsonObject.put("authorization", authorization);

        renderJson(jsonObject);
    }

    public void ist(){
        String token = getPara("token");
        String title = getPara("title");

        String authorization = "T000";
        HashMap<String, java.io.Serializable> map = new HashMap<>();
        map.put("mid", 1);
        map.put("title", "Java 研讨会");

        HashMap<String, java.io.Serializable> map1 = new HashMap<String, java.io.Serializable>();
        map1.put("mid", 2);
        map1.put("title", "安卓研讨会");

        List<HashMap<String, java.io.Serializable>> list = new LinkedList<HashMap<String, java.io.Serializable>>();
        list.add(map);
        list.add(map1);

        jsonObject.put("meetingList", list);
        jsonObject.put("authorization", authorization);

        renderJson(jsonObject);
    }

    public void share(){
        String token = getPara("token");
        int mid = getParaToInt("mid");

        String url = "https://www.baidu.com/";
        String authorization = "T000";

        jsonObject.put("url", url);
        jsonObject.put("authorization", authorization);

        renderJson(jsonObject);

    }
}
