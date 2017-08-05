package com.larryluk.xss.mvp.presenter;

import com.larryluk.xss.mvp.view.MainView;

/**
 * Created by Larry on 2017/8/5.
 */

public interface MainPresenter {
    void attachView(MainView mainView);
    void detachView();
    void loadChapter(String url);
}
