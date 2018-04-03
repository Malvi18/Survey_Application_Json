package com.example.registerentrysir.other;

import android.os.AsyncTask;
import android.view.View;


import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Dell on 12-01-2018.
 */

public class MyAsyncTask extends AsyncTask<String, Void, String> {

    private MyAsyncCallBack myAsyncCallBack;
    private HashMap<String, String> hashMap;
    private int flag;

    public MyAsyncTask(MyAsyncCallBack myAsyncCallBack, HashMap<String, String> hashMap, int flag) {
        this.myAsyncCallBack = myAsyncCallBack;
        this.hashMap = hashMap;
        this.flag = flag;
    }



    public HashMap<String, String> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<String, String> hashMap) {
        this.hashMap = hashMap;
    }

    public MyAsyncTask(MyAsyncCallBack myAsyncCallBack, int flag) {
        this.myAsyncCallBack = myAsyncCallBack;
        this.flag=flag;
    }

    @Override
    protected String doInBackground(String... strings) {

        OkHttpClient client = new OkHttpClient();
        Request.Builder request = new Request.Builder().url(strings[0]);
        if (hashMap != null) {
            FormBody.Builder bodyBuilder = new FormBody.Builder();
            Set<String> keySet = hashMap.keySet();
            for (String key : keySet) {
                bodyBuilder.add(key, hashMap.get(key));
            }

            FormBody formBody = bodyBuilder.build();
            request.post(formBody);
        }
        Request request1 = request.build();
        try {
            Response response = client.newCall(request1).execute();
            String result = response.body().string();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
           myAsyncCallBack.asynCallBack(s,flag);
        // Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}


