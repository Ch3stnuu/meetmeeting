package cn.ntanjee.meetmeeting.controller;

import cn.ntanjee.meetmeeting.httpclient.HttpSender;
import cn.ntanjee.meetmeeting.model.Contact;
import cn.ntanjee.meetmeeting.model.Request;
import cn.ntanjee.meetmeeting.model.User;
import cn.ntanjee.meetmeeting.service.ContactService;
import cn.ntanjee.meetmeeting.service.MeetingService;
import cn.ntanjee.meetmeeting.service.UserService;
import cn.ntanjee.meetmeeting.vo.ConInfo;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 74123
 */
public class ContactController extends Controller{
    private JSONObject jsonObject = new JSONObject();

    public void index(){
        String token = getPara("token");

        int uid = TokenAnalysis.analysis(token);
        List<Contact> list = ContactService.getInstance().getContactList(uid);
        List<cn.ntanjee.meetmeeting.vo.Contact> contactList = new LinkedList<>();

        for (Contact contact:
             list) {
            contactList.add(new cn.ntanjee.meetmeeting.vo.Contact(contact.get("cid"),
                    UserService.getInstance().getByUid(contact.get("cid")).get("username")));
        }

        jsonObject.put("contactList", contactList);
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

        int uid = TokenAnalysis.analysis(token);
        ContactService.getInstance().createContact(uid, cid, 0);

        int[] cids = {cid};
        HttpSender.conSender(uid, cids, 1);

        jsonObject.put("authorization", "T000");

        renderJson(jsonObject);
    }

    public void admit(){
        String token = getPara("token");
        int cid = getParaToInt("cid");
        int admit = getParaToInt("admit");

        int uid = TokenAnalysis.analysis(token);
        ContactService.getInstance().updateContact(cid, uid);
        ContactService.getInstance().createContact(uid, cid, 1);

        int[] cids = {cid};
        HttpSender.conSender(uid, cids, 2);

        jsonObject.put("authorization", "T000");

        renderJson(jsonObject);
    }

    public void info(){
        String token = getPara("token");
        int cid = getParaToInt("cid");

        int uid = TokenAnalysis.analysis(token);
        User user = UserService.getInstance().getByUid(cid);
        Boolean b = ContactService.getInstance().isFriend(uid, cid);
        int isFriend;

        if (b) {
            isFriend = 1;
        } else {
            isFriend = 0;
        }

        ConInfo conInfo = new ConInfo(cid, user.get("username"), isFriend, "T000");

        renderJson(conInfo);
    }
}
