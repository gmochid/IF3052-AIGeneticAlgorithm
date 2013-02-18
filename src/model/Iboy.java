package model;

import java.util.HashMap;

/**
 *
 * @author Yulianti Oenang
 */
public class Iboy {
    private static Iboy activeIboy;
    private Integer mModalUang;
    private Integer mTambahanUang;
    private Integer mCurrentUang;
    private Integer mWaktu;
    private Integer mEnergi;
    private HashMap<String, Integer> mOwned = new HashMap<String, Integer>();
    
    public Iboy(Integer modalUang, Integer tambahanUang, Integer waktu, Integer energi) {
        mModalUang = modalUang;
        mTambahanUang = tambahanUang;
        mCurrentUang = mModalUang;
        mWaktu = waktu;
        mEnergi = energi;
    }

    /* STATIC METHOD */
    public static void setActiveIboy(Iboy iboy) {
        activeIboy = iboy;
    }
    public static Iboy getActiveIboy() {
        return activeIboy;
    }

    /* METHOD */
    public void beliBarang(String barangID) {
        if (mOwned.containsKey(barangID)) {
            Integer i = mOwned.get(barangID);
            i++;
            mOwned.put(barangID, i);
        } else {
            mOwned.put(barangID, 1);
        }
    }
    public void pakaiBarang(String barangID) {
        Integer i = mOwned.get(barangID);
        i--;
        mOwned.put(barangID, i);
    }
    public void tambahUang() {
        mCurrentUang += mTambahanUang;
    }

    /* GETTER and SETTER */
    public Integer getCurrentUang() {       return mCurrentUang;    }
    public Integer getModalUang()   {       return mModalUang;      }
    public Integer getTambahanUang(){       return mTambahanUang;   }
    public Integer getWaktu()       {       return mWaktu;          }
    public Integer getEnergi()      {       return mEnergi;         }
    
    public void setModalUang(Integer uang)      {        mModalUang = uang;     }
    public void setTambahanUang(Integer uang)   {        mTambahanUang = uang;  }
    public void setWaktu(Integer waktu)         {        mWaktu = waktu;        }
    public void setEnergi(Integer energi)       {        mEnergi = energi;      }

    public static void main(String[] args) {
        Iboy iboy = new Iboy(1000, 11, 12, 100);
        Iboy.setActiveIboy(iboy);
        Iboy.getActiveIboy().beliBarang("A");
        Iboy.getActiveIboy().beliBarang("A");
        Iboy.getActiveIboy().beliBarang("B");
        Iboy.getActiveIboy().beliBarang("A");
        System.out.println(Iboy.getActiveIboy().mOwned.get("A"));
    }
}
