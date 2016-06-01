package com.guo.zhihudemo.http;

import com.google.gson.Gson;
import com.guo.zhihudemo.entity.News;
import com.guo.zhihudemo.entity.NewsDetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guo on 2016/6/1.
 */
public class JsonHelper {
    public static List<News> parseJsonToList(String json) throws JSONException{
        JSONObject newsContent=new JSONObject(json);
        JSONArray newsArray=newsContent.getJSONArray("stories");
        List<News> newsList=new ArrayList<News>();
        for(int i=0;i<newsArray.length();i++){
            JSONObject newsInJson=newsArray.getJSONObject(i);
            int id=newsInJson.optInt("id");
            String title=newsInJson.optString("title");
            String image="";
            if(newsInJson.has("images")){
                image=(String)newsInJson.getJSONArray("images").get(0);
            }
            News news=new News(id,title,image);
            newsList.add(news);
        }
        return newsList;
    }

    public static NewsDetail parseJsonToDetail(String json) throws JSONException{
        Gson gson=new Gson();
        return gson.fromJson(json,NewsDetail.class);
    }

}
