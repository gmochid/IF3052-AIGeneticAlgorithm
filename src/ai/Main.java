/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import geneticalgo.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import log.Logger;

/**
 *
 * @author frilla
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        // Summon Logger
        Logger.summonWindow();
        
        JFrame f = new JFrame("Iboy");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel p = new JPanel();
        String S = GeneticAlgo.GA();

        Logger.log("Genetic algorithm completed. Printing log...");
        Logger.updateT2();
        Logger.log("Log printed.");
        Logger.log(Logger.countCrossOver + " crossovers");
        Logger.log(Logger.countMutation + " mutations");
        
        screen display = new simulasi(868, 600, S);
        
        p.add(display);
        f.add(p);
        f.pack();
        
        Logger.log("Loading visualization...");
        
        f.setVisible(true);
        display.loadContent();
        Logger.log("Iboy is ready to rock!");
        
    }
}
