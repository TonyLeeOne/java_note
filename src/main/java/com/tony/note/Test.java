package com.tony.note;

import org.apache.http.HttpClientConnection;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author jli2
 * @date 6/28/2019 5:24 PM
 **/
public class Test {
    public static void main(String[] args) {
        String path="https://avatars0.githubusercontent.com/u/39149457?v=4";
        try {
            URL url=new URL(path);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestMethod("GET");

            DataInputStream inputStream=new DataInputStream(url.openStream());
            String image="C:\\Users\\jli2\\test\\test.gif";
            FileOutputStream outputStream=new FileOutputStream(new File(image));
            ByteArrayOutputStream outputStream1=new ByteArrayOutputStream();
            byte[] bytes=new byte[1024];
            int len;
            while ((len=inputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
            }
            byte[] context=outputStream1.toByteArray();
            outputStream.write(context);
            outputStream.close();
            inputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
    }
}
