package com.example.firenear.greenhousecontrol.ui;

import android.support.design.widget.NavigationView;
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

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity implements BlueToothCallback {

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
        scanDevice();
    }


    public void initNavigationDrawer() {

        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id){
                    case R.id.scan:
                        Toast.makeText(getApplicationContext(),"Scan",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.home:
                        Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.farmerHelp:
                        Toast.makeText(getApplicationContext(),"FarmerHelp",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
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
