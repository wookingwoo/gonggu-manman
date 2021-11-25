package com.wookingwoo.gonggu_manman.ui.home;

import android.graphics.drawable.Drawable;

public class Category {
    private String name;
    private String imgURL;


    public Category(String imgURL, String name) {
        this.imgURL = imgURL;
        this.name = name;
    }

    public void setImgURL(String iv_image) {
        this.imgURL = imgURL;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getImgURL() {
        return imgURL;
    }


    public String getName() {
        return name;
    }
}
