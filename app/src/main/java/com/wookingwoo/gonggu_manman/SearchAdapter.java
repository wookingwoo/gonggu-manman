package com.wookingwoo.gonggu_manman;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.CustomViewHolder> {

    private ArrayList<SearchData> arrayList;

    public SearchAdapter(ArrayList<SearchData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public SearchAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.CustomViewHolder holder, int position) {

        String imgURL = arrayList.get(position).getImgURL();
        Glide.with(holder.iv_image.getContext()).load(imgURL).into(holder.iv_image);

        holder.name.setText(arrayList.get(position).getName());
        holder.name2.setText(arrayList.get(position).getName2());
        holder.name3.setText(arrayList.get(position).getName3());
        holder.name4.setText(arrayList.get(position).getName4());
        holder.name5.setText(arrayList.get(position).getName5());
        holder.name6.setText(arrayList.get(position).getName6());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String curName = holder.name.getText().toString();
                Toast.makeText(v.getContext(), curName, Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                remove(holder.getAdapterPosition());
                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public void remove(int position) {
        try {
            arrayList.remove(position);
            notifyItemRemoved(position);
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView iv_image;
        protected TextView name;
        protected TextView name2;
        protected TextView name3;
        protected TextView name4;
        protected TextView name5;
        protected TextView name6;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_image = (ImageView) itemView.findViewById(R.id.iv_image);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.name2 = (TextView) itemView.findViewById(R.id.name2);
            this.name3 = (TextView) itemView.findViewById(R.id.name3);
            this.name4 = (TextView) itemView.findViewById(R.id.name4);
            this.name5 = (TextView) itemView.findViewById(R.id.name5);
            this.name6 = (TextView) itemView.findViewById(R.id.name6);
        }
    }
}
