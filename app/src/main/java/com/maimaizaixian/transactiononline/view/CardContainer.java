package com.maimaizaixian.transactiononline.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.module.account.domain.User;
import com.maimaizaixian.transactiononline.utils.LogUtil;
import com.maimaizaixian.transactiononline.view.domain.CardModel;

import java.util.Random;

/**
 * This is layer card view
 * Created by Administrator on 2015/11/2.
 */
public class CardContainer extends AdapterView<ListAdapter> {

    public static final int INVALID_POINTER_ID = -1;
    public static final double MAX_ROTATION_RADIUS = Math.PI / 64;
    public static final int MAX_VISIBLE = 10;
    public static final int PADDING = 150;

    private int mFlingSlop; //fling minimum value
    private int mTouchSlop; //the distance of the gestures


    private int mNextAdapterPosition = -1;
    private int mNumberOfCards;
    private int mGravity;
    private View mTopCard;
    private View mBottomCard;

    private float mLastTouchX;
    private float mLastTouchY;
    private int mActionPointerId = INVALID_POINTER_ID;
    private boolean mIsDragging;

    private GestureDetector mGestureDetector;

    private ListAdapter mListAdapter;

    private final Random mRandom = new Random();
    private final Rect mBoundsRect = new Rect();
    private final Rect mChildRect = new Rect();
    private final Matrix mMatrix = new Matrix();

    private OnDismissListener dismissListener;


    private final DataSetObserver mDataSetObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            clearStack();
            ensureFull();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
            clearStack();
        }
    };


    public CardContainer(Context context) {
        this(context, null);
    }

    public CardContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public interface OnDismissListener {
        void onDismiss(int position);
    }


    public void setOnDismissListener(OnDismissListener listener) {
        this.dismissListener = listener;
    }

    /**
     * initialized
     */
    private void init() {
        //initOrientation();

        ViewConfiguration viewConfiguration = android.view.ViewConfiguration.get(getContext());
        mFlingSlop = viewConfiguration.getScaledMinimumFlingVelocity();
        mTouchSlop = viewConfiguration.getScaledTouchSlop();
        mGestureDetector = new GestureDetector(getContext(), new GestureListener());

    }


    /**
     * set orientation
     */
    private void initOrientation() {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
           /* if (i == getChildCount() - 1) {
                child.setRotation(getChildRotation());
            }*/

        }
    }


    /**
     * clear data
     */
    private void clearStack() {
        removeAllViewsInLayout();
        mNextAdapterPosition = 0;
        mTopCard = null;
        mBottomCard = null;
    }


    /**
     * get child view rotation degrees
     *
     * @return
     */
    private float getChildRotation() {
        return (float) Math.toDegrees(mRandom.nextGaussian() * MAX_ROTATION_RADIUS);
    }


    @Override
    public ListAdapter getAdapter() {
        return mListAdapter;
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        if (mListAdapter != null) {
            mListAdapter.unregisterDataSetObserver(mDataSetObserver);
        }

        clearStack();
        mTopCard = null;
        mListAdapter = adapter;
        mNextAdapterPosition = 0;
        adapter.registerDataSetObserver(mDataSetObserver);

        ensureFull();

        if (getChildCount() != 0) {
            mTopCard = getChildAt(getChildCount() - 1);
            mTopCard.setLayerType(LAYER_TYPE_HARDWARE, null);
        }

        mNumberOfCards = getAdapter().getCount();
        requestLayout();

    }

    @Override
    public View getSelectedView() {
        //throw new RuntimeException();
        return mTopCard;
    }

    @Override
    public void setSelection(int position) {
        //throw new RuntimeException();
    }


    /**
     * initialized view
     */
    private void ensureFull() {

        while (mNextAdapterPosition < mListAdapter.getCount() && getChildCount() < MAX_VISIBLE) {

         /*   if (mNextAdapterPosition == mListAdapter.getCount()-2) {
                //
                if (dismissListener != null) {
                    dismissListener.onDismiss(mNextAdapterPosition);
                }
            }*/

            View view = mListAdapter.getView(mNextAdapterPosition, null, this);

            if (view == mBottomCard) {
                // removeView(mBottomCard);
                break;
            }


            view.setLayerType(LAYER_TYPE_SOFTWARE, null);

            if (mNextAdapterPosition == mListAdapter.getCount() - 1) {
                view.setRotation(5);
            }

            addViewInLayout(view, 0, new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                    mListAdapter.getItemViewType(mNextAdapterPosition)), false);
            requestLayout();
            mNextAdapterPosition += 1;
        }

        if (mBottomCard == null) {
            mBottomCard = mListAdapter.getView(mListAdapter.getCount() - 1, null, this);
            mBottomCard.setRotation(5);
            addViewInLayout(mBottomCard, 0, new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                    mListAdapter.getItemViewType(mNextAdapterPosition)), false);
        }

    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        for (int i = 0; i < getChildCount(); i++) {
            mBoundsRect.set(0, 0, getWidth(), getHeight());

            View view = getChildAt(i);
            int w, h;
            w = view.getMeasuredWidth();
            h = view.getMeasuredHeight();

            Gravity.apply(mGravity, w, h, mBoundsRect, mChildRect);

            view.layout(mChildRect.left, mChildRect.top, mChildRect.right, mChildRect.bottom);

        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int requestWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight() - PADDING;
        int requestHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom() - PADDING;

       /* int R1, R2;
        if (requestWidth > requestHeight) {
            R1 = requestHeight;
            R2 = requestWidth;
        } else {
            R1 = requestWidth;
            R2 = requestHeight;
        }

        int childWidth = (int) ((R1 * Math.cos(MAX_ROTATION_RADIUS) - R2 * Math.sin(MAX_ROTATION_RADIUS)) / Math.cos(2 * MAX_ROTATION_RADIUS));
        int childHeight = (int) ((R2 * Math.cos(MAX_ROTATION_RADIUS) - R1 * Math.sin(MAX_ROTATION_RADIUS)) / Math.cos(2 * MAX_ROTATION_RADIUS));*/

        int childWidth = requestWidth;
        int childHeight = requestHeight;

        int childWidthMeasureSpec, childHeightMeasureSpec;
        childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.AT_MOST);
        childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.AT_MOST);

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            assert child != null;
            child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mTopCard == null) {
            return false;
        }

        if (mGestureDetector.onTouchEvent(event)) {
            return true;
        }

        final int pointerIndex;
        final float x, y;
        final float dx, dy;

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mTopCard.getHitRect(mChildRect);

                pointerIndex = event.getActionIndex();

                x = event.getX(pointerIndex);
                y = event.getY(pointerIndex);

                if (!mChildRect.contains((int) x, (int) y)) {
                    return false;
                }

                mLastTouchX = x;
                mLastTouchY = y;
                mActionPointerId = event.getPointerId(pointerIndex);


                float points[] = {x - mTopCard.getLeft(), y - mTopCard.getTop()};

                LogUtil.printf(points[0] + "===before>" + points[1]);

                mTopCard.getMatrix().invert(mMatrix);
                mMatrix.mapPoints(points);

                LogUtil.printf(points[0] + "===>" + points[1] + "==>" + points.length);

                mTopCard.setPivotX(points[0]);
                mTopCard.setPivotY(points[1]);
                break;

            case MotionEvent.ACTION_MOVE:

                pointerIndex = event.findPointerIndex(mActionPointerId);
                x = event.getX(pointerIndex);
                y = event.getY(pointerIndex);

                dx = x - mLastTouchX;
                dy = y - mLastTouchY;

                if (Math.abs(dx) > mTouchSlop || Math.abs(dy) > mTouchSlop) {
                    mIsDragging = true;
                }

                if (!mIsDragging) {
                    mIsDragging = true;
                }

                mTopCard.setTranslationX(mTopCard.getTranslationX() + dx);
                mTopCard.setTranslationY(mTopCard.getTranslationY() + dy);

                mTopCard.setRotation(40 * mTopCard.getTranslationX() / (getWidth() / 2.f));

                mLastTouchX = x;
                mLastTouchY = y;

                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:

                if (!mIsDragging) {
                    return true;
                }

                mIsDragging = false;

                mActionPointerId = INVALID_POINTER_ID;
                ValueAnimator animator = ObjectAnimator.ofPropertyValuesHolder(mTopCard,
                        PropertyValuesHolder.ofFloat("translationX", 0),
                        PropertyValuesHolder.ofFloat("translationY", 0),
                        PropertyValuesHolder.ofFloat("rotation", 0), //(float) Math.toDegrees(getChildRotation())
                        PropertyValuesHolder.ofFloat("pivotX", mTopCard.getWidth() / 2.f),
                        PropertyValuesHolder.ofFloat("pivotY", mTopCard.getHeight() / 2.f)
                ).setDuration(250);

                animator.setInterpolator(new AccelerateInterpolator());
                animator.start();

                break;

            case MotionEvent.ACTION_POINTER_UP:
                pointerIndex = event.getActionIndex();
                final int pointerId = event.getPointerId(pointerIndex);

                if (pointerId == mActionPointerId) {
                    final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                    mLastTouchX = event.getX(newPointerIndex);
                    mLastTouchY = event.getY(newPointerIndex);

                    mActionPointerId = event.getPointerId(newPointerIndex);
                }

                break;

        }

        return true;

    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (mTopCard == null) {
            return false;
        }

        if (mGestureDetector.onTouchEvent(event)) {
            return true;
        }

        final int pointerIndex;
        final float x, y;
        final float dx, dy;
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mTopCard.getHitRect(mChildRect);

                //User cardModel = (User) getAdapter().getItem(getChildCount() - 1);

              /*  if (cardModel.getOnClickListener() != null) {
                    cardModel.getOnClickListener().OnClickListener();
                }*/

                pointerIndex = event.getActionIndex();
                x = event.getX(pointerIndex);
                y = event.getY(pointerIndex);

                if (!mChildRect.contains((int) x, (int) y)) {
                    return false;
                }

                mLastTouchX = x;
                mLastTouchY = y;
                mActionPointerId = event.getPointerId(pointerIndex);
                break;
            case MotionEvent.ACTION_MOVE:
                pointerIndex = event.findPointerIndex(mActionPointerId);
                x = event.getX(pointerIndex);
                y = event.getY(pointerIndex);
                if (Math.abs(x - mLastTouchX) > mTouchSlop || Math.abs(y - mLastTouchY) > mTouchSlop) {
                    float[] points = new float[]{x - mTopCard.getLeft(), y - mTopCard.getTop()};
                    mTopCard.getMatrix().invert(mMatrix);
                    mMatrix.mapPoints(points);
                    mTopCard.setPivotX(points[0]);
                    mTopCard.setPivotY(points[1]);
                    return true;
                }
                break;
        }

        return false;
    }

    public int getGravity() {
        return mGravity;
    }

    public void setGravity(int gravity) {
        mGravity = gravity;
    }


    /**
     * View child LayoutParams
     */
    private class LayoutParams extends ViewGroup.LayoutParams {

        int viewType;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(int w, int h, int viewType) {
            super(w, h);
            this.viewType = viewType;
        }


    }


    /**
     * Gesture Listener for GestureDetector
     */
    private class GestureListener extends GestureDetector.SimpleOnGestureListener {


        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            final View topCard = mTopCard;
            float dx = e2.getX() - e1.getX();
            if (Math.abs(dx) > mTouchSlop &&
                    Math.abs(velocityX) > Math.abs(velocityY) &&
                    Math.abs(velocityX) > mFlingSlop * 3) {
                float targetX = topCard.getX();
                float targetY = topCard.getY();
                long duration = 0;

                mBoundsRect.set(0 - topCard.getWidth() - 100, 0 - topCard.getHeight() - 100, getWidth() + 100, getHeight() + 100);

                while (mBoundsRect.contains((int) targetX, (int) targetY)) {
                    targetX += velocityX / 10;
                    targetY += velocityY / 10;
                    duration += 100;
                }

                duration = Math.min(500, duration);
                // User cardModel = (User) getAdapter().getItem(getChildCount() - 1);


                /*if (cardModel.getOnCardDismissedListener() != null) {
                    if (targetX > 0) {
                        cardModel.getOnCardDismissedListener().onLike();
                    } else {
                        cardModel.getOnCardDismissedListener().onDislike();
                    }
                }*/

                topCard.animate()
                        .setDuration(duration)
                        .alpha(.75f)
                        .setInterpolator(new LinearInterpolator())
                        .x(targetX)
                        .y(targetY)
                        .rotation(Math.copySign(45, velocityX))
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                removeViewInLayout(topCard);
                                ensureFull();

                                mTopCard = getChildAt(getChildCount() - 1);
                                if (mTopCard != null)
                                    mTopCard.setLayerType(LAYER_TYPE_HARDWARE, null);

                                if (mTopCard == mBottomCard) {
                                    if (dismissListener != null) {
                                        dismissListener.onDismiss(mNextAdapterPosition);
                                    }
                                }
                            }


                            @Override
                            public void onAnimationCancel(Animator animation) {
                                onAnimationEnd(animation);
                            }
                        });
                return true;
            } else
                return false;

        }
    }
}

