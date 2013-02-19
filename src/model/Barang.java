package model;

import java.util.HashMap;

/**
 *
 * @author Yulianti Oenang
 */
public class Barang {
    private static HashMap<Integer, Barang> barangLib = new HashMap<Integer, Barang>();
    private char mBarangID;
    private Integer mHarga;
    private Integer mCurrentStock;
    private Integer mRestock;

    public Barang(char barangID,Integer harga, Integer restock){
        mBarangID = barangID;
        mHarga = harga;
        mRestock = restock;
    }

    /* STATIC METHOD */
    public static void addBarang(Barang barang) {
        barangLib.put((int) barang.getBarangID() - (int) 'A', barang);
    }
    public static Barang getBarang(char key) {
        return barangLib.get((int) key - (int) 'A');
    }
    public static Barang getBarang(Integer key) {
        return barangLib.get(key);
    }
    public static Integer getTotalBarang() {
        return barangLib.size();
    }

    /* METHOD */
    public void reset() {
        mCurrentStock = mRestock;
    }
    public Boolean isPurchaseable() {
        return mCurrentStock > 0;
    }
    public void purchased() {
        mCurrentStock--;
    }

    /* GETTER and SETTER */
    public char getBarangID()   {   return mBarangID;   }
    public Integer getHarga()   {   return mHarga;      }
    public Integer getRestock() {   return mRestock;    }

    public void setBarangID(char k)         {   mBarangID = k;      }
    public void setHarga(Integer harga)     {   mHarga = harga;     }
    public void setRestock(Integer restock) {   mRestock = restock; }
}
