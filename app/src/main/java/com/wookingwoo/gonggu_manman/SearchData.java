package com.wookingwoo.gonggu_manman;

public class SearchData {

    private String imgURL;

    private String name;
    private String name2;
    private String name3;
    private String name4;
    private String name5;
    private String name6;

    private String documentID;


    public SearchData(String imgURL, String name, String name2, String name3, String name4, String name5, String name6, String documentID) {
        this.imgURL = imgURL;
        this.name = name;
        this.name2 = name2;
        this.name3 = name3;
        this.name4 = name4;
        this.name5 = name5;
        this.name6 = name6;
        this.documentID = documentID;

    }

    public String getImgURL() {
        return imgURL;
    }


    public void setImgURL(String iv_image) {
        this.imgURL = imgURL;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    public String getName4() {
        return name4;
    }

    public void setName4(String name4) {
        this.name4 = name4;
    }

    public String getName5() {
        return name5;
    }

    public void setName5(String name5) {
        this.name5 = name5;
    }

    public String getName6() {
        return name6;
    }

    public void setName6(String name6) {
        this.name6 = name6;
    }

    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

}
