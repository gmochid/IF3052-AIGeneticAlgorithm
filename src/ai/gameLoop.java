/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

/**
 *
 * @author frilla
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class gameLoop extends Thread{
    public static final int FRAME_RATE = 24;
    private float _period = 20000.0f/(float)FRAME_RATE; // Lama animasi
    private long _beforeTime = 0; // Waktu sebelum siklus dimulai
    private long _afterTime = 0; // Waktu setelah siklus selesai
    private int _timeDiff = 0; // Jarak waktu antara waktu mulai dan waktu selesai
    private int _sleepTime = 0; // waktu tidur
    private long lastUpdateTime; // waktu terakhir update
    private long lastRenderTime; //waktu terakhir render

    private screen display;
    private boolean isRun = true;

    public gameLoop(screen display) {
        this.display = display;
        isRun = true;
        System.out.println("Inisiasi GameLoop");
    }

    public boolean  isRun() {
        return isRun;
    }

    public void stopRun() {
        isRun = false;
    }

    public void run() {
        lastUpdateTime = System.currentTimeMillis() - (int)_period;
        lastRenderTime = System.currentTimeMillis() - (int)_period;
        while(isRun) {
                _beforeTime = System.currentTimeMillis();

                gameLoop.this.display.update((_beforeTime - lastUpdateTime) / 1000.0f);
                gameLoop.this.display.repaint();
                gameLoop.this.display.render((_beforeTime - lastRenderTime) / 1000.0f);

                _afterTime = System.currentTimeMillis();
                lastUpdateTime = _afterTime;
                lastRenderTime = _afterTime;
                _timeDiff = (int)_afterTime - (int)_beforeTime;
                _sleepTime = (int)_period - _timeDiff;

                //Memberikan jeda antar frame
                if(_sleepTime >= 0) {
                    try {
                        // Sleep time
                        Thread.sleep(_sleepTime);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
/*
                while(_sleepTime < 0) {
                    long curTime = System.currentTimeMillis();
                    // update game
                    gameLoop.this.display.update((curTime - lastUpdateTime) / 1000.0f);
                    lastUpdateTime = System.currentTimeMillis();
                    _sleepTime += _period;
                }
          */
        }
    }

}

