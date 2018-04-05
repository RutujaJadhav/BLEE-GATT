package com.example.android.bluetoothlegatt;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

public class MainSelectServiceActivity extends Activity {

    private static final String TAG="";

    Switch wifimqtt,LORA;
    EditText SSID,PASSWORD,MQTTSERVER,MQTTPORT,APPKEY,APPSESSIONKEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_select_service);

        wifimqtt= (Switch)findViewById(R.id.wifi_mqtt);
        LORA=(Switch)findViewById(R.id.lora);
        SSID=(EditText)findViewById(R.id.ssid);
        PASSWORD=(EditText)findViewById(R.id.password);
        MQTTSERVER=(EditText)findViewById(R.id.mqttserver);
        MQTTPORT=(EditText)findViewById(R.id.mqttport);
        APPKEY=(EditText)findViewById(R.id.appkey);
        APPSESSIONKEY=(EditText)findViewById(R.id.appsessionkey);
    }

    public void loraServices(View view) {

        APPKEY.setVisibility(View.VISIBLE);
        APPSESSIONKEY.setVisibility(View.VISIBLE);


        Log.w(TAG,"Value to be sent for lora");
        Log.w(TAG, String.valueOf(globalvars.getVartosend));
    }

    public void wifimqttServices(View view) {

        SSID.setVisibility(View.VISIBLE);
        PASSWORD.setVisibility(View.VISIBLE);
        MQTTSERVER.setVisibility(View.VISIBLE);
        MQTTPORT.setVisibility( View.VISIBLE);
    }

    public void toSensors(View view) {

        String ssid_send,password_send,mqttserver_send,mqttport_send;
        String appkey_send, appsessionkey_send;

        ssid_send=SSID.getText().toString();
        password_send=PASSWORD.getText().toString();
        mqttserver_send=MQTTSERVER.getText().toString();
        mqttport_send=MQTTPORT.getText().toString();

        appkey_send=APPKEY.getText().toString();
        appsessionkey_send=APPSESSIONKEY.getText().toString();

        if (appkey_send!=null){
            globalvars.getVartosend=globalvars.getVartosend+";0;0;1;0;0;0;0";

            globalvars.getVartosend=globalvars.getVartosend+";"+appkey_send;
            globalvars.getVartosend=globalvars.getVartosend+";"+appsessionkey_send;
            Log.d(TAG,"string to be sent");
            // int w = Log.w((globalvars.getVartosend));
        }

         if (ssid_send!=null){
             globalvars.getVartosend=globalvars.getVartosend+";1;1;0";

            globalvars.getVartosend=globalvars.getVartosend+";"+ssid_send;
            globalvars.getVartosend=globalvars.getVartosend+";"+password_send;
            globalvars.getVartosend=globalvars.getVartosend+";"+mqttserver_send;
            globalvars.getVartosend=globalvars.getVartosend+";"+mqttport_send+";0;0";
        }

        Log.w(TAG,"Value to be sent");
        Log.w(TAG, String.valueOf(globalvars.getVartosend));

        Intent intent = new Intent(getApplicationContext(), SelectSensorsActivity.class);
        startActivity(intent);
    }



}
