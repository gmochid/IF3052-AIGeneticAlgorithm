/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgo;
import ai.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import model.*;
import parser.Parser;
import log.Logger;
/**
 *
 * @author Yulianti Oenang
 */
public class GeneticAlgo {
    
    private static ArrayList<Arrangement> populasi;
    
    public static String GA() {
        Logger.log("Loading general file...");
        Parser.parseGeneralFile("file.txt");
        Logger.log("General file loaded.");
        
        Logger.log("Loading schedule file...");
        Parser.parseScheduleFile("file2.txt");
        Logger.log("Schedule file loaded.");

        Logger.log("Initializing population...");
        initializePopulation();
        Logger.log("Population initialized. See 'Genes' tab for details.");
        /*
        populasi = new ArrayList<Arrangement>();
        for (int i = 0; i < 5; i++) {
            Arrangement a = new Arrangement(14);
            a.validate();
            populasi.add(a);
        }
         * 
         */
        Logger.log2("Initial population:");
        for (Arrangement a: populasi) {
            a.validate();
            Logger.log2(a.getArrangement() + " " + a.calculateTotalEnlightenment());
        }
        Logger.log2("\n");

        Logger.log("Applying genetic algorithm...");
        long start = System.currentTimeMillis();

        int i=0;
        // SAMPE BERHENTI
        while(i<=100){
            crossingOver();
            i++;
            Logger.log2(populasi.get(0).getFinalArrangement() + " " + populasi.get(0).calculateTotalEnlightenment());
            Logger.log2("");
            if(populasi.get(0).calculateTotalEnlightenment() > 200)
                break;
        }
        
        Logger.log2("Final result:");
        Logger.log2(populasi.get(0).getFinalArrangement() + " ");
        Logger.log2("Enlightenment: " + populasi.get(0).calculateTotalEnlightenment());

        long now = System.currentTimeMillis();
        long delta = now - start;
        Logger.log("Genetic algorithm took " + String.valueOf(delta) + " ms");
        
        return populasi.get(0).getFinalArrangement();
    }

    public static void initializePopulation() {
        populasi            = new ArrayList<Arrangement>();
        int minggu          = Iboy.getActiveIboy().getMinggu();
        int jam             = minggu * 7 * 10;
        char[] jadwal       = new char[jam];
        int time            = 0;
        int counter         = 0;
        Random x            = new Random();
        for (int m = 0; m < 4; m++) {                               //loop untuk hasilkan 4 (jadwal) individu dalam populasi atau 4 parent
            time = 0;                                               //time dari 0-140 menunjukkan jam, direset saat create new (jadwal) individu
            for (int i = 0; i < (jam / 10); i++) {                  //loop hari per minggunya
                Cewek.resetAll();                                   //kembalikan jumlah max-bisa-di-date per harinya si ce
                for (int j = 0; j < 10; j++) {                      //loop 10 jam per harinya 
                    counter = 0;                                    
                    StringBuilder pilihan = new StringBuilder();    //pilihan adalah daftar ce yang bisa di random untuk ngedate jam tsb
                    for (int k = 1; k <= Cewek.getTotalCewek(); k++) {
                        if (Cewek.getCewek(k).isDateable(time)) {
                            pilihan.append(k);                      //assign pilihan dengan cewekID
                            counter++;                              //counter jadi semacam 'length array' nya pilihan
                        }
                    }
                    int len = pilihan.capacity();                   //menambah nilai 0 ke dalam pilihan
                    for (int k = 0; k < len * 3 / 10; k++) {        
                        pilihan.append(0);
                        counter++;
                    }
                    String available = pilihan.toString();

                    int rnd = x.nextInt(counter);                   //randomisasi untuk memilih ce yg akan di assign

                    jadwal[time] = available.charAt(rnd);           //untuk jam ini, pilih ce ini
                    time++;
                }
            }

            populasi.add(new Arrangement(String.valueOf(jadwal)));
        }
    }

    
    
    private static void crossingOver() {
        int crosspoint;
        
        ArrayList<Arrangement> children = new ArrayList<Arrangement>();
        for (int i = 0; i < populasi.size(); i++) {
            for (int j = i + 1; j < populasi.size(); j++) {
                children.add(new Arrangement(populasi.get(i).getArrangement().substring(0)));
                children.add(new Arrangement(populasi.get(j).getArrangement().substring(0)));
            }
        }
        
        for(Arrangement ar: children) {
            ar.validate();
        }
        
        for (int i = 0; i < (children.size() / 2); i++) {
            int x = (2 * i);
            int y = (2 * i) + 1;
            crosspoint = children.get(x).crossOver(children.get(y));
            Random r = new Random();
            children.get(x).mutation2(r.nextInt(Iboy.getActiveIboy().getMinggu() * 70));
            children.get(y).mutation2(r.nextInt(Iboy.getActiveIboy().getMinggu() * 70));
            //children.get(x).mutation(crosspoint);
            //children.get(y).mutation(crosspoint);
        }

        /**
         * mutasinya ditaruh sini ya
         */
        for(Arrangement ar: children) {
            ar.validate();
        }

        Collections.sort(children, new ArrangementComparator());

        populasi.clear();

        int len = 0;
        for (int i = 1; i <= children.size(); i++) {
            int idx = children.size() - i;
            if(populasi.isEmpty()) {
                populasi.add(children.get(idx));
                len++;
            } else if(children.get(idx).getArrangement().equals(populasi.get(populasi.size() - 1).getArrangement())) {
            } else {
                populasi.add(children.get(idx));
                len++;
            }
            if (len == 3)
                break;
        }
        /*
        for (Arrangement ar : populasi) {
            System.out.print(ar.getArrangement() + " ");
            System.out.println(ar.calculateTotalEnlightenment());
        }*/
    }

    public static void main(String[] args) {
        System.out.println(GA());
    }
}
