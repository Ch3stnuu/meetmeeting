package cn.ntanjee.meetmeeting.controller.meeting;

import cn.ntanjee.meetmeeting.controller.TokenAnalysis;
import cn.ntanjee.meetmeeting.httpclient.HttpSender;
import cn.ntanjee.meetmeeting.model.Request;
import cn.ntanjee.meetmeeting.model.User;
import cn.ntanjee.meetmeeting.service.GroupService;
import cn.ntanjee.meetmeeting.service.MeetingService;
import cn.ntanjee.meetmeeting.service.UserService;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;

import java.util.List;

/**
 * @author 74123
 */
public class MeetingRequestController extends Controller{
    private JSONObject jsonObject = new JSONObject();

    public void index(){
        int rid = getParaToInt("rid");
        int auth = getParaToInt("auth");
        String token = getPara("token");

        int uid = TokenAnalysis.analysis(token);
        MeetingService.getInstance().reviewRequest(rid, auth);
//        GroupService.getInstance().updateGroup()

        String tel = MeetingService.getInstance().getRequestByRid(rid).get("tel");
        List<User> users = null;
        users = UserService.getInstance().getByNameOrAcc(tel);
        System.out.println(users);

        if (users != null) {
            int u = users.get(0).get("uid");
            String[] cid = {String.valueOf(u)};
            HttpSender.sender(uid, cid, rid, null, 4, auth);
        }

        jsonObject.put("authorization", "T000");

        renderJson(jsonObject);
    }

    public void put(){
        String token = getPara("token");
        int mid = getParaToInt("mid");
        String name = getPara("name");
        String phone = getPara("phone");
        String remark = getPara("remark");

        int i = -1;
        int uid = TokenAnalysis.analysis(token);
        i = MeetingService.getInstance().createRequest(uid, mid, name, phone, remark);

        if (i < 0){
            jsonObject.put("authorization", "T001");
        } else {
            jsonObject.put("mid", mid);

            int u = MeetingService.getInstance().getMeetingById(mid).get("uid");
            String[] cid ={String.valueOf(u)};
            String title = MeetingService.getInstance().getMeetingById(mid).get("title");

            String content = "会议名称：" + title + "\n" +
                    "申请人姓名：" + name + "\n" +
                    "联系电话：" + phone + "\n" +
                    "备注：" + remark;

            HttpSender.sender(uid, cid, -1, content, 3, -1);
        }

        renderJson(jsonObject);
    }

    public void list(){
        String token = getPara("token");

        int uid = TokenAnalysis.analysis(token);
        List<Request> requestList = MeetingService.getInstance().getRequestByUid(uid);

        jsonObject.put("requestList", requestList);

        renderJson(jsonObject);
    }

    public void info(){
        int rid = getParaToInt("rid");
        String token = getPara("token");

        Request request = MeetingService.getInstance().getRequestByRid(rid);

        renderJson(request);
    }

    public void mylist(){
        String token = getPara("token");
        int status = getParaToInt("status");

        int uid = TokenAnalysis.analysis(token);
        List<Request> list = MeetingService.getInstance().getMyRequest(uid, status);

        jsonObject.put("meetingList", list);
        jsonObject.put("authorization", "T000");

        renderJson(jsonObject);
    }
}
