package cn.ntanjee.meetmeeting.httpclient;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.*;
import java.sql.Timestamp;
import java.util.Random;

/**
 * @author 74123
 */
public class MyHttpClient {
    public static String doPost(String url, JSONObject params) {
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        String result = null;

        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

        PostMethod postMethod = new PostMethod(url);
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);

        String appKey = "p5tvi9dspn4y4";
        String appSecret = "nttVJ7lzlSZRh5";

        Long nonce = new Random().nextLong();
        if (nonce < 0) {
            nonce = -nonce;
        }
        System.out.println(nonce);
        postMethod.setRequestHeader("App-Key", appKey);
        postMethod.setRequestHeader("Nonce", nonce.toString());

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        postMethod.setRequestHeader("Timestamp", timestamp.toString());

        String signature = appSecret + nonce + timestamp;
        String shaSignature = DigestUtils.shaHex(signature);
        postMethod.setRequestHeader("signature", shaSignature);

        postMethod.setRequestHeader("Content-Type", "application/json");

        try {
            StringRequestEntity entity = new StringRequestEntity(params.toJSONString(),
                    "application/json", "UTF-8");
            postMethod.setRequestEntity(entity);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            int statusCode = httpClient.executeMethod(postMethod);

            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method fault: " + postMethod.getStatusLine());
            }

            inputStream = postMethod.getResponseBodyAsStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            StringBuilder sbf = new StringBuilder();

            String temp = null;
            while ((temp = bufferedReader.readLine()) != null) {
                sbf.append(temp).append("\r\n");
            }
            result = sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            postMethod.releaseConnection();
        }

        return result;
    }
}
