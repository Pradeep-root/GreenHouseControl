package com.example.firenear.greenhousecontrol;

import android.app.Application;
import android.content.Context;

import com.example.firenear.greenhousecontrol.bluetooth.BluetoothSerial;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothClassicService;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothConfiguration;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothService;

import java.util.UUID;

/**
 * Created by pradeep on 23/10/17.
 */

public class GreenHouseApp extends Application {

    private static BluetoothService service;

    public GreenHouseApp() {


    }

    public static BluetoothService getBlueSerialDefault(Context context){

        BluetoothConfiguration config = new BluetoothConfiguration();
        config.context = context;
        config.bluetoothServiceClass = BluetoothClassicService.class; // BluetoothClassicService.class or BluetoothLeService.class
        config.bufferSize = 1024;
        config.characterDelimiter = '\n';
        config.deviceName = "GreenHouseControl";
        config.callListenersInMainThread = true;

        // Bluetooth Classic
        config.uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

        BluetoothService.init(config);
        if(service == null){
            service =  BluetoothService.getDefaultInstance();
        }
        return service;
    }

}
