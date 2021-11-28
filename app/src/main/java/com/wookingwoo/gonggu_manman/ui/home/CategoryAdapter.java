package com.wookingwoo.gonggu_manman.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wookingwoo.gonggu_manman.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CustomViewHolder> {

    private ArrayList<Category> arrayList;

    public CategoryAdapter(ArrayList<Category> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CategoryAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_home, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CustomViewHolder holder, int position) {


        String imgURL = arrayList.get(position).getImgURL();
        Glide.with(holder.iv_category.getContext()).load(imgURL).into(holder.iv_category);


        holder.tv_name.setText(arrayList.get(position).getName());

        holder.itemView.setTag(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String curName = holder.tv_name.getText().toString();
                Toast.makeText(view.getContext(), "Search 메뉴에서 선택 가능해요~: " + curName, Toast.LENGTH_SHORT).show();

                Log.i("category", "onClick: " + curName);
            }
        });


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                String curName = holder.tv_name.getText().toString();

                Toast.makeText(view.getContext(), curName, Toast.LENGTH_SHORT).show();
                Log.i("category", "LongClick: " + curName);

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {


        protected ImageView iv_category;
        protected TextView tv_name;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.iv_category = (ImageView) itemView.findViewById(R.id.item_image);
            this.tv_name = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
