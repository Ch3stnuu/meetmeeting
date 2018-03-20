package cn.ntanjee.meetmeeting.controller.activity;

import cn.ntanjee.meetmeeting.vo.TestObject;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;

import java.util.LinkedList;
import java.util.List;

public class SinginController extends Controller{
    private JSONObject jsonObject = new JSONObject();

    public void index(){
        String token = getPara("token");
        int gid = getParaToInt("gid");

        Boolean b = false;

        if (b){
            jsonObject.put("isSuccess", 1);
            jsonObject.put("isDone", 0);
        }else {
            jsonObject.put("isSuccess", 0);
            jsonObject.put("isDone", 1);
        }

        renderJson(jsonObject);
    }

    public void put(){
        String token = getPara("token");
        int gid = getParaToInt("gid");

        renderNull();
    }

    public void absence(){
        String token = getPara("token");
        int gid = getParaToInt("gid");

        TestObject t1 = new TestObject(1, "大黄");
        TestObject t2 = new TestObject(2, "小黄");

        List<TestObject> list = new LinkedList<>();
        list.add(t1);
        list.add(t2);

        jsonObject.put("absenceList", list);

        renderJson(jsonObject);
    }
}
