package com.example.dicodingpemula.exampleView;

public class BarangContoh {
    private int id;
    private String Nama_Barang;
    private String Harga;
    private String Exp;
    private String Deskripsi ;
    private String image;

    public BarangContoh(String nama_Barang, String harga, String exp, String deskripsi, String image) {
        Nama_Barang = nama_Barang;
        Harga = harga;
        Exp = exp;
        Deskripsi = deskripsi;
        this.image = image;
    }

    public BarangContoh() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama_Barang() {
        return Nama_Barang;
    }

    public void setNama_Barang(String nama_Barang) {
        Nama_Barang = nama_Barang;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }

    public String getExp() {
        return Exp;
    }

    public void setExp(String exp) {
        Exp = exp;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
