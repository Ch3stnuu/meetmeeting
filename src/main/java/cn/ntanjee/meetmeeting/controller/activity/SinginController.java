package cn.ntanjee.meetmeeting.controller.activity;

import cn.ntanjee.meetmeeting.model.User;
import cn.ntanjee.meetmeeting.service.SigninService;
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

        Boolean b = SigninService.getInstance().sign(gid, 3);

        if (b){
            jsonObject.put("isSuccess", 1);
            jsonObject.put("isDone", 0);
            jsonObject.put("authorization", "T000");
        }else {
            jsonObject.put("isSuccess", 0);
            jsonObject.put("isDone", 1);
            jsonObject.put("authorization", "T001");
        }

        renderJson(jsonObject);
    }

    public void put(){
        String token = getPara("token");
        int gid = getParaToInt("gid");

        int g = SigninService.getInstance().createSignin(gid);

        jsonObject.put("gid", g);

        renderJson(jsonObject);
    }

    public void absence(){
        String token = getPara("token");
        int gid = getParaToInt("gid");

        List<User> list = SigninService.getInstance().getAbsence(gid);

        jsonObject.put("absenceList", list);

        renderJson(jsonObject);
    }
}
