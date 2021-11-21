package com.wookingwoo.gonggu_manman;

public class CreateInfo {
    private String title;
    private String join;
    private String recruit;
    private String detail;
    private String category;
    private String image;
    private String writer;

    public CreateInfo(String title, String category, String join, String recruit, String detail, String image, String writer) {
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
}
