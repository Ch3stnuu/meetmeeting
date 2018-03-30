package cn.ntanjee.meetmeeting.controller.meeting;

import cn.ntanjee.meetmeeting.controller.TokenAnalysis;
import cn.ntanjee.meetmeeting.model.Meeting;
import cn.ntanjee.meetmeeting.service.MeetingService;
import cn.ntanjee.meetmeeting.vo.MeInfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class MeetingController extends Controller {
    private JSONObject jsonObject = new JSONObject();

    public void index() {
        String token = getPara("token");
        String label = getPara("label");

        List<Meeting> list = MeetingService.getInstance().getMeetingByLabel(label);

        jsonObject.put("meetingList", list);
        jsonObject.put("authorization", "T000");

        renderJson(jsonObject);
    }

    public void info(){
        String token = getPara("token");
        int mid = getParaToInt("mid");
        int isPass = 0;

        int uid = TokenAnalysis.analysis(token);

        Meeting meeting = MeetingService.getInstance().getMeetingById(mid);
        Boolean b = MeetingService.getInstance().isReuestedMeeting(mid, uid);

        if (b) {
            int status = MeetingService.getInstance().getRequestStatus(mid, uid);
            if (status == 1){
                isPass = 1;
            }
        } else {
            if (meeting.get("res").equals(0)){
                isPass = 1;
            }
        }

        MeInfo meInfo = new MeInfo(meeting.get("title"), meeting.get("content"), meeting.get("mid"),
                meeting.get("date").toString(), meeting.get("site"), isPass);

        renderJson(meInfo);

    }

    public void release() throws Exception {
        String token = getPara("token");
        String title = getPara("title");
        String content = getPara("content");
        Date date = getParaToDate("date");
        String site = getPara("site");
        String label = getPara("label");
        int res = getParaToInt("restrict");
        int isPublic = getParaToInt("isPublic");
        List<String> arrayLists = JSON.parseArray(label, String.class);

        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDate = LocalDateTime.ofInstant(instant, zoneId);

        String[] labelArray = new String[arrayLists.size()];
        for(int i = 0; i < arrayLists.size(); i++){
            labelArray[i] = arrayLists.get(i);
        }

        int mid = -1;
        int uid = TokenAnalysis.analysis(token);
         mid = MeetingService.getInstance().createMeeting(uid, title, content, localDate,
                site, labelArray, res, isPublic);

        if(mid >= 0){
            jsonObject.put("mid", mid);
            jsonObject.put("code", "R001");
            jsonObject.put("authorization", "T000");
        }else {
            jsonObject.put("code","R000");
        }

        renderJson(jsonObject);
    }

    public void list(){
        String token = getPara("token");

        int uid = TokenAnalysis.analysis(token);
        List<Meeting> list = MeetingService.getInstance().getMeetingListByUid(uid);

        jsonObject.put("meetingList", list);
        jsonObject.put("authorization", "T000");

        renderJson(jsonObject);
    }

    public void ist(){
        String token = getPara("token");
        String title = getPara("title");

        List<Meeting> list = MeetingService.getInstance().getMeetingByTitle(title);

        jsonObject.put("meetingList", list);
        jsonObject.put("authorization", "T000");

        renderJson(jsonObject);
    }

    public void share(){
        String token = getPara("token");
        int mid = getParaToInt("mid");

        String url = getRequest().getHeader("Host") + "/meeting/info?mid=" + mid + "&token=" + token;

        jsonObject.put("url", url);
        jsonObject.put("authorization", "T000");

        renderJson(jsonObject);

    }

}
