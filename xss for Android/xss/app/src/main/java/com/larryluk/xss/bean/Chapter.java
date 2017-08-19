package com.larryluk.xss.bean;

import com.larryluk.xss.util.StringUtil;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by larryluk on 2017/8/3.
 */
public class Chapter extends DataSupport{
    private int id; //数据库ID
    private String context;
    private String title;
    private int prev;    //指的是数据库中的位置
    private String prevUrl;
    private int next = 0;
    private String nextUrl;
    private String nowUrl;

    @Column(ignore = true)
    private Status status; //不存进数据库

    public Chapter(Status stauts) {
        this.status = stauts;
    }

    public Chapter() {}

    public String getNowUrl() {
        return nowUrl;
    }

    public void setNowUrl(String nowUrl) {
        this.nowUrl = nowUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
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

    public String getIndexUrl() {
        return nowUrl.substring(0, nowUrl.lastIndexOf('/'));
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isEmpty() {
        if(StringUtil.isNullOrEmpty(this.getTitle()) ||
                StringUtil.isNullOrEmpty(this.getContext()) ||
                StringUtil.isNullOrEmpty(this.getNextUrl()) ||
                StringUtil.isNullOrEmpty(this.getPrevUrl())) {
            return true;
        }

        return false;
    }
    @Override
    public String toString() {
        return new StringBuilder(title).append("\n")
                .append(context).append("\n")
                .append(prev).append("    ")
                .append(next).toString();
    }
}
