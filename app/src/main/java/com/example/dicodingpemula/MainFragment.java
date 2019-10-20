package com.example.dicodingpemula;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;


public class MainFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private SQLiteDatabaseHandler dbHelper;
    private BarangAdapter adapter;
    private String filter = "";


    Barang barang;
    private ImageView btAdd;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        //initialize the variables
        mRecyclerView = (RecyclerView)v.findViewById(R.id.rv_barang);
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        //populate recyclerview
        populaterecyclerView();


        btAdd = v.findViewById(R.id.addcircle);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BarangAdd.class);
                startActivity(intent);
            }
        });

        return v;
    }
    private void populaterecyclerView(){
        dbHelper = new SQLiteDatabaseHandler(getActivity());
        adapter = new BarangAdapter((ArrayList<Barang>) dbHelper.barangList(), getActivity(), mRecyclerView);
        mRecyclerView.setAdapter(adapter);

    }

}
