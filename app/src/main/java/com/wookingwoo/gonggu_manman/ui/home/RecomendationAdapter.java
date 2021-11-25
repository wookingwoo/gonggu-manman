package com.wookingwoo.gonggu_manman.ui.home;

import android.content.Context;
import android.content.Intent;
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
import com.wookingwoo.gonggu_manman.FeatureAttend;
import com.wookingwoo.gonggu_manman.R;
import com.wookingwoo.gonggu_manman.searchTitle.SearchActivity;

import java.util.ArrayList;

public class RecomendationAdapter extends RecyclerView.Adapter<RecomendationAdapter.CustomViewHolder> {


    private ArrayList<Recomendation> arrayList;

    public RecomendationAdapter(ArrayList<Recomendation> arrayList) {
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public RecomendationAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recomendation_home, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecomendationAdapter.CustomViewHolder holder, int position) {

        String title = arrayList.get(position).getTitle();
        String documentID = arrayList.get(position).getDocumentID();
        String imgURL = arrayList.get(position).getImgURL();


        Glide.with(holder.iv_image.getContext()).load(imgURL).into(holder.iv_image);


        holder.tv_title.setText(arrayList.get(position).getTitle());
        holder.tv_price.setText(arrayList.get(position).getPrice());

        holder.itemView.setTag(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(view.getContext(), documentID, Toast.LENGTH_SHORT).show();
                Toast.makeText(view.getContext(), "공구장터로 이동합니다: " + title, Toast.LENGTH_SHORT).show();
                Log.i("Recomendation", "onClick: " + documentID);

                // FeatureAttend로 인텐트 전환
                Intent detailIntent = new Intent(view.getContext(), FeatureAttend.class);
                detailIntent.putExtra("documentID", documentID);
                view.getContext().startActivity(detailIntent);
            }
        });


//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//
//            @Override
//            public boolean onLongClick(View view) {
//                String curName = holder.tv_title.getText().toString();
//
//                Toast.makeText(view.getContext(), "롱클릭", Toast.LENGTH_SHORT).show();
//                Log.i("Recomendation", "LongClick: " + curName);
//
//                return true;
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {


        protected ImageView iv_image;
        protected TextView tv_title;
        protected TextView tv_price;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.iv_image = (ImageView) itemView.findViewById(R.id.item_image);
            this.tv_title = (TextView) itemView.findViewById(R.id.title);
            this.tv_price = (TextView) itemView.findViewById(R.id.price);
        }
    }
}
