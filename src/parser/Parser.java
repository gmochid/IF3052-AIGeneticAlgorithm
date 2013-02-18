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
            Barang barang = new Barang(sc.next(), sc.nextInt(), sc.nextInt());
            Barang.addBarang(barang);
        }
    }
}
