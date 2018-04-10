package cn.ntanjee.meetmeeting.controller.user;

import cn.ntanjee.meetmeeting.controller.TokenAnalysis;
import cn.ntanjee.meetmeeting.model.User;
import cn.ntanjee.meetmeeting.service.UserService;
import com.alibaba.fastjson.JSONObject;
import com.github.youyinnn.youwebutils.second.JwtHelper;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.upload.UploadFile;

import java.io.File;
import java.io.IOException;

/**
 * @author 74123
 */
public class UserController extends Controller {
    private JSONObject jsonObject = new JSONObject();

    @Clear
    public void session(){
        String method = getRequest().getMethod();

        if (method.equals("POST")){
            signin();
        }else {
            signout();
        }
    }

    @Clear
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

    public void npwd() {
        String account = getPara("account");
        String password = getPara("password");

        Boolean isRight = UserService.getInstance().isAccExist(account);

        if (isRight) {
            Boolean b = UserService.getInstance().updatePwdByAccount(account, password);
            if (b) {
                jsonObject.put("code", "U000");
            } else {
                jsonObject.put("code", "U001");
            }
        } else {
            jsonObject.put("code", "L001");
        }
        jsonObject.put("authorization", "T001");

        renderJson(jsonObject);
    }

    public void cpwd(){
        String token = getPara("token");
        String opassword = getPara("opassword");
        String npassword = getPara("npassword");

        int uid = TokenAnalysis.analysis(token);
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

        int uid = TokenAnalysis.analysis(token);
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

    @ActionKey("/user/info/icon")
    public void icon(){
        int uid = getParaToInt("id");
        String token = getPara("token");

        String iconUrl = UserService.getInstance().getByUid(uid).get("icon");

        jsonObject.put("icon", iconUrl);

        renderJson(jsonObject);
    }
}
