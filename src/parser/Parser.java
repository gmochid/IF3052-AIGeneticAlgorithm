/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import model.Barang;
import model.Cewek;
import model.Iboy;

/**
 *
 * @author Yulianti Oenang
 */
public class Parser {
    public static void parseScheduleFile(String filename) {
        FileInputStream fis = null;
        try {
             fis = new FileInputStream(filename);
        } catch (FileNotFoundException ex) {
        }
        Scanner sc = new Scanner(fis);
        for (int i = 1; i <= Cewek.getTotalCewek(); i++) {
            Cewek.getCewek(i).setJadwal(sc.next());
        }
        /*for (int i = 1; i <= Cewek.getTotalCewek(); i++) {
            System.out.println(Cewek.getCewek(i).getCewekID()+":"+Cewek.getCewek(i).getJadwal());
        }*/
    }

    public static void parseGeneralFile(String filename) {
        FileInputStream fis = null;
        try {
             fis = new FileInputStream(filename);
        } catch (FileNotFoundException ex) {
        }
        Scanner sc = new Scanner(fis);
        Iboy iboy = new Iboy(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
        Iboy.setActiveIboy(iboy);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            Cewek cewek = new Cewek(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.next());
            Cewek.addCewek(cewek);
        }
        /*for (int i = 1; i <= Cewek.getTotalCewek(); i++) {
            System.out.println(Cewek.getCewek(i).getCewekID()+":"+Cewek.getCewek(i).getEnlightenment());
        }*/
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            String s = sc.next();
            Barang barang = new Barang(s.charAt(0), sc.nextInt(), sc.nextInt(), Iboy.getActiveIboy().getMinggu() * 7);
            Barang.addBarang(barang);
        }
        /*for (int i = 0; i < Barang.getTotalBarang(); i++) {
            System.out.println(Barang.getBarang(i).getBarangID()+":"+Barang.getBarang(i).getHarga());
        }*/
    }
}
