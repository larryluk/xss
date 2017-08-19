package com.larryluk.xss.mvp.view;

import android.support.annotation.NonNull;

/**
 * Created by Larry on 2017/8/13.
 */

public interface IFragmentManager {
    void onChange(@NonNull int FragmentID);
    void onChangeTitle(String s);
}
