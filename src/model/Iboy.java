/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Yulianti Oenang
 */
public class Iboy {
    private int ModalUang;
    private int TambahanUang;
    private int Waktu;
    private int Energi;
    
    public Iboy(){}
    public int getModalUang(){
        return ModalUang;
    }
    public void setModalUang(int uang){
        ModalUang=uang;
    }
    public int getTambahanUang(){
        return TambahanUang;
    }
    public void setTambahanUang(int uang){
        TambahanUang=uang;
    }
    public int getWaktu(){
        return Waktu;
    }
    public void setWaktu(int waktu)
    {
        Waktu=waktu;
    }
    public int getEnergi(){
        return Energi;
    }
    public void setEnergi(int energi){
        Energi=energi;
    }
}
