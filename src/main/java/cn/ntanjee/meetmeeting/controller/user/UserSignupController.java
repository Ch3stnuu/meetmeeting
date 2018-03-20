package cn.ntanjee.meetmeeting.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;

public class UserSignupController extends Controller {
    private JSONObject jsonObject = new JSONObject();

    public void index(){
        String account = getPara("account");
        String username = getPara("username");
        String password = getPara("password");
        String repassword = getPara("repassword");

        boolean b = true;

        if (b) {
            jsonObject.put("code", "S000");
        } else {
            jsonObject.put("code", "S001");
        }

        renderJson(jsonObject);
    }

    public void authcode() {
        String method = getRequest().getMethod();

        if (method.equals("POST")){
            authPost();
        }else {
            authGet();
        }
    }

    private void authGet(){
        String phone = getPara("phone");

        boolean b = false;

        if (b){
            jsonObject.put("legle", 1);
        }else {
            jsonObject.put("legle", 0);
        }

        renderJson(jsonObject);
    }

    private void authPost() {
        String phone = getPara("phone");
        String authcode = getPara("authcode");

        boolean b = true;
        String account = phone;

        if (b) {
            jsonObject.put("code", "AC00");
            jsonObject.put("account", account);
        } else {
            jsonObject.put("code", "AC01");
        }

        renderJson(jsonObject);
    }
}
