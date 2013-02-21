/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import ai.gameLoop;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
/**
 *
 * @author frilla
 */
public class screen extends Component{
    
    /**
     * GameLoop merupakan Thread yang utama game
 
 */

    // untuk render
    protected final int screenWidth;
    protected final int screenHeight;
    protected Graphics canvas;
    private BufferedImage img = null;
    
    protected int frameRate;
    private int frameCounter;
    private float timeFPSCount;
    
    protected gameLoop GameLoop;
    
    public screen(int width, int height) {
        this.screenHeight = height;
        this.screenWidth = width;
        setPreferredSize(new Dimension(screenWidth,screenHeight));
    }
    
    public void loadContent() {
        this.GameLoop = new gameLoop(this);
        this.GameLoop.start();
    }
    
    public void render(float elapsedTime) {
        frameCounter++;
        timeFPSCount += elapsedTime;
        if(timeFPSCount >= 1) {
            timeFPSCount = 0;
            frameRate = frameCounter;
            frameCounter = 0;
        }
    }
    
    public void update(float elapsedTime) {
        
    }
    
    public void paint(Graphics canvas) {    
        //img = ImageIO.read(null)
        /*Graphics2D g = (Graphics2D) canvas;
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("screen2.jpg"));
        } catch (IOException e) {
            }
        g.drawImage(img, null, 800, 0);*/
    }

}