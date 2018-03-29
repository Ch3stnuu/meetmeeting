package cn.ntanjee.meetmeeting.controller.user;

import cn.ntanjee.meetmeeting.model.User;
import cn.ntanjee.meetmeeting.service.UserService;
import com.alibaba.fastjson.JSONObject;
import com.github.youyinnn.youwebutils.second.JwtHelper;
import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.upload.UploadFile;

import java.io.File;
import java.io.IOException;

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

        User user = UserService.getInstance().getByAccAndPwd(account, password);

        if (user != null){
            JwtHelper.initJWTWithHMAC256("heeeyou", "chestnut");
            JwtHelper jwtHelper = new JwtHelper();
            jwtHelper.setClaim("uid", user.get("uid"));

            String token = jwtHelper.getToken();

            jsonObject.put("code", "L000");
            jsonObject.put("token", token);
        } else {
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
        String method = getRequest().getMethod();

        if (method.equals("POST")){
            pwdP();
        }else {
            pwdG();
        }
    }

    //未完成 无短信发送业务
    private void pwdG(){
        String phone = getPara("phone");

        boolean b = true;

        if (b) {
            jsonObject.put("code", "S000");
        } else {
            jsonObject.put("code", "S001");
        }

        renderJson(jsonObject);
    }

    private void pwdP(){
        String phone = getPara("phone");
        String authcode = getPara("authcode");

        jsonObject.put("code", "AC00");
        jsonObject.put("account", phone);

        renderJson(jsonObject);
    }

    public void npwd() {
        String account = getPara("account");
        String password = getPara("password");

        int uid = 1;

        Boolean b = UserService.getInstance().updatePwd(uid, password);
        if (b) {
            jsonObject.put("authorization", "T001");
            jsonObject.put("code", "U000");
        }

        renderJson(jsonObject);
    }

    public void cpwd(){
        String token = getPara("token");
        String opassword = getPara("opassword");
        String npassword = getPara("npassword");

        int uid = 1;
        Boolean isRight = UserService.getInstance().isPwdRight(uid, opassword);
        if (isRight) {
            Boolean b = UserService.getInstance().updatePwd(uid, npassword);
            if (b) {
                jsonObject.put("code", "CP00");
            } else {
                jsonObject.put("code", "CP01");
            }
        } else {
            jsonObject.put("code", "OP01");
        }

        renderJson(jsonObject);
    }

    public void info() {
        UploadFile file = getFile("icon");
        String token = getPara("token");
        String username = getPara("username");

        int uid = 2;
        User user = UserService.getInstance().getByUid(uid);
        String newFilePath = PathKit.getWebRootPath() + File.separator + "image" + File.separator + user.get("account") + ".jpg";
        file.getFile().renameTo(new File(newFilePath));

        Boolean b = UserService.getInstance().editUser(uid, username, newFilePath);

        if (b) {
            User user1 = UserService.getInstance().getByUid(uid);
            jsonObject.put("username", user1.get("username"));
            jsonObject.put("icon", user1.get("icon"));
            jsonObject.put("code", "I000");
        } else {
            jsonObject.put("code", "I001");
        }

        renderJson(jsonObject);
    }
}
