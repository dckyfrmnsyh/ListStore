package com.example.dicodingpemula;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.MyViewHolder> {
    private Context mycontext;
    private ArrayList<Barang> barangs;
    private RecyclerView mRecyclerV;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvBarang,tvHarga,tvExp,tvDeskripsi;
        public ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBarang = (TextView) itemView.findViewById(R.id.tv_nama_barang);
            tvHarga = (TextView) itemView.findViewById(R.id.tv_harga);
            tvExp = (TextView) itemView.findViewById(R.id.tv_exp);
            tvDeskripsi = (TextView) itemView.findViewById(R.id.tv_deskripsi);
            imageView = (ImageView) itemView.findViewById(R.id.img_item_photo);
        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public BarangAdapter(ArrayList<Barang> myDataset, Context context, RecyclerView recyclerView) {
        barangs = myDataset;
        mycontext = context;
        mRecyclerV = recyclerView;
    }
    @NonNull
    @Override
    public BarangAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_barang, viewGroup, false);
        return new MyViewHolder(view);
    }

    public void add(int position, Barang barang) {
        barangs.add(position, barang);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        barangs.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        final Barang barang = barangs.get(position);
        final byte[] image = barang.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        holder.imageView.setImageBitmap(bitmap);
//        Glide.with(holder.itemView.getContext())
//                .load(barang.getImage())
//                .apply(new RequestOptions().override(151, 151))
//                .into(holder.imageView);
        holder.tvBarang.setText(barang.getNama_Barang());
        holder.tvHarga.setText(barang.getHarga());
        holder.tvExp.setText(barang.getExp());
        holder.tvDeskripsi.setText(barang.getDeskripsi());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] dialogitem = {"Detail", "Update", "Delete"};
                AlertDialog.Builder builder = new AlertDialog.Builder(mycontext);
                builder.setTitle("Choose an Action");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch(item){
                            case 0 :
                                Intent dataForm = new Intent(holder.itemView.getContext(), DetailBarang.class);
                                dataForm.putExtra("id",barang.getId());
                                dataForm.putExtra("nama_barang", holder.tvBarang.getText().toString());
                                dataForm.putExtra("harga", holder.tvHarga.getText().toString());
                                dataForm.putExtra("exp", holder.tvExp.getText().toString());
                                dataForm.putExtra("deskripsi", holder.tvDeskripsi.getText().toString());
                                mycontext.startActivity(dataForm);
                                break;
                            case 1 :
                                Intent intent = new Intent(holder.itemView.getContext(), updateBarang.class);
                                intent.putExtra("id",barang.getId());
                                Bundle bundle1 = new Bundle();
                                bundle1.putByteArray("image", (barang.getImage()));
                                intent.putExtras(bundle1);
                                mycontext.startActivity(intent);
                                break;
                            case 2 :
                                SQLiteDatabaseHandler dbHelper = new SQLiteDatabaseHandler(mycontext);
                                dbHelper.deleteBarangRecord(barang.getId(), mycontext);
//
                                barangs.remove(position);
                                mRecyclerV.removeViewAt(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, barangs.size());
                                notifyDataSetChanged();
                                break;
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }});
    }


    @Override
    public int getItemCount() {
        return barangs.size();
    }



}