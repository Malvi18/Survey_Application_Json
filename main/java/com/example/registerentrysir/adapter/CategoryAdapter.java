package com.example.registerentrysir.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.registerentrysir.R;
import com.example.registerentrysir.model.Category;

import java.util.ArrayList;

/**
 * Created by Dell on 19-01-2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder>{

    private ArrayList<Category> categoryModels;
    public interface CategoryAdapterCallBack{
        void showOptions(Category category);
    }
    private CategoryAdapterCallBack categoryAdapterCallBack;

    public CategoryAdapter(ArrayList<Category> categoryModels, CategoryAdapterCallBack categoryAdapterCallBack) {
        this.categoryModels = categoryModels;
        this.categoryAdapterCallBack = categoryAdapterCallBack;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_row_layout, parent, false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Category categoryModel=categoryModels.get(position);
        holder.txt.setText(categoryModel.getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //categoryAdapterCallBack.changeFragment(categoryModel);
            }
        });
        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryAdapterCallBack.showOptions(categoryModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt;
        ImageView btnView;
      /*  RadioGroup rg;
        RadioButton rb1,rb2,rb3,rb4;*/
        View view;
        public MyViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            txt=itemView.findViewById(R.id.txt);
            btnView=itemView.findViewById(R.id.btnView);
           /* rg=itemView.findViewById(R.id.radio_grp);
            rb1=itemView.findViewById(R.id.select1);
            rb2=itemView.findViewById(R.id.select2);
            rb3=itemView.findViewById(R.id.select3);
            rb4=itemView.findViewById(R.id.select4);*/
        }
    }
}
