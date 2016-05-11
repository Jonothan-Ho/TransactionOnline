package com.maimaizaixian.transactiononline.framework.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.maimaizaixian.transactiononline.framework.listener.OnItemClickListener;
import com.maimaizaixian.transactiononline.framework.listener.OnItemDeleteListener;

import java.util.List;

/**
 * The base class adapter for RecyclerView
 * Created by Administrator on 2015/10/21.
 */
public abstract class BaseAdapter<K, T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    protected List<K> mDataSource;
    protected Context context;
    protected LayoutInflater mLayoutInflater;
    protected OnItemClickListener mItemClickListener;
    protected OnItemDeleteListener mItemDeleteListener;

    public BaseAdapter(Context context, List<K> source) {
        this.mDataSource = source;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public BaseAdapter(Context context, List<K> source, OnItemClickListener clickListener) {
        this(context, source);
        this.mItemClickListener = clickListener;
    }

    /**
     * @param listener
     */
    public void addOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    /**
     * @param listener
     */
    public void addOnItemDeleteListener(OnItemDeleteListener listener) {
        this.mItemDeleteListener = listener;
    }

    /**
     *
     */
    public void removItemClickListener() {
        this.mItemClickListener = null;
    }

    /**
     *
     */
    public void removItemDeleteListener() {
        this.mItemDeleteListener = null;
    }


    /**
     * add data into source
     *
     * @param data
     */
    public void addData(K data) {
        mDataSource.add(data);
        notifyItemInserted(mDataSource.size());
    }

    /**
     * repalce the data source
     *
     * @param newData
     */
    public void replaceData(List<K> newData) {
        this.mDataSource = newData;
        notifyDataSetChanged();
    }

    /**
     * @param dataSource
     */
    public void addData(List<K> dataSource) {
        this.mDataSource.addAll(dataSource);
        notifyDataSetChanged();
    }

    /**
     * @param position
     * @return
     */
    public K getItem(int position) {
        return mDataSource.get(position);
    }


    /**
     * remove data from datasource
     *
     * @param position
     */
    public void removeData(int position) {
        if (mDataSource != null && mDataSource.size() > position) {
            K obj = mDataSource.remove(position);
            handleRemovedData(obj);
            notifyDataSetChanged();
        }
    }

    /**
     * @param position
     */
    public void justRemoveData(int position) {
        if (mDataSource != null && mDataSource.size() > position) {
            mDataSource.remove(position);
        }
    }


    /**
     *
     */
    public void removeAllData() {
        mDataSource.clear();
        notifyDataSetChanged();
    }


    /**
     * handle remove data from source
     *
     * @param obj
     */
    public void handleRemovedData(K obj) {
    }

    @Override
    public int getItemCount() {
        return mDataSource.size();
    }
}
