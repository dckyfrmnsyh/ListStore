package com.example.dicodingpemula;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SQLiteDatabaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "barang.db";
    private static final int DATABASE_VERSION = 3 ;
    public static final String TABLE_NAME = "Barang";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAMA_BARANG = "nama_barang";
    public static final String COLUMN_HARGA = "harga";
    public static final String COLUMN_EXP = "exp";
    public static final String COLUMN_DESKRIPSI = "deskripsi";
    public static final String COLUMN_IMAGE = "image";

    public SQLiteDatabaseHandler (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAMA_BARANG + " TEXT NOT NULL, " +
                COLUMN_HARGA + " TEXT NOT NULL, " +
                COLUMN_EXP + " TEXT NOT NULL, " +
                COLUMN_DESKRIPSI + " TEXT NOT NULL, " +
                COLUMN_IMAGE + " BLOB NOT NULL);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    /**create record**/
    public void saveNewBarang(Barang barang) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA_BARANG, barang.getNama_Barang());
        values.put(COLUMN_HARGA, barang.getHarga());
        values.put(COLUMN_EXP, barang.getExp());
        values.put(COLUMN_DESKRIPSI, barang.getDeskripsi());
        values.put(COLUMN_IMAGE, barang.getImage());

        // insert

        db.insert(TABLE_NAME,null, values);
        db.close();
    }

    /**Query records, give options to filter results**/
    public List<Barang> barangList() {
        String query;
            query = "SELECT  * FROM " + TABLE_NAME;

        ArrayList<Barang> barangArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Barang barang;

        if (cursor.moveToFirst()) {
            do {
                barang = new Barang();
                barang.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                barang.setNama_Barang(cursor.getString(cursor.getColumnIndex(COLUMN_NAMA_BARANG)));
                barang.setHarga(cursor.getString(cursor.getColumnIndex(COLUMN_HARGA)));
                barang.setExp(cursor.getString(cursor.getColumnIndex(COLUMN_EXP)));
                barang.setDeskripsi(cursor.getString(cursor.getColumnIndex(COLUMN_DESKRIPSI)));
                barang.setImage(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE)));
                barangArrayList.add(barang);
            } while (cursor.moveToNext());
        }


        return barangArrayList;
    }

    /**Query only 1 record**/
    public Barang getBarang(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  * FROM " + TABLE_NAME + " WHERE "+COLUMN_ID+"="+ id;
        Cursor cursor = db.rawQuery(query, null);

        Barang receivedBarang = new Barang();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            receivedBarang.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            receivedBarang.setNama_Barang(cursor.getString(cursor.getColumnIndex(COLUMN_NAMA_BARANG)));
            receivedBarang.setHarga(cursor.getString(cursor.getColumnIndex(COLUMN_HARGA)));
            receivedBarang.setExp(cursor.getString(cursor.getColumnIndex(COLUMN_EXP)));
            receivedBarang.setDeskripsi(cursor.getString(cursor.getColumnIndex(COLUMN_DESKRIPSI)));
            receivedBarang.setImage(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE)));
        }



        return receivedBarang;


    }


    /**delete record**/
    public void deleteBarangRecord(long id, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE "+COLUMN_ID+"='"+id+"'");
        Toast.makeText(context, "Deleted successfully.", Toast.LENGTH_SHORT).show();

    }

    /**update record**/
    public void updateBarangRecord(long barangId, Context context, Barang updatedbarang) {
        SQLiteDatabase db = this.getWritableDatabase();
        //you can use the constants above instead of typing the column names
        db.execSQL("UPDATE  "+TABLE_NAME+" SET " +
                ""+COLUMN_NAMA_BARANG+" ='"+ updatedbarang.getNama_Barang() + "'," +
                " "+COLUMN_HARGA+" ='" + updatedbarang.getHarga()+ "'," +
                " "+COLUMN_EXP+" ='"+ updatedbarang.getExp() + "'," +
                " "+COLUMN_DESKRIPSI+" ='"+ updatedbarang.getDeskripsi() + "'," +
                " "+COLUMN_IMAGE+" ='"+ (Arrays.toString(updatedbarang.getImage())) + "'" +
                "  WHERE "+COLUMN_ID+"='" + barangId + "'");
        Toast.makeText(context, "Updated successfully.", Toast.LENGTH_SHORT).show();
    }

    public void updateData(String nama_barang, String exp,String harga, String deskripsi, byte[] image, int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE "+TABLE_NAME+" SET nama_barang = ?, harga = ?,exp = ?, deskripsi = ?, image = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, nama_barang);
        statement.bindString(2, harga);
        statement.bindString(3, exp);
        statement.bindString(4, deskripsi);
        statement.bindBlob(5, image);
        statement.bindDouble(6, (double)id);

        statement.execute();
        database.close();
    }
}
