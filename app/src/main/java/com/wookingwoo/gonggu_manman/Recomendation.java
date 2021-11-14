package com.wookingwoo.gonggu_manman;

public class Recomendation {
    private String title;
    private String price;
    private int iv_image;

    public Recomendation(int iv_image, String title, String price) {

        this.iv_image = iv_image;
        this.title = title;
        this.price = price;

    }

    public void setIv_image(int iv_image) {
        this.iv_image = iv_image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getIv_image() {
        return iv_image;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }
}
