package com.larryluk.xss.mvp.view;

import com.larryluk.xss.bean.Chapter;

/**
 * Created by Larry on 2017/7/31.
 */

public interface MainView {
    void showProgressDialog();
    void hideProgressDialog();
    void showErrorMessage(String text);
    void onLoadPageSuccess(Chapter chapter);
}
