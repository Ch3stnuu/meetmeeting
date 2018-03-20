package cn.ntanjee.meetmeeting.controller.activity;

import cn.ntanjee.meetmeeting.vo.TestObject;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;

import java.util.LinkedList;
import java.util.List;

public class PrizeController extends Controller {
    private JSONObject jsonObject = new JSONObject();

    public void index(){
        String token = getPara("token");
        int number = getParaToInt("number");

        TestObject t1 = new TestObject(1, "大黄");
        TestObject t2 = new TestObject(2, "小黄");

        List<TestObject> list = new LinkedList<>();
        list.add(t1);
        list.add(t2);

        jsonObject.put("prizeList", list);

        renderJson(jsonObject);
    }
}
