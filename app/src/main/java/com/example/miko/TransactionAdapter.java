package com.example.miko.laundryonline;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by miko on 11/28/17.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionViewHolder> {

    Context mContext;
    Cursor mCursor;

    public TransactionAdapter(Context context){
        this.mContext=context;
    }

    public void swapCursor(Cursor cursor){
        this.mCursor=cursor;
    }

    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_view,parent,false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TransactionViewHolder holder, int position) {
        mCursor.moveToPosition(position);
        int tanggalColoumnIndex = mCursor.getColumnIndex(DatabaseContact.TransaksiEntry.COLOUMN_TANGGAL);
        int jamColoumnIndex = mCursor.getColumnIndex(DatabaseContact.TransaksiEntry.COLOUMN_JAM);
        int kantongColoumnIndex = mCursor.getColumnIndex(DatabaseContact.TransaksiEntry.COLOUMN_KANTONG);
        int bedColoumnIndex = mCursor.getColumnIndex(DatabaseContact.TransaksiEntry.COLOUMN_BED);
        int karpetColoumnIndex = mCursor.getColumnIndex(DatabaseContact.TransaksiEntry.COLOUMN_KARPET);
        int totalColoumnIndex = mCursor.getColumnIndex(DatabaseContact.TransaksiEntry.COLOUMN_TOTAL);

        String tanggal = mCursor.getString(tanggalColoumnIndex);
        String jam = mCursor.getString(jamColoumnIndex);
        String kantong = mCursor.getString(kantongColoumnIndex);
        String bed = mCursor.getString(bedColoumnIndex);
        String karpet = mCursor.getString(karpetColoumnIndex);
        String total = mCursor.getString(totalColoumnIndex);

        holder.tv_tanggal.setText(tanggal);
        holder.tv_jam.setText(jam);
        holder.tv_kantong.setText(kantong);
        holder.tv_bed.setText(bed);
        holder.tv_karpet.setText(karpet);
        holder.tv_total.setText(total);
    }

    @Override
    public int getItemCount() {
        if(mCursor == null){
        return 0;
        }
        return mCursor.getCount();
    }
}
