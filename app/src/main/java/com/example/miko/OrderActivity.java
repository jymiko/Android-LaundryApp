package com.example.miko.laundryonline;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class OrderActivity extends AppCompatActivity {

    LinearLayout hide_button;
    Calendar calendar_order;
    DatePickerDialog.OnDateSetListener date_order;
    RadioGroup groupone;
    RadioButton radioreguler,radioexpress;

    EditText tgl_order,wkt_order;
    TextView kantong,bed,karpet,changetext,changetext2,changetext3,total_bayar;
    Button btn_process,btn_plus,btn_plus2,btn_plus3,btn_minus,btn_minus2,btn_minus3;

    int jumlah=0;
    int jumlah2=0;
    int jumlah3=0;

    float pricekantong = 20000;
    float pricebed = 25000;
    float pricekarpet = 40000;

    float buykantong =0;
    float buybed=0;
    float buykarpet=0;

    float total;

    View rootView;

    private DatabaseHelper dbHelper;
    private SQLiteDatabase mDb;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        rootView = (View) findViewById(R.id.rootView);
        dbHelper = new DatabaseHelper(this);
        mDb = dbHelper.getWritableDatabase();

        calendar_order = Calendar.getInstance();

        date_order = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                calendar_order.set(Calendar.YEAR,year);
                calendar_order.set(Calendar.MONTH,monthOfYear);
                calendar_order.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel();
            }
        };

        tgl_order = (EditText)findViewById(R.id.tgl_order);
        tgl_order.setTextIsSelectable(true);
        tgl_order.setFocusable(false);
        tgl_order.setKeyListener(null);
        tgl_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(OrderActivity.this, date_order, calendar_order
                        .get(Calendar.YEAR), calendar_order.get(Calendar.MONTH),
                        calendar_order.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        wkt_order = (EditText)findViewById(R.id.wkt_order);
        wkt_order.setTextIsSelectable(true);
        wkt_order.setFocusable(false);
        wkt_order.setKeyListener(null);
        wkt_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(OrderActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        wkt_order.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });




        kantong = (TextView)findViewById(R.id.kantong);
        bed = (TextView)findViewById(R.id.bed);
        karpet = (TextView)findViewById(R.id.karpet);
        changetext = (TextView)findViewById(R.id.changetext);
        changetext2 = (TextView)findViewById(R.id.changetext2);
        changetext3 = (TextView)findViewById(R.id.changetext3);
        total_bayar = (TextView)findViewById(R.id.total_bayar);

        btn_plus = (Button)findViewById(R.id.btn_plus);
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumlah++;

                if(changetext.getText().toString().matches("") || changetext2 .getText().toString().matches("") || changetext3.getText().toString().matches("")) {
                total_bayar.setText("0");
                }else{
                    changetext.setText(Integer.toString(jumlah));
                    buykantong = jumlah * pricekantong;
                    hitungTotal();
                }
            }
        });

        btn_minus = (Button)findViewById(R.id.btn_minus);
        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumlah--;
            if(jumlah <0){
                jumlah=0;
            }
            else if (changetext.getText().toString().matches("") || changetext2 .getText().toString().matches("") || changetext3.getText().toString().matches("")) {
                total_bayar.setText("0");
            }else {
                changetext.setText(Integer.toString(jumlah));
                buykantong = jumlah * pricekantong;
                hitungTotal();
            }
            }
        });

        btn_plus2 = (Button)findViewById(R.id.btn_plus2);
        btn_plus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumlah2++;
                changetext2.setText(Integer.toString(jumlah2));
                 if (changetext.getText().toString().matches("") || changetext2 .getText().toString().matches("") || changetext3.getText().toString().matches("")) {
                    total_bayar.setText("0");
                }else {
                    buybed = jumlah2 * pricebed;
                    hitungTotal();
                }
            }
        });

        btn_minus2 = (Button)findViewById(R.id.btn_minus2);
        btn_minus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumlah2--;
                if(jumlah2 <0){
                    jumlah2=0;
                }
                else if (changetext.getText().toString().matches("") || changetext2 .getText().toString().matches("") || changetext3.getText().toString().matches("")) {
                    total_bayar.setText("0");
                }else {
                    changetext2.setText(Integer.toString(jumlah2));
                    buybed = jumlah2 * pricebed;
                    hitungTotal();
                }
            }
        });

        btn_plus3 = (Button)findViewById(R.id.btn_plus3);
        btn_plus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumlah3++;
                changetext3.setText(Integer.toString(jumlah3));
                if (changetext.getText().toString().matches("") || changetext2 .getText().toString().matches("") || changetext3.getText().toString().matches("")) {
                    total_bayar.setText("0");
                }else {
                    buykarpet = jumlah3 * pricekarpet;
                    hitungTotal();
                }
            }
        });

        btn_minus3 = (Button)findViewById(R.id.btn_minus3);
        btn_minus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumlah3--;
                if(jumlah3 <0){
                    jumlah3=0;
                }
                else if (changetext.getText().toString().matches("") || changetext2 .getText().toString().matches("") || changetext3.getText().toString().matches("")) {
                    total_bayar.setText("0");
                }else {
                    changetext3.setText(Integer.toString(jumlah3));
                    buykarpet = jumlah3 * pricekarpet;
                   hitungTotal();
                }
            }
        });

        groupone = (RadioGroup)findViewById(R.id.groupone);
        radioreguler = (RadioButton)findViewById(R.id.radioreguler);
        radioexpress = (RadioButton)findViewById(R.id.radioexpress);

        groupone.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(radioreguler.isChecked()==true){
                    kantong.setVisibility(View.VISIBLE);
                    changetext.setVisibility(View.VISIBLE);
                    btn_plus.setVisibility(View.VISIBLE);
                    btn_minus.setVisibility(View.VISIBLE);
                    bed.setVisibility(View.VISIBLE);
                    changetext2.setVisibility(View.VISIBLE);
                    btn_plus2.setVisibility(View.VISIBLE);
                    btn_minus2.setVisibility(View.VISIBLE);
                    karpet.setVisibility(View.VISIBLE);
                    changetext3.setVisibility(View.VISIBLE);
                    btn_plus3.setVisibility(View.VISIBLE);
                    btn_minus3.setVisibility(View.VISIBLE);
                }else{
                    kantong.setVisibility(View.VISIBLE);
                    changetext.setVisibility(View.VISIBLE);
                    btn_plus.setVisibility(View.VISIBLE);
                    btn_minus.setVisibility(View.VISIBLE);
                    bed.setVisibility(View.GONE);
                    changetext2.setVisibility(View.GONE);
                    btn_plus2.setVisibility(View.GONE);
                    btn_minus2.setVisibility(View.GONE);
                    karpet.setVisibility(View.GONE);
                    changetext3.setVisibility(View.GONE);
                    btn_plus3.setVisibility(View.GONE);
                    btn_minus3.setVisibility(View.GONE);
                }
            }
        });

        btn_process = (Button)findViewById(R.id.btn_process);
        btn_process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savetoDatabase();
            }
        });

    }

    private void hitungTotal(){
        total=buykantong+buybed+buykarpet;
        total_bayar.setText(Float.toString(total));
    }

    private void savetoDatabase(){
        String tanggal = tgl_order.getText().toString();
        String waktu = wkt_order.getText().toString();
        String kantong = changetext.getText().toString();
        String bed = changetext2.getText().toString();
        String karpet = changetext3.getText().toString();
        String total = total_bayar.getText().toString();

        if(!TextUtils.isEmpty(tanggal)
                && !TextUtils.isEmpty(waktu)
                && !TextUtils.isEmpty(kantong)
                && !TextUtils.isEmpty(bed)
                && !TextUtils.isEmpty(karpet)
                && !TextUtils.isEmpty(total)){

            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseContact.TransaksiEntry.COLOUMN_TANGGAL,tanggal);
            contentValues.put(DatabaseContact.TransaksiEntry.COLOUMN_JAM,waktu);
            contentValues.put(DatabaseContact.TransaksiEntry.COLOUMN_KANTONG,kantong);
            contentValues.put(DatabaseContact.TransaksiEntry.COLOUMN_BED,bed);
            contentValues.put(DatabaseContact.TransaksiEntry.COLOUMN_KARPET,karpet);
            contentValues.put(DatabaseContact.TransaksiEntry.COLOUMN_TOTAL,total);

            long result = mDb.insert(
                    DatabaseContact.TransaksiEntry.TABLE_NAME,
                    null,
                    contentValues
            );

            if(result > 0){
                finish();
            }else {
                Snackbar snackbar = Snackbar.make(
                        rootView,
                        "GAGAL",
                        Snackbar.LENGTH_LONG
                );
                snackbar.show();
            }
        } else {
            Snackbar snackbar = Snackbar.make(
                    rootView,
                    "Silahkan isi form terlebih dahulu",
                    Snackbar.LENGTH_LONG
            );
            snackbar.show();
        }

    }

    private void updateLabel(){
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tgl_order.setText(sdf.format(calendar_order.getTime()));
    }

}
