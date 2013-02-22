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
        System.out.println(S);
        screen display = new simulasi(1150, 600, S);
        
        p.add(display);
        f.add(p);
        f.pack();
        
        f.setVisible(true);
        display.loadContent();
    }
}
