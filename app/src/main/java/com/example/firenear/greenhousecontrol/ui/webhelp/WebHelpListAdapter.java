package com.example.firenear.greenhousecontrol.ui.webhelp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.firenear.greenhousecontrol.R;
import com.example.firenear.greenhousecontrol.model.WebLink;

import java.util.ArrayList;

/**
 * Created by pradeep on 16/10/17.
 */

public class WebHelpListAdapter extends RecyclerView.Adapter<WebHelpListAdapter.Holder> {

    private  ArrayList<WebLink> webLinks;

    public WebHelpListAdapter(ArrayList<WebLink> webLinks) {
        this.webLinks =  webLinks;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.web_list_item, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewBasicInfo;

        public Holder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.txtTitle);
        }
    }
}
