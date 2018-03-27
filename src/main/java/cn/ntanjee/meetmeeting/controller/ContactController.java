package cn.ntanjee.meetmeeting.controller;

import cn.ntanjee.meetmeeting.model.Contact;
import cn.ntanjee.meetmeeting.model.User;
import cn.ntanjee.meetmeeting.service.ContactService;
import cn.ntanjee.meetmeeting.service.UserService;
import cn.ntanjee.meetmeeting.vo.TestObject;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;

import java.util.LinkedList;
import java.util.List;

public class ContactController extends Controller{
    private JSONObject jsonObject = new JSONObject();

    //bug
    public void index(){
        String token = getPara("token");

        int uid = 1;
        List<Contact> list = ContactService.getInstance().getContactList(uid);

        jsonObject.put("contactList", list);
        jsonObject.put("authorization", "T000");

        renderJson(jsonObject);
    }

    public void search(){
        String token = getPara("token");
        String username = getPara("username");

        List<User> list = UserService.getInstance().getByNameOrAcc(username);

        jsonObject.put("contactList", list);
        jsonObject.put("authorization", "T000");

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
