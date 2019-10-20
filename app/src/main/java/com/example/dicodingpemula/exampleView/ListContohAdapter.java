package com.example.dicodingpemula.exampleView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dicodingpemula.DetailBarang;
import com.example.dicodingpemula.MainActivity;
import com.example.dicodingpemula.MainFragment;
import com.example.dicodingpemula.R;
import com.example.dicodingpemula.adapterSlidingTab.MyPagerAdapter;

import java.util.ArrayList;

public class ListContohAdapter extends RecyclerView.Adapter<ListContohAdapter.ListViewHolder> {

    private Context contextku;
    private ArrayList<BarangContoh> listResep;

    public ListContohAdapter(Context contextku, ArrayList<BarangContoh> listResep) {
        this.contextku = contextku;
        this.listResep = listResep;
    }

    @NonNull
    @Override
    public ListContohAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_contoh, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, final int position) {
        final BarangContoh hero = listResep.get(position);
        Glide.with(holder.itemView.getContext())
                .load(hero.getImage())
                .into(holder.imgPhotoC);
        holder.tvBarangC.setText(hero.getNama_Barang());
        holder.tvHargaC.setText(hero.getHarga());
        holder.tvExpC.setText(hero.getExp());
        holder.tvDeskripsiC.setText(hero.getDeskripsi());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] dialogitem = {"Detail"};
                AlertDialog.Builder builder = new AlertDialog.Builder(contextku);
                builder.setTitle("Choose");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch(item){
                            case 0 :
                                Intent dataForm = new Intent(holder.itemView.getContext(), DetailContoh.class);
                                dataForm.putExtra("nama_barang", holder.tvBarangC.getText().toString());
                                dataForm.putExtra("harga", holder.tvHargaC.getText().toString());
                                dataForm.putExtra("exp", holder.tvExpC.getText().toString());
                                dataForm.putExtra("deskripsi", holder.tvDeskripsiC.getText().toString());
                                Bundle bundle = new Bundle();
                                bundle.putString("image", (hero.getImage()));
                                dataForm.putExtras(bundle);
                                contextku.startActivity(dataForm);
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
        return listResep.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhotoC;
        public TextView tvBarangC,tvHargaC,tvExpC,tvDeskripsiC;
        ListViewHolder(View itemView) {
            super(itemView);
            imgPhotoC = itemView.findViewById(R.id.photo_contoh);
            tvBarangC = itemView.findViewById(R.id.nmBarang_contoh);
            tvHargaC = itemView.findViewById(R.id.harga_contoh);
            tvExpC = itemView.findViewById(R.id.exp_contoh);
            tvDeskripsiC = itemView.findViewById(R.id.deskripsi_contoh);
        }
    }
}