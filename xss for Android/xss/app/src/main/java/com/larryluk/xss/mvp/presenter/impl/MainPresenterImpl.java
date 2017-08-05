package com.larryluk.xss.mvp.presenter.impl;

import com.larryluk.xss.bean.Chapter;
import com.larryluk.xss.mvp.model.MainModel;
import com.larryluk.xss.mvp.model.impl.MainModelImpl;
import com.larryluk.xss.mvp.presenter.MainPresenter;
import com.larryluk.xss.mvp.view.MainView;
import com.larryluk.xss.util.Constants;

import rx.Subscriber;

/**
 * Created by Larry on 2017/8/5.
 */

public class MainPresenterImpl implements MainPresenter {
    private MainModel mainModel;
    private MainView mainView;

    public MainPresenterImpl() {
        mainModel = new MainModelImpl();
    }

    @Override
    public void attachView(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void detachView() {
        mainView = null;
    }

    @Override
    public void loadChapter(String url) {
        mainModel.getChapter(new Subscriber<Chapter>() {

            @Override
            public void onStart() {
                mainView.showProgressDialog();
            }

            @Override
            public void onCompleted() {
                mainView.hideProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                mainView.showErrorMessage(e.getMessage());
            }

            @Override
            public void onNext(Chapter chapter) {
                if(Constants.LOAD_FAIL.toString().equals(chapter.getMsgCode())) {
                    mainView.showErrorMessage(chapter.getMsgContent());
                    return ;
                }

                mainView.onLoadPageSuccess(chapter);
            }
        }, url);
    }
}
