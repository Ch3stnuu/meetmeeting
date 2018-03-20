package cn.ntanjee.meetmeeting.controller.activity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class VoteController extends Controller {
    private JSONObject jsonObject = new JSONObject();

    public void index(){
        String method = getRequest().getMethod();

        if (method.equals("POST")){
            admin();
        }else {
            user();
        }
    }

    public void info(){
        String token = getPara("token");
        int vid = getParaToInt("vid");

        List<String> item = new LinkedList<>();
        item.add("是");
        item.add("一定是");
        item.add("肯定是");

        jsonObject.put("vname", "aa");
        jsonObject.put("item", item);
        jsonObject.put("isOver", 0);

        renderJson(jsonObject);

    }

    private void admin(){
        String token = getPara("token");
        int gid = getParaToInt("gid");
        String vname = getPara("vname");
        int minute = getParaToInt("minute");
        String item = getPara("item");
        List<String> aitem = JSON.parseArray(item, String.class);

        jsonObject.put("vid", 1);
        jsonObject.put("gid", 3);

        renderJson(jsonObject);
    }

    private void user(){
        String token = getPara("token");
        int vid = getParaToInt("vid");
        String item = getPara("item");

        jsonObject.put("isPass", 0);

        renderJson(jsonObject);
    }

    public void result(){
        String token = getPara("token");
        int vid = getParaToInt("vid");

        HashMap map = new HashMap();
        map.put("选项一", 12);
        map.put("选项二", 13);
        map.put("选项三", 14);

        jsonObject.put("result", map);

        renderJson(jsonObject);
    }
}
