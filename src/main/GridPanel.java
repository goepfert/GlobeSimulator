/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author goepfert
 */
public final class GridPanel extends javax.swing.JPanel {

    private Graphics2D g2;
    private Config conf;
    private Color[] colorArray;
    private int nX;
    private int nY;
    private int nPixel;
    private int wRplus;
    private int hRplus;
    private int wR;
    private int hR;
    private Color selectedColor;

    public GridPanel() {
        initComponents();

        conf = Config.getInstance();
        nX = conf.getnX();
        nY = conf.getnY();
        nPixel = conf.getnPixel();

        colorArray = new Color[nX * nY];
        selectedColor = Color.BLACK;
        setAllColors(selectedColor);
    }

    public void setAllColors(Color color) {

        for (int yIdx = 0; yIdx < nY; yIdx++) {
            for (int xIdx = 0; xIdx < nX; xIdx++) {
                colorArray[(yIdx * nX) + xIdx] = color;
            }
        }
    }

    public void setColorArray(Color col[]) {        
        //System.arraycopy(col, 0, colorArray, 0, col.length);        
        colorArray = col;
    }

    public Color[] getColorArray() {
        return colorArray;
    }

    @Override
    protected void paintComponent(final Graphics g) {

        g2 = (Graphics2D) g;
        super.paintComponent(g);

        updateVals();

        for (int yIdx = 0; yIdx < nY; yIdx++) {
            for (int xIdx = 0; xIdx < nX; xIdx++) {
                g2.setColor(colorArray[(yIdx * nX) + xIdx]);
                g2.fillRect(xIdx * wRplus + nPixel, yIdx * hRplus + nPixel, wR, hR);
                //System.out.println("x / y: " + xIdx + " / " + yIdx);
            }
        }

    }

    private void updateVals() {

        wRplus = (getWidth() - nPixel) / nX;
        hRplus = (getHeight() - nPixel) / nY;

        wR = (getWidth() - (nX + 1) * nPixel) / nX;
        hR = (getHeight() - (nY + 1) * nPixel) / nY;

    }

    private int getColorIdx(int xPos, int yPos) {
        int Idx = -1;

        updateVals();

        for (int yIdx = 0; yIdx < nY; yIdx++) {
            for (int xIdx = 0; xIdx < nX; xIdx++) {

                if (xPos > (xIdx * wRplus + nPixel) && xPos <= (xIdx * wRplus + nPixel + wR)
                        && yPos > (yIdx * hRplus + nPixel) && yPos <= (yIdx * hRplus + nPixel + hR)) {                    
                    return (yIdx * nX) + xIdx;
                }
            }
        }

        return Idx;
    }

    public void setSelectedColor(Color selectedColor) {
        this.selectedColor = selectedColor;
    }

    private void mousePressedAndDragged(java.awt.event.MouseEvent evt) {
        int xPos = evt.getX();
        int yPos = evt.getY();
                
        int colorIdx = getColorIdx(xPos, yPos);

        if (colorIdx < 0) {
            return;
        }

        colorArray[colorIdx] = selectedColor;
        this.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        mousePressedAndDragged(evt);
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        mousePressedAndDragged(evt);
    }//GEN-LAST:event_formMouseDragged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
