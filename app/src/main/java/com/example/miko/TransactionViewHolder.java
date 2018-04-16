package com.example.miko.laundryonline;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by miko on 11/28/17.
 */

public class TransactionViewHolder extends RecyclerView.ViewHolder {

    public TextView tv_tanggal,tv_jam,tv_paket,tv_kantong,tv_bed,tv_karpet,tv_total;

    public TransactionViewHolder(View itemView) {
        super(itemView);
        tv_tanggal = (TextView)itemView.findViewById(R.id.tv_tanggal);
        tv_jam = (TextView)itemView.findViewById(R.id.tv_jam);
        tv_kantong = (TextView)itemView.findViewById(R.id.tv_kantong);
        tv_bed = (TextView)itemView.findViewById(R.id.tv_bed);
        tv_karpet = (TextView)itemView.findViewById(R.id.tv_karpet);
        tv_total = (TextView)itemView.findViewById(R.id.tv_total);
    }
}
