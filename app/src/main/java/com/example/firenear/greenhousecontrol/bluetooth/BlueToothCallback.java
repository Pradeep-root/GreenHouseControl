package com.example.firenear.greenhousecontrol.bluetooth;

import org.json.JSONArray;

/**
 * Created by pradeep on 30/9/17.
 */

public interface BlueToothCallback {


    void scannedDevices(JSONArray jsonArray);
    void connectedDevice(boolean status);
    void readData(String data);
    void error(String error);
}
