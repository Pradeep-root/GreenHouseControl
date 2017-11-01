package com.example.firenear.greenhousecontrol.ui.operate;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.firenear.greenhousecontrol.GreenHouseApp;
import com.example.firenear.greenhousecontrol.R;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothService;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothStatus;

/**
 * A simple {@link Fragment} subclass.
 */
public class OperateFragment extends Fragment {


    private BluetoothService service;

    public OperateFragment() {
        // Required empty public constructor
        service  = new GreenHouseApp().getBlueSerialDefault(getActivity());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_operate, container, false);
        service.setOnEventCallback(new BluetoothService.OnBluetoothEventCallback() {
            @Override
            public void onDataRead(byte[] bytes, int i) {
                Log.d("onDataRead", "onDataRead");
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

            }
        });
        return rootView;
    }

}
