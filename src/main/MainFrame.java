package main;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author goepfert
 */
public class MainFrame extends javax.swing.JFrame {

    private Config conf;
    private boolean hasRed = false;
    private boolean hasGreen = false;
    private boolean hasBlue = false;
    private Color selectedColor;
    //
    BufferedImage orgImg = null;
    BufferedImage scaledImg = null;
    BufferedImage convertedImg = null;
    //
    private JFileChooser fc;
    private JFileChooser fc_matrix;
    //
    private int redTH;
    private int greenTH;
    private int blueTH;
    //
    private MatrixFrame matrixFrame;
    private AnimationFrame animFrame;

    public MainFrame() {
        initComponents();

        conf = Config.getInstance();
        readSliderVals();
        setPickedColor();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        gridPanel = new main.GridPanel();
        colorPickPanel = new javax.swing.JPanel();
        redCB = new javax.swing.JCheckBox();
        greenCB = new javax.swing.JCheckBox();
        blueCB = new javax.swing.JCheckBox();
        prevPanel = new main.PrevPanel();
        setBGButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        imagePanel = new javax.swing.JPanel();
        blueSlider = new javax.swing.JSlider();
        applyTHButton = new javax.swing.JButton();
        redSlider = new javax.swing.JSlider();
        greenSlider = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        loadImageButton = new javax.swing.JButton();
        loadMatrixButton = new javax.swing.JButton();
        saveMatrixButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        start3DButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GobeSimulator v0.3");
        setName("mainFrame");
        setResizable(false);

        gridPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout gridPanelLayout = new javax.swing.GroupLayout(gridPanel);
        gridPanel.setLayout(gridPanelLayout);
        gridPanelLayout.setHorizontalGroup(
            gridPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        gridPanelLayout.setVerticalGroup(
            gridPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 318, Short.MAX_VALUE)
        );

        colorPickPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Colorpicker"));

        redCB.setText("Red");
        redCB.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                redCBStateChanged(evt);
            }
        });

        greenCB.setText("Green");
        greenCB.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                greenCBStateChanged(evt);
            }
        });

        blueCB.setText("Blue");
        blueCB.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                blueCBStateChanged(evt);
            }
        });

        prevPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout prevPanelLayout = new javax.swing.GroupLayout(prevPanel);
        prevPanel.setLayout(prevPanelLayout);
        prevPanelLayout.setHorizontalGroup(
            prevPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
        prevPanelLayout.setVerticalGroup(
            prevPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 56, Short.MAX_VALUE)
        );

        setBGButton.setText("Apply");
        setBGButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setBGButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("<html>set color as<br> background</html>");

        javax.swing.GroupLayout colorPickPanelLayout = new javax.swing.GroupLayout(colorPickPanel);
        colorPickPanel.setLayout(colorPickPanelLayout);
        colorPickPanelLayout.setHorizontalGroup(
            colorPickPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(colorPickPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(colorPickPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(colorPickPanelLayout.createSequentialGroup()
                        .addGroup(colorPickPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(redCB)
                            .addComponent(greenCB)
                            .addComponent(blueCB))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(prevPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(colorPickPanelLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(setBGButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        colorPickPanelLayout.setVerticalGroup(
            colorPickPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(colorPickPanelLayout.createSequentialGroup()
                .addGroup(colorPickPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(colorPickPanelLayout.createSequentialGroup()
                        .addComponent(redCB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(greenCB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(blueCB))
                    .addGroup(colorPickPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(prevPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(colorPickPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(setBGButton)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        imagePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Set RGB Thresholds"));
        imagePanel.setLayout(new java.awt.GridBagLayout());

        blueSlider.setMaximum(255);
        blueSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                blueSliderStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 164;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 6);
        imagePanel.add(blueSlider, gridBagConstraints);

        applyTHButton.setText("Apply");
        applyTHButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyTHButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 6);
        imagePanel.add(applyTHButton, gridBagConstraints);

        redSlider.setMajorTickSpacing(1);
        redSlider.setMaximum(255);
        redSlider.setMinorTickSpacing(1);
        redSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                redSliderStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 164;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 6);
        imagePanel.add(redSlider, gridBagConstraints);

        greenSlider.setMaximum(255);
        greenSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                greenSliderStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 164;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 6);
        imagePanel.add(greenSlider, gridBagConstraints);

        jLabel1.setText("Red");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        imagePanel.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Green");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        imagePanel.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Blue");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        imagePanel.add(jLabel3, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("File Operations"));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        loadImageButton.setText("Load Image");
        loadImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadImageButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel2.add(loadImageButton, gridBagConstraints);

        loadMatrixButton.setText("Load Matrix");
        loadMatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadMatrixButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel2.add(loadMatrixButton, gridBagConstraints);

        saveMatrixButton.setText("Save Matrix");
        saveMatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMatrixButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel2.add(saveMatrixButton, gridBagConstraints);

        jLabel5.setText("Load  Image from File.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanel2.add(jLabel5, gridBagConstraints);

        jLabel6.setText("Load RGB Matrix from File.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanel2.add(jLabel6, gridBagConstraints);

        jLabel7.setText("Save RGB Matrix to File.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanel2.add(jLabel7, gridBagConstraints);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("3D Animation"));

        start3DButton.setText("Start ");
        start3DButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                start3DButtonActionPerformed(evt);
            }
        });

        jLabel8.setText("<html>Start or Update 3D <br>Animation</html>");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(start3DButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(start3DButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(colorPickPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(97, 135, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(closeButton)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(gridPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gridPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(closeButton))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(colorPickPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(imagePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void redCBStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_redCBStateChanged
        if (redCB.isSelected()) {
            hasRed = true;
        } else {
            hasRed = false;
        }

        setPickedColor();
    }//GEN-LAST:event_redCBStateChanged

    private void greenCBStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_greenCBStateChanged
        if (greenCB.isSelected()) {
            hasGreen = true;
        } else {
            hasGreen = false;
        }

        setPickedColor();
    }//GEN-LAST:event_greenCBStateChanged

    private void blueCBStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_blueCBStateChanged
        if (blueCB.isSelected()) {
            hasBlue = true;
        } else {
            hasBlue = false;
        }

        setPickedColor();
    }//GEN-LAST:event_blueCBStateChanged

    private void setPickedColor() {
        int r = 0;
        int g = 0;
        int b = 0;

        if (hasRed) {
            r = 255;
        }
        if (hasGreen) {
            g = 255;
        }
        if (hasBlue) {
            b = 255;
        }

        selectedColor = new Color(r, g, b);

        prevPanel.setSelectedColor(selectedColor);
        gridPanel.setSelectedColor(selectedColor);
    }

    private void setBGButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setBGButtonActionPerformed
        gridPanel.setAllColors(selectedColor);
        gridPanel.repaint();
    }//GEN-LAST:event_setBGButtonActionPerformed

    private void applyTHButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyTHButtonActionPerformed
        applyTH();
    }//GEN-LAST:event_applyTHButtonActionPerformed

    private void redSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_redSliderStateChanged
        readSliderVals();
        applyTH();
    }//GEN-LAST:event_redSliderStateChanged

    private void greenSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_greenSliderStateChanged
        readSliderVals();
        applyTH();
    }//GEN-LAST:event_greenSliderStateChanged

    private void blueSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_blueSliderStateChanged
        readSliderVals();
        applyTH();
    }//GEN-LAST:event_blueSliderStateChanged

    private void readSliderVals() {
        redTH = redSlider.getValue();
        greenTH = greenSlider.getValue();
        blueTH = blueSlider.getValue();
    }

    private void applyTH() {
        if (scaledImg != null) {
            //convertColorArray(gridPanel.getColorArray());
            //gridPanel.repaint();            
            convertedImg = convertImg(scaledImg);
            showImg(convertedImg);
        }
    }

    private void loadMatrixButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadMatrixButtonActionPerformed
        if (fc_matrix == null) {
            fc_matrix = new JFileChooser();
        }

        int returnVal = fc_matrix.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fc_matrix.getSelectedFile();
                System.out.println("Loading file: " + file.getName());
                gridPanel.setColorArray(getColorArrayFromFile(file));
                gridPanel.repaint();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Loading cancelled by user.");
        }
    }//GEN-LAST:event_loadMatrixButtonActionPerformed

    private void loadImageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadImageButtonActionPerformed

        orgImg = null;

        if (fc == null) {
            fc = new JFileChooser();
            fc.addChoosableFileFilter(new ImageFilter());
            fc.setAcceptAllFileFilterUsed(false);
            fc.setAccessory(new ImagePreview(fc));
        }

        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                orgImg = ImageIO.read(file);
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Attaching file: " + file.getName());
        } else {
            System.out.println("Attachment cancelled by user.");
        }

        if (orgImg != null) {
            scaledImg = Tools.resizeImage(orgImg, conf.getnX(), conf.getnY());
            convertedImg = scaledImg;
            showImg(scaledImg);
        }
    }//GEN-LAST:event_loadImageButtonActionPerformed

    private void saveMatrixButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMatrixButtonActionPerformed
        if (matrixFrame == null) {
            matrixFrame = new MatrixFrame();
        }

        matrixFrame.setMatrix(gridPanel.getColorArray(), conf.getnX(), conf.getnY());
        matrixFrame.setVisible(true);
    }//GEN-LAST:event_saveMatrixButtonActionPerformed

    private void start3DButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_start3DButtonActionPerformed

        if (animFrame == null) {
            animFrame = new AnimationFrame();
            animFrame.init(gridPanel.getColorArray(), conf.getnX(), conf.getnY());
        } else {
            animFrame.reloadSpheres(gridPanel.getColorArray());
        }
        animFrame.setVisible(true);
    }//GEN-LAST:event_start3DButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private Color[] getColorArrayFromFile(File file) throws FileNotFoundException {

        int nX = conf.getnX();
        int nY = conf.getnY();
        Color[] colorArray = new Color[nX * nY];

        String readText = new String();
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String str;
            while ((str = in.readLine()) != null) {
                readText += str;
            }
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        //System.out.println(readText);

        int nStrings = 3 * nY * nX / 8;
        int nStringsRow = nStrings / nX;
        int nStringsRowColor = nStringsRow / 3;

        StringTokenizer sT = new StringTokenizer(readText, " ");
        if (sT.countTokens() != nStrings) {
            System.err.println("wrong dimensions!");
            System.err.println("found nTokens: " + sT.countTokens());
            System.err.println("but calcTokens is: " + nStrings);
            return colorArray;
        }

        //copy tokens to string array
        String[] colString = new String[nStrings];
        int idx = 0;
        while (sT.hasMoreTokens()) {
            String thisToken = sT.nextToken();
            thisToken = thisToken.replace("B", "");
            thisToken = thisToken.replace(",", "");
            thisToken = thisToken.replace(" ", "");

            colString[idx] = thisToken;
            idx++;
        }

        int sIdx = 0;
        int bIdx = 0;
        for (int xIdx = 0; xIdx < nX; xIdx++) {
            sIdx = xIdx * nStringsRow;
            for (int yIdx = 0; yIdx < nY; yIdx++) {

                int r = 0;
                int g = 0;
                int b = 0;
                if ("1".equals(colString[sIdx].charAt(bIdx) + "")) {
                    g = 255;
                }
                if ("1".equals(colString[sIdx + 1 * nStringsRowColor].charAt(bIdx) + "")) {
                    r = 255;
                }
                if ("1".equals(colString[sIdx + 2 * nStringsRowColor].charAt(bIdx) + "")) {
                    b = 255;
                }
                colorArray[(yIdx * nX) + xIdx] = new Color(r, g, b);

                bIdx++;
                //reset byte index
                if ((yIdx + 1) % 8 == 0) {
                    bIdx = 0;
                    sIdx++;
                }
            }//end col
        }//end row

        colorArray = Tools.shiftMatrix(colorArray, nX, nY);

        return colorArray;
    }

    private BufferedImage convertImg(BufferedImage scaledImg) {

        BufferedImage conImg = new BufferedImage(scaledImg.getWidth(), scaledImg.getHeight(), scaledImg.getType());

        int nX = conf.getnX();
        int nY = conf.getnY();        

        int[] rgb = new int[nX * nY];
        int[] modrgb = new int[nX * nY];

        scaledImg.getRGB(0, 0, nX, nY, rgb, 0, nX); //Get all pixels

        for (int yIdx = 0; yIdx < nY; yIdx++) {
            for (int xIdx = 0; xIdx < nX; xIdx++) {

                Color color = new Color(rgb[(yIdx * nX) + xIdx]);
                int r = 0;
                int g = 0;
                int b = 0;

                r = getMyColor(color.getRed(), redTH);
                g = getMyColor(color.getGreen(), greenTH);
                b = getMyColor(color.getBlue(), blueTH);     

                modrgb[(yIdx * nX) + xIdx] = Tools.argb(255, r, g, b);
            }
        }

        conImg.setRGB(0, 0, nX, nY, modrgb, 0, nX);

        return conImg;
    }

    private int getMyColor(int color, int colorTH) {
        int c = 0;
        int diff = 0;
        float colorWidth = 0f;
        
        int colorDepth = conf.getColorDepth();

        if (color > colorTH) {
            diff = 255 - colorTH;
            colorWidth = ((float) diff) / ((float) colorDepth);
            for (int i = 0; i < colorDepth; i++) {
                if (color > colorTH + i * colorWidth && color <= colorTH + (i + 1) * colorWidth) {
                    c = (int) (colorTH + (i + 1) * colorWidth);
                    break;
                }
            }
        }

        return c;
    }

    private void showImg(BufferedImage img) {
        int nX = conf.getnX();
        int nY = conf.getnY();

        int[] rgb = new int[nX * nY];
        Color[] colorArray = new Color[nX * nY];

        img.getRGB(0, 0, nX, nY, rgb, 0, nX); //Get all pixels
        for (int yIdx = 0; yIdx < nY; yIdx++) {
            for (int xIdx = 0; xIdx < nX; xIdx++) {
                colorArray[(yIdx * nX) + xIdx] = new Color(rgb[(yIdx * nX) + xIdx]);
            }
        }

        gridPanel.setColorArray(colorArray);
        gridPanel.repaint();
    }

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
        /*
         * try { for (javax.swing.UIManager.LookAndFeelInfo info :
         * javax.swing.UIManager.getInstalledLookAndFeels()) { if
         * ("Nimbus".equals(info.getName())) {
         * javax.swing.UIManager.setLookAndFeel(info.getClassName()); break; } }
         * } catch (ClassNotFoundException ex) {
         * java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE,
         * null, ex); } catch (InstantiationException ex) {
         * java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE,
         * null, ex); } catch (IllegalAccessException ex) {
         * java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE,
         * null, ex); } catch (javax.swing.UnsupportedLookAndFeelException ex) {
         * java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE,
         * null, ex); }
         *
         */
        //</editor-fold>        

        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
            //UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyTHButton;
    private javax.swing.JCheckBox blueCB;
    private javax.swing.JSlider blueSlider;
    private javax.swing.JButton closeButton;
    private javax.swing.JPanel colorPickPanel;
    private javax.swing.JCheckBox greenCB;
    private javax.swing.JSlider greenSlider;
    private main.GridPanel gridPanel;
    private javax.swing.JPanel imagePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton loadImageButton;
    private javax.swing.JButton loadMatrixButton;
    private main.PrevPanel prevPanel;
    private javax.swing.JCheckBox redCB;
    private javax.swing.JSlider redSlider;
    private javax.swing.JButton saveMatrixButton;
    private javax.swing.JButton setBGButton;
    private javax.swing.JButton start3DButton;
    // End of variables declaration//GEN-END:variables
}
