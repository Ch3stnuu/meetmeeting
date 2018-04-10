package cn.ntanjee.meetmeeting.controller.activity;

import cn.ntanjee.meetmeeting.model.User;
import cn.ntanjee.meetmeeting.service.SigninService;
import cn.ntanjee.meetmeeting.vo.TestObject;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 74123
 */
public class PrizeController extends Controller {
    private JSONObject jsonObject = new JSONObject();

    public void index(){
        String token = getPara("token");
        int number = getParaToInt("number");
        int gid = getParaToInt("gid");

        List<User> list = SigninService.getInstance().getRandomUser(gid, number);

        jsonObject.put("prizeList", list);

        renderJson(jsonObject);
    }
}
