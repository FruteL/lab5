package com.example.lab5_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //Блок Ресиверов и запуски оповещений

    //ШС дял камеры
    private final BroadcastReceiver broadcastReceiverForCam = new BroadcastReceiver() {

        @Override
        public void onReceive(Context c, Intent intent) {
            camera();
        }
    };
    private void camera() {
        CameraAlert notice = new CameraAlert();
        notice.show(getSupportFragmentManager(),"cam");
    }
    //ШС для батареи
    private final BroadcastReceiver broadcastReceiverForBatt = new BroadcastReceiver() {

        @Override
        public void onReceive(Context c, Intent intent) {
            if (intent.getAction().equals("android.intent.action.BATTERY_LOW")) {
                battery();
            }
        }
    };
    private void battery() {
        BetteryAlert notice = new BetteryAlert();
        notice.show(getSupportFragmentManager(),"battery");
    }
    //ШС для АвиоРежима
    private final BroadcastReceiver broadcastReceiverForAir = new BroadcastReceiver() {

        @Override
        public void onReceive(Context c, Intent intent) {
            boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);
            airplaneMode(isAirplaneModeOn);
        }
    };
    private void airplaneMode(boolean AirplaneModeOn) {
        AirAlert notice = new AirAlert (AirplaneModeOn);
        notice.show(getSupportFragmentManager(),"airmode");
    }

    @Override
    protected void onStart() {
        super.onStart();
        //блок интентов для оповещений на определенные собития
        IntentFilter intentCameraFilter = new IntentFilter(Intent.ACTION_CAMERA_BUTTON);
        Intent intentCamera = registerReceiver(broadcastReceiverForCam, intentCameraFilter);
        IntentFilter intentBatteryFilter = new IntentFilter(Intent.ACTION_BATTERY_LOW);
        Intent intentBattery = registerReceiver(broadcastReceiverForBatt, intentBatteryFilter);
        IntentFilter intentAirFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        Intent intentAir = registerReceiver(broadcastReceiverForAir, intentAirFilter);
    }

    @Override
    protected void onStop() {
        //Остановка оповещений
        super.onStop();

        if(broadcastReceiverForAir != null){
            unregisterReceiver(broadcastReceiverForAir);
        }
        if(broadcastReceiverForCam != null){
            unregisterReceiver(broadcastReceiverForCam);
        }
        if (broadcastReceiverForBatt != null){
            unregisterReceiver(broadcastReceiverForBatt);
        }
    }
}
