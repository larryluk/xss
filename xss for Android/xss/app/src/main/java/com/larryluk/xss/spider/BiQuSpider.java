package com.larryluk.xss.spider;

import com.larryluk.xss.bean.Chapter;
import com.larryluk.xss.util.Constants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Larry on 2017/8/5.
 * 笔趣阁解析器
 * 网址：http://www.biquzi.com/
 */

public class BiQuSpider implements XSSpider {
    /**
     * 解析html
     *
     * @param s
     * @return
     */
    @Override
    public Chapter resolver(String s) {
        String base = "http://www.biquzi.com";
        Document document = Jsoup.parse(s);
        Elements context = document.select("#content");
        Elements a = document.select("a");
        Elements title = document.select("h1");

        String next = "";
        String prev = "";
        for(Element e : a) {
            String text = e.text();

            if(text.equals("下一")) {
                next = e.attr("href");
            }

            if(text.equals("上一")) {
                prev = e.attr("href");
            }

            if(next.length() != 0 && prev.length() != 0) {
                break;
            }
        }

        String text = context.toString();

        text = text.replaceAll("<br>|&nbsp;|<div id=\"content\">|</div>", "");
        String t = title.toString().replaceAll("<h1>|</h1>", "");

        Chapter chapter = new Chapter(Constants.LOAD_SUC.toString(), Constants.LOAD_FAIL_MSG.toString());
        chapter.setTitle(t);
        chapter.setContext(text);
        chapter.setNextUrl(base + next);
        chapter.setPrevUrl(base + prev);

        return chapter;
    }
}
