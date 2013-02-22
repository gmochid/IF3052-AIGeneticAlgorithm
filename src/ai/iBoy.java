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
public class iBoy {
    private float posX;
    private float posY;
    private int width;
    private int height;

    public iBoy() {
        System.out.println("create iBoy");
        posX = 9;
        posY = 4;
    }

    public void setPosition(float x, float y) {
        posX = x;
        posY = y;
    }
    
    public void update(float x, float y) {
        posX = x;
        posY = y;
    }
    
    public float getX() {
        return posX;
    }
    public float getY() {
        return posY;
    }

    public void render(Graphics g) {
        Graphics2D canvas = (Graphics2D) g;
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("iboy.png"));
        } catch (IOException e) {
            }
        canvas.drawImage(img, null,(int)posX*50, (int)posY*50);
    }

    public void drawHeart (Graphics g){
        Graphics2D canvas = (Graphics2D) g;
        BufferedImage img = null;
        BufferedImage heart = null;
        try {
            img = ImageIO.read(new File("iboy.png"));
            heart = ImageIO.read(new File("heart.png"));
        } catch (IOException e) {
            }
        canvas.drawImage(img, null,(int)posX*50, (int)posY*50);
        canvas.drawImage(heart, null,((int)posX*50)+40, ((int)posY*50)-10);
    }
    
    public void drawDollar (Graphics g){
        Graphics2D canvas = (Graphics2D) g;
        BufferedImage img = null;
        BufferedImage heart = null;
        try {
            img = ImageIO.read(new File("iboy.png"));
            heart = ImageIO.read(new File("buy.png"));
        } catch (IOException e) {
            }
        canvas.drawImage(img, null,(int)posX*50, (int)posY*50);
        canvas.drawImage(heart, null,((int)posX*50)+40, ((int)posY*50)-10);
    }
    
    public void reset() { //iBoy kembali ke rumah
        posX = 9;
        posY = 4;
    }
}
