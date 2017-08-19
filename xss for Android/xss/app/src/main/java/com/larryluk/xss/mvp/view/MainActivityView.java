package com.larryluk.xss.mvp.view;

import com.larryluk.xss.bean.Index;

import java.util.List;

/**
 * Created by Larry on 2017/8/19.
 */

public interface MainActivityView {
    void showProgressDialog();
    void hideProgressDialog();
    void showErrorMessage(String text);
    void getIndexSuc(List<Index> list);
}
