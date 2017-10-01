package com.example.firenear.greenhousecontrol.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.firenear.greenhousecontrol.R;
import com.example.firenear.greenhousecontrol.bluetooth.BlueToothCallback;
import com.example.firenear.greenhousecontrol.bluetooth.BluetoothSerial;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity implements BlueToothCallback {

    private BluetoothSerial bluetoothSerial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bluetoothSerial = BluetoothSerial.newInstance();
        scanDevice();
    }

    @Override
    public void scannedDevices(JSONArray jsonArray) {
        Log.d("bluetooth:", jsonArray.toString());
    }

    @Override
    public void connectedDevice(boolean status) {

    }

    @Override
    public void readData(String data) {

    }

    @Override
    public void error(String error) {

    }

    public void scanDevice(){
        try {
            bluetoothSerial.execute(this,BluetoothSerial.DISCOVER_UNPAIRED, new String[]{""},this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
