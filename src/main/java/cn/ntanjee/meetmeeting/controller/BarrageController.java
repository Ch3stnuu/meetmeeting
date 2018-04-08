package cn.ntanjee.meetmeeting.controller;

import com.github.youyinnn.server.Server;
import com.jfinal.core.Controller;

/**
 * @author youyinnn
 */
public class BarrageController extends Controller {

    /**
     * 主办方打开网页弹幕
     */
    public void index(){
        render("barrage.html");
    }

    /**
     * 主办方连接网页弹幕的请求
     * 需要在网页下方的输入框输入系统给主办方提供的key, 可以是会议id, 也可以是和会议唯一对应的一串token
     */
    public void connect() {
        String key = getPara("key");
        // 这里的innerKey的获取需要自行更改
        // 123只是示例
        String innerKey = "123";

        // key对比成功则返回1 否则返回0
        if (innerKey.equals(key)) {
            renderJson("{\"code\":1}");
        } else {
            renderJson("{\"code\":0}");
        }
    }

    /**
     * 客户端请求发送弹幕
     */
    public void send() {
        String msg = getPara("msg");
        String key = getPara("key");
        Server.toUser(msg, key);
        renderNull();
    }
}
