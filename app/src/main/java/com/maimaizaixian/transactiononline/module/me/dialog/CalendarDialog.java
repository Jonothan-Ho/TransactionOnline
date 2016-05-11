package com.maimaizaixian.transactiononline.module.me.dialog;

import android.app.Activity;
import android.widget.CalendarView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.dialog.BaseDialog;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

/**
 * Created by Administrator on 2015/11/12.
 */
public class CalendarDialog extends BaseDialog {

   // private MaterialCalendarView mCalendarView;
    private OnDateSelectedListener mDateSelectedListener;


    public CalendarDialog(Activity context) {
        super(context);
        if (context instanceof OnDateSelectedListener) {
            mDateSelectedListener = (OnDateSelectedListener) context;
        }
    }

    @Override
    public int getCustomView() {
        return R.layout.dialog_me_calendar;
    }

    @Override
    public void initView() {
       /* mCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        if (mDateSelectedListener != null) {
            mCalendarView.setOnDateChangedListener(mDateSelectedListener);
        }*/
    }
}
