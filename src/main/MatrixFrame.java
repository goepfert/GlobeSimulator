/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author goepfert
 */
public class MatrixFrame extends javax.swing.JFrame {

    private Config conf;
    private JFileChooser fc;
    private String text;

    public MatrixFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        conf = Config.getInstance();
        text = new String();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        matrixTextArea = new javax.swing.JTextArea();
        saveButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        editTB = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Globe Array");

        matrixTextArea.setColumns(20);
        matrixTextArea.setEditable(false);
        matrixTextArea.setRows(5);
        jScrollPane1.setViewportView(matrixTextArea);

        saveButton.setText("Save to File");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        editTB.setText("Allow editing");
        editTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editTBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(saveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(editTB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(closeButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(closeButton)
                    .addComponent(editTB))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        if (fc == null) {
            fc = new JFileChooser();
        }

        int returnVal = fc.showSaveDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fc.getSelectedFile();
                System.out.println("Saving: " + file.getName() + ".");

                Writer output = null;
                output = new BufferedWriter(new FileWriter(file));
                output.write(text);
                output.close();
            } catch (IOException ex) {
                Logger.getLogger(MatrixFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("Save command cancelled by user.");
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void editTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editTBActionPerformed
        if (editTB.isSelected()) {
            matrixTextArea.setEditable(true);
        } else {
            matrixTextArea.setEditable(false);
        }
    }//GEN-LAST:event_editTBActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MatrixFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MatrixFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MatrixFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MatrixFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MatrixFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JToggleButton editTB;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea matrixTextArea;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables

    void setMatrix(Color[] colors, int nX, int nY) {

        text = "";

        Color[] shiftedColors = Tools.shiftMatrix(colors, nX, nY);

        byte[] red = new byte[nY];   //is red on in this column
        byte[] green = new byte[nY]; //is green on in this column
        byte[] blue = new byte[nY];  //is blue on in this column

        for (int xIdx = 0; xIdx < nX; xIdx++) {
            for (int yIdx = 0; yIdx < nY; yIdx++) {

                if (shiftedColors[(yIdx * nX) + xIdx].getRed() > 0) {
                    red[yIdx] = 1;
                } else {
                    red[yIdx] = 0;
                }

                if (shiftedColors[(yIdx * nX) + xIdx].getGreen() > 0) {
                    green[yIdx] = 1;
                } else {
                    green[yIdx] = 0;
                }

                if (shiftedColors[(yIdx * nX) + xIdx].getBlue() > 0) {
                    blue[yIdx] = 1;
                } else {
                    blue[yIdx] = 0;
                }

            }// end column

            //fill red, green, blue
            //http://stackoverflow.com/questions/1270760/passing-a-string-by-reference-in-java
            text += addArrayToText(green);
            text += addArrayToText(red);
            text += addArrayToText(blue);

            text += "\n";

        }//end row

        matrixTextArea.setText(text);

    }//end setMatrix

    private String addArrayToText(byte[] color) {

        String myText = new String();

        for (int k = 0; k < (conf.getnY() / 8); k++) {
            byte colorByte = 0;
            for (int bitIdx = 0; bitIdx < 8; bitIdx++) {
                //System.out.println("k / b:  " + k + " / " + bitIdx);

                if (color[k * 8 + Math.abs(bitIdx - 7)] > 0) {
                    colorByte |= (1 << bitIdx);
                } else {
                    colorByte &= ~(1 << bitIdx);
                }

            }//end one byte

            //System.out.println(Tools.toBinaryString(colorByte));
            myText += ("B" + Tools.toBinaryString(colorByte) + ", ");
        }//next red byte in column

        return myText;
    }
}
