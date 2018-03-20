package cn.ntanjee.meetmeeting.controller;

import cn.ntanjee.meetmeeting.vo.TestObject;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;

import java.util.LinkedList;
import java.util.List;

public class ContactController extends Controller{
    private JSONObject jsonObject = new JSONObject();

    public void index(){
        String token = getPara("token");

        TestObject o = new TestObject(1, "小明");
        TestObject o2 = new TestObject(2, "小红");

        List<TestObject> list = new LinkedList<>();
        list.add(o);
        list.add(o2);

        jsonObject.put("contactList", list);
        jsonObject.put("authorization", "T000");

        renderJson(jsonObject);
    }

    public void search(){
        String token = getPara("token");
        String username = getPara("username");

        Boolean b = true;
        TestObject o = new TestObject(1, "小明");
        TestObject o2 = new TestObject(2, "小红");

        List<TestObject> list = new LinkedList<>();
        list.add(o);
        list.add(o2);

        if (b) {
            jsonObject.put("contactList", list);
            jsonObject.put("authorization", "T000");
        } else {
            jsonObject.put("authorization", "T001");
        }

        renderJson(jsonObject);
    }

    public void add(){
        String token = getPara("token");
        int cid = getParaToInt("cid");

        Boolean b = true;

        if (b) {
            jsonObject.put("authorization", "T000");
        } else {
            jsonObject.put("authorization", "T001");
        }

        renderJson(jsonObject);
    }

    public void admit(){
        String token = getPara("token");
        int cid = getParaToInt("cid");
        int admit = getParaToInt("admit");

        jsonObject.put("authorization", "T000");

        renderJson(jsonObject);
    }

    public void info(){
        String token = getPara("token");
        int cid = getParaToInt("cid");

        TestObject o = new TestObject(1, "小明");

        jsonObject.put("", o);
        jsonObject.put("authorization", "T000");

        renderJson(jsonObject);
    }
}
