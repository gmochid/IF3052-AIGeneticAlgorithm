/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;

/**
 *
 * @author Yulianti Oenang
 */
public class Barang {
    private static HashMap<String, Barang> barangLib = new HashMap<String, Barang>();
    private String mBarangID;
    private Integer mHarga;
    private Integer mRestock;

    public Barang(String barangID,Integer harga, Integer restock){
        mBarangID = barangID;
        mHarga = harga;
        mRestock = restock;
    }

    /* STATIC METHOD */
    public static void addBarang(Barang barang) {
        barangLib.put(barang.getBarangID(), barang);
    }
    public static Barang getBarang(String key) {
        return barangLib.get(key);
    }

    /* GETTER and SETTER */
    public String getBarangID() {   return mBarangID;   }
    public Integer getHarga()   {   return mHarga;      }
    public Integer getRestock() {   return mRestock;    }

    public void setBarangID(String k)       {   mBarangID=k;        }
    public void setHarga(Integer harga)     {   mHarga = harga;     }
    public void setRestock(Integer restock) {   mRestock = restock; }
}
