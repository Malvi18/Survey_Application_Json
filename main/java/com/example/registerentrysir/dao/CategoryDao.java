package com.example.registerentrysir.dao;

import com.example.registerentrysir.model.Category;
import com.example.registerentrysir.other.Constants;
import com.example.registerentrysir.other.MyAsyncCallBack;
import com.example.registerentrysir.other.MyAsyncTask;

import java.util.HashMap;

/**
 * Created by Dell on 07-03-2018.
 */

public class CategoryDao implements MyAsyncCallBack {
    private static final int SAVE_CATEGORY = 100;
    private static final int GET_CATEGORY = 200;
    private MyAsyncTask asyncTask;

    public interface CategoryCallBack{
        void callBack(String result,int flag);
    }
    CategoryCallBack categoryCallBack;

    public void saveCategory(Category category,CategoryCallBack categoryCallBack){
        this.categoryCallBack=categoryCallBack;
        asyncTask=new MyAsyncTask(this,SAVE_CATEGORY);
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("category",category.getName());
        asyncTask.setHashMap(hashMap);
        asyncTask.execute(Constants.CATEGORY+Constants.INSERT);
    }

    public void getCategories(CategoryCallBack categoryCallBack){
        this.categoryCallBack=categoryCallBack;
        asyncTask=new MyAsyncTask(this,GET_CATEGORY);
        asyncTask.execute(Constants.CATEGORY+Constants.SELECT);
    }

    @Override
    public void asynCallBack(String result, int flag) {
        categoryCallBack.callBack(result,flag);
    }
}
