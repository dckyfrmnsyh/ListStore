package com.example.dicodingpemula;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class updateBarang extends AppCompatActivity {

    private EditText dtBarang,dtHarga,dtExp,dtDes;
    Button btUpdate,btnChoose;
    private ImageView imageView;
    SQLiteDatabaseHandler dbHelper;
    final int REQUEST_CODE_GALLERY = 888;
    private static byte[] byteArray;
    Barang queri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_barang);

        dtBarang = findViewById(R.id.edt_nama_barang_update);
        dtHarga = findViewById(R.id.edt_harga_update);
        dtExp = findViewById(R.id.edt_exp_update);
        dtDes = findViewById(R.id.edt_deskripsi_update);
        imageView = (ImageView) findViewById(R.id.imageView_update);

        btUpdate = findViewById(R.id.update);
        btnChoose = findViewById(R.id.choose);
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        updateBarang.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
//                Toast.makeText(updateBarang.this, "Not ready for this time", Toast.LENGTH_SHORT).show();
            }
        });

        dbHelper = new SQLiteDatabaseHandler(this);
         queri = dbHelper.getBarang(getIntent().getExtras().getInt("id"));
        dtBarang.setText(queri.getNama_Barang());
        dtHarga.setText(queri.getHarga());
        dtExp.setText(queri.getExp());
        dtDes.setText(queri.getDeskripsi());
        byte[] image = queri.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        imageView.setImageBitmap(bitmap);
        Glide.with(this)
                .load(bitmap)
                .into(imageView);


        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imageViewToByte(imageView);
                updateRecord();
            }
        });

    }

    private void updateRecord() {
        try{
            String nama_barang = dtBarang.getText().toString().trim();
            String harga = dtHarga.getText().toString().trim();
            String exp = dtExp.getText().toString().trim();
            String deskripsi = dtDes.getText().toString().trim();

//            Barang updateBarang = new Barang(nama_barang,harga,exp,deskripsi,queri.getImage());

            dbHelper.updateData(nama_barang,harga,exp,deskripsi,imageViewToByte(imageView),queri.getId());

//            dbHelper.updateBarangRecord(getIntent().getExtras().getInt("id"),this,updateBarang);

            Toast.makeText(this, "Updated successfully.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,MainActivity.class));

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        bitmap =  Bitmap.createScaledBitmap(bitmap, 300, 200, true);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
