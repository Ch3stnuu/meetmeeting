package cn.ntanjee.meetmeeting;


import com.github.youyinnn.youwebutils.second.JwtHelper;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;


public class TokenInterceptor implements Interceptor{

    @Override
    public void intercept(Invocation inv) {
        String token = inv.getController().getRequest().getParameter("token");
        if (token != null) {
            JwtHelper.initJWTWithHMAC256("heeeyou", "chestnut");
            Boolean isRight = JwtHelper.verify(token);
            if (isRight) {
                System.out.println("isRight");
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
