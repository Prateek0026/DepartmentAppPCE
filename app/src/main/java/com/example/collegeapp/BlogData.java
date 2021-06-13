package com.example.collegeapp;

public class BlogData {

    String blogData, blogPic,currentDate,uniqueKey;

    public BlogData(String blogData, String blogPic, String currentDate, String uniqueKey) {
        this.blogData = blogData;
        this.blogPic = blogPic;
        this.currentDate = currentDate;
        this.uniqueKey = uniqueKey;
    }

    public BlogData() {
    }

    public String getBlogData() {
        return blogData;
    }

    public void setBlogData(String blogData) {
        this.blogData = blogData;
    }

    public String getBlogPic() {
        return blogPic;
    }

    public void setBlogPic(String blogPic) {
        this.blogPic = blogPic;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }
}
