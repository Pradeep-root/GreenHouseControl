package com.example.firenear.greenhousecontrol.ui.automode;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.firenear.greenhousecontrol.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AutoModeFragment extends Fragment {

    private Button btnPlant1;
    private Button btnPlant2;
    private Button btnPlant3;
    private Button btnPlant4;
    SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public AutoModeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_auto_mode, container, false);

        sharedPreferences = getActivity().getSharedPreferences("LoginPersi",getActivity().MODE_PRIVATE);
        editor = sharedPreferences.edit();

        btnPlant1 = (Button) rootView.findViewById(R.id.btn_plant1);
        btnPlant2 = (Button) rootView.findViewById(R.id.btn_plant2);
        btnPlant3 = (Button) rootView.findViewById(R.id.btn_plant3);
        btnPlant4 = (Button) rootView.findViewById(R.id.btn_plant4);


        btnPlant1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "AutoSet-> Temp:24C', Humidity:30%, Soil moisture:70%", Toast.LENGTH_SHORT).show();
                editor.putString("AutoType", btnPlant1.getText().toString());
                editor.putInt("Temp", 24);
                editor.putInt("Hum", 30);
                editor.putInt("Mos", 70);
                editor.commit();

            }
        });

        btnPlant2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "AutoSet-> Temp:27C', Humidity:25%, Soil moisture:80%", Toast.LENGTH_SHORT).show();
                editor.putString("AutoType", btnPlant2.getText().toString());
                editor.putInt("Temp", 24);
                editor.putInt("Hum", 30);
                editor.putInt("Mos", 70);
                editor.commit();
            }
        });

        btnPlant3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "AutoSet-> Temp:24C', Humidity:30%, Soil moisture:90%", Toast.LENGTH_SHORT).show();
                editor.putString("AutoType", btnPlant3.getText().toString());
                editor.putInt("Temp", 24);
                editor.putInt("Hum", 30);
                editor.putInt("Mos", 70);
                editor.commit();
            }
        });

        btnPlant4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "AutoSet-> Temp:28C', Humidity:35%, Soil moisture:90%", Toast.LENGTH_SHORT).show();
                editor.putString("AutoType", btnPlant4.getText().toString());
                editor.putInt("Temp", 24);
                editor.putInt("Hum", 30);
                editor.putInt("Mos", 70);
                editor.commit();
            }
        });

        return rootView;
    }

}
