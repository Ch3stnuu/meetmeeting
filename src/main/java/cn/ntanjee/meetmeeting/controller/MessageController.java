package cn.ntanjee.meetmeeting.controller;

import cn.ntanjee.meetmeeting.vo.TestObject;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;

import java.util.LinkedList;
import java.util.List;

public class MessageController extends Controller {
    private JSONObject jsonObject = new JSONObject();

    public void list(){
        String token = getPara("token");

        Boolean b = true;
        TestObject o = new TestObject(1, "小明");
        TestObject o2 = new TestObject(2, "小红");

        List<TestObject> list = new LinkedList<>();
        list.add(o);
        list.add(o2);

        if (b) {
            jsonObject.put("msgList", list);
        } else {
            jsonObject.put("authorization", "T000");
        }

        renderJson(jsonObject);
    }

    public void info(){
        String token = getPara("token");
        int msgId = getParaToInt("msgId");
        int type = getParaToInt("type");

        TestObject o = new TestObject(1, "小明");

        renderJson(o);
    }

}
