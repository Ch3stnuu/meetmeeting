package cn.ntanjee.meetmeeting.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;

import java.io.File;

public class UserController extends Controller {
    private JSONObject jsonObject = new JSONObject();

    public void session(){
        String method = getRequest().getMethod();

        if (method.equals("POST")){
            signin();
        }else {
            signout();
        }
    }

    private void signin() {
        String account = getPara("account");
        String password = getPara("password");

        Boolean b = false;
        String token = "ndjheujkjbkjbhu.dj838u8u8.dnnanjdnak";

        if (b){
            jsonObject.put("code", "L000");
            jsonObject.put("token", token);
        }else {
            jsonObject.put("code", "L0001");
            jsonObject.put("authorization", "T001");
        }

        renderJson(jsonObject);
    }

    private void signout(){
        String token = getPara("token");

        jsonObject.put("authrization", "T001");

        renderJson(jsonObject);
    }

    public void pwd(){
        String phone = getPara("phone");

        boolean b = true;

        if (b) {
            jsonObject.put("code", "S000");
        } else {
            jsonObject.put("code", "S001");
        }

        renderJson(jsonObject);
    }

    public void npwd() {
        String account = getPara("account");
        String password = getPara("password");
        String  repassword = getPara("repassword");

        jsonObject.put("authorization", "T001");

        renderJson(jsonObject);
    }

    public void cpwd(){
        String token = getPara("token");
        String opassword = getPara("opassword");
        String npassword = getPara("npassword");
        String repassword = getPara("repassword");

        Boolean b = true;

        if (b) {
            jsonObject.put("code", "CP00");
            jsonObject.put("authorization", "T001");
        } else {
            jsonObject.put("code", "CP01");
        }

        renderJson(jsonObject);
    }

    public void info(){
        String token = getPara("token");
        String username = getPara("username");
        File icon = new File(getPara("icon"));

        Boolean b = true;

        if (b) {
            jsonObject.put("username", "黄猪");
            jsonObject.put("icon", "/usr/www/meetmeeting/img/face.jpg");
            jsonObject.put("code", "I001");
        } else {
            jsonObject.put("code", "I001");
        }

        renderJson(jsonObject);
    }
}
