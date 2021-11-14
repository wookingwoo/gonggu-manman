package com.wookingwoo.gonggu_manman;

import android.graphics.drawable.Drawable;

public class Category {
    private String name;
    private Drawable d;

    public void setD(Drawable d) {
        this.d = d;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getD() {
        return d;
    }

    public String getName() {
        return name;
    }
}
