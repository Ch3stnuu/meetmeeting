package cn.ntanjee.meetmeeting.httpclient;

import cn.ntanjee.meetmeeting.model.User;
import cn.ntanjee.meetmeeting.service.MeetingService;
import cn.ntanjee.meetmeeting.service.UserService;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * @author 74123
 */
public class HttpSender {
    public static void sender(int uid, String[] cid, int rid, Object content, int mType, int mark) {
        Map<String, Object> message = new HashMap<>();
        message.put("objectName", "RC:TxtMsg");

        if (mType == 3) {
            message.put("content", content);
        } else if (mType == 4) {
            String title = MeetingService.getInstance().getRequestByRid(rid).getTitle();
            String status = null;
            if (mark == 0) {
                status = "未通过";
            } else if (mark == 1) {
                status = "已通过";
            }
            message.put("content", "您申请参加的 " + title + " 会议，审核" + status);
        }

        jsonComplete(uid, cid, message);
    }

    public static void conSender(int uid, String[] cid, int mType) {
        Map<String, Object> message = new HashMap<>();
        message.put("objectName", "RC:ContactNtf");

        User user = UserService.getInstance().getByUid(uid);
        String uName = user.get("username");

        //消息类型为
        // 1：好友申请   2：好友添加结果
        if (mType == 1) {
            message.put("operation", "op1");
            message.put("message", "用户 " + uName + " 请求添加您为好友");
        } else if (mType == 2) {
            message.put("operation", "op2");
            message.put("message", "您已成功添加 " + uName + " 为好友");
        }

        message.put("sourceUserId", uid);
        message.put("targetUserId", cid);

        jsonComplete(uid, cid, message);

    }

    private static void jsonComplete(int uid, String[] cid, Map<String, Object> message){
        JSONObject audience = new JSONObject();
        audience.put("userid", cid);
        audience.put("is_to_all", false);

        JSONObject notification = new JSONObject();
        notification.put("alert", "this id a push");

        String[] platform = {"android"};
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("platform", platform);
        jsonObject.put("fromuserid", String.valueOf(uid));
        jsonObject.put("audience", audience);
        jsonObject.put("message", message);
        jsonObject.put("notification", notification);

        MyHttpClient.doPost("https://api.cn.ronghub.com/push.json", jsonObject);
    }
}
