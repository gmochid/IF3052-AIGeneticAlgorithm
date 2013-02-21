/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgo;
import java.util.ArrayList;
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
        
        ArrayList<Arrangement> AL = new ArrayList<Arrangement>();
        for (int i = 0; i < 4; i++) {
            AL.add(new Arrangement(14));
        }
        for (Arrangement a: AL) {
            System.out.println(a.getArrangement());
        }
    }
}
