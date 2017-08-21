package com.larryluk.xss.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by larryluk on 2017/8/3.
 */
public class HttpRequestUtil {

    public static String getHtml(String webpath) throws IOException {
        BufferedReader in = null;
        URL url = new URL(webpath);
        URLConnection uc = url.openConnection();
        uc.addRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        uc.addRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
        uc.addRequestProperty("Accept-Encoding", "gbk");
        uc.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.48 Safari/537.36");
        uc.connect();
        in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "GBK"));
        StringBuilder sb = new StringBuilder(1024);
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line).append("\n");
        }

        if(in != null) in.close();

        return sb.toString();
    }
    public static String getHtml(String webpath, String encoding) throws IOException {
        BufferedReader in = null;
        URL url = new URL(webpath);
        URLConnection uc = url.openConnection();
        uc.addRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        uc.addRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
        uc.addRequestProperty("Accept-Encoding", "gbk");
        uc.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.48 Safari/537.36");
        uc.connect();
        in = new BufferedReader(new InputStreamReader(uc.getInputStream(), encoding));
        StringBuilder sb = new StringBuilder(1024);
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line).append("\n");
        }

        if(in != null) in.close();

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        String s = getHtml("http://www.biquzi.com/11_11850/7644114.html");
        String base = "http://www.biquzi.com/";
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
        System.out.println(title);
        System.out.println(text);
        System.out.println(next);
    }
}
