package com.example.vicky.a163050072_hw3;

/**
 * Created by vicky on 4/3/18.
 */
import android.content.Intent;
import android.hardware.Sensor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class Record extends Fragment {
    EditText label;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.record, container, false);
        label = (EditText)rootView.findViewById(R.id.txtLabel);

        final Switch onOffSwitch = (Switch)rootView.findViewById(R.id.sensorOnOffSwitch);
        onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (onOffSwitch.isPressed()) {
                    Log.v("Switch Statev=", ""+isChecked);
                    if(isChecked){
                        Log.v("Switch ispressed1=", ""+isChecked);
                        Sensors sensorsFragement = (Sensors)getFragmentManager().findFragmentByTag("android:switcher:" + R.id.container + ":" + 1);
                        Login loginFragment = (Login)getFragmentManager().findFragmentByTag("android:switcher:" + R.id.container + ":" + 0);
                        Intent serviceIntent = new Intent(getActivity(),SensorListener.class);
                        serviceIntent.putExtra("sensorIds",sensorsFragement.getCheckedSensors()); //3-> both the sensor
                        serviceIntent.putExtra("label",label.getText().toString());
                        serviceIntent.putExtra("userInfo",loginFragment.getUserInfo());
                        getActivity().startService(serviceIntent);
                        Log.v("Switch ispressed2=", ""+isChecked);
                    }else{
                        Log.v("Swside ispressed vicky=", ""+isChecked);
                        getActivity().stopService(new Intent(getActivity(),SensorListener.class));
                    }
                }
            }

        });

        return rootView;
    }
}
