package com.example.registerentrysir.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.registerentrysir.R;
import com.example.registerentrysir.db.DBHelper;
import com.example.registerentrysir.model.User;
import com.example.registerentrysir.other.Constants;
import com.example.registerentrysir.other.MyAsyncCallBack;
import com.example.registerentrysir.other.MyAsyncTask;
import com.example.registerentrysir.other.UtilityHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener, MyAsyncCallBack {
    DBHelper dbHelper;
    //TextView txtUsername,txtPassword;
    Context context;
    EditText username, password;
    Button btnLogin, btnRegister;
    TextView tvResult;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        context = getActivity();
        dbHelper = new DBHelper(getContext());
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        tvResult = view.findViewById(R.id.tvResult);
        btnLogin = view.findViewById(R.id.btnLogin);
        btnRegister = view.findViewById(R.id.btnRegister);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
             /* Cursor cursor = dbHelper.getAllData();
    }
        StringBuffer stringBuffer = new StringBuffer();
        String username1 = username.getText().toString();
        String password1 = password.getText().toString();
       Boolean result1 = dbHelper.insertData(username1, password1);
        if (result1 == true) {
            Toast.makeText(getActivity(), "Inserted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Not Inserted", Toast.LENGTH_SHORT).show();


       if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                stringBuffer.append("id" + cursor.getString(0) + "\n");
                stringBuffer.append("username" + cursor.getString(1) + "\n");
                stringBuffer.append("password" + cursor.getString(2) + "\n");
            }
            tvResult.setText(stringBuffer.toString());
            Toast.makeText(getActivity(), "Data Successs", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Not Successs", Toast.LENGTH_SHORT).show();
     }
*/
        switch (v.getId()) {
            case R.id.btnLogin:
                String username1 = username.getText().toString();
                String password1 = password.getText().toString();
                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("username",username1);
                hashMap.put("password",password1);

                MyAsyncTask asyncTask = new MyAsyncTask(this, 100);
                asyncTask.setHashMap(hashMap);
                asyncTask.execute(Constants.LOGIN_VERIFICATION);

            break;

            case R.id.btnRegister:
                Fragment fragment2 = new RegisterFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.Frame_Layout, fragment2)
                        .addToBackStack(String.valueOf(LoginFragment.class)).commit();
                break;
        }
    }


    @Override
    public void asynCallBack(String result, int flag) {
       // Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();

        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            JSONObject jsonObject1 = jsonArray.getJSONObject(0);
           /* User user = new User();
            user.setId(Integer.parseInt(jsonObject1.getString("id")));
            user.setUsername(jsonObject1.getString("username"));
*/
            UtilityHelper utilityHelper=new UtilityHelper();
            utilityHelper.writeUser(context,jsonObject1.toString());
            Fragment fragment = new CategoryFragment();
            getFragmentManager().beginTransaction().replace(R.id.Frame_Layout, fragment).commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }



