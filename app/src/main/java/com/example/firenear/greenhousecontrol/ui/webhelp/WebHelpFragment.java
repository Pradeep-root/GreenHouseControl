package com.example.firenear.greenhousecontrol.ui.webhelp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.firenear.greenhousecontrol.R;
import com.example.firenear.greenhousecontrol.model.WebLink;

import java.util.ArrayList;


public class WebHelpFragment extends Fragment {

    private RecyclerView recyclerView;

    public static WebHelpFragment newInstance() {
        WebHelpFragment fragment = new WebHelpFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_web_help, container, false);
        init(rootView);
        loadData();
        return rootView;
    }

    private void loadData() {
        ArrayList<WebLink> webLinks = new ArrayList<>();
        WebLink webLink1 = new WebLink();
        webLink1.setTitle("Kisan Help line");
        webLink1.setUrl("http://www.farmer.gov.in/");
        webLinks.add(webLink1);

        WebLink webLink2 = new WebLink();
        webLink2.setTitle("Food and Agricultural Organisation.");
        webLink2.setUrl("http://fao.org/");
        webLinks.add(webLink2);

        WebLink webLink3 = new WebLink();
        webLink3.setTitle("Soil and Land Use Survey of India's official ");
        webLink3.setUrl("http://slusi.dacnet.nic.in/");
        webLinks.add(webLink3);

        WebLink webLink4 = new WebLink();
        webLink4.setTitle("Wikipedia like encyclopedia");
        webLink4.setUrl("http://www.wikifarming.org/");
        webLinks.add(webLink4);

        WebLink webLink5 = new WebLink();
        webLink5.setTitle("pesticides");
        webLink5.setUrl("http://pesticidewatch.org/");
        webLinks.add(webLink5);

        WebLink webLink6 = new WebLink();
        webLink6.setTitle("Agriculture and Cooperation");
        webLink6.setUrl("http://agricoop.nic.in/");
        webLinks.add(webLink6);


        WebHelpListAdapter webHelpListAdapter = new WebHelpListAdapter(webLinks);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(webHelpListAdapter);

    }

    private void init(View rootView) {
       recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_web_help_list);
    }

}
