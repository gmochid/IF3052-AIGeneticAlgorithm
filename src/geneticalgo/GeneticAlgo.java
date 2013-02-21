/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import model.*;
import parser.Parser;
/**
 *
 * @author Yulianti Oenang
 */
public class GeneticAlgo {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Parser.parseGeneralFile("file.txt");
        Parser.parseScheduleFile("file2.txt");

        initializePopulation();
        /*
        populasi = new ArrayList<Arrangement>();
        for (int i = 0; i < 5; i++) {
            Arrangement a = new Arrangement(14);
            a.validate();
            populasi.add(a);
        }
         * 
         */
        for (Arrangement a: populasi) {
            a.validate();
            System.out.println(a.getArrangement() + " " + a.calculateTotalEnlightenment());
        }

        int i=0;
        // SAMPE BERHENTI
        while(i<=100){
            crossingOver();
            i++;
            System.out.println(populasi.get(0).getFinalArrangement() + " " + populasi.get(0).calculateTotalEnlightenment());
        }

        System.out.println("Final : " + populasi.get(0).getFinalArrangement() + " " + populasi.get(0).calculateTotalEnlightenment());
    }

    public static void initializePopulation() {
        populasi = new ArrayList<Arrangement>();
        int minggu = Iboy.getActiveIboy().getMinggu().intValue();
        int hari = minggu * 7 * 10;
        char[] jadwal = new char[hari];
        int jumCe = Cewek.getTotalCewek();
        int jum0max = (int) (0.4 * jumCe);
        int panjangArray = jum0max + jumCe;
        Integer time = new Integer(0);
        int counter = 0;
        Random x = new Random();
        for (int m = 0; m < 4; m++) {
            time = 0;
            for (int i = 0; i < (hari / 10); i++) {
                Cewek.resetAll();
                for (int j = 0; j < 10; j++) {
                    counter = 0;
                    StringBuilder pilihan = new StringBuilder();
                    for (int k = 1; k <= jumCe; k++) {
                        if (Cewek.getCewek(k).isDateable(time)) {
                            pilihan.append(k);
                            counter++;
                        }
                    }
                    int len = pilihan.capacity();
                    for (int k = 0; k < len * 3 / 10; k++) {
                        pilihan.append(0);
                        counter++;
                    }
                    String available = pilihan.toString();

                    int rnd = x.nextInt(counter);

                    jadwal[time] = available.charAt(rnd);
                    time++;
                }
            }

            // populasi[m] = new Arrangement(String.valueOf(jadwal));
            populasi.add(new Arrangement(String.valueOf(jadwal)));
        }
    }

    private static ArrayList<Arrangement> populasi;

    private static void crossingOver() {
        ArrayList<Arrangement> children = new ArrayList<Arrangement>();
        for (int i = 0; i < populasi.size(); i++) {
            for (int j = i + 1; j < populasi.size(); j++) {
                children.add(new Arrangement(populasi.get(i).getArrangement().substring(0)));
                children.add(new Arrangement(populasi.get(j).getArrangement().substring(0)));
            }
        }
        for (int i = 0; i < (children.size() / 2); i++) {
            int x = (2 * i);
            int y = (2 * i) + 1;
            children.get(x).crossOver(children.get(y));
        }
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
}
