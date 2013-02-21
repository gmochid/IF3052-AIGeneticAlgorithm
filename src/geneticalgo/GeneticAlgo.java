/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgo;
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
        
        /*Arrangement a = new Arrangement(7);
        a.validate();
        for (int i = 0; i < 7; i++) {
            System.out.println(a.getArrangement().substring(i * 10, (i+1) * 10));
        }*/
        Arrangement a = new Arrangement("011002201101111002020221000002");
        Arrangement b = new Arrangement("022210011101211000011212120021");
        a.crossOver(b);
        System.out.println(a.getArrangement());
        System.out.println(b.getArrangement());
    }
}
