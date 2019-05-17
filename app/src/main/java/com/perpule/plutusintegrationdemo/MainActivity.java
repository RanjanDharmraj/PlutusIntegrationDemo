package com.perpule.plutusintegrationdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.perpule.plutusintegrationdemo.Header;


public class MainActivity extends AppCompatActivity {

    private static final String PLUTUS_SMART_ACTION = "com.pinelabs.masterapp.SERVER";
    private static final String PLUTUS_SMART_PACKAGE ="com.pinlabs.masterapp";
    //private static final int MESSAGE_CODE = 1001;
    private static final String BILLING_REQUEST_TAG = "MASTERAPPREQUEST";
    private static final String BILLING_RESPONSE_TAG = "MASTERAPPRESPONSE";

    private Messenger mServerMessage;
    private boolean isBound;

    private int MsgCode=1000;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startPinelabService();

        findViewById(R.id.btn_sale).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MsgCode=1001;
                if(isBound) {
                    startMessenger(requestAPI(MsgCode));
                } else  {
                    Toast.makeText(MainActivity.this,"Service not bound",Toast.LENGTH_SHORT).show();
                    // Handle Error
                }
            }
        });
        
        findViewById(R.id.btn_printdata).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MsgCode=1002;
                //CODE FOR START MESSENGER TO PRINT DATA API
            }
        });
        findViewById(R.id.btn_settlement).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MsgCode=1003;
                //CODE FOR START MESSENGER TO SETTLEMENT API
            }
        });
        findViewById(R.id.btn_getTerminalInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MsgCode=1003;
                //CODE FOR START MESSENGER TO GET TERMINAL INFO API
            }
        });
    }


    void startPinelabService() {

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


    private void startMessenger(String value) {
        Message message = Message.obtain(null, MsgCode);
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
            //convert string to json
            //Gson json=new GsonBuilder();
            //ResponseAPI(json);
        }

    }



    private String requestAPI(int MsgCode){
        switch(MsgCode)
        {
            case 1000:
                Toast.makeText(MainActivity.this,"Choose an API",Toast.LENGTH_SHORT).show();
                break;
            case 1001:
                com.perpule.plutusintegrationdemo.doTransaction.Request request = new com.perpule.plutusintegrationdemo.doTransaction.Request();
                request.setHeader(new Header("DemoAPI1","user1234","1001","1.0"));
                com.perpule.plutusintegrationdemo.doTransaction.DetailRequest detailRequest = new com.perpule.plutusintegrationdemo.doTransaction.DetailRequest();
                detailRequest.setTransactionType(4006);
                detailRequest.setBillingRefNo("TXN111111");
                detailRequest.setPaymentAmount("12345678");
                detailRequest.setBankCode("01");
                detailRequest.setInvoiceNo("00000001");
                request.setDetailRequest(detailRequest);
                return new GsonBuilder().create().toJson(request);
            case 1002:
                Toast.makeText(MainActivity.this,"Not configured API",Toast.LENGTH_SHORT).show();
                break;
            case 1003:
                Header header = new Header("DemoAPI3","user1234","1003","1.0");
                Toast.makeText(MainActivity.this,"Sent Settlement request",Toast.LENGTH_SHORT).show();
                return new GsonBuilder().create().toJson(header);
            case 1004:
                Header header2 = new Header("DemoAPI4","user1234","1004","1.0");
                Toast.makeText(MainActivity.this,"Sent Terminal Info request",Toast.LENGTH_SHORT).show();
                return new GsonBuilder().create().toJson(header2);
            default:
                Toast.makeText(MainActivity.this,"ERROR in api chosen",Toast.LENGTH_SHORT).show();
                break;
        }

        return null;
    }

    private void ResponseAPI(){
        switch(MsgCode){
            case 1000:
                Toast.makeText(MainActivity.this,"Choose an API",Toast.LENGTH_SHORT).show();
                break;
            case 1001:
                com.perpule.plutusintegrationdemo.doTransaction.Response response= new com.perpule.plutusintegrationdemo.doTransaction.Response();
                //converrt received json to above class type
                String billrefno = response.getDetailResponse().getPayments().get(0).getBillingRefNo();
                TextView text= findViewById(R.id.output);
                text.setText(billrefno);
                break;
            case 1002:
                Toast.makeText(MainActivity.this,"Not configured API",Toast.LENGTH_SHORT).show();
                break;
            case 1003:
                Toast.makeText(MainActivity.this,"Nothing to respond",Toast.LENGTH_SHORT).show();
                break;
            case 1004:
                Toast.makeText(MainActivity.this,"Nothing to respond",Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(MainActivity.this,"Nothing to respond",Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
