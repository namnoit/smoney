package com.example.smoney;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class HistoryFragment extends Fragment {
    ImageButton add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_history, container, false);
        add = view.findViewById(R.id.addButton);
        addNewRecord(add);
        return view;
    }
    void addNewRecord(ImageButton add ){

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Select_wallet.class);
                startActivity(i);
                getActivity().setTitle("jhgjh");

            }
        });
    }
}
