package com.perpule.plutusintegrationdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.gson.GsonBuilder;
import com.perpule.plutusintegrationdemo.model.Header;

public class MainActivity extends AppCompatActivity {

    private static final String PLUTUS_SMART_ACTION = "com.pinelabs.masterapp.SERVER";
    private static final String PLUTUS_SMART_PACKAGE ="com.pinlabs.masterapp";
    private static final int MESSAGE_CODE = 1001;
    private static final String BILLING_REQUEST_TAG = "MASTERAPPREQUEST";
    private static final String BILLING_RESPONSE_TAG = "MASTERAPPRESPONSE";

    private Messenger mServerMessage;
    private boolean isBound;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startPinlabService();

        findViewById(R.id.btn_sale).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBound) {
                    startMessenger(getHeader());
                } else  {
                    // Handle Error
                }
            }
        });
    }


    void startPinlabService() {

        Intent intent = new Intent();
        intent.setAction(PLUTUS_SMART_ACTION);
        intent.setPackage(PLUTUS_SMART_PACKAGE);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mServerMessage = new Messenger(service);
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mServerMessage = null;
            isBound = false;
        }
    };

    private String getHeader() {
        Header header = new Header("gfdg", "fdhgg", "hsgdg", "djshdhj");
        return new GsonBuilder().create().toJson(header);
    }

    private void startMessenger(String value) {
        Message message = Message.obtain(null, MESSAGE_CODE);
        Bundle bundle = new Bundle();
        bundle.putString(BILLING_REQUEST_TAG, value);

        try {
            message.replyTo = new Messenger(new IncomingHandler());
            mServerMessage.send(message);
        } catch (RemoteException e){
            e.printStackTrace();
        }
    }

    private class IncomingHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String value = bundle.getString(BILLING_RESPONSE_TAG);

        }

    }

}
