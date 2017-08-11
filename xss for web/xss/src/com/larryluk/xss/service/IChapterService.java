package com.larryluk.xss.service;

import com.larryluk.xss.bean.Chapter;
import com.larryluk.xss.bean.Result;

import java.io.IOException;

/**
 * Created by larryluk on 2017/8/10.
 */
public interface IChapterService {
    Result<Chapter> getXsChapter(String url) throws IOException;
}
