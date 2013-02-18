/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgo;
import model.Cewek;
import model.Barang;
import model.Iboy;
import java.io.*;
/**
 *
 * @author Yulianti Oenang
 */
public class GeneticAlgo {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        int JumlahKandidat,Enlightenment,Energy,MaxJam,JenisBarang,Harga,Restock;
        String KodeBarang;
        String KodePre;
        Iboy I;
        Cewek []C=new Cewek[100];
        Barang[]B=new Barang[100];
    try{
        FileInputStream fstream = new FileInputStream("D:\\Kuliah D\\Semester 6\\AI\\Tugas Besar 1\\GeneticAlgo\\src\\geneticalgo\\file.txt");
        DataInputStream in = new DataInputStream(fstream);
        //Get modal uang
        String s1="";
        char ch=(char) in.read();
        //Read File Line By Line
        while (ch!=' ' && ch!='\n')   {
            s1=s1+ch;
            ch=(char) in.read();
        }
        I.setModalUang(Integer.parseInt(s1));
  
        //Get TambahanUang
        String s2="";
        ch=(char)in.read();
        while (ch!=' ' && ch!='\n')   {
        s2=s2+ch;
        ch=(char) in.read();
        }
        I.setTambahanUang(Integer.parseInt(s2));
  
        //Get Waktu
        String s3="";
        ch=(char)in.read();
        while (ch!=' ' && ch!='\n')   {
            s3=s3+ch;
            ch=(char) in.read();
        }
        I.setWaktu(Integer.parseInt(s3));

        //Get Energi
        String s4="";
        ch=(char)in.read();
        while (ch!=' ' && ch!='\n' && ch!='\r')   {
          s4=s4+ch;
          ch=(char) in.read();
        }
        I.setEnergi(Integer.parseInt(s4));
        ch=(char)in.read();

        //Get JumlahKandidat
        s1="";
        ch=(char)in.read();
        while (ch!=' ' && ch!='\n' && ch!='\r')   {
          s1=s1+ch;
          ch=(char) in.read();
        }

        JumlahKandidat=(Integer.parseInt(s1));
        ch=(char)in.read();
        for(int i=0;i<JumlahKandidat;i++)
        {
            s1="";
            ch=(char)in.read();
            while (ch!=' ' && ch!='\n')   {
                s1=s1+ch;
                ch=(char) in.read();
            }
            Enlightenment=Integer.parseInt(s1);
            s1="";
            ch=(char)in.read();
            while (ch!=' ' && ch!='\n')   {
                s1=s1+ch;
                ch=(char) in.read();
            }
            Energy=Integer.parseInt(s1);
            s1="";
            ch=(char)in.read();
            while (ch!=' ' && ch!='\n')   {
                s1=s1+ch;
                ch=(char) in.read();
            }
            MaxJam=Integer.parseInt(s1);
            s1="";
            ch=(char)in.read();
            while (ch!=' ' && ch!='\n' && ch!='\r')   {
                s1=s1+ch;
                ch=(char) in.read();
            }
            KodePre=s1;
            C[i]=new Cewek(Enlightenment,Energy,MaxJam,KodePre);
            ch=(char)in.read();
        }
        //Get JenisBarang
        s1="";
        ch=(char)in.read();
            while (ch!=' ' && ch!='\n' && ch!='\r')   {
                s1=s1+ch;
                ch=(char) in.read();
            }
        JenisBarang=Integer.parseInt(s1);
        ch=(char)in.read();
        //Set Barang
        for(int i=0;i<JenisBarang;i++)
        {
            s1="";
            ch=(char)in.read();
            while (ch!=' ' && ch!='\n' && ch!='\r')   {
                s1=s1+ch;
                ch=(char) in.read();
            }
            KodeBarang=s1;
            s1="";
            ch=(char)in.read();
            while (ch!=' ' && ch!='\n' && ch!='\r')   {
                s1=s1+ch;
                ch=(char) in.read();
            }
            Harga=Integer.parseInt(s1);

            s1="";
            ch=(char)in.read();
            while (ch!=' ' && ch!='\n' && ch!='\r')   {
                s1=s1+ch;
                ch=(char) in.read();
            }
            Restock=Integer.parseInt(s1);
            B[i]=new Barang(KodeBarang,Harga,Restock);
            ch=(char)in.read();
        }
        in.close();

    }catch (Exception e){//Catch exception if any
  System.err.println("Error: " + e.getMessage());
  }
  }
}
