package cn.ntanjee.meetmeeting.controller;

import cn.ntanjee.meetmeeting.model.Group;
import cn.ntanjee.meetmeeting.model.User;
import cn.ntanjee.meetmeeting.service.GroupService;
import cn.ntanjee.meetmeeting.service.UserService;
import cn.ntanjee.meetmeeting.vo.GroupInfo;
import cn.ntanjee.meetmeeting.vo.TestObject;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;

import java.util.LinkedList;
import java.util.List;

public class GroupController extends Controller {

    public void list(){
        String token = getPara("token");

        int uid = 1;

        List<Group> list = GroupService.getInstance().getListByConferee(uid);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("groupList", list);
        jsonObject.put("authorization", "T000");

        renderJson(jsonObject);
    }

    public void info(){
        String token = getPara("token");
        int gid = getParaToInt("gid");

        int uid = 1;
        Group group = GroupService.getInstance().getGroupById(gid);

        int isAdmin = 0;
        if (group.get("admin").equals(uid)) {
            isAdmin = 1;
        }

        User admin = UserService.getInstance().getByUid(group.get("admin"));

        int[] confereeUids = GroupService.getInstance().getConfereeUid(gid);
        List<User> list = new LinkedList<>();
        for (int i:
             confereeUids) {
            list.add(UserService.getInstance().getByUid(i));
        }

        GroupInfo groupInfo = new GroupInfo(group.get("mid"), admin, list, isAdmin, "T000");

        renderJson(groupInfo);
    }
}
