package com.example.vicky.a163050072_hw3;

/**
 * Created by vicky on 4/3/18.
 */
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Login extends Fragment {
    ViewPager viewPager;
    EditText txtFirstName,txtLastName,txtMobile,txtEmail,txtAge;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    RadioButton rdoGender;
    String firstName,lastName,mobile,email,age,gender;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.login, container, false);
        txtFirstName = (EditText)rootView.findViewById(R.id.txtFirstName);
        txtLastName = (EditText)rootView.findViewById(R.id.txtLastName);
        txtMobile = (EditText)rootView.findViewById(R.id.txtMobile);
        txtEmail = (EditText)rootView.findViewById(R.id.txtEmail);
        txtAge = (EditText)rootView.findViewById(R.id.txtAge);
        radioGroup = (RadioGroup)rootView.findViewById(R.id.radioGroup);
        Button btn = (Button) rootView.findViewById(R.id.btnSubmit);
        viewPager = (ViewPager) getActivity().findViewById(R.id.container);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                boolean flg = false;
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId!=-1)
                    radioButton = (RadioButton) rootView.findViewById(selectedId);
                int rid = radioGroup.getCheckedRadioButtonId();
                if(!(txtFirstName.getText().toString().length()!=0 && txtLastName.getText().toString().length()!=0 && txtEmail.getText().toString().length()!=0 &&
                        txtMobile.getText().toString().length()!=0 && txtAge.getText().toString().length()!=0 && selectedId !=-1))
                    flg = true;
                if(flg){
                    Toast toast=Toast.makeText(getActivity(),"plz fill up all fields!", Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show();
                }
                else
                    viewPager.setCurrentItem(1);
            }
        });
        return rootView;
    }
    public String getUserInfo(){
        return txtFirstName.getText().toString()+","+txtLastName.getText().toString()+","+txtMobile.getText().toString()+","
                +txtEmail.getText().toString()+","+txtAge.getText().toString()+","+radioButton.getText()+"\n";


    }
}
