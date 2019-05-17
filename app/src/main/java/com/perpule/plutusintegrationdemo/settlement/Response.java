package com.perpule.plutusintegrationdemo.settlement;

import android.support.v7.app.AppCompatActivity;


public class Response extends  AppCompatActivity  {
    private
    private String AcquierCode;
    private String TID;
    private String MID;
    private long CreditCount;
    private long CreditAmount;
    private long DebitCount;
    private long DebitAmount;
    private Object[] SettlementSummary = new Object[8];


    @Override
    protected void onStart() {
        super.onStart();
        SettlementSummary[0] = new String (""); //
        SettlementSummary[1] = new String (""); //
        SettlementSummary[2] = new String("");  //
        SettlementSummary[3] = new String (""); //
        SettlementSummary[4] = new Long(1);
        SettlementSummary[5] = new Long(1);


    }
}
