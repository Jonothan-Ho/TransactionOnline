package com.maimaizaixian.transactiononline.module.hall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.adapter.BaseAdapter;
import com.maimaizaixian.transactiononline.module.common.domain.Area;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/11/6.
 */
public class ConditionAreaAdapter extends BaseAdapter<ConditionAreaAdapter.LocalArea, ConditionAreaAdapter.ViewHolder> {


    public ConditionAreaAdapter(Context context, List<ConditionAreaAdapter.LocalArea> source) {
        super(context, source);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_hall_condition_area, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ConditionAreaAdapter.LocalArea area = mDataSource.get(position);
        holder.textProvince.setText(area.getProvince());
        holder.textCity.setText(area.getCity());

        if (position > 0) {
            if (area.getPid() == mDataSource.get(position - 1).getPid()) {
                holder.textProvince.setVisibility(View.GONE);
            } else {
                holder.textProvince.setVisibility(View.VISIBLE);
            }
        } else {
            holder.textProvince.setVisibility(View.VISIBLE);
        }

        if (mItemClickListener != null) {
            holder.textCity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.click(v, position);
                }
            });
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textProvince;
        public TextView textCity;
        public View viewDivider;


        public ViewHolder(View itemView) {
            super(itemView);
            textProvince = (TextView) itemView.findViewById(R.id.tv_province);
            textCity = (TextView) itemView.findViewById(R.id.tv_city);
            viewDivider = itemView.findViewById(R.id.view_divider);
        }
    }


    public static class LocalArea implements Serializable {
        private String province;
        private String city;
        private int pid;
        private int cid;

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }

}
