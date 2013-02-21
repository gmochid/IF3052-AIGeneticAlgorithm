package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Yulianti Oenang
 */
public class Cewek {
    private static HashMap<Integer, Cewek> cewekLib = new HashMap<Integer, Cewek>();
    private static Integer cewekTotal = 0;
    private Integer mEnlightenment;
    private Integer mEnergiHabis;
    private Integer mCurrentWaktu;
    private Integer mWaktuMax;
    private String mPrerequisiteID;
    private Integer mCewekID;
    private String mJadwal;
    public Cewek(Integer enlightenment,Integer energihabis, Integer waktumax, String prerequisiteID, Integer minggu){
        mEnlightenment = enlightenment;
        mEnergiHabis = energihabis;
        mWaktuMax = waktumax;
        mPrerequisiteID = prerequisiteID;
        mCewekID = ++cewekTotal;
    }

    /* STATIC METHOD */
    public static Cewek getCewek(Integer key) {
        return cewekLib.get(key);
    }
    public static void addCewek(Cewek cewek) {
        cewekLib.put(cewek.getCewekID(), cewek);
    }
    public static Integer getTotalCewek() {
        return cewekLib.size();
    }

    /* METHOD */
    public void reset() {
        mCurrentWaktu = mWaktuMax;
    }
    public void dateIboy() {
        mCurrentWaktu--;
    }
    public Barang[] getPrerequisite()   {
        ArrayList<Barang> AB = new ArrayList<Barang>();
        for (int i = 0; i < getPrerequisiteID().length(); i++) {
            AB.add(Barang.getBarang(getPrerequisiteID().charAt(i)));
        }
        return (Barang[]) AB.toArray();
    }
    public Boolean isDateable(Integer time) {
        return (mCurrentWaktu > 0) && (mJadwal.charAt(time) == '1');
    }

    /* GETTER and SETTER */
    public Integer getCewekID()         {   return mCewekID;            }
    public Integer getEnlightenment()   {   return mEnlightenment;      }
    public Integer getEnergiHabis()     {   return mEnergiHabis;        }
    public Integer getWaktuMax()        {   return mWaktuMax;           }
    public String getPrerequisiteID()   {   return mPrerequisiteID;     }
    public String getJadwal()           {   return mJadwal;             }

    public void setJadwal(String jadwal)                {   mJadwal = jadwal;               }
    public void setEnglightenment(Integer enlightment)  {   mEnlightenment = enlightment;   }
    public void setEnergiHabis(Integer energiHabis)     {   mEnergiHabis = energiHabis;     }
    public void setWaktuMax(Integer waktuMax)           {   mWaktuMax = waktuMax;           }
    public void setPrerequisiteID(String prerequisiteID){   mPrerequisiteID = prerequisiteID;}
}
