package model;

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
    private Integer mWaktuMax;
    private String mPrerequisiteID;
    private Integer mCewekID;
    public Cewek(Integer enlightenment,Integer energihabis, Integer waktumax, String prerequisiteID){
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

    /* GETTER and SETTER */
    public Barang getPrerequisite()     {   return Barang.getBarang(mPrerequisiteID);}
    public Integer getCewekID()         {   return mCewekID;           }
    public Integer getEnlightenment()   {   return mEnlightenment;     }
    public Integer getEnergiHabis()     {   return mEnergiHabis;       }
    public Integer getWaktuMax()        {   return mWaktuMax;          }
    public String getPrerequisiteID()   {   return mPrerequisiteID;    }

    public void setEnglightenment(Integer enlightment)  {   mEnlightenment = enlightment;   }
    public void setEnergiHabis(Integer energiHabis)     {   mEnergiHabis = energiHabis;     }
    public void setWaktuMax(Integer waktuMax)           {   mWaktuMax = waktuMax;           }
    public void setPrerequisiteID(String prerequisiteID){   mPrerequisiteID = prerequisiteID;}
}
