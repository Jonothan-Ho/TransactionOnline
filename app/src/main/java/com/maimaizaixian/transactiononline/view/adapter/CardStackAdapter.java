package com.maimaizaixian.transactiononline.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.maimaizaixian.transactiononline.module.account.domain.User;
import com.maimaizaixian.transactiononline.module.common.domain.CardModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public abstract class CardStackAdapter extends BaseCardStackAdapter {
    private final Context mContext;

    /**
     * Lock used to modify the content of {@link #mData}. Any write operation
     * performed on the deque should be synchronized on this lock.
     */
    private final Object mLock = new Object();
    private ArrayList<CardModel> mData;

    public CardStackAdapter(Context context) {
        mContext = context;
        mData = new ArrayList<CardModel>();
    }

    public CardStackAdapter(Context context, Collection<? extends CardModel> items) {
        mContext = context;
        mData = new ArrayList<CardModel>(items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FrameLayout wrapper = (FrameLayout) convertView;
        FrameLayout innerWrapper;
        View cardView;
        View convertedCardView;
        if (wrapper == null) {
            wrapper = new FrameLayout(mContext);
            //wrapper.setBackgroundResource(R.mipmap.bg_card);

            if (shouldFillCardBackground()) {
                innerWrapper = new FrameLayout(mContext);
                //innerWrapper.setBackgroundColor(mContext.getResources().getColor(R.color.color_grey_bg));
                //innerWrapper.setBackgroundResource(R.mipmap.bg_card);
                wrapper.addView(innerWrapper);
            } else {
                innerWrapper = wrapper;
            }
            cardView = getCardView(position, getCardModel(position), null, parent);
            innerWrapper.addView(cardView);
        } else {
            if (shouldFillCardBackground()) {
                innerWrapper = (FrameLayout) wrapper.getChildAt(0);
            } else {
                innerWrapper = wrapper;
            }
            cardView = innerWrapper.getChildAt(0);
            convertedCardView = getCardView(position, getCardModel(position), cardView, parent);
            if (convertedCardView != cardView) {
                wrapper.removeView(cardView);
                wrapper.addView(convertedCardView);
            }
        }

        return wrapper;
    }

    protected abstract View getCardView(int position, CardModel model, View convertView, ViewGroup parent);

    public boolean shouldFillCardBackground() {
        return true;
    }

    public void add(CardModel item) {
        synchronized (mLock) {
            mData.add(item);
        }
        notifyDataSetChanged();
    }


    /**
     * @param users
     */
    public void addAll(List<? extends CardModel> users) {
        mData.addAll(users);
        notifyDataSetChanged();
    }


    /**
     * @param users
     */
    public void replaceAll(List<? extends CardModel> users) {
        mData.clear();
        mData.addAll(users);
        notifyDataSetChanged();
    }


    public CardModel pop() {
        CardModel model;
        synchronized (mLock) {
            model = mData.remove(mData.size() - 1);
        }
        notifyDataSetChanged();
        return model;
    }

    @Override
    public Object getItem(int position) {
        return getCardModel(position);
    }

    public CardModel getCardModel(int position) {
        synchronized (mLock) {
            //int index = mData.size() - 1 - position;
            //return mData.get(index >= 0 ? index : 0);
            return mData.get(mData.size() - 1 - position);
        }
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    public Context getContext() {
        return mContext;
    }
}
