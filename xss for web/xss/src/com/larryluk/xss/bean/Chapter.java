package com.larryluk.xss.bean;

/**
 * Created by larryluk on 2017/8/3.
 */
public class Chapter {

    private int id;
    private String content;
    private String title;
    private int prev;    //指的是数据库中的位置
    private String prevUrl;
    private int next;
    private String nextUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrev() {
        return prev;
    }

    public void setPrev(int prev) {
        this.prev = prev;
    }

    public String getPrevUrl() {
        return prevUrl;
    }

    public void setPrevUrl(String prevUrl) {
        this.prevUrl = prevUrl;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }
}
