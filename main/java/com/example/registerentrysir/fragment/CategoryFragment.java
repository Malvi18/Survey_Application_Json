package com.example.registerentrysir.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.registerentrysir.R;
import com.example.registerentrysir.adapter.CategoryAdapter;
import com.example.registerentrysir.dao.CategoryDao;
import com.example.registerentrysir.model.Category;
import com.example.registerentrysir.other.MyAsyncCallBack;
import com.example.registerentrysir.other.UtilityHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements MyAsyncCallBack, CategoryAdapter.CategoryAdapterCallBack {

    private static final int GET_CATEGORY = 100;
    Context context;
    RecyclerView recyclerView;
    CategoryDao dao=new CategoryDao();
    private ArrayList<Category> categories;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context=getActivity();
        if(UtilityHelper.isAdmin(context))
            setHasOptionsMenu(true);
        View view= inflater.inflate(R.layout.fragment_category, container, false);

        recyclerView=view.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        prepareData();
/*        CategoryAdapter categoryAdapter=new CategoryAdapter
                (categoryModels,CategoryFragment.this);
        recyclerView.setAdapter(categoryAdapter);*/
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.category_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                Fragment fragment=new NewCategoryFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.Frame_Layout,fragment)
                        .addToBackStack(CategoryFragment.class.getName())
                        .commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void prepareData() {
       dao.getCategories(new CategoryDao.CategoryCallBack() {
           @Override
           public void callBack(String result, int flag) {
               try {
                   categories=new ArrayList<Category>();
                   JSONObject jsonObject=new JSONObject(result);
                   JSONArray jsonArray=jsonObject.getJSONArray("result");
                   for (int i=0;i<jsonArray.length();i++){
                       Category category=new Category();
                       JSONObject categoryJson=jsonArray.getJSONObject(i);
                       category.setId(categoryJson.getInt("id"));
                       category.setName(categoryJson.getString("name"));
                       categories.add(category);
                   }
                   setAdapter(categories);
               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }
       });
    }

    private void setAdapter(ArrayList<Category> categories) {
        CategoryAdapter adapter=new CategoryAdapter(categories,this);
        recyclerView.setAdapter(adapter);
    }

    public void changeFragment(Category model) {

        Fragment fragment3=new SubCategoryQueFragment();
       Bundle bundle=new Bundle();
        bundle.putString("SUBCATEGORY",model.getName());
        bundle.putInt("ID",model.getId());
        fragment3.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.Frame_Layout,fragment3)
                .addToBackStack(CategoryFragment.class.getName())
                .commit();

    }

    @Override
    public void asynCallBack(String result, int flag) {
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showOptions(Category category) {
        new AlertDialog.Builder(context)
                .setTitle("Choose Options")
                .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create().show();
    }
}
