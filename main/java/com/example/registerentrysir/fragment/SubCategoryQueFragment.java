package com.example.registerentrysir.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.registerentrysir.R;
import com.example.registerentrysir.adapter.SubCategoryAdapter;
import com.example.registerentrysir.model.SubCategoryModel;
import com.example.registerentrysir.other.MyAsyncCallBack;
import com.example.registerentrysir.other.MyAsyncTask;
import com.example.registerentrysir.other.SubCategoryCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

public class SubCategoryQueFragment extends Fragment implements SubCategoryCallback,
        MyAsyncCallBack{


    private static final int SELECT_CATEGORIES = 1;
    private static final int INSERT_CATEGORY = 2;
    private static final int DELETE_CATEGORIES = 3;
    // private static final int UPDATE_CATEGORY = 4;
    RecyclerView recyclerView;
    Context context;
    Spinner subspinner1, subspinner2;
    private ArrayList<SubCategoryModel> subCategoryModels;
    private SubCategoryModel subCategory = null;

    public SubCategoryQueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sub_category_que, container, false);
        context = getActivity();
       /* subspinner1 = view.findViewById(R.id.subspinner1);
        subspinner2 = view.findViewById(R.id.subspinner2);



        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(getActivity(),
                R.array.array1, android.R.layout.simple_spinner_item);
        subspinner1.setAdapter(adapter1);
        subspinner2.setOnItemSelectedListener(this);*/
        ImageButton btnUpdate = view.findViewById(R.id.btnUpdate);
        recyclerView = view.findViewById(R.id.recycleView1);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
       /* Bundle bundle=getArguments();
        int id=bundle.getInt("ID",-1);*/

        prepareQue();
        //SubCategoryAdapter subCategoryAdapter=new SubCategoryAdapter(subCategoryModels,SubCategoryQueFragment.this);
        //recyclerView.setAdapter(subCategoryAdapter);
     /*  btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Update Button Click", Toast.LENGTH_SHORT).show();
                updateData();
            }
        });*/
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.category_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add:
                showInputAlertDialog();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    private void showInputAlertDialog() {
        View customView = LayoutInflater.from(context).inflate(R.layout.fragment_new_que_add, null);
        final EditText etCategory = customView.findViewById(R.id.etCategory);
        if (subCategory != null)
            etCategory.setText(subCategory.getName());
        new AlertDialog.Builder(context).setTitle("Enter Question and select option for that..")
                .setView(customView)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        if (subCategory == null) {
                            String cate = etCategory.getText().toString();
                            saveCategoryOnServer(cate);
                            Toast.makeText(context, "save data", Toast.LENGTH_SHORT).show();
                        } else {
                            String cate1 = subCategory.setName(etCategory.getText().toString());
                            updateDataOnServer(subCategory, cate1);
                            Toast.makeText(context, "edit data", Toast.LENGTH_SHORT).show();

                        }
                    }


                }).show();
    }

    private void updateDataOnServer(SubCategoryModel subCategoryModel, String cate1) {
        String url = "category.php";

        HashMap<String, String> hm = new HashMap<>();

        hm.put("flag", "2");
        hm.put("c_id", subCategoryModel.getId() + "");
        hm.put("cate", cate1);

        MyAsyncTask myAsyncTask = new MyAsyncTask(this, INSERT_CATEGORY);
        myAsyncTask.setHashMap(hm);
        myAsyncTask.execute(url);
    }

    private void saveCategoryOnServer(String cate) {

        String url = "category.php";
        HashMap<String, String> hm = new HashMap<>();
        hm.put("flag", "1");

        hm.put("cate", cate);

        MyAsyncTask myAsyncTask = new MyAsyncTask(this, INSERT_CATEGORY);
        myAsyncTask.setHashMap(hm);
        myAsyncTask.execute(url);

    }

    private void prepareQue() {
        String url = "category.php";
        HashMap<String, String> hm = new HashMap<>();
        hm.put("flag", "4");
        MyAsyncTask asyncTask = new MyAsyncTask(this, SELECT_CATEGORIES);
        asyncTask.setHashMap(hm);
        asyncTask.execute(url);
    }


   /* private void updateData() {
        String url = "category.php";
        subCategoryModels = new ArrayList<>();
        {
            subCategoryModels.add(new SubCategoryModel(1, "c_id"));
            subCategoryModels.add(new SubCategoryModel(1, "c_name"));
        }
        HashMap<String, String> hm = new HashMap<>();
        hm.put("flag", "2");
        MyAsyncTask asyncTask = new MyAsyncTask(this, UPDATE_CATEGORY);
        asyncTask.setHashMap(hm);
        asyncTask.execute(url);
    }
*/

        /* subCategoryModels=new ArrayList<>();
        {

            switch (id) {
                case 1:
                {
                subCategoryModels.add(new SubCategoryModel(1,"which drink you use?",
                        "coco","pspsi","pin","dd"));
                subCategoryModels.add(new SubCategoryModel
                        (2,"which drink you use?","coco","pspsi",
                                "pin","dd"));
                subCategoryModels.add(new SubCategoryModel
                        (3,"which drink you use?","coco","pspsi",
                                "pin","dd"));
                break;
            }
                case 2:
                {
                    subCategoryModels.add(new SubCategoryModel
                            (1,"which cloth?","coco","pspsi",
                                    "pin","dd"));
                    subCategoryModels.add(new SubCategoryModel
                            (2,"which drink you use?","coco","pspsi",
                                    "pin","dd"));
                    subCategoryModels.add(new SubCategoryModel
                            (3,"which drink you use?","coco","pspsi",
                                    "pin","dd"));
                    break;
                }
                case 3:
                {
                    subCategoryModels.add(new SubCategoryModel
                            (1,"which file you use?","coco","pspsi",
                                    "pin","dd"));
                    subCategoryModels.add(new SubCategoryModel
                            (2,"which drink you use?","coco","pspsi",
                                    "pin","dd"));
                    subCategoryModels.add(new SubCategoryModel
                            (3,"which drink you use?","coco","pspsi",
                                    "pin","dd"));
                    break;
                }
                case 4:
                {
                    subCategoryModels.add(new SubCategoryModel
                            (1,"which milk you use?","coco","pspsi",
                                    "pin","dd"));
                    subCategoryModels.add(new SubCategoryModel
                            (2,"which drink you use?","coco","pspsi",
                                    "pin","dd"));
                    subCategoryModels.add(new SubCategoryModel
                            (3,"which drink you use?","coco","pspsi",
                                    "pin","dd"));
                    break;
                }
                }
            }*/


    @Override
    public void fragChange(SubCategoryModel subCategoryModel, int flag) {
        if (flag == 1) {
            String url = "category.php";
            HashMap<String, String> hm = new HashMap<>();
            hm.put("flag", "3");
            hm.put("c_id", subCategoryModel.getId() + "");
            MyAsyncTask asyncTask = new MyAsyncTask(this, DELETE_CATEGORIES);
            asyncTask.setHashMap(hm);
            asyncTask.execute(url);
        } else if (flag == 2) {
            this.subCategory = subCategoryModel;
            showInputAlertDialog();
        }
    }


    @Override
    public void asynCallBack(String result, int flag) {
        switch (flag) {
            case SELECT_CATEGORIES:
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    subCategoryModels = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject categoryObject = jsonArray.getJSONObject(i);
                        int id = Integer.parseInt(categoryObject.getString("c_id"));
                        String name = categoryObject.getString("c_name");
                        subCategoryModels.add(new SubCategoryModel(id, name));
                    }
                    SubCategoryAdapter adapter = new SubCategoryAdapter(subCategoryModels, SubCategoryQueFragment.this);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case INSERT_CATEGORY:
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                break;
            case DELETE_CATEGORIES:
                prepareQue();
                break;
          /*  case UPDATE_CATEGORY:
                prepareQue();
                break;*/

        }
    }


  /*  @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (subspinner1.getSelectedItem().equals("option menu")) {
            Toast.makeText(getActivity(), "option selected",
                    Toast.LENGTH_SHORT).show();

            ArrayAdapter adapter2 = ArrayAdapter.createFromResource(getActivity(),
                    R.array.array2, android.R.layout.simple_spinner_item);
            subspinner2.setAdapter(adapter2);
        } else {
            ArrayAdapter adapter2 = ArrayAdapter.createFromResource(getActivity(),
                    R.array.array2, android.R.layout.simple_spinner_item);
            subspinner2.setAdapter(adapter2);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }
*/
    }

