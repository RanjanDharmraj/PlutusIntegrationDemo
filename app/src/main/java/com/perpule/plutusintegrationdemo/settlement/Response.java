package com.perpule.plutusintegrationdemo.settlement;

import android.support.v7.app.AppCompatActivity;


public class Response extends  AppCompatActivity  {
    private String BactchName;
    private String AcquierCode;
    private String TID;
    private String MID;
    private long CreditCount;
    private long CreditAmount;
    private long DebitCount;
    private long DebitAmount;
    Object[] arr = new Object[6];


    @Override
    protected void onStart() {
        super.onStart();
        arr[0] = new String("First Pair");
        arr[1] = new Integer(1);
        arr[2] = new String("Second Pair");
        arr[3] = new Integer(2);
        arr[4] = new String("Third Pair");
        arr[5] = new Integer(3);


    }
}
