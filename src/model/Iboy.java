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
        int total = 0;
        for (int i = 0; i < prereq.length; i++) {
            if (!prereq[i].isPurchaseable(time)) {
                return false;
            }
            total += prereq[i].getHarga();
        }
        return (total > mCurrentUang)&&(mCurrentEnergy >= cewek.getEnergiHabis()) && (cewek.isDateable(time));
    }
    public void dateCewek(Integer cewekID, Integer time) {
        Cewek c = Cewek.getCewek(cewekID);
        Barang[] prereq = c.getPrerequisite();
        for (int i = 0; i < prereq.length; i++) {
            purchaseBarang(cewekID, time);
        }
        useEnergy(cewekID);
        c.dateIboy();
    }
    public void purchaseBarang(Integer barangID, Integer time) {
        mCurrentUang -= Barang.getBarang(barangID).getHarga();
        Barang.getBarang(barangID).purchased(time);
    }
    public void useEnergy(Integer cewekID) {
        mCurrentEnergy -= Cewek.getCewek(cewekID).getEnergiHabis();
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
}
