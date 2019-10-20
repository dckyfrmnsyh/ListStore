package com.example.dicodingpemula.exampleView;


import java.util.ArrayList;

public class ContohData {
    public static String[][] data = new String[][]{
            {"","Rokok Surya","24.000","--", "rokok surya ini di jual per pack bukan pcs", "https://image1ws.indotrading.com/s3/productimages/co37129/p534552/w300-h300/7b811168-037c-4209-89fb-6954fda7065ew.jpg"},
            {"","Detol","5.000","--", "ini sabun mandi bukan sabun cuci pakaian apalagi cuci pring","https://cf.shopee.co.id/file/ac2356935029b6203527f75d5619fa97"},
            {"","Mitu Sabun Mandi","10.000","12/11/2020", "Sabun mitu ini untuk bayi yaaa", "https://www.tororo.com/pub/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/m/i/mitu_sabun_mandi_2in1_200ml.jpg"},
            {"","Rinso","20.000","--", "di jual perdos bukan eceran", "https://cdn.monotaro.id/media/catalog/product/cache/6/image/400x400/b5fa40980320eb406ba395dece54e4a8/S/0/S000001479-3.jpg"},
            {"","French fries","10.000","20/22/2029", "Ini jajanan di cek terus untuk tanggal kadaluarsanya", "https://www.static-src.com/wcsstore/Indraprastha/images/catalog/medium//87/MTA-2641703/siantar-top_siantar-top-french-fries-2000-snack--75-g-_full02.jpg"},
            {"","Roma Kelapa","10.000","03/03/2020", "biasa di beli pakdhe depan", "https://ecs7.tokopedia.net/img/cache/700/product-1/2016/10/28/1163717/1163717_3c5ea384-fa5b-4e1f-80e7-272421483bde.jpg"},
            {"","Biskuat Bolu","2.000","02/05/2021", "jangan di kasih ke ade kalo ade minta. ntar batuk", "https://ecs7.tokopedia.net/img/product-1/2016/10/4/6189222/6189222_0251df28-5103-4cbf-b505-7a40ac37a266.jpg"},
            {"","Belvita", "5.000","12/04/2020","Biasanya di beli ibu samping rumah", "https://yukcoba.in/uploads/items/355/44094b974c699776e998456e64419a7b.jpg"},
            {"","Bedak Marcks", "4.000","--","Bedak semua kalangan. Ibu siti suruh sisain 1", "https://cf.shopee.co.id/file/486f498daa096b9e576242361eb50f31"},
            {"","Lipstick", "20.000","15/05/2025","Stock kalo habis beli di deket pasar", "https://d2pa5gi5n2e1an.cloudfront.net/global/images/product/beauty/Wardah_Matte_Lipstick/Wardah_Matte_Lipstick_L_1.jpg"},
    };
    public static ArrayList<BarangContoh> getListData(){
        ArrayList<BarangContoh> list = new ArrayList<>();
        for (String[] aData : data) {
            BarangContoh contoh = new BarangContoh();

            contoh.setNama_Barang(aData[1]);
            contoh.setHarga(aData[2]);
            contoh.setExp(aData[3]);
            contoh.setDeskripsi(aData[4]);
            contoh.setImage(aData[5]);
            list.add(contoh);
        }

        return list;
    }
}
