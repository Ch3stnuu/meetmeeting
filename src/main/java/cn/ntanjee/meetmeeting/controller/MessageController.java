package cn.ntanjee.meetmeeting.controller;

import cn.ntanjee.meetmeeting.model.Message;
import cn.ntanjee.meetmeeting.service.MessageService;
import cn.ntanjee.meetmeeting.vo.MessInfo;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;

import java.sql.Timestamp;
import java.util.List;

public class MessageController extends Controller {
    private JSONObject jsonObject = new JSONObject();

    //bug
    public void list(){
        String token = getPara("token");

        int uid = 2;
        List<Message> list = MessageService.getInstance().getListByUid(uid);

        jsonObject.put("msgList", list);

        renderJson(jsonObject);
    }

    public void info(){
        String token = getPara("token");
        int msgId = getParaToInt("msgId");
        int type = getParaToInt("type");

        Message message = MessageService.getInstance().getByMsgId(msgId);
        int isRequest;
        if (type == 3) {
            isRequest = 1;
        } else {
            isRequest = 0;
        }

        Timestamp timestamp = message.get("send_date_time");
        String timeStr = timestamp.toLocalDateTime().toString();

        MessInfo messInfo = new MessInfo(message.get("message"), message.get("sender_id"),
                timeStr, isRequest);

        renderJson(messInfo);
    }

}
