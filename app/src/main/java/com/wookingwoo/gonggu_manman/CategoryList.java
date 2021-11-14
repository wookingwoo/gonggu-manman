package com.wookingwoo.gonggu_manman;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryList extends BaseAdapter {
    ArrayList<Category> categories = new ArrayList<>();

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int i) {
        return categories.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Context c = viewGroup.getContext();
        if (view == null) {
            LayoutInflater li = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = li.inflate(R.layout.category_home, viewGroup, false);

        }

        TextView tv = view.findViewById(R.id.category_name);
        ImageView iv = view.findViewById(R.id.category_image);


        Category category = categories.get(i);

        tv.setText(category.getName());
        iv.setImageDrawable(category.getD());


        return view;
    }

    public void addCategory(String name, Drawable d) {
        Category category = new Category();

        category.setName(name);
        category.setD(d);

        categories.add(category);
    }
}
