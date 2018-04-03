package com.example.registerentrysir.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.ConditionVariable;
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

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener, MyAsyncCallBack {
     EditText reg_username,reg_password,reg_email,reg_phone,reg_address;
     Button reg_btn;
     Context context;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_register, container, false);
        context=getActivity();
        reg_username=view.findViewById(R.id.reg_username);
        reg_password=view.findViewById(R.id.reg_password);
        reg_email=view.findViewById(R.id.reg_email);
        reg_phone=view.findViewById(R.id.reg_phone);
        reg_address=view.findViewById(R.id.reg_address);
        reg_btn=view.findViewById(R.id.reg_btn);

        reg_btn.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        String reg_username1=reg_username.getText().toString();
        String reg_password1=reg_password.getText().toString();
        String reg_email1=reg_email.getText().toString();
        String reg_phone1=reg_phone.getText().toString();
        String reg_address1=reg_address.getText().toString();

        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("username",reg_username1);
        hashMap.put("password",reg_password1);
        hashMap.put("email",reg_email1);
        hashMap.put("phone",reg_phone1);
        hashMap.put("address",reg_address1);

       // Toast.makeText(getActivity(), ""+reg_password1+""+reg_username1+""+reg_address1+""+reg_phone1+""+reg_email1+"", Toast.LENGTH_LONG).show();

        String url="webser.php";
        /*username=" + reg_username1 + "&password="
                + reg_password1+ "&email=" + reg_email1+ "&phone=" + reg_phone1+ "&address=" + reg_address1;
*/
        MyAsyncTask asyncTask = new MyAsyncTask(this,100);
        asyncTask.setHashMap(hashMap);
        asyncTask.execute(url);

    }

    @Override
    public void asynCallBack(String result,int flag) {
        Toast.makeText(context,result, Toast.LENGTH_SHORT).show();
    }
}
