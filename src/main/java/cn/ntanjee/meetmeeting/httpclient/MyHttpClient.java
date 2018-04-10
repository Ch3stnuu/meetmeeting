package cn.ntanjee.meetmeeting.httpclient;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author 74123
 */
public class MyHttpClient {
    public static String goPost(String url, Map<String, Object> paraMap) {
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        String result = null;

        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

        PostMethod postMethod = new PostMethod(url);
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);

        NameValuePair[] nameValuePairs = null;
        if (paraMap != null && paraMap.size() > 0) {
            nameValuePairs = new NameValuePair[paraMap.size()];

            Set<Map.Entry<String, Object>> entrySet = paraMap.entrySet();
            Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();

            int index = 0;
            while (iterator.hasNext()) {
                Map.Entry<String, Object> mapEntry = iterator.next();
                nameValuePairs[index] = new NameValuePair(mapEntry.getKey(), (String) mapEntry.getValue());
                index++;
            }
        }

        if (nameValuePairs != null && nameValuePairs.length > 0) {
            postMethod.setRequestBody(nameValuePairs);
        }

        try {
            int statusCode = httpClient.executeMethod(postMethod);

            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method fault: " + postMethod.getStatusLine());
            }

            inputStream = postMethod.getResponseBodyAsStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            StringBuilder builder = new StringBuilder();

            String temp = null;
            while ((temp = bufferedReader.readLine()) != null) {
                builder.append(temp).append("\r\n");
            }
            result = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            postMethod.releaseConnection();
        }

        return result;
    }

    public static String doGet(String url) {
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        String result = null;

        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);

        GetMethod getMethod = new GetMethod(url);
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, true));

        try {
            int statusCode = httpClient.executeMethod(getMethod);

            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method fault: " + getMethod.getStatusLine());
            } else {
                inputStream = getMethod.getResponseBodyAsStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                StringBuilder sbf = new StringBuilder();

                String temp = null;
                while ((temp = bufferedReader.readLine()) != null) {
                    sbf.append(temp).append("\r\n");
                }

                result = sbf.toString();
            }
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

            getMethod.releaseConnection();
        }

        return result;
    }

}
