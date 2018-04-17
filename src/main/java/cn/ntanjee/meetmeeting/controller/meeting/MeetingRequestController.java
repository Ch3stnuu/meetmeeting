package cn.ntanjee.meetmeeting.controller.meeting;

import cn.ntanjee.meetmeeting.controller.TokenAnalysis;
import cn.ntanjee.meetmeeting.httpclient.HttpSender;
import cn.ntanjee.meetmeeting.model.Meeting;
import cn.ntanjee.meetmeeting.model.Request;
import cn.ntanjee.meetmeeting.model.User;
import cn.ntanjee.meetmeeting.service.GroupService;
import cn.ntanjee.meetmeeting.service.MeetingService;
import cn.ntanjee.meetmeeting.service.UserService;
import cn.ntanjee.meetmeeting.vo.MyList;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;

import java.util.LinkedList;
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

        List<User> users = null;
        String tel = MeetingService.getInstance().getRequestByRid(rid).get("tel");
        users = UserService.getInstance().getByNameOrAcc(tel);

        //验证token
        int uid = TokenAnalysis.analysis(token);

        //如果同意，向群组中添加成员
        if (auth == 1) {
            int gid;
            Request request = MeetingService.getInstance().getRequestByRid(rid);
            gid = GroupService.getInstance().getGidByMid(request.get("mid"));
            if (gid > 0) {
                //修改请求状态
                MeetingService.getInstance().reviewRequest(rid, auth);
                //向Group表中添加成员
                GroupService.getInstance().updateGroup(gid, users.get(0).get("uid"));
                jsonObject.put("code", "R000");
            } else {
                jsonObject.put("code", "R001");
            }
        }

        int cid = users.get(0).get("uid");
        //发送http请求给融云
        HttpSender.sender(uid, cid, rid, null, 4, auth);

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

            int cid = MeetingService.getInstance().getMeetingById(mid).get("uid");
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

        List<MyList> myLists = new LinkedList<>();
        for (Request re:
             list) {
            Meeting meeting = MeetingService.getInstance().getMeetingById(re.get("mid"));
            MyList myList = new MyList(re.get("mid"), meeting.get("title"), re.get("status"));
            myLists.add(myList);
        }

        jsonObject.put("meetingList", myLists);
        jsonObject.put("authorization", "T000");

        renderJson(jsonObject);
    }
}
