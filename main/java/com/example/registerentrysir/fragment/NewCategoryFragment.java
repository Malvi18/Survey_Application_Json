package com.example.registerentrysir.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.registerentrysir.R;
import com.example.registerentrysir.dao.CategoryDao;
import com.example.registerentrysir.model.Category;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewCategoryFragment extends Fragment {


    public NewCategoryFragment() {
        // Required empty public constructor
    }

    EditText etName;
    Button btnSave;
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_new_category, container, false);
        context=getActivity();
        etName=view.findViewById(R.id.etName);
        btnSave=view.findViewById(R.id.btnSave);
        // Inflate the layout for this fragment
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCategory();
            }
        });
        return view;
    }

    private void saveCategory() {
        Category category=new Category();
        category.setName(etName.getText().toString());

        CategoryDao dao=new CategoryDao();
        dao.saveCategory(category, new CategoryDao.CategoryCallBack() {
            @Override
            public void callBack(String result,int flag) {
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                etName.setText("");
            }
        });
    }

}
