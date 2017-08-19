package com.larryluk.xss.ui;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.larryluk.xss.R;
import com.larryluk.xss.application.XSSActivity;
import com.larryluk.xss.bean.Index;
import com.larryluk.xss.mvp.presenter.MainPresenter;
import com.larryluk.xss.mvp.presenter.impl.MainPresenterImpl;
import com.larryluk.xss.mvp.view.IFragmentManager;
import com.larryluk.xss.mvp.view.MainActivityView;
import com.larryluk.xss.mvp.view.MainView;
import com.larryluk.xss.ui.paper.RollPaper;
import com.larryluk.xss.util.Constants;
import com.larryluk.xss.util.XSSCache;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends XSSActivity
        implements NavigationView.OnNavigationItemSelectedListener, IFragmentManager, MainActivityView{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_body)
    FrameLayout mainBody;
    private ProgressDialog dialog;
    private MainPresenter mainPresenter;

    private MainView mainView;

    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        init();
    }

    private void init() {
//        mainPresenter.loadChapter("http://www.biquzi.com/11_11850/7644114.html");

        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(Constants.WAIL_MSG.toString());

        mainPresenter = new MainPresenterImpl();
        mainPresenter.attachView(this);

        //fragment管理
        RollPaper rollPaper = new RollPaper();
        mainView = rollPaper;
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_body, rollPaper);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_load) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final EditText et = new EditText(this);
            builder.setTitle("请输入小说网址");
            builder.setView(et);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String url = et.getText().toString();
                    mainView.loadPage(url);
                }
            });
            builder.setNegativeButton("取消", null);
            builder.show();


            // Handle the camera action
        } else if (id == R.id.nav_index) {
            Object o = XSSCache.get(Constants.NOW_PAGE_INDEX);
            if(o instanceof String)
                mainPresenter.getIndex(((String) o));
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onChange(@NonNull int FragmentID) {

    }

    @Override
    public void onChangeTitle(String s) {
        toolbar.setTitle(s);
    }

    private void showIndexDialog(List<Index> indices) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("章节目录");

        final String[] show = new String[indices.size()];
        final String[] data = new String[indices.size()];

        Collections.reverse(indices);
        Index index = null;
        for(int i = 0, j = indices.size(); i < j; i++) {
            index = indices.get(i);
            show[i] = index.getTitle();
            data[i] = index.getUrl();
        }

        /**
         * 设置内容区域为简单列表项
         */

        builder.setItems(show, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.i(TAG, data[i]);
                mainView.loadPage(data[i]);
            }
        });
        builder.setCancelable(true);
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    @Override
    public void showProgressDialog() {
        dialog.show();
    }

    @Override
    public void hideProgressDialog() {
        dialog.dismiss();
    }

    @Override
    public void showErrorMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getIndexSuc(List<Index> list) {
        showIndexDialog(list);
    }
}
