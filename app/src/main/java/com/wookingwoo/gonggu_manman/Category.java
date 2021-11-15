package com.wookingwoo.gonggu_manman;

import android.graphics.drawable.Drawable;

public class Category {
    private String name;
    private int iv_category;

    public Category(int iv_category, String name) {

        this.iv_category = iv_category;
        this.name = name;

    }

    public void setIv_category(int iv_category) {
        this.iv_category = iv_category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIv_category() {
        return iv_category;
    }

    public String getName() {
        return name;
    }
}
