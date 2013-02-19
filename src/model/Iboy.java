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
    private Integer mCurrentEnergy;
    private Integer mWaktu;
    private Integer mEnergi;
    private HashMap<Integer, Integer> mOwned = new HashMap<Integer, Integer>();
    
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
    public void reset() {
        mCurrentUang = mModalUang;
        mCurrentEnergy = mEnergi;
    }
    public void nextDay() {
        mCurrentEnergy = mEnergi;
        mCurrentUang += mTambahanUang;
    }
    public Boolean isCewekDateable(Integer cewekID, Integer time) {
        Cewek cewek = Cewek.getCewek(cewekID);
        Barang[] prereq = cewek.getPrerequisite();
        for (int i = 0; i < prereq.length; i++) {
            if (!prereq[i].isPurchaseable()) {
                return false;
            }
        }
        return (mCurrentEnergy >= cewek.getEnergiHabis()) && (cewek.isDateable(time));
    }
    public void dateCewek(Integer cewekID) {
        Cewek c = Cewek.getCewek(cewekID);
        Barang[] prereq = c.getPrerequisite();
        for (int i = 0; i < prereq.length; i++) {
            useBarang((int)prereq[i].getBarangID() - (int)'A');
        }
        useEnergy(cewekID);
        c.dateIboy();
    }
    public Boolean isBarangPurchasable(Integer barangID) {
        Barang barang = Barang.getBarang(barangID);
        return (barang.getHarga() >= mCurrentUang) && (barang.isPurchaseable());
    }
    public void purchaseBarang(Integer barangID) {
        if (mOwned.containsKey(barangID)) {
            Integer i = mOwned.get(barangID);
            i++;
            mOwned.put(barangID, i);
        } else {
            mOwned.put(barangID, 1);
        }
        mCurrentUang -= Barang.getBarang(barangID).getHarga();
    }
    public void useEnergy(Integer cewekID) {
        mCurrentEnergy -= Cewek.getCewek(cewekID).getEnergiHabis();
    }
    public void useBarang(Integer barangID) {
        Integer i = mOwned.get(barangID);
        i--;
        mOwned.put(barangID, i);
    }
    public void tambahUang() {
        mCurrentUang += mTambahanUang;
    }

    /* GETTER and SETTER */
    public Integer getCurrentUang() {       return mCurrentUang;    }
    public Integer getCurrentEnergy() {     return mCurrentEnergy;  }
    public Integer getModalUang()   {       return mModalUang;      }
    public Integer getTambahanUang(){       return mTambahanUang;   }
    public Integer getWaktu()       {       return mWaktu;          }
    public Integer getEnergi()      {       return mEnergi;         }
    
    public void setModalUang(Integer uang)      {        mModalUang = uang;     }
    public void setTambahanUang(Integer uang)   {        mTambahanUang = uang;  }
    public void setWaktu(Integer waktu)         {        mWaktu = waktu;        }
    public void setEnergi(Integer energi)       {        mEnergi = energi;      }

    public static void main(Integer[] args) {
        Iboy iboy = new Iboy(1000, 11, 12, 100);
        Iboy.setActiveIboy(iboy);
        Iboy.getActiveIboy().purchaseBarang(0);
        Iboy.getActiveIboy().purchaseBarang(0);
        Iboy.getActiveIboy().purchaseBarang(1);
        Iboy.getActiveIboy().purchaseBarang(1);
        System.out.println(Iboy.getActiveIboy().mOwned.get(0));
    }
}
