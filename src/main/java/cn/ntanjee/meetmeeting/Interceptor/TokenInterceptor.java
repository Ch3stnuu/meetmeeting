package cn.ntanjee.meetmeeting.Interceptor;


import com.github.youyinnn.youwebutils.second.JwtHelper;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

import javax.servlet.http.HttpServletResponse;


public class TokenInterceptor implements Interceptor{

    @Override
    public void intercept(Invocation inv) {
        HttpServletResponse response = inv.getController().getResponse();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        String token = inv.getController().getRequest().getParameter("token");
        if (token != null) {
            JwtHelper.initJWTWithHMAC256("heeeyou", "chestnut");
            Boolean isRight = JwtHelper.verify(token);
            if (isRight) {
                inv.invoke();
            } else {
                inv.getController().renderJson("authorization", "T001");
            }
        } else {
            inv.invoke();
        }
        System.out.println("finish");
    }
}
