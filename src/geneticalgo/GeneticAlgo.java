/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgo;
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
    }
}
