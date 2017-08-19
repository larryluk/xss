package com.larryluk.xss.spider;

import com.larryluk.xss.bean.Chapter;
import com.larryluk.xss.bean.Index;
import com.larryluk.xss.bean.Status;
import com.larryluk.xss.util.Constants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Larry on 2017/8/5.
 * 笔趣阁解析器
 * 网址：http://www.biquzi.com/
 */

public class BiQuSpider implements XSSpider {
    /**
     * 解析html
     *
     * @param html
     * @return
     */
    @Override
    public Chapter resolver(String html) {
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

        Chapter chapter = new Chapter(new Status(Constants.LOAD_SUC.toString(), Constants.LOAD_FAIL_MSG.toString()));
        chapter.setTitle(t);
        chapter.setContext(text);
        chapter.setNextUrl(base + next);
        chapter.setPrevUrl(base + prev);
//        chapter.setNowUrl(s);

        return chapter;
    }

    @Override
    public List<Index> getIndex(String html, String baseUrl) {
        Document document = Jsoup.parse(html);
        Elements select = document.select("#list a");

        List<Index> indexes = new ArrayList<>(150);
        for(Element e : select) {
            String href = e.attr("href");
            href = href.substring(href.lastIndexOf('/'));
            href = baseUrl + href;

            indexes.add(new Index(e.text(), href));
        }
        return indexes;
    }
}
