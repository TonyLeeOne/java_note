package com.tony.note.utils;


import com.tony.note.configuration.GitHubProperties;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author jli2
 * @date 4/10/2019 9:08 PM
 **/
public class HttpUtils {

    public static String sendPost(String url, GitHubProperties properties, String code) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String entityStr = null;
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> list = new LinkedList<>();
            BasicNameValuePair clientId = new BasicNameValuePair("client_id", properties.getId());
            BasicNameValuePair secret = new BasicNameValuePair("client_secret", properties.getSecret());
            BasicNameValuePair codes = new BasicNameValuePair("code", code);
            list.add(clientId);
            list.add(secret);
            list.add(codes);
            UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(list);
            httpPost.setEntity(encodedFormEntity);
            /*
             * 添加请求头信息
             */
            // 浏览器表示
            httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)");
            // 传输的类型
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");

            // 执行请求
            response = httpclient.execute(httpPost);
            // 获得响应的实体对象
            HttpEntity entity = response.getEntity();
            // 使用Apache提供的工具类进行转换成字符串
            entityStr = EntityUtils.toString(entity, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            release(response,httpclient);
        }

        return entityStr;
    }

    public static String sendGet(String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String entityStr = null;
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)");
            // 传输的类型
            httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded");
            // 执行请求
            response = httpclient.execute(httpGet);
            // 获得响应的实体对象
            HttpEntity entity = response.getEntity();
            // 使用Apache提供的工具类进行转换成字符串
            entityStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            release(response,httpclient);

        }

        return entityStr;


    }

    private static void release(CloseableHttpResponse response,CloseableHttpClient httpclient) {
        if (null != response) {
            try {
                response.close();
                httpclient.close();
            } catch (IOException e) {
                System.err.println("释放连接出错");
                e.printStackTrace();
            }
        }
    }

}
