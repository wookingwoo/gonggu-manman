package com.wookingwoo.gonggu_manman;

public class CreateInfo {
    private String title;
    private int join;
    private int recruit;
    private String detail;
    private String category;
    private String image;
    private String writer;

    public CreateInfo(String title, String category, int join, int recruit, String detail, String image, String writer) {
        this.title = title;
        this.category = category;
        this.join = join;
        this.recruit = recruit;
        this.detail = detail;
        this.image = image;
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getJoin() {
        return join;
    }

    public void setJoin(int join) {
        this.join = join;
    }

    public int getRecruit() {
        return recruit;
    }

    public void setRecruit(int recruit) {
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
}
