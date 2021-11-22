package com.wookingwoo.gonggu_manman;

public class Recomendation {
    private String title;
    private String price;
    private String imgURL;
    private String documentID;

    public Recomendation(String imgURL, String title, String price, String documentID) {

        this.imgURL = imgURL;
        this.title = title;
        this.price = price;
        this.documentID = documentID;

    }

    public void setImgURL(String iv_image) {
        this.imgURL = imgURL;
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


    public String getImgURL() {
        return imgURL;
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
