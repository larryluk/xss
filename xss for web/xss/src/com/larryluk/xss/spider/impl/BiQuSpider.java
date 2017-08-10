package com.larryluk.xss.spider.impl;

import com.larryluk.xss.bean.Chapter;
import com.larryluk.xss.spider.Spider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by larryluk on 2017/8/10.
 */
public class BiQuSpider implements Spider {

    @Override
    public Chapter getChapter(String html) {
        String base = "http://www.biquzi.com";
        Document document = Jsoup.parse(html);
        Elements context = document.select("#content");
        Elements a = document.select("a");
        Elements title = document.select("h1");

        String next = "";
        String prev = "";
        for(Element e : a) {
            String text = e.text();

            if(text.contains("下一")) {
                next = e.attr("href");
            }

            if(text.contains("上一")) {
                prev = e.attr("href");
            }

            if(next.length() != 0 && prev.length() != 0) {
                break;
            }
        }

        String text = context.toString();

        text = text.replaceAll("<br>|&nbsp;|<div id=\"content\">|</div>", "");
        String t = title.toString().replaceAll("<h1>|</h1>", "");
        prev = base + prev;
        next = base + next;

        Chapter chapter = new Chapter();
        chapter.setContext(text);
        chapter.setTitle(t);
        chapter.setNextUrl(next);
        chapter.setPrevUrl(prev);

        return chapter;
    }
}
