package com.guo.zhihudemo.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by guo on 2016/6/1.
 */
public class Http {
    public static String NEWSLIST_LATEST="http://news-at.zhihu.com/api/4/news/latest";
    public static String STORY_VIEW="http://daily.zhihu.com/story/";
    public static String NEWSDETAIL="http://news-at.zhihu.com/api/4/news/";

    public static String get(String urlAddr) throws IOException{
        HttpURLConnection con=null;
        try {
            URL url=new URL(urlAddr);
            con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent","Mozilla/5.0");
            if(con.getResponseCode()==HttpURLConnection.HTTP_OK){
                BufferedReader in=new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line;
                StringBuilder response=new StringBuilder();
                while((line=in.readLine())!=null){
                    response.append(line);
                }
                in.close();
                return response.toString();
            }else {
                throw new IOException("Network Error - response code: " + con.getResponseCode());
            }
        }finally {
            if(con!=null){
                con.disconnect();
            }
        }
    }

    public static String getLastNewsList() throws IOException{
        return get(NEWSLIST_LATEST);
    }
    public static String getNewsDetail(int id) throws IOException{
        return get(NEWSDETAIL+id);
    }


}
