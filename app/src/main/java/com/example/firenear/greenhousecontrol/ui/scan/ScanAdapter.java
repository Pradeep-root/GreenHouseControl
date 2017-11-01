package com.example.firenear.greenhousecontrol.ui.scan;

import android.bluetooth.BluetoothDevice;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.firenear.greenhousecontrol.R;

import java.util.ArrayList;

/**
 * Created by pradeep on 24/10/17.
 */

public class ScanAdapter extends RecyclerView.Adapter<ScanAdapter.Holder> {

    private ArrayList<BluetoothDevice> bluetoothDevices;

    public ScanAdapter(ArrayList<BluetoothDevice> bluetoothDevices) {
        this.bluetoothDevices = bluetoothDevices;
    }


    @Override
    public ScanAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.device_item1, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(ScanAdapter.Holder holder, int position) {
            holder.textDeviceName.setText(bluetoothDevices.get(position).getName().trim());
            holder.textDeviceAddress.setText(bluetoothDevices.get(position).getAddress().trim());
    }

    @Override
    public int getItemCount() {
        return bluetoothDevices.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView textDeviceName;
        private TextView textDeviceAddress;
        public Holder(View itemView) {
            super(itemView);
            textDeviceAddress = (TextView) itemView.findViewById(R.id.textDeviceMac);
            textDeviceName = (TextView) itemView.findViewById(R.id.textDeviceName);
        }
    }

    public void updateList(BluetoothDevice bluetoothDevice){
        this.bluetoothDevices.add(bluetoothDevice);
        notifyDataSetChanged();
    }
}
