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

    public static String getHtml(String webPath) throws IOException {
        BufferedReader in = null;
        URL url = new URL(webPath);
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

    public static void main(String[] args) throws IOException {
    }
}
