package com.ma.ectsmpm.mashable;

public class ParseItem {

    private String imgUrl;
    private String title;
    private String desc;
    private String date;
    private String categories;
    private String postLink;
    private String author;

    public ParseItem(String imgUrl, String title, String desc, String date, String categories, String postLink, String author) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.categories = categories;
        this.postLink = postLink;
        this.author = author;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getPostLink() {
        return postLink;
    }

    public void setPostLink(String postLink) {
        this.postLink = postLink;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
