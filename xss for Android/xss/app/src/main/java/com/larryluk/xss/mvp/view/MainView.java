package com.larryluk.xss.mvp.view;

import com.larryluk.xss.bean.Chapter;
import com.larryluk.xss.bean.Index;

import java.util.List;

/**
 * Created by Larry on 2017/7/31.
 */

public interface MainView {
    void showProgressDialog();
    void hideProgressDialog();
    void showErrorMessage(String text);
    void onLoadPageSuccess(Chapter chapter);
    void loadPage(String url);
    void preloadSuc(Chapter chapter);
}
