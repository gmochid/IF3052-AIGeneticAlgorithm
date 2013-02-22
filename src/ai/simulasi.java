/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import model.Barang;
import model.Cewek;
import model.Iboy;

/**
 *
 * @author frilla
 */

public class simulasi extends screen{
    private iBoy player;
    private map peta;
    private item listItem;
    private String arrangement;
    
    private static boolean react;
    private static boolean buy;
    private static char girl;
    
    private static int day;
    private static int time;
    private static int energy;
    private static int curEnergy;
    private static int Money;
    private static int Gaji;
    private static int Love;
    
    static public int iterator;
    
    public simulasi(int width,int height, String S) {
        super(width, height);
        peta = new map (width, height);
        player = new iBoy();
        listItem = new item();
        arrangement = S;
        iterator = 0;
        day = 1;
        time = 10;
        girl = '0';
        react = false;
        buy = false;
        Love = 0;
        Iboy iboy = Iboy.getActiveIboy();
        Money = iboy.getModalUang();
        Gaji = iboy.getTambahanUang();
        energy = iboy.getEnergi();
        curEnergy = energy;
    }
    
    public void update(float elapsedTime) {
        Point p = searchHouse(arrangement.charAt(iterator));
        react = false;
        buy = false;
        
        int x = p.x;
        int y = p.y;
        
        //IF SCHEDULE FINISHED
        if (iterator == arrangement.length()) {
            try {
                gameLoop.sleep(0xc350);
            } catch (InterruptedException ex) {
                Logger.getLogger(simulasi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //IF IBOY ARRIVED--------------------------------------------
        if ((player.getX()==y)&&(player.getY()==x)) {
            char c = arrangement.charAt(iterator);
            //IF IBOY ARRIVED ON THE GIRL'S HOUSE
            if (!((c >= 'A')&&(c <= 'Z'))&&(c != '0')) {
                react = true;
                girl = c;
                char[] userBarang = whichItem(c).clone();
                for (int i = 0; i < userBarang.length; i++) {
                   listItem.useItem(userBarang[i]);
                }
                curEnergy = curEnergy - useEnergy(c);
                Love = Love + muchLove(c);
                repaint();
                try {
                    gameLoop.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(simulasi.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //IF IBOY WENT TO THE MALL
            if ((c >= 'A')&&(c <= 'Z')){
                listItem.addItem(c);
                buy = true;
                repaint();
                
                Barang item = Barang.getBarang(c);
                Money = Money - item.getHarga();
            }
            
            iterator = iterator + 1;
            repaint();
            
            //IF A DAY HAS PASSED
            if (iterator%10 == 0){
                player.setPosition(-100, -100);
                super.update(elapsedTime);
                repaint();
                time++;
                try {
                    gameLoop.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(simulasi.class.getName()).log(Level.SEVERE, null, ex);
                }
                player.reset();
                day++;
                time=10;
                curEnergy = energy;
                Money = Money + Gaji;
                super.update(elapsedTime);
            } else {
                time++;
                super.update(elapsedTime);
            }
        } else {
            goToPosition (x, y);
            super.update(elapsedTime);
        }        
    }
    
    public void goToPosition (int x, int y) {
        int i = (int)player.getY(); //6
        int j = (int)player.getX(); //9
        if ((x != i)||(y != j)) {
            if ((y > j)&&(peta.isPath(i,j+1))) {
                    j = j+1;
            } else
            if ((y < j)&&(peta.isPath(i,j-1))) {
                    j = j-1;
                } else
            if ((x < i)&&(peta.isPath(i-1,j))) {
                    i = i-1;
            } else
            if ((x >= i)&&(peta.isPath(i+1,j))) {
                    i = i+1;
            }
        }
        player.setPosition(j, i);
    }
    
    public char[] whichItem(char c) {
        Cewek cewek = Cewek.getCewek((int)c - (int)'0');
        char[] barang = new char[cewek.getPrerequisite().size()];
        for (int i = 0; i<cewek.getPrerequisite().size(); i++) {
            barang[i] = cewek.getPrerequisite().get(i).getBarangID();
        }
        return barang;
    }
    
    public int useEnergy (char c) {
        Cewek cewek = Cewek.getCewek((int)c - (int)'0');
        return cewek.getEnergiHabis();
    }
    
    public int muchLove (char c) {
     Cewek cewek = Cewek.getCewek((int)c - (int)'0');
        return cewek.getEnlightenment();   
    }
    
    public Point searchHouse (char c) {
        Point pos = new Point();
        if (c == '0') {
            pos.x = 5;
            pos.y = 9;
        } else 
        if (c == '1') {
            pos.x = 5;
            pos.y = 2;
        } else 
        if (c == '2') {
            pos.x = 5;
            pos.y = 3;
        } else 
        if (c == '3') {
            pos.x = 5;
            pos.y = 6;
        } else 
        if (c == '4') {
            pos.x = 5;
            pos.y = 13;
        } else 
        if (c == '5') {
            pos.x = 6;
            pos.y = 13;
        } else 
        if (c == '6') {
            pos.x = 2;
            pos.y = 4;
        } else 
        if (c == '7') {
            pos.x = 6;
            pos.y = 9;
        } else 
        if (c == '8') {
            pos.x = 8;
            pos.y = 11;
        } else 
        if (c == '9') {
            pos.x = 9;
            pos.y = 11;
        } else {
            pos.x = 5;
            pos.y = 4;
        }
        return pos;
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        peta.paint(g);
        listItem.renders(g);
        
        //DRAW GIRL
        if (react) {
            paintGirl(g, girl);
            player.drawHeart(g);
        } else 
        //DRAW BUY ITEM
        if (buy) {
            player.drawDollar(g);
        } else {
            player.render(g);
        }
        
        //DRAW ENERGY BAR
        g.setColor(Color.red);
        g.fillRect((int)(player.getX()*50) + 10, (int)(player.getY()*50) - 2, 30, 5);
        g.setColor(Color.green);
        g.fillRect((int)(player.getX()*50) + 10, (int)(player.getY()*50) - 2, (int)(((float)curEnergy/energy)*30), 5);
        
        
        //DRAW DATE & TIME
        g.setFont(new Font("Century Gothic", Font.BOLD, 22));
        g.setColor(Color.black);
        g.drawString("Day : "+day, 520, 35);
        g.drawString("Time : "+time+".00", 640, 35);
        g.drawString(""+Money, 690, 73);
        g.drawString(""+Love, 690, 115);
        
        
        //DRAW NIGHT
        if (time == 20) {
            paintBlack(g);
        }
    }
    
    
    public void paintGirl(Graphics canvas, char c) {
        Graphics2D g = (Graphics2D) canvas;
        BufferedImage img = null;
        BufferedImage face = null;
        try {
            img = ImageIO.read(new File("assets/girl/" + c + ".png"));
            face = ImageIO.read(new File("assets/girl/" + c + "a.png"));
        } catch (IOException e) {
            }
        if (c=='1'|| c == '7' || c=='9' ) {
            g.drawImage(img, null,((int)player.getX()*50)-15, ((int)player.getY()*50)-20);
            g.drawImage(face, null,((int)player.getX()*50)-80, ((int)player.getY()*50)-80);
        }
        else 
            if (c=='5'||c=='8') {
                g.drawImage(img, null,((int)player.getX()*50+30), ((int)player.getY()*50)-15);
                g.drawImage(face, null,((int)player.getX()*50)+10, ((int)player.getY()*50)-80);
            }
        else 
        { 
            g.drawImage(img, null,((int)player.getX()*50), ((int)player.getY()*50)-40); 
            g.drawImage(face, null,((int)player.getX()*50)-60, ((int)player.getY()*50)-80);
        }
    }
    
    public void paintBlack(Graphics canvas) {
        Graphics2D g = (Graphics2D) canvas;
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("assets/night.png"));
        } catch (IOException e) {
            }
        g.drawImage(img, null, 0, 0);
    }

}
