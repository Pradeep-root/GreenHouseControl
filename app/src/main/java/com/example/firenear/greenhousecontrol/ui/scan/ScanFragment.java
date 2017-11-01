package com.example.firenear.greenhousecontrol.ui.scan;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.firenear.greenhousecontrol.GreenHouseApp;
import com.example.firenear.greenhousecontrol.R;
import com.example.firenear.greenhousecontrol.ui.operate.OperateFragment;
import com.example.firenear.greenhousecontrol.ui.webhelp.WebHelpFragment;
import com.example.firenear.greenhousecontrol.ui.webhelp.WebHelpListAdapter;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothDeviceDecorator;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothService;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothStatus;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScanFragment extends Fragment {
    BluetoothService service;
    RecyclerView recyclerView;
    private ArrayList<BluetoothDevice> bluetoothDevices;
    private BluetoothAdapter mBluetoothAdapter;
    private DeviceItemAdapter mAdapter;

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
        loadData();
        scanBluetooth();
        return rootView;
    }

    private void scanBluetooth() {
       service  = new GreenHouseApp().getBlueSerialDefault(getContext());
        service.startScan();

        service.setOnScanCallback(new BluetoothService.OnBluetoothScanCallback() {
            @Override
            public void onDeviceDiscovered(BluetoothDevice bluetoothDevice, int rssi) {
                Log.d("onDeviceDiscovered ", "onDeviceDiscovered: " + bluetoothDevice.getName() + " - " + bluetoothDevice.getAddress() + " - " + Arrays.toString(bluetoothDevice.getUuids()));
                BluetoothDeviceDecorator dv = new BluetoothDeviceDecorator(bluetoothDevice, rssi);
                int index = mAdapter.getDevices().indexOf(dv);
                if (index < 0) {
                    mAdapter.getDevices().add(dv);
                    mAdapter.notifyItemInserted(mAdapter.getDevices().size() - 1);
                } else {
                    mAdapter.getDevices().get(index).setDevice(bluetoothDevice);
                    mAdapter.getDevices().get(index).setRSSI(rssi);
                    mAdapter.notifyItemChanged(index);
                }
            }

            @Override
            public void onStartScan() {

            }

            @Override
            public void onStopScan() {

            }
        });

        service.setOnEventCallback(new BluetoothService.OnBluetoothEventCallback() {
            @Override
            public void onDataRead(byte[] bytes, int i) {

            }

            @Override
            public void onStatusChange(BluetoothStatus bluetoothStatus) {
                Log.d("onStatusChange ", "onDeviceDiscovered: ");
                if(bluetoothStatus == BluetoothStatus.CONNECTED){
                    Toast.makeText(getActivity(), "Connected", Toast.LENGTH_SHORT).show();

                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, new OperateFragment()).commit();
                }else if(bluetoothStatus == BluetoothStatus.CONNECTING){
                    Toast.makeText(getActivity(), "Connecting...", Toast.LENGTH_SHORT).show();
                }
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
    }

    private void init(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_scan_list);
    }

    public void loadData(){
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);
        mAdapter = new DeviceItemAdapter(getActivity(), mBluetoothAdapter.getBondedDevices());
        recyclerView.setAdapter(mAdapter);

    }
}
