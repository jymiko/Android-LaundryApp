package com.example.miko.laundryonline;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView view_history;
    TransactionAdapter adapter;
    private SQLiteDatabase mDb;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        dbHelper = new DatabaseHelper(this);
        mDb = dbHelper.getReadableDatabase();

        view_history = (RecyclerView)findViewById(R.id.view_history);
        adapter = new TransactionAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        view_history.setLayoutManager(linearLayoutManager);
        view_history.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        swapData();
    }

    private void swapData(){
        adapter.swapCursor(getAllHistory());
        adapter.notifyDataSetChanged();
    }

    private Cursor getAllHistory(){
        return mDb.query(
                DatabaseContact.TransaksiEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
}
