package com.example.firenear.greenhousecontrol.ui;

import android.bluetooth.BluetoothDevice;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firenear.greenhousecontrol.R;
import com.example.firenear.greenhousecontrol.bluetooth.BlueToothCallback;
import com.example.firenear.greenhousecontrol.bluetooth.BluetoothSerial;
import com.example.firenear.greenhousecontrol.ui.webhelp.WebHelpFragment;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothClassicService;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothConfiguration;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothService;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;
import java.util.UUID;

import co.lujun.lmbluetoothsdk.BluetoothController;
import co.lujun.lmbluetoothsdk.base.BluetoothListener;

public class MainActivity extends AppCompatActivity {

    private BluetoothSerial bluetoothSerial;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initNavigationDrawer();
        bluetoothSerial = BluetoothSerial.newInstance();

        BluetoothConfiguration config = new BluetoothConfiguration();
        config.context = getApplicationContext();
        config.bluetoothServiceClass = BluetoothClassicService.class; // BluetoothClassicService.class or BluetoothLeService.class
        config.bufferSize = 1024;
        config.characterDelimiter = '\n';
        config.deviceName = "GreenHouseControl";
        config.callListenersInMainThread = true;

        // Bluetooth Classic
        config.uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

        BluetoothService.init(config);

        final BluetoothService service = BluetoothService.getDefaultInstance();

        service.startScan();

        service.setOnScanCallback(new BluetoothService.OnBluetoothScanCallback() {
            @Override
            public void onDeviceDiscovered(BluetoothDevice bluetoothDevice, int i) {
                Log.d("onDeviceDiscovered", bluetoothDevice.getName());
                service.connect(bluetoothDevice);

            }

            @Override
            public void onStartScan() {
                Log.d("onDeviceDiscovered", "onDeviceDiscovered");
            }

            @Override
            public void onStopScan() {
                Log.d("onDeviceDiscovered", "onDeviceDiscovered");
            }
        });

        service.setOnEventCallback(new BluetoothService.OnBluetoothEventCallback() {
            @Override
            public void onDataRead(byte[] bytes, int i) {

            }

            @Override
            public void onStatusChange(BluetoothStatus bluetoothStatus) {

            }

            @Override
            public void onDeviceName(String s) {

            }

            @Override
            public void onToast(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                Log.d("onToast", s);

            }

            @Override
            public void onDataWrite(byte[] bytes) {

            }
        });
    }


    public void initNavigationDrawer() {

        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id){
                    case R.id.scan:
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.home:
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.farmerHelp:
                        drawerLayout.closeDrawers();
                        openWebHelp();
                        break;
                    case R.id.logout:
                        finish();

                }
                return true;
            }
        });
        View header = navigationView.getHeaderView(0);
        TextView tv_email = (TextView)header.findViewById(R.id.tv_email);
        tv_email.setText("987654321");
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void openWebHelp() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction bt = fm.beginTransaction();
        bt.replace(R.id.frameContainer, WebHelpFragment.newInstance());
        bt.commit();
    }

}
