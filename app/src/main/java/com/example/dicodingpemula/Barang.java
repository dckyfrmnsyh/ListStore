package com.example.dicodingpemula;


public class Barang {

    private int id;
    private String Nama_Barang;
    private String Harga;
    private String Exp;
    private String Deskripsi ;
    private byte[] image;

    public Barang(String nama_Barang, String harga, String exp, String deskripsi, byte[] image) {
        Nama_Barang = nama_Barang;
        Harga = harga;
        Exp = exp;
        Deskripsi = deskripsi;
        this.image = image;
    }

    public Barang() {

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
