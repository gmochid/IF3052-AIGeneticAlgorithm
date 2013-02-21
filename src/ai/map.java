/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 *
 * @author frilla
 */
public class map {
    public int lebar;
    public int panjang;
    public static int[][] grid; //1--path, 0--bukan path
    String source;
    
    public map(int lebar, int number) {
        //lebar = 18;
        //panjang = 12;
        int[][] peta = {
          { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
          { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
          { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
          { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
          { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
          { 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0 },
          { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0 },
          { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
          { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
          { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
          { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
          { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        };
        grid = peta;
    }
    
    public void paint(Graphics canvas) {    
        //img = ImageIO.read(null)
        Graphics2D g = (Graphics2D) canvas;
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("assets/map.jpg"));
        } catch (IOException e) {
            }
        g.drawImage(img, null, 0, 0);
    }
    
    public static boolean isPath (int i, int j) {
        if (grid[i][j] == 1)
            return true;
        else
            return false;
    }
    
    public int getEle (int i, int j) {
        return grid[i][j];
    }
}
