package com.wookingwoo.gonggu_manman;

public class CreateInfo {
    private String title;
    private String join;
    private String recruit;
    private String detail;

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


    public CreateInfo(String title, String join, String recruit, String detail) {
        this.title = title;
        this.join = join;
        this.recruit = recruit;
        this.detail = detail;
    }
}
