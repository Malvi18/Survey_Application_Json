package com.example.registerentrysir.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.registerentrysir.R;
import com.example.registerentrysir.db.DBHelper;
import com.example.registerentrysir.fragment.SubCategoryQueFragment;

import com.example.registerentrysir.model.SubCategoryModel;
import com.example.registerentrysir.other.SubCategoryCallback;

import java.util.ArrayList;

import static com.example.registerentrysir.other.UtilityHelper.USER_NAME;

/**
 * Created by Dell on 22-01-2018.
 */

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder> {
    private ArrayList<SubCategoryModel> subCategoryModels;
    private SubCategoryCallback subCategoryCallback;

    public SubCategoryAdapter(ArrayList<SubCategoryModel> subCategoryModels, SubCategoryCallback subCategoryCallback) {
        this.subCategoryModels = subCategoryModels;
        this.subCategoryCallback = subCategoryCallback;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sub_category_que_layout, parent, false);

        SubCategoryAdapter.MyViewHolder viewHolder=new SubCategoryAdapter.MyViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final SubCategoryModel subCategoryModel=subCategoryModels.get(position);
        holder.txtQue.setText(subCategoryModel.getName());


        /* holder.rb1.setText((CharSequence) subCategoryModel.getOptionA());
        holder.rb2.setText((CharSequence) subCategoryModel.getOptionB());
        holder.rb3.setText((CharSequence) subCategoryModel.getOptionC());
        holder.rb4.setText((CharSequence) subCategoryModel.getOptionD());*/
        /*holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subCategoryCallback.fragChange(subCategoryModel);

            }
        });*/
        if(USER_NAME=="user") {
            holder.btnDelete.setVisibility(View.GONE);
            holder.btnEdit.setVisibility(View.GONE);
        }
            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    subCategoryCallback.fragChange(subCategoryModel, 1);

                }
            });
            holder.btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    subCategoryCallback.fragChange(subCategoryModel, 2);

                }
            });
        }



    @Override
    public int getItemCount() {
        return subCategoryModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
          TextView txtQue;
          ImageButton btnDelete, btnEdit;
          View view;
//          RadioGroup rg;
//          RadioButton rb1,rb2,rb3,rb4;
        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            btnDelete=itemView.findViewById(R.id.btnDelete);
            btnEdit=itemView.findViewById(R.id.btnUpdate);
            txtQue=itemView.findViewById(R.id.txtQue);
           /* rg=itemView.findViewById(R.id.radio_grp);*/
           /* rb1=itemView.findViewById(R.id.select1);
            rb2=itemView.findViewById(R.id.select2);
            rb3=itemView.findViewById(R.id.select3);
            rb4=itemView.findViewById(R.id.select4);*/

        }
    }
}
