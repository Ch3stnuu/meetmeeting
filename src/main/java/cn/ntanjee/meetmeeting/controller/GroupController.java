package cn.ntanjee.meetmeeting.controller;

import cn.ntanjee.meetmeeting.vo.TestObject;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;

import java.util.LinkedList;
import java.util.List;

public class GroupController extends Controller {
    private JSONObject jsonObject = new JSONObject();

    public void list(){
        String token = getPara("token");

        TestObject o = new TestObject(1, "小明");
        TestObject o2 = new TestObject(2, "小红");

        List<TestObject> list = new LinkedList<>();
        list.add(o);
        list.add(o2);

        jsonObject.put("groupList", list);
        jsonObject.put("authorization", "T000");

        renderJson(jsonObject);
    }

    public void info(){
        String token = getPara("token");
        String gid = getPara("gid");

        TestObject o = new TestObject(1, "小明");
        TestObject o2 = new TestObject(2, "小红");
        TestObject o3 = new TestObject(2, "小小");

        List<TestObject> list = new LinkedList<>();
        list.add(o);
        list.add(o2);

        jsonObject.put("mid", gid);
        jsonObject.put("admin", o3);
        jsonObject.put("conferee", list);
        jsonObject.put("authorization", "T000");

        renderJson(jsonObject);
    }
}
