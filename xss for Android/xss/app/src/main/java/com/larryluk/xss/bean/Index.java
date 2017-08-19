package com.larryluk.xss.bean;

/**
 * Created by Larry on 2017/8/19.
 */

public class Index {
    private String title;
    private String url;

    public Index() {
    }

    public Index(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return new StringBuilder(this.url).append("/")
                .append(this.title).toString();
    }
}
