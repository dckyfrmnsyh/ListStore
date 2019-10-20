package com.example.dicodingpemula;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class DetailBarang extends AppCompatActivity {

    TextView barang,harga,exp,deskripsi;
    ImageView image;
    SQLiteDatabaseHandler dbHelper;
    private Toolbar mTopToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_barang);
//
//        mTopToolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(mTopToolbar);

        barang = findViewById(R.id.dt_nama_barang);
        harga = findViewById(R.id.dt_harga);
        exp = findViewById(R.id.dt_exp);
        deskripsi = findViewById(R.id.dt_des);
        image = findViewById(R.id.dt_image);
        barang.setText(getIntent().getExtras().getString("nama_barang"));
        harga.setText(getIntent().getExtras().getString("harga"));
        exp.setText(getIntent().getExtras().getString("exp"));
        deskripsi.setText(getIntent().getExtras().getString("deskripsi"));
        dbHelper = new SQLiteDatabaseHandler(this);
        Barang queri = dbHelper.getBarang(getIntent().getExtras().getInt("id"));
        Bundle bundle = null;
        Glide.with(this)
                .load(queri.getImage())
                .into(image);



    }
}
