/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

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
        for (int i = 0; i < Cewek.getTotalCewek(); i++) {
            Cewek.getCewek(i).setJadwal(sc.next());
        }
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
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            String s = sc.next();
            Barang barang = new Barang(s.charAt(0), sc.nextInt(), sc.nextInt(), Iboy.getActiveIboy().getWaktu());
            Barang.addBarang(barang);
        }
    }
}
