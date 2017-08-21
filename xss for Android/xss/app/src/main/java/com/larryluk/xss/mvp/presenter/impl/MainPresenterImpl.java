package com.larryluk.xss.mvp.presenter.impl;

import com.larryluk.xss.bean.Chapter;
import com.larryluk.xss.bean.Index;
import com.larryluk.xss.bean.Status;
import com.larryluk.xss.mvp.model.MainModel;
import com.larryluk.xss.mvp.model.impl.MainModelImpl;
import com.larryluk.xss.mvp.presenter.MainPresenter;
import com.larryluk.xss.mvp.view.MainActivityView;
import com.larryluk.xss.mvp.view.MainView;
import com.larryluk.xss.util.Constants;

import java.util.List;

import rx.Subscriber;

/**
 * Created by Larry on 2017/8/5.
 */

public class MainPresenterImpl implements MainPresenter {
    private MainModel mainModel;
    private MainActivityView mainActivityView;
    private MainView mainView;

    public MainPresenterImpl() {
        mainModel = new MainModelImpl();
    }

    @Override
    public void attachView(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void attachView(MainActivityView mainActivityView) {
        this.mainActivityView = mainActivityView;
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
                if(checkChapter(chapter))
                    mainView.onLoadPageSuccess(chapter);
            }
        }, url);
    }

    @Override
    public void preload(String url) {
        mainModel.getChapter(new Subscriber<Chapter>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Chapter chapter) {
                if(checkChapter(chapter))
                    mainView.preloadSuc(chapter);
            }
        }, url);
    }

    @Override
    public void getIndex(String url) {
        mainModel.getIndex(new Subscriber<List<Index>>() {
            @Override
            public void onStart() {
                mainActivityView.showProgressDialog();
            }

            @Override
            public void onCompleted() {
                mainActivityView.hideProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                mainActivityView.showErrorMessage("网络错误");
            }

            @Override
            public void onNext(List<Index> indices) {
                mainActivityView.getIndexSuc(indices);
            }
        }, url);
    }

    /**
     *
     * @param chapter
     * @return false 不通过校验
     */
    public boolean checkChapter(Chapter chapter) {
        Status status = chapter.getStatus();
        if(Constants.LOAD_FAIL.toString().equals(status.getCode())) {
            mainView.showErrorMessage(status.getMsg());
            return false;
        }

        if(chapter.isEmpty()) {
            mainView.showErrorMessage("已经是最新章节");
            return false;
        }

        return true;
    }
}
