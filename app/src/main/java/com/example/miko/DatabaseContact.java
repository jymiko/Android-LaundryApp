package com.example.miko.laundryonline;

import android.provider.BaseColumns;

/**
 * Created by miko on 11/28/17.
 */

public class DatabaseContact {
    public static final class TransaksiEntry implements BaseColumns{
        public static final String TABLE_NAME = "input_data";
        public static final String COLOUMN_TANGGAL = "tanggal";
        public static final String COLOUMN_JAM = "jam";
        public static final String COLOUMN_KANTONG = "kantong";
        public static final String COLOUMN_BED = "bed";
        public static final String COLOUMN_KARPET = "karpet";
        public static final String COLOUMN_TOTAL = "total";
    }
}
