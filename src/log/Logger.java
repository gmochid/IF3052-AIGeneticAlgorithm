/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package log;

/**
 *
 * @author Andhika
 */
public class Logger {
    private static LogGUI window;
    public static int countCrossOver = 0;
    public static int countMutation = 0;

    public static void summonWindow() {
        Logger.window = LogGUI.singleton();
        
        Logger.window.setVisible(true);
    }
    
    public static void log(String s) {
        Logger.window.addLog(s);
    }
    
    public static void log2(String s) {
        Logger.window.addLogAdv(s);
    }
    
    public static void updateT2() {
        Logger.window.updateTextarea2();
    }
}
