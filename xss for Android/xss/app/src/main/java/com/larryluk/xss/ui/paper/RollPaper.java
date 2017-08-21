package com.larryluk.xss.ui.paper;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XScrollView;
import com.larryluk.xss.R;
import com.larryluk.xss.bean.Chapter;
import com.larryluk.xss.bean.Index;
import com.larryluk.xss.mvp.presenter.MainPresenter;
import com.larryluk.xss.mvp.presenter.impl.MainPresenterImpl;
import com.larryluk.xss.mvp.view.IFragmentManager;
import com.larryluk.xss.mvp.view.MainView;
import com.larryluk.xss.util.Constants;
import com.larryluk.xss.util.XSSCache;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Cache;

/**
 * Created by Larry on 2017/8/13.
 */

public class RollPaper extends Fragment implements MainView, XRefreshView.XRefreshViewListener {
    private static final String TAG = "RollPaper";
    @BindView(R.id.chapter_body)
    TextView chapterBody;
    @BindView(R.id.refreshView)
    XRefreshView refreshView;
    @BindView(R.id.scrollView)
    XScrollView scrollView;
    private IFragmentManager fragmentManager;
    private MainPresenter mainPresenter;
    private ProgressDialog dialog;
    private Chapter chapter;
    private StringBuilder chapterContent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.roll_paper_fragment, container, false);

        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        if (fragmentManager == null)
            fragmentManager = (IFragmentManager) getActivity();

        chapterContent = new StringBuilder(102400);
        dialog = new ProgressDialog(getActivity());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(Constants.WAIL_MSG.toString());

        SharedPreferences sp = getActivity().getSharedPreferences(Constants.LOCAL_INFO.toString(), Activity.MODE_PRIVATE);
        int now = sp.getInt(Constants.NOW_PAGE.toString(), -1);

        mainPresenter = new MainPresenterImpl();
        mainPresenter.attachView(this);
        if (now != -1) {
            Chapter chapter = DataSupport.find(Chapter.class, now);
            onLoadPageSuccess(chapter);
        }

        refreshView.setPullRefreshEnable(false);
        refreshView.setPullLoadEnable(false);
        refreshView.setAutoRefresh(false);
        refreshView.setAutoLoadMore(true);
        refreshView.setXRefreshViewListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IFragmentManager) {
            fragmentManager = (IFragmentManager) context;
        }
    }

    @Override
    public void onDetach() {
        fragmentManager = null;
        if (chapter != null && !chapter.isEmpty()) {
            SharedPreferences.Editor edit = getActivity().getSharedPreferences(Constants.LOCAL_INFO.toString(), Activity.MODE_PRIVATE).edit();
            edit.putInt(Constants.NOW_PAGE.toString(), chapter.getId());
            edit.apply();
            edit.commit();
        }

        super.onDetach();
    }

    @Override
    public void showProgressDialog() {
//        dialog.show();
    }

    @Override
    public void hideProgressDialog() {
//        dialog.dismiss();
    }

    @Override
    public void showErrorMessage(String text) {
        Log.d(TAG, text);
        Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();
        refreshView.stopLoadMore();
    }

    @Override
    public void onLoadPageSuccess(Chapter chapter) {
        showChapter(chapter);
        mainPresenter.preload(chapter.getNextUrl());
    }

    @Override
    public void loadPage(String url) {
        chapterContent = new StringBuilder(10240);
        mainPresenter.loadChapter(url);
    }

    @Override
    public void preloadSuc(Chapter chapter) {
        chapter.save();
        int id = chapter.getId();

        if(id != 0){
            this.chapter.setNext(chapter.getId());
            chapter.setPrev(this.chapter.getId());
            this.chapter.save();
            chapter.save();
        }

    }

    @Override
    public void onRefresh() {
        Toast.makeText(getActivity(), "释放", Toast.LENGTH_SHORT).show();
    }

    /**
     * @param isSilence 是不是静默加载，静默加载即不显示footerview，自动监听滚动到底部并触发此回调
     */
    @Override
    public void onLoadMore(boolean isSilence) {
        int next = this.chapter.getNext();
        if(next == 0){
            mainPresenter.loadChapter(chapter.getNextUrl());
        } else {
            Chapter chapter = DataSupport.find(Chapter.class, next);
            showChapter(chapter);
            mainPresenter.preload(chapter.getNextUrl());
        }
    }

    /**
     * 用户手指释放的监听回调
     *
     * @param direction >0: 下拉释放，<0:上拉释放 注：暂时没有使用这个方法
     */
    @Override
    public void onRelease(float direction) {
        mainPresenter.loadChapter(chapter.getPrevUrl());
    }

    /**
     * 获取headerview显示的高度与headerview高度的比例
     *
     * @param headerMovePercent 移动距离和headerview高度的比例
     * @param offsetY           headerview移动的距离
     */
    @Override
    public void onHeaderMove(double headerMovePercent, int offsetY) {

    }

    /**
     * 显示章节到页面上
     * @param chapter
     */
    public void showChapter(Chapter chapter) {
        this.chapter = chapter;
        XSSCache.put(Constants.NOW_PAGE_INDEX, this.chapter.getIndexUrl());
        if (chapterContent.length() > 0) {
            chapterContent.append("\n\n");
            chapterContent.append("|").append(chapter.getTitle()).append("|");
        }

        chapterContent.append(chapter.getContext());
        fragmentManager.onChangeTitle(chapter.getTitle().trim().split(" ")[1]);
        chapterBody.setText(chapterContent.toString());
        refreshView.stopLoadMore();
    }
}
