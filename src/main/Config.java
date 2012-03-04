/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author goepfert
 */
public class Config {
    
    private int nX;
    private int nY;
    private int nPixel;


    public int getnX() {
        return nX;
    }

    public void setnX(int nX) {
        this.nX = nX;
    }

    public int getnY() {
        return nY;
    }

    public void setnY(int nY) {
        this.nY = nY;
    }
    
    public int getnPixel() {
        return nPixel;
    }

    public void setnPixel(int nPixel) {
        this.nPixel = nPixel;
    }
    
    private void setDefaults() {
        nX = 200;
        nY = 40;
        nPixel = 0;
    }
            
    private static Config instance = null;
    
    private Config() {
        setDefaults();
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }
    
}
