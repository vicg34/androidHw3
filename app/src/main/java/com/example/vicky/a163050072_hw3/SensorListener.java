package com.example.vicky.a163050072_hw3;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class SensorListener extends Service implements SensorEventListener{
    private SensorManager sensorManager;
    Sensor accelerometer;

    public SensorListener() {
        Log.v("service listener=","constructor");
    }

    public void onCreate(){
        super.onCreate();

        Log.v("inside sensor service","onCreate working");
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);


    }
    @Override
    public void onAccuracyChanged(Sensor sensor,int i){
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent){
        Log.v("inside sensor service","onSensorChanged working");
        Log.d("sensorv",String.valueOf(sensorEvent.values[0])+"  "+String.valueOf(sensorEvent.values[1])+" "+String.valueOf(sensorEvent.values[2]));
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onDestroy() {
        Log.v("inside sensor service","onDestroy method");
        sensorManager.unregisterListener(this, accelerometer);
    }
}
