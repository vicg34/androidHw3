package com.example.vicky.a163050072_hw3;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.util.Date;


public class SensorListener extends Service implements SensorEventListener{
    private SensorManager sensorManager;
    Sensor accelerometer;
    private LocationListener listener;
    private LocationManager locationManager;
    String langitude="-",latitude="-",accx="-",accy="-",accz="-",label="-",userInfo="-";

    File path;
    String currentDateTimeString;
    File file;
    FileOutputStream stream = null;
    OutputStreamWriter myOutWriter = null;


    public SensorListener() {
        Log.v("service listener=","constructor");
    }

    public void onCreate(){
        super.onCreate();
        Log.v("inside sensor service","onCreate working");
    }
    public int onStartCommand (Intent intent, int flags, int startId) {
        String sensorsToRun = intent.getStringExtra("sensorIds");
        label = intent.getStringExtra("label");
        userInfo = intent.getStringExtra("userInfo");

        Log.d("sensorToRun",sensorsToRun);
        int intSensorsToRun = Integer.parseInt(sensorsToRun);
        switch(intSensorsToRun){
            case 1:
                registerAccelerometer();
                break;
            case 2:
                registerGps();
                break;
            case 3:
                registerAccelerometer();
                registerGps();
                break;
        }

        path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS);
        currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        file = new File(path, currentDateTimeString+".csv");
        try{
            stream = new FileOutputStream(file,true);
            myOutWriter = new OutputStreamWriter(stream);
        }catch(Exception e){
            Log.d("line no 76:",e.toString());
        }
        try{
            myOutWriter.append(userInfo);
        }catch(Exception e){
            Log.d("line no 81:",e.toString());
        }
        return START_STICKY;
    }
    @Override
    public void onAccuracyChanged(Sensor sensor,int i){
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent){
        Log.v("inside sensor service","onSensorChanged working");
        //Log.d("sensorv",String.valueOf(sensorEvent.values[0])+"  "+String.valueOf(sensorEvent.values[1])+" "+String.valueOf(sensorEvent.values[2]));
        accx = String.valueOf(sensorEvent.values[0]);
        accy = String.valueOf(sensorEvent.values[1]);
        accz = String.valueOf(sensorEvent.values[2]);
        writeCSVFile();

    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    private void registerAccelerometer(){
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void registerGps(){
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //Intent i = new Intent("location_update");
                Log.d("coordinates",location.getLongitude()+" "+location.getLatitude());
                langitude = String.valueOf(location.getLongitude());
                latitude = String.valueOf(location.getLatitude());
                writeCSVFile();
                //sendBroadcast(i);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                //Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //startActivity(i);
            }
        };
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        //noinspection MissingPermission
        //permission end
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000,0,listener);
    }

    private void writeCSVFile(){
        try {
            String temp =  currentDateTimeString+","+langitude+","+latitude+","+accx+","+accy+","+","+accz+","+label+"\n";
            myOutWriter.append(temp);
        } catch(Exception e){
            Log.d("line no 149: ",e.toString());
        }

    }

    @Override
    public void onDestroy() {
        Log.v("inside sensor service","onDestroy method");
        if(sensorManager != null)
            sensorManager.unregisterListener(this, accelerometer);
        //GPS START
        if(locationManager != null){
            //noinspection MissingPermission
            locationManager.removeUpdates(listener);
        }
        //GPS END
        try{
            myOutWriter.close();
            stream.close();
        }catch (Exception e){

        }
    }
}
