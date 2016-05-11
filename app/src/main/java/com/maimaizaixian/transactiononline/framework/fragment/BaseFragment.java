package com.maimaizaixian.transactiononline.framework.fragment;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maimaizaixian.transactiononline.utils.LogUtil;

/**
 * Fragment's subclass
 * All the fragment in this app must extends this fragment
 * manage all the fragment in this app
 * Created by Administrator on 2015/10/9.
 */
public abstract class BaseFragment extends Fragment {


    /**
     * Fragment's content view
     */
    protected View rootView;
    protected Bundle mSavedInstanceState;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mSavedInstanceState = savedInstanceState;
        if (rootView == null) {
            rootView = inflater.inflate(getCustomView(), container, false);
            initView();
            initListener();
        }

        return rootView;
    }

    protected abstract int getCustomView();

    protected abstract void initView();

    protected abstract void initListener();


    /**
     * create an instance  of Fragment by generics
     *
     * @param clazz
     * @param bundle
     * @param <T>
     * @return
     */
    public static <T> T newInstance(Class<T> clazz, Bundle bundle) {
        try {
            T t = clazz.newInstance();
            if (bundle != null) {
                ((Fragment) t).setArguments(bundle);
            }
            return t;
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
