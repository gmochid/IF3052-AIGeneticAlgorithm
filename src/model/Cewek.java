/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Yulianti Oenang
 */
public class Cewek {
    private int Enlightenment;
    private int EnergiHabis;
    private int WaktuKetemuMax;
    private String Prerequisit;
    public Cewek(int enlightenment,int energihabis, int waktuketemumax, String kodepre){
        Enlightenment=enlightenment;
        EnergiHabis=energihabis;
        WaktuKetemuMax=waktuketemumax;
        Prerequisit=kodepre;
    }
    public int getEnlightenment(){
        return Enlightenment;
    }
    public void setEnglightenment(int e)
    {
        Enlightenment=e;
    }
    public int getEnergiHabis(){
        return EnergiHabis;
    }
    public void setEnergiHabis(int e){
        EnergiHabis=e;
    }
    public int getWaktuKetemuMax(){
        return WaktuKetemuMax;
    }
    public void setWaktuKetemuMax(int w){
        WaktuKetemuMax=w;
    }
    public String getPrerequisit(){
        return Prerequisit;
    }
    public void setPrerequisit(String p){
        Prerequisit=p;
    }
            
    
}
