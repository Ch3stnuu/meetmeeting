package cn.ntanjee.meetmeeting.config;

import cn.ntanjee.meetmeeting.controller.ContactController;
import cn.ntanjee.meetmeeting.controller.GroupController;
import cn.ntanjee.meetmeeting.controller.MessageController;
import cn.ntanjee.meetmeeting.controller.activity.PrizeController;
import cn.ntanjee.meetmeeting.controller.activity.SinginController;
import cn.ntanjee.meetmeeting.controller.activity.VoteController;
import cn.ntanjee.meetmeeting.controller.meeting.MeetingController;
import cn.ntanjee.meetmeeting.controller.meeting.MeetingRequestController;
import cn.ntanjee.meetmeeting.controller.user.UserSignupController;
import com.jfinal.config.*;
import com.jfinal.template.Engine;

public class MeeConfig extends JFinalConfig{

    public void configConstant(Constants constants) {
        constants.setDevMode(true);
        constants.setUrlParaSeparator("&");
    }

    public void configRoute(Routes routes) {
        routes.add("/meeting", MeetingController.class);
        routes.add("/meeting/request", MeetingRequestController.class);
        routes.add("/activity/vote", VoteController.class);
        routes.add("/activity/signin", SinginController.class);
        routes.add("/activity/prize", PrizeController.class);
        routes.add("/user", UserSignupController.class);
        routes.add("/user/signup", UserSignupController.class);
        routes.add("/group", GroupController.class);
        routes.add("/contact", ContactController.class);
        routes.add("message", MessageController.class);
    }

    public void configEngine(Engine engine) {

    }

    public void configPlugin(Plugins plugins) {

    }

    public void configInterceptor(Interceptors interceptors) {

    }

    public void configHandler(Handlers handlers) {

    }

    @Override
    public void afterJFinalStart() {

    }
}
