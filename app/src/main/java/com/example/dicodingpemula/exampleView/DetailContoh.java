package com.example.dicodingpemula.exampleView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dicodingpemula.R;

import java.util.ArrayList;

public class DetailContoh extends AppCompatActivity {
    private TextView barang,harga,exp,deskripsi;
    private ImageView image;
    private ArrayList<BarangContoh> listContoh = new ArrayList<>();

    Toolbar mTopToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contoh);


        barang = findViewById(R.id.dt_nama_barangC);
        harga = findViewById(R.id.dt_hargaC);
        exp = findViewById(R.id.dt_expC);
        deskripsi = findViewById(R.id.dt_desC);
        image = findViewById(R.id.dt_imageC);

        barang.setText(getIntent().getExtras().getString("nama_barang"));
        harga.setText(getIntent().getExtras().getString("harga"));
        exp.setText(getIntent().getExtras().getString("exp"));
        deskripsi.setText(getIntent().getExtras().getString("deskripsi"));
        Bundle bundle = null;
        Glide.with(this)
                .load(getIntent().getExtras().getString("image"))
                .into(image);

    }
}
