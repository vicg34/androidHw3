package com.example.vicky.a163050072_hw3;

/**
 * Created by vicky on 4/3/18.
 */
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

public class Sensors extends Fragment {
    CheckBox cbAcce,cbGps;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sensors, container, false);
        cbGps=(CheckBox)rootView.findViewById(R.id.checkbox_gps);
        cbAcce = (CheckBox)rootView.findViewById(R.id.checkbox_accelerometer);
        if(!runtime_permissions())
            enable_checkbox(cbGps,true);
        return rootView;
    }
    public String getCheckedSensors(){
        String res;
        if(cbAcce.isChecked() && cbGps.isChecked())
            res = "3";
        else if(cbAcce.isChecked())
            res = "1";
            else if(cbGps.isChecked())
                    res = "2";
                else
                    res = "4";
        return res;

    }
    private boolean runtime_permissions() {
        if(Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},100);

            return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100){
            if( grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                enable_checkbox(cbGps,true);
            }else {
                enable_checkbox(cbGps,false);
            }
        }
    }
    private void enable_checkbox(CheckBox cb,boolean doEnable) {
        if(doEnable)
            cb.setEnabled(true);
        else
            cb.setEnabled(false);

    }
}