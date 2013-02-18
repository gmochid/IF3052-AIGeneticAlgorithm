/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Yulianti Oenang
 */
public class Barang {
    private String KodeBarang;
    private int Harga;
    private int Restock;
    public Barang(String kodebarang,int harga, int restock){
        KodeBarang=kodebarang;
        Harga=harga;
        Restock=restock;
    }
    public String getKodeBarang(){
        return KodeBarang;
    };
    public void setKodeBarang(String k){
        KodeBarang=k;
    };
    public int getHarga(){
        return Harga;
    }
    public void setHarga(int h){
        Harga=h;
    }
    public int getRestock(){
        return Restock;
    };
    public void setRestock(int R)
    {
        Restock=R;
    }
    
}
