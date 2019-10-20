package com.example.dicodingpemula;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dicodingpemula.exampleView.BarangContoh;
import com.example.dicodingpemula.exampleView.ContohData;
import com.example.dicodingpemula.exampleView.ListContohAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExampleFragment extends Fragment {

    private RecyclerView rvContoh;
    private ArrayList<BarangContoh> listContoh = new ArrayList<>();
    private ListContohAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_example, container, false);

        rvContoh = v.findViewById(R.id.rv_barang_contoh);
        rvContoh.setHasFixedSize(true);

        listContoh.addAll(ContohData.getListData());
        showRecyclerList();

        return v;
    }
    private void showRecyclerList(){
        rvContoh.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ListContohAdapter(getActivity(),listContoh);
        rvContoh.setAdapter(adapter);
    }

}
