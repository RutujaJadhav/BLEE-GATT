package com.example.android.bluetoothlegatt;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import static com.example.android.bluetoothlegatt.DeviceControlActivity.mBluetoothLeService;
import static java.lang.String.*;

public class SelectSensorsActivity extends Activity{

    private final static String TAG = SelectSensorsActivity.class.getSimpleName();
    Switch temp,alt,acc,light_;
    EditText freq_temp,freq_alt,freq_acc,freq_light;
    int j,modval;

   public static Queue<String> strstosend = new LinkedList<>();

    DeviceControlActivity DCA= new DeviceControlActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_sensors);

        temp= findViewById(R.id.temperature);
        alt= findViewById(R.id.altitude);
        acc= findViewById(R.id.accelerometer);
        light_= findViewById(R.id.light);

        freq_acc= findViewById(R.id.acc_freq);
        freq_alt= findViewById(R.id.alt_freq);
        freq_light= findViewById(R.id.light_freq);
        freq_temp= findViewById(R.id.temp_freq);
    }


    public void temptosend(View view) {
        freq_temp.setVisibility(View.VISIBLE);
    }

    public void alttosend(View view) {
        freq_alt.setVisibility(View.VISIBLE);
    }

    public void acctosend(View view) {
        freq_acc.setVisibility(View.VISIBLE);
    }

    public void lighttosend(View view) {
        freq_light.setVisibility(View.VISIBLE);
    }

    public void finalSendData(View view) {
        String f_acc,f_alt,f_light,f_temp;
        f_temp=freq_temp.getText().toString();
        f_acc=freq_acc.getText().toString();
        f_alt=freq_alt.getText().toString();
        f_light=freq_light.getText().toString();


        if (!f_temp.matches("")){

            MainSelectServiceActivity.getVartosend=MainSelectServiceActivity.getVartosend+";"+f_temp;
        }
        else {MainSelectServiceActivity.getVartosend=MainSelectServiceActivity.getVartosend+";0";}

        if (!f_alt.matches("")){
            MainSelectServiceActivity.getVartosend=MainSelectServiceActivity.getVartosend+";"+f_alt;
        }
        else {MainSelectServiceActivity.getVartosend=MainSelectServiceActivity.getVartosend+";0";}

        if (!f_acc.matches("")){
            MainSelectServiceActivity.getVartosend=MainSelectServiceActivity.getVartosend+";"+f_acc;
        }
        else {MainSelectServiceActivity.getVartosend=MainSelectServiceActivity.getVartosend+";0";}

        if (!f_light.matches("")){
            MainSelectServiceActivity.getVartosend=MainSelectServiceActivity.getVartosend+";"+f_light+">e";
        }
        else {MainSelectServiceActivity.getVartosend=MainSelectServiceActivity.getVartosend+";0"+">e";}


        Log.w(TAG,"THE LENGTH OF STRING IS");
        int length=MainSelectServiceActivity.getVartosend.length();
        Log.w(TAG, valueOf(length));

        modval=length%10;
        Log.w(TAG,"FINAL STRING TO SEND");
        Log.w(TAG,MainSelectServiceActivity.getVartosend);

        int end=0;
        for ( j=0;j<length;j=j+10) {
            end = Math.min(j+10, length);
            if (j==0) {
                globalvars.finalvartosend=MainSelectServiceActivity.getVartosend.substring(0,10);
                mBluetoothLeService.writeToCharacteristic();

            }
            else {


            strstosend.add(MainSelectServiceActivity.getVartosend.substring(j,end));
            Log.w(TAG,"STRING IN PIECES");
            Log.w(TAG,globalvars.finalvartosend);
          //  mBluetoothLeService.writeToCharacteristic();


        }
        }


       Intent intent = new Intent(getApplicationContext(), finalActivity.class);
        startActivity(intent);
    }

}
