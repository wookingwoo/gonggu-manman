package com.wookingwoo.gonggu_manman.ui.create;

import java.util.List;

public class CreateInfo {
    private String title;
    private String city;
    private String sigungu;
    private String join;
    private String recruit;
    private String price;
    private String detail;
    private String category;
    private String image;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSigungu() {
        return sigungu;
    }

    public void setSigungu(String sigungu) {
        this.sigungu = sigungu;
    }

    public List<String> getMethod() {
        return method;
    }

    public void setMethod(List<String> method) {
        this.method = method;
    }

    private List<String> method;
    private String writer;


    public CreateInfo(String title, String category, String city, String sigungu, String join, String recruit, String price, String detail, String image, List<String> method, String writer) {
        this.title = title;
        this.category = category;
        this.join = join;
        this.recruit = recruit;
        this.city = city;
        this.sigungu = sigungu;
        this.price = price;
        this.detail = detail;
        this.image = image;
        this.method = method;
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJoin() {
        return join;
    }

    public void setJoin(String join) {
        this.join = join;
    }

    public String getRecruit() {
        return recruit;
    }

    public void setRecruit(String recruit) {
        this.recruit = recruit;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
