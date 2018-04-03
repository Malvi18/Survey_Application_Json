package com.example.registerentrysir.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.registerentrysir.R;
import com.example.registerentrysir.other.MyAsyncCallBack;
import com.example.registerentrysir.other.MyAsyncTask;
import com.example.registerentrysir.other.UtilityHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminFragment extends Fragment implements View.OnClickListener,MyAsyncCallBack {
     Context context;
    EditText etUsername,etPassword;
    Button btnSubmit;
    public AdminFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_admin, container, false);
        context=getActivity();
        etUsername=view.findViewById(R.id.etUsername);
        etPassword=view.findViewById(R.id.etPassword);
        btnSubmit=view.findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
       /* boolean ValidUser=etUsername.getText().toString().equals("test") && etPassword.getText().toString().equals("test");
        if(ValidUser==true)
        {
            SharedPreferences sharedPreferences=getActivity().getSharedPreferences("user_info",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("username",etUsername.getText().toString());
            editor.putString("password",etPassword.getText().toString());*/

        String username1=etUsername.getText().toString();
        String password1=etPassword.getText().toString();

        String url = "adminlogin.php?" +
                "username=" + username1 + "&password=" + password1;
        MyAsyncTask asyncTask = new MyAsyncTask(this,100);
        asyncTask.execute(url);

           // editor.commit();
            Toast.makeText(context, "Admin Login", Toast.LENGTH_SHORT).show();
        }


    @Override
    public void asynCallBack(String result, int flag) {
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            JSONObject userJsonObject = jsonArray.getJSONObject(0);
            /*User user=new User();
            user.setId(Integer.parseInt(userJsonObject.getString("uid")));
            user.setEmail(userJsonObject.getString("email"));
            user.setPassword(userJsonObject.getString("pass"));
            user.setStatus(userJsonObject.getString("status"));*/
            UtilityHelper helper = new UtilityHelper();
            helper.writeUser(context, userJsonObject.toString());
            Fragment fragment = new CategoryFragment();
            getFragmentManager().beginTransaction()
                    .replace(R.id.Frame_Layout, fragment)
                    .commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    }

