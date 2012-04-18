package main;

import java.util.Properties;
import javax.swing.ImageIcon;

/**
 *
 * @author goepfert
 */
public class ConfigFrame extends javax.swing.JFrame {

    private Config conf;

    public ConfigFrame() {
        initComponents();

        conf = Config.getInstance();
        readValues();

        ImageIcon imageIcon = new ImageIcon("Logo_SL_Bloom_4.png");
        this.setIconImage(imageIcon.getImage());
        
        setLocationRelativeTo(getParent());
    }

    private void readValues() {
        Properties props = conf.getConfProps();

        nXSpinner.setValue(Integer.parseInt(props.getProperty("nX")));
        nYSpinner.setValue(Integer.parseInt(props.getProperty("nY")));
        colorDepthSpinner.setValue(Integer.parseInt(props.getProperty("colorDepth")));


        sphereSizeSlider.setValue((int) Float.parseFloat(props.getProperty("sphereSize")) * 10);
        //lightSourceBrightnessSlider.setValue((int)Float.parseFloat(props.getProperty("lightSourceBrightness")) * 10);
        lightSourceBrightnessSlider.setValue(5);
        ledBrightnessSlider.setValue((int) Float.parseFloat(props.getProperty("ledBrightness")) * 10);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        applyButton = new javax.swing.JButton();
        Close = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        rotationSpeedSlider = new javax.swing.JSlider();
        jLabel5 = new javax.swing.JLabel();
        sphereSizeSlider = new javax.swing.JSlider();
        lightSourceBrightnessSlider = new javax.swing.JSlider();
        ledBrightnessSlider = new javax.swing.JSlider();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        colorDepthSpinner = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        nYSpinner = new javax.swing.JSpinner();
        nXSpinner = new javax.swing.JSpinner();
        freeMoveTB = new javax.swing.JToggleButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Preferences");
        setResizable(false);

        applyButton.setText("Apply");
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });

        Close.setText("Close");
        Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseActionPerformed(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridBagLayout());

        rotationSpeedSlider.setMaximum(30000);
        rotationSpeedSlider.setMinimum(500);
        rotationSpeedSlider.setMinorTickSpacing(200);
        rotationSpeedSlider.setValue(10000);
        rotationSpeedSlider.setInverted(true);
        rotationSpeedSlider.setPreferredSize(new java.awt.Dimension(70, 50));
        rotationSpeedSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rotationSpeedSliderStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 164;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(rotationSpeedSlider, gridBagConstraints);

        jLabel5.setText("LED Brightness");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel5, gridBagConstraints);

        sphereSizeSlider.setMajorTickSpacing(1);
        sphereSizeSlider.setMaximum(30);
        sphereSizeSlider.setMinorTickSpacing(1);
        sphereSizeSlider.setValue(10);
        sphereSizeSlider.setPreferredSize(new java.awt.Dimension(70, 50));
        sphereSizeSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sphereSizeSliderStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 164;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(sphereSizeSlider, gridBagConstraints);

        lightSourceBrightnessSlider.setMajorTickSpacing(1);
        lightSourceBrightnessSlider.setMaximum(8);
        lightSourceBrightnessSlider.setMinimum(1);
        lightSourceBrightnessSlider.setMinorTickSpacing(1);
        lightSourceBrightnessSlider.setValue(5);
        lightSourceBrightnessSlider.setPreferredSize(new java.awt.Dimension(70, 50));
        lightSourceBrightnessSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                lightSourceBrightnessSliderStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 164;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(lightSourceBrightnessSlider, gridBagConstraints);

        ledBrightnessSlider.setMajorTickSpacing(1);
        ledBrightnessSlider.setMaximum(40);
        ledBrightnessSlider.setMinimum(1);
        ledBrightnessSlider.setMinorTickSpacing(1);
        ledBrightnessSlider.setValue(25);
        ledBrightnessSlider.setPreferredSize(new java.awt.Dimension(70, 50));
        ledBrightnessSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ledBrightnessSliderStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 164;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(ledBrightnessSlider, gridBagConstraints);

        jLabel6.setText("Rotation Speed");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel6, gridBagConstraints);

        jLabel4.setText("Light Source Brightness");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel3.setText("LED Size");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel3, gridBagConstraints);

        colorDepthSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 16, 5, 5);
        jPanel1.add(colorDepthSpinner, gridBagConstraints);

        jLabel2.setText("Color Depth");
        jLabel2.setToolTipText("grayscale of each individual color (RGB)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel1.setText("Number of LEDs");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel1, gridBagConstraints);

        nYSpinner.setModel(new javax.swing.SpinnerNumberModel(40, 8, 80, 8));
        nYSpinner.setToolTipText("nY");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
        jPanel1.add(nYSpinner, gridBagConstraints);

        nXSpinner.setModel(new javax.swing.SpinnerNumberModel(200, 1, 400, 10));
        nXSpinner.setToolTipText("nX");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanel1.add(nXSpinner, gridBagConstraints);

        freeMoveTB.setSelected(true);
        freeMoveTB.setText("Auto Rotate");
        freeMoveTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                freeMoveTBActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 16, 5, 5);
        jPanel1.add(freeMoveTB, gridBagConstraints);

        jLabel7.setText("Free Aspect / Auto Rotate");
        jLabel7.setToolTipText("Free Aspect: Move Mouse + Left Mouse Button Pressed for Rotation; Move Mouse + 3rd Button (Wheel) Pressed for Zoom");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel7, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(applyButton)
                        .addGap(10, 10, 10)
                        .addComponent(Close)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(applyButton)
                    .addComponent(Close))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtonActionPerformed
        Properties props = conf.getConfProps();
        props.setProperty("nX", nXSpinner.getValue().toString());
        props.setProperty("nY", nYSpinner.getValue().toString());
        props.setProperty("colorDepth", colorDepthSpinner.getValue().toString());
        conf.notifySubscribers();
    }//GEN-LAST:event_applyButtonActionPerformed

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_CloseActionPerformed

    private void sphereSizeSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sphereSizeSliderStateChanged
        float size = (float) (sphereSizeSlider.getValue() / 10.);
        Properties props = conf.getConfProps();
        props.setProperty("sphereSize", size + "");
        conf.notifySubscriber("animFrame");
    }//GEN-LAST:event_sphereSizeSliderStateChanged

    private void lightSourceBrightnessSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_lightSourceBrightnessSliderStateChanged
        float val = (float) (lightSourceBrightnessSlider.getValue() / 10.f);
        Properties props = conf.getConfProps();
        props.setProperty("lightSourceBrightness", "" + val);
        conf.notifySubscriber("animFrame");
    }//GEN-LAST:event_lightSourceBrightnessSliderStateChanged

    private void ledBrightnessSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ledBrightnessSliderStateChanged
        float val = (float) (ledBrightnessSlider.getValue() / 10.f);
        Properties props = conf.getConfProps();
        props.setProperty("ledBrightness", "" + val);
        conf.notifySubscriber("animFrame");
    }//GEN-LAST:event_ledBrightnessSliderStateChanged

    private void rotationSpeedSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rotationSpeedSliderStateChanged
        int val = rotationSpeedSlider.getValue();
        if (val == rotationSpeedSlider.getMaximum()) {
            val = 0;
        }
        Properties props = conf.getConfProps();
        props.setProperty("rotSpeed", "" + val);
        conf.notifySubscriber("animFrame");
    }//GEN-LAST:event_rotationSpeedSliderStateChanged

    private void freeMoveTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_freeMoveTBActionPerformed
        Properties props = conf.getConfProps();
        if (freeMoveTB.isSelected()) {
            props.setProperty("freeMove", "" + 0);
        } else {
            props.setProperty("freeMove", "" + 1);
        }
        conf.notifySubscriber("mainFrame"); //Yes, that's right!
    }//GEN-LAST:event_freeMoveTBActionPerformed

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
            java.util.logging.Logger.getLogger(ConfigFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfigFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfigFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfigFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ConfigFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Close;
    private javax.swing.JButton applyButton;
    private javax.swing.JSpinner colorDepthSpinner;
    private javax.swing.JToggleButton freeMoveTB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSlider ledBrightnessSlider;
    private javax.swing.JSlider lightSourceBrightnessSlider;
    private javax.swing.JSpinner nXSpinner;
    private javax.swing.JSpinner nYSpinner;
    private javax.swing.JSlider rotationSpeedSlider;
    private javax.swing.JSlider sphereSizeSlider;
    // End of variables declaration//GEN-END:variables
}
