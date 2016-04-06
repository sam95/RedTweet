package com.example.pavan.redtweet;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView tv = (TextView) container.findViewById(R.id.tcv);
        String receive = getActivity().getIntent().getExtras().getString("data");
        //String id = getIntent().getStringExtra("data");
        tv.setText(receive);


        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
