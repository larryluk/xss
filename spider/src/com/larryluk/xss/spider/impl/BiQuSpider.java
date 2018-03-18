package com.larryluk.xss.spider.impl;

import com.larryluk.xss.bean.Book;
import com.larryluk.xss.bean.Chapter;
import com.larryluk.xss.bean.Index;
import com.larryluk.xss.spider.Spider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 针对 http://www.biquge.com.tw 的爬虫实现
 * Created by larryluk on 2017/8/10.
 */
@Repository("biquSpider")
public class BiQuSpider implements Spider {

    @Value("${constant.base}")
    private String base;
    @Override
    public Chapter getChapter(String html) {
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

//        text = text.replaceAll("<br>|&nbsp;|<div id=\"content\">|</div>", "");
        String t = title.toString().replaceAll("<h1>|</h1>", "");
        prev = base + prev;
        next = base + next;

        Chapter chapter = new Chapter();
        chapter.setContent(text);
        chapter.setTitle(t);
        chapter.setNextUrl(next);
        chapter.setPrevUrl(prev);
        chapter.setIndex(next.substring(0, next.lastIndexOf('/')));

        return chapter;
    }

    @Override
    public List<Index> getIndex(String html) {
        Document document = Jsoup.parse(html);
        Elements select = document.select("#list a");

        List<Index> indexes = new ArrayList<>(150);
        for(Element e : select) {
            String href = e.attr("href");
            href = base + href;

            indexes.add(new Index(e.text(), href));
        }
        return indexes;
    }

    @Override
    public List<Book> searchBooks(String html) {
        Document document = Jsoup.parse(html);
        Elements select = document.select("#nr");

        List<Book> books = new ArrayList<>();
        Book book;
        for (Element e : select) {
            String bookName = e.child(0).child(0).text();
            String indexUrl = e.child(0).child(0).attr("href");
            String lastChapter = e.child(1).child(0).text();
            String author = e.child(2).text();
            String status = e.child(5).text();

            book = new Book();
            book.setAuthor(author);
            book.setBookName(bookName);
            book.setIndexUrl(indexUrl);
            book.setLastChapter(lastChapter);
            book.setStatus(status);

            books.add(book);
        }

        return books;
    }
}
