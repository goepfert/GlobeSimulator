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
public final class PrevPanel extends javax.swing.JPanel {

    private Graphics2D g2;
    private Color selectedColor;

    public PrevPanel() {
        initComponents();

        selectedColor = Color.black;
    }

    public void setSelectedColor(Color selectedColor) {
        this.selectedColor = selectedColor;
        this.repaint();
    }

    @Override
    protected void paintComponent(final Graphics g) {

        g2 = (Graphics2D) g;
        super.paintComponent(g);

        g2.setColor(selectedColor);
        g2.fillRect(0, 0, getWidth(), getHeight());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
