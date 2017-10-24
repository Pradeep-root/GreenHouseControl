package com.example.firenear.greenhousecontrol.ui.scan;


import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.firenear.greenhousecontrol.GreenHouseApp;
import com.example.firenear.greenhousecontrol.R;
import com.example.firenear.greenhousecontrol.ui.webhelp.WebHelpFragment;
import com.example.firenear.greenhousecontrol.ui.webhelp.WebHelpListAdapter;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothService;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothStatus;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScanFragment extends Fragment {
    BluetoothService service;
    RecyclerView recyclerView;
    ScanAdapter scanAdapter;
    private ArrayList<BluetoothDevice> bluetoothDevices;

    public ScanFragment() {
        // Required empty public constructor
        bluetoothDevices = new ArrayList<>();
    }

    public static ScanFragment newInstance() {
        ScanFragment fragment = new ScanFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_scan, container, false);
        init(rootView);
        scanBluetooth();
        return rootView;
    }

    private void scanBluetooth() {
       service  = new GreenHouseApp().getBlueSerialDefault(getContext());
        service.startScan();

        service.setOnScanCallback(new BluetoothService.OnBluetoothScanCallback() {
            @Override
            public void onDeviceDiscovered(BluetoothDevice bluetoothDevice, int i) {
                Log.d("onDeviceDiscovered", bluetoothDevice.getName()+" "+bluetoothDevice.getAddress());
                bluetoothDevices.add(bluetoothDevice);
            }

            @Override
            public void onStartScan() {

            }

            @Override
            public void onStopScan() {
                Log.d("onStopScan", "onStopScan");
                loadData(bluetoothDevices);
            }
        });
    }

    private void init(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_scan_list);
    }

    public void loadData(ArrayList<BluetoothDevice> bluetoothDevices){
        scanAdapter = new ScanAdapter(bluetoothDevices);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(scanAdapter);
    }

}
