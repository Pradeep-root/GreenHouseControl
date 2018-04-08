package com.example.firenear.greenhousecontrol.ui.operate;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firenear.greenhousecontrol.GreenHouseApp;
import com.example.firenear.greenhousecontrol.R;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothService;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothStatus;

import java.io.UnsupportedEncodingException;

/**
 * A simple {@link Fragment} subclass.
 */
public class OperateFragment extends Fragment {


    private BluetoothService service;
    private TextView textTemperature;
    private TextView textHumidity;
    private TextView textSoilMoisture;

    private Switch aSwitchAutoManual;
    private Switch aSwitchFan;
    private Switch aSwitchLight;
    private Switch aSwitchPump;
    private Switch aSwitchFlip;
    private String switchCode = "0000";
    private SharedPreferences sharedPreferences;
    boolean autoMode = false;
    private TextView textAutoMode;

    public OperateFragment() {
        // Required empty public constructor
        service  = new GreenHouseApp().getBlueSerialDefault(getActivity());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        sharedPreferences = getActivity().getSharedPreferences("LoginPersi",getActivity().MODE_PRIVATE);
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_operate, container, false);
        init(rootView);
        event();
        textAutoMode.setText(sharedPreferences.getString("AutoType",""));
        action();
        return rootView;
    }

    private void action() {
        aSwitchAutoManual.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                autoMode = isChecked;
              if(isChecked){
                  service.write("*".getBytes());
                  aSwitchLight.setEnabled(true);
                  aSwitchFan.setEnabled(true);
                  aSwitchFlip.setEnabled(true);
                  aSwitchPump.setEnabled(true);
                  textAutoMode.setVisibility(View.INVISIBLE);
              }else {
                  aSwitchLight.setEnabled(false);
                  aSwitchFan.setEnabled(false);
                  aSwitchFlip.setEnabled(false);
                  aSwitchPump.setEnabled(false);
                  textAutoMode.setVisibility(View.VISIBLE);

              }
            }
        });

        aSwitchFlip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    StringBuilder newCode = new StringBuilder(switchCode);
                    newCode.setCharAt(0, '0');
                    String stringCode = newCode.toString();
                    stringCode = "w"+stringCode;
                    service.write(stringCode.getBytes());

                }else {
                    StringBuilder newCode = new StringBuilder(switchCode);
                    newCode.setCharAt(0, '1');
                    String stringCode = newCode.toString();
                    stringCode = "w"+stringCode;
                    service.write(stringCode.getBytes());
                }
            }
        });

        aSwitchLight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    StringBuilder newCode = new StringBuilder(switchCode);
                    newCode.setCharAt(1, '0');
                    String stringCode = newCode.toString();
                    stringCode = "w"+stringCode;
                    service.write(stringCode.getBytes());
                }else {
                    StringBuilder newCode = new StringBuilder(switchCode);
                    newCode.setCharAt(1, '1');
                    String stringCode = newCode.toString();
                    stringCode = "w"+stringCode;
                    service.write(stringCode.getBytes());
                }
            }
        });

        aSwitchFan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    StringBuilder newCode = new StringBuilder(switchCode);
                    newCode.setCharAt(2, '0');
                    String stringCode = newCode.toString();
                    stringCode = "w"+stringCode;
                    service.write(stringCode.getBytes());
                }else {
                    StringBuilder newCode = new StringBuilder(switchCode);
                    newCode.setCharAt(2, '1');
                    String stringCode = newCode.toString();
                    stringCode = "w"+stringCode;
                    service.write(stringCode.getBytes());
                }
            }
        });


        aSwitchPump.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    StringBuilder newCode = new StringBuilder(switchCode);
                    newCode.setCharAt(3, '0');
                    String stringCode = newCode.toString();
                    stringCode = "w"+stringCode;
                    service.write(stringCode.getBytes());
                }else {
                    StringBuilder newCode = new StringBuilder(switchCode);
                    newCode.setCharAt(3, '1');
                    String stringCode = newCode.toString();
                    stringCode = "w"+stringCode;
                    service.write(stringCode.getBytes());
                }
            }
        });


    }

    private void init(View rootView) {
        textTemperature = (TextView) rootView.findViewById(R.id.textTemperature);
        textHumidity = (TextView) rootView.findViewById(R.id.textHumidity);
        textSoilMoisture = (TextView) rootView.findViewById(R.id.textSoil);
        aSwitchAutoManual = (Switch) rootView.findViewById(R.id.switchAutoManual);
        aSwitchFan = (Switch) rootView.findViewById(R.id.switchFan);
        aSwitchFlip = (Switch) rootView.findViewById(R.id.switchFlip);
        aSwitchLight = (Switch) rootView.findViewById(R.id.switchLight);
        aSwitchPump = (Switch) rootView.findViewById(R.id.switchPump);
        textAutoMode = (TextView) rootView.findViewById(R.id.textAutomode);

        aSwitchLight.setEnabled(false);
        aSwitchFan.setEnabled(false);
        aSwitchFlip.setEnabled(false);
        aSwitchPump.setEnabled(false);
    }

    private void event() {

        service.setOnEventCallback(new BluetoothService.OnBluetoothEventCallback() {
            @Override
            public void onDataRead(byte[] bytes, int i) {
                try {
                    String text = new String(bytes, "UTF-8");
                    if(text.contains("s")){
                        String triText = text.replace("-", "");
                        triText = triText.replace("s","");
                        String[] sensorValues = triText.split(",");
                        textSoilMoisture.setText(sensorValues[0]+"%");
                        textHumidity.setText(sensorValues[1]+"%");
                        textTemperature.setText(sensorValues[2]+" 'C");
                        int temp =  sharedPreferences.getInt("Temp",0);
                        int hum = sharedPreferences.getInt("Hum", 0);
                        int mos = sharedPreferences.getInt("Mos", 0);

                        if(autoMode){
                            if(Integer.parseInt(sensorValues[0]) > temp){

                            }


                        }

                    }else if(text.contains("a")) {
                        switchCode = text.replace("a", "").replace("e", "");
                        if(switchCode.charAt(0) == '0'){
                            aSwitchFlip.setChecked(true);
                        }else {
                            aSwitchFlip.setChecked(false);
                        }

                        if(switchCode.charAt(1) == '0'){
                            aSwitchLight.setChecked(true);
                        }else {
                            aSwitchLight.setChecked(false);
                        }

                        if(switchCode.charAt(2) == '0'){
                            aSwitchFan.setChecked(true);
                        }else {
                            aSwitchFan.setChecked(false);
                        }

                        if(switchCode.charAt(3) == '0'){
                            aSwitchPump.setChecked(true);
                        }else {
                            aSwitchPump.setChecked(false);
                        }
                    }

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onStatusChange(BluetoothStatus bluetoothStatus) {

            }

            @Override
            public void onDeviceName(String s) {

            }

            @Override
            public void onToast(String s) {

            }

            @Override
            public void onDataWrite(byte[] bytes) {
                /*try {+
                    String text = new String(bytes, "UTF-8");
                    Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }*/

            }
        });
    }
}
