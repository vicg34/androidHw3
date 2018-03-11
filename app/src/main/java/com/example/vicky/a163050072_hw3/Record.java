package com.example.vicky.a163050072_hw3;

/**
 * Created by vicky on 4/3/18.
 */
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

public class Record extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.record, container, false);

        final Switch onOffSwitch = (Switch)rootView.findViewById(R.id.sensorOnOffSwitch);
        onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (onOffSwitch.isPressed()) {
                    Log.v("Switch Statev=", ""+isChecked);
                    if(isChecked){
                        Log.v("Switch side ispressed1=", ""+isChecked);
                        getActivity().startService(new Intent(getActivity(),SensorListener.class));
                        Log.v("Switch side ispressed2=", ""+isChecked);
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
