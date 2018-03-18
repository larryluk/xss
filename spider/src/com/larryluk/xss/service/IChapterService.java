package com.larryluk.xss.service;

import com.larryluk.xss.bean.Chapter;
import com.larryluk.xss.bean.Index;
import com.larryluk.xss.bean.Result;

import java.io.IOException;
import java.util.List;

/**
 * Created by larryluk on 2017/8/10.
 */
public interface IChapterService {
    Result<Chapter> getXsChapter(String url) throws IOException;

    Result<List<Index>> getXsIndex(String url) throws IOException;

    Result<?> searchXsByKey(String key) throws IOException;
}
