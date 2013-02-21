/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author frilla
 */
public class item {
    private char[] type;
    private int length;

    public item() {
        type = new char[20];
        for (int i=0; i<20; i++) {
            type[i] = 0;
        }
        length = 0;
    }

    public void addItem(char c) {
        type[length] = c;
        length++;
    }
    
    public void useItem (char c) {
        int i = 0;
        while (type[i]!=c) {
            i++;
        }
        while (type[i]!=0) {
            type[i] = type[i+1];
        }
        length--;
    }

    public void renders(Graphics g) {
        Graphics2D canvas = (Graphics2D) g;
        BufferedImage img = null;
        for (int i =0; i<length; i++) {
            char c = type[i];
            try {
                img = ImageIO.read(new File("item/"+ c + ".jpg"));
            } catch (IOException e) {
                System.out.println("GAGAL LOAD ITEM");
                }
            canvas.drawImage(img, null, 810+((i%6)*50) , 430+((i/6)*50));
        }
    }

}
