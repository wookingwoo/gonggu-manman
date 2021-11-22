package com.wookingwoo.gonggu_manman;

public class Recomendation {
    private String title;
    private String price;
    private int iv_image;
    private String imgURL;
    private String documentID;

    public Recomendation(int iv_image, String title, String price, String documentID) {

        this.iv_image = iv_image;
        this.title = title;
        this.price = price;
        this.documentID = documentID;

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


    public void setDocumentID(String documentID) {
        this.documentID = documentID;
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

    public String getDocumentID() {
        return documentID;
    }
}
