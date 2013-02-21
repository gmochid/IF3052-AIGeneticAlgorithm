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
    private Integer[] mCurrentStock;
    private Integer mRestock;

    public Barang(char barangID,Integer harga, Integer restock, Integer days){
        mBarangID = barangID;
        mHarga = harga;
        mRestock = restock;
        mCurrentStock = new Integer[days];
        for (int i = 0; i < days; i++) {
            mCurrentStock[i] = mRestock;
        }
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
        for (int i = 0; i < mCurrentStock.length; i++) {
            mCurrentStock[i] = mRestock;
        }
    }
    public Boolean isPurchaseable(Integer days) {
        return mCurrentStock[days] > 0;
    }
    public void purchase(Integer days) {
        mCurrentStock[days]--;
    }

    /* GETTER and SETTER */
    public char getBarangID()   {   return mBarangID;   }
    public Integer getHarga()   {   return mHarga;      }
    public Integer getRestock() {   return mRestock;    }

    public void setBarangID(char k)         {   mBarangID = k;      }
    public void setHarga(Integer harga)     {   mHarga = harga;     }
    public void setRestock(Integer restock) {   mRestock = restock; }
}
