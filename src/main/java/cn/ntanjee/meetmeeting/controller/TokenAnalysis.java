package cn.ntanjee.meetmeeting.controller;

import com.github.youyinnn.youwebutils.second.JwtHelper;


public class TokenAnalysis {
    public static int analysis(String token) {
        System.out.println(token);
        JwtHelper.initJWTWithHMAC256("heeeyou", "chestnut");
        return JwtHelper.getClaimAsInteger(token, "uid");
    }
}
