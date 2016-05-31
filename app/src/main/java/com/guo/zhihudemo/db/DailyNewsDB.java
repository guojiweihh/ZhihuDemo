package com.guo.zhihudemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.guo.zhihudemo.entity.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guo on 2016/5/31.
 */
public class DailyNewsDB {
    private DBHelper dbHelper;
    private static DailyNewsDB dailyNewsDB;
    private SQLiteDatabase db;
    private String[] allColumns={DBHelper.COLUMN_ID,DBHelper.COLUMN_NEWS_ID,DBHelper.COLUMN_NEWS_TITLE,DBHelper.COLUMN_NEWS_IMAGE};

    private DailyNewsDB(Context context){
        dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();
    }

    public synchronized static DailyNewsDB getInstance(Context context){
        if(dailyNewsDB==null){
            dailyNewsDB=new DailyNewsDB(context);
        }
        return dailyNewsDB;
    }

    public void saveFavourite(News news){
        if(news!=null){
            ContentValues values=new ContentValues();
            values.put(DBHelper.COLUMN_NEWS_ID,news.getId());
            values.put(DBHelper.COLUMN_NEWS_TITLE,news.getTitle());
            values.put(DBHelper.COLUMN_NEWS_IMAGE,news.getImage());
            db.insert(DBHelper.TABLE_NAME,null,values);

        }
    }

    public List<News> loadFavourite(){
        List<News> favouriteList=new ArrayList<News>();
        Cursor cursor=db.query(DBHelper.TABLE_NAME,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                News news=new News();
                news.setId(cursor.getInt(cursor.getColumnIndex("news_id")));
                news.setTitle(cursor.getString(cursor.getColumnIndex("news_title")));
                news.setImage(cursor.getString(cursor.getColumnIndex("news_image")));
                favouriteList.add(news);

            }while (cursor.moveToNext());
        }
        if(cursor!=null){
            cursor.close();
        }
        return favouriteList;
    }
    public boolean isFavourite(News news){
        Cursor cursor=db.query(DBHelper.TABLE_NAME,null,DBHelper.COLUMN_NEWS_ID+"=?",new String[]{news.getId()+""},null,null,null);
        if(cursor.moveToNext()){
            return true;
        }else {
            return false;
        }
    }

    public void deleteFavourite(News news){
        if(news!=null){
            db.delete(DBHelper.TABLE_NAME,DBHelper.COLUMN_NEWS_ID+"=?",new String[]{news.getId()+""});
        }
    }

    public synchronized void closeDB(){
        if(dailyNewsDB!=null){
            db.close();
        }
    }



}
