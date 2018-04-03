package com.example.registerentrysir.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.registerentrysir.R;
import com.example.registerentrysir.other.UtilityHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class IdentifiedLoginFragment extends Fragment implements View.OnClickListener {

    Button btnUser,btnAdmin;
    Context context;
    public IdentifiedLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_identified_login, container, false);
        btnAdmin=view.findViewById(R.id.btnAdmin);
        btnUser=view.findViewById(R.id.btnUser);
         btnAdmin.setOnClickListener(this);
         btnUser.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
           case R.id.btnAdmin:
            Fragment fragment = new AdminFragment();
            getFragmentManager().beginTransaction().replace(R.id.Frame_Layout, fragment)
                    .addToBackStack(LoginFragment.class.getName()).commit();
               Toast.makeText(getActivity(), "You are logged as Admin", Toast.LENGTH_SHORT).show();
            break;
           case R.id.btnUser:
            Fragment fragment1 = new LoginFragment();
            getFragmentManager().beginTransaction().replace(R.id.Frame_Layout, fragment1)
                    .addToBackStack(LoginFragment.class.getName()).commit();
               Toast.makeText(getActivity(), "You are logged as User", Toast.LENGTH_SHORT).show();
            break;
        }

}
}
