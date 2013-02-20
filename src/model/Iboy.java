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
    private Integer[] mCurrentUang;
    private Integer mCurrentEnergy;
    private Integer mEnergi;
    private Integer mMinggu;
    
    public Iboy(Integer modalUang, Integer tambahanUang, Integer minggu, Integer energi) {
        mModalUang = modalUang;
        mEnergi = energi;
        mMinggu = minggu;
        mCurrentUang = new Integer[minggu * 7 * 10];
        mCurrentUang[0] = modalUang;
        for (int i = 1; i < (minggu * 7 * 10); i++) {
            if(i % 10 == 0) {
                mCurrentUang[i] = mCurrentUang[i-1] + tambahanUang;
            } else {
                mCurrentUang[i] = mCurrentUang[i-1];
            }
        }
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
        mCurrentEnergy = mEnergi;
    }
    public Boolean isCewekDateable(Integer cewekID, Integer time) {
        Cewek cewek = Cewek.getCewek(cewekID);
        return (mCurrentEnergy >= cewek.getEnergiHabis()) && (cewek.isDateable(time));
    }
    public void dateCewek(Integer cewekID, Integer time) {
        Cewek c = Cewek.getCewek(cewekID);
        useEnergy(cewekID);
        c.dateIboy();
    }
    public void purchaseBarang(Integer barangID, Integer time) {
        useUang(Barang.getBarang(barangID).getHarga(), time);
        Barang.getBarang(barangID).purchased(time);
    }
    public void useEnergy(Integer cewekID) {
        mCurrentEnergy -= Cewek.getCewek(cewekID).getEnergiHabis();
    }
    public Boolean isUangAvailable(Integer amount, Integer time) {
        for (int i = time; i < (mMinggu * 7 * 10); i++) {
            if((mCurrentUang[i] - amount) < 0) {
                return false;
            }
        }
        return true;
    }
    public void useUang(Integer amount, Integer time) {
        for (int i = time; i < (mMinggu * 7 * 10); i++) {
            mCurrentUang[i] -= amount;
        }
    }

    /* GETTER and SETTER */
    public Integer getCurrentUang(Integer time)
                                    {       return mCurrentUang[time];}
    public Integer getCurrentEnergy()
                                    {       return mCurrentEnergy;  }
    public Integer getModalUang()   {       return mModalUang;      }
    public Integer getTambahanUang(){       return mTambahanUang;   }
    public Integer getEnergi()      {       return mEnergi;         }
    public Integer getMinggu()      {       return mMinggu;         }
    
    public void setModalUang(Integer uang)      {        mModalUang = uang;     }
    public void setTambahanUang(Integer uang)   {        mTambahanUang = uang;  }
    public void setEnergi(Integer energi)       {        mEnergi = energi;      }
}
