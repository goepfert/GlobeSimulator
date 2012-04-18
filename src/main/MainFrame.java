package main;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author goepfert
 */
public class MainFrame extends javax.swing.JFrame implements Subscriber {

    private Config conf;
    private int nX;
    private int nY;
    private int colorDepth;
    private int freeMove;
    //
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
    private ConfigFrame confFrame;
    private TextInputFrame textFrame;

    public MainFrame() {
        initComponents();

        conf = Config.getInstance();
        conf.subscribe("mainFrame", this);
        readValues();

        readSliderVals();

        selectedColor = Color.BLACK;
        setPickedColor();
        
        ImageIcon imageIcon = new ImageIcon("Logo_SL_Bloom_4.png");
        this.setIconImage(imageIcon.getImage());        
    }

    private void readValues() {
        Properties prop = conf.getConfProps();

        nX = Integer.parseInt(prop.getProperty("nX"));
        nY = Integer.parseInt(prop.getProperty("nY"));
        colorDepth = Integer.parseInt(prop.getProperty("colorDepth"));
        freeMove = Integer.parseInt(prop.getProperty("freeMove"));
    }

    @Override
    public void readSubscription() {

        int tmp_nX = nX;
        int tmp_nY = nY;
        int tmp_freeMove = freeMove;

        readValues();
        reloadImg(orgImg);

        if (tmp_nX != nX || tmp_nY != nY || tmp_freeMove != freeMove) {
            if (animFrame != null) {
                animFrame.dispose();
                animFrame = null;
                conf.unsubscribe("animFrame");
                start3DButton.doClick();
                return;
            }
        }

        reloadAnim();
    }

    public void reloadAnim() {
        if (animFrame != null && animFrame.isVisible()) {
            animFrame.reloadAppearance(gridPanel.getColorArray());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        gridPanel = new main.GridPanel();
        colorPickPanel = new javax.swing.JPanel();
        prevPanel = new main.PrevPanel();
        setBGButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        selectColorButton = new javax.swing.JButton();
        applyTHButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        imagePanel = new javax.swing.JPanel();
        blueSlider = new javax.swing.JSlider();
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
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        textInputButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        start3DButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        exitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GobeSimulator v0.5 by SolderLab");
        setName("mainFrame");
        setResizable(false);

        gridPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout gridPanelLayout = new javax.swing.GroupLayout(gridPanel);
        gridPanel.setLayout(gridPanelLayout);
        gridPanelLayout.setHorizontalGroup(
            gridPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1199, Short.MAX_VALUE)
        );
        gridPanelLayout.setVerticalGroup(
            gridPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 317, Short.MAX_VALUE)
        );

        colorPickPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("ColorPicker"));
        colorPickPanel.setLayout(new java.awt.GridBagLayout());

        prevPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        prevPanel.setPreferredSize(new java.awt.Dimension(30, 30));
        prevPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                prevPanelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout prevPanelLayout = new javax.swing.GroupLayout(prevPanel);
        prevPanel.setLayout(prevPanelLayout);
        prevPanelLayout.setHorizontalGroup(
            prevPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );
        prevPanelLayout.setVerticalGroup(
            prevPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        colorPickPanel.add(prevPanel, gridBagConstraints);

        setBGButton.setText("Set");
        setBGButton.setToolTipText("And Apply RGB Color Thresholds");
        setBGButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setBGButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        colorPickPanel.add(setBGButton, gridBagConstraints);

        jLabel4.setText("<html>Set Color as<br>Background</html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        colorPickPanel.add(jLabel4, gridBagConstraints);

        selectColorButton.setText("Select");
        selectColorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectColorButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        colorPickPanel.add(selectColorButton, gridBagConstraints);

        applyTHButton.setText("Apply");
        applyTHButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyTHButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        colorPickPanel.add(applyTHButton, gridBagConstraints);

        jLabel9.setText("<html>Apply RGB<br>Thresholds</html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        colorPickPanel.add(jLabel9, gridBagConstraints);

        imagePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Adjust RGB Thresholds"));
        imagePanel.setLayout(new java.awt.GridBagLayout());

        blueSlider.setMaximum(255);
        blueSlider.setValue(0);
        blueSlider.setPreferredSize(new java.awt.Dimension(70, 50));
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
        gridBagConstraints.insets = new java.awt.Insets(10, 6, 10, 6);
        imagePanel.add(blueSlider, gridBagConstraints);

        redSlider.setMajorTickSpacing(1);
        redSlider.setMaximum(255);
        redSlider.setMinorTickSpacing(1);
        redSlider.setValue(0);
        redSlider.setPreferredSize(new java.awt.Dimension(70, 50));
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
        gridBagConstraints.insets = new java.awt.Insets(10, 6, 10, 6);
        imagePanel.add(redSlider, gridBagConstraints);

        greenSlider.setMaximum(255);
        greenSlider.setValue(0);
        greenSlider.setPreferredSize(new java.awt.Dimension(70, 50));
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
        gridBagConstraints.insets = new java.awt.Insets(10, 6, 10, 6);
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

        jButton1.setText("Preferences");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Text"));

        textInputButton.setText("Edit");
        textInputButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textInputButtonActionPerformed(evt);
            }
        });

        jLabel10.setText("<html>Set and Configure<br> Text</html>");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(textInputButton)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textInputButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

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
                .addComponent(start3DButton)
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

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(exitButton, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(gridPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gridPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(colorPickPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exitButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

        //selectedColor = new Color(r, g, b);

        prevPanel.setSelectedColor(selectedColor);
        gridPanel.setSelectedColor(selectedColor);
    }

    private void setBGButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setBGButtonActionPerformed
        gridPanel.setAllColors(selectedColor);
        applyTHAction();       
    }//GEN-LAST:event_setBGButtonActionPerformed

    private void redSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_redSliderStateChanged
        readSliderVals();
        convertAndShow();
    }//GEN-LAST:event_redSliderStateChanged

    private void greenSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_greenSliderStateChanged
        readSliderVals();
        convertAndShow();
    }//GEN-LAST:event_greenSliderStateChanged

    private void blueSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_blueSliderStateChanged
        readSliderVals();
        convertAndShow();
    }//GEN-LAST:event_blueSliderStateChanged

    private void readSliderVals() {
        redTH = redSlider.getValue();
        greenTH = greenSlider.getValue();
        blueTH = blueSlider.getValue();
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

        reloadImg(orgImg);
    }//GEN-LAST:event_loadImageButtonActionPerformed

    private void saveMatrixButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMatrixButtonActionPerformed
        if (matrixFrame == null) {
            matrixFrame = new MatrixFrame();
        }

        matrixFrame.setMatrix(gridPanel.getColorArray(), nX, nY);
        matrixFrame.setVisible(true);
    }//GEN-LAST:event_saveMatrixButtonActionPerformed

    private void start3DButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_start3DButtonActionPerformed

        if (animFrame == null) {
            animFrame = new AnimationFrame();
            animFrame.init(gridPanel.getColorArray());
        } else {
            animFrame.reloadAppearance(gridPanel.getColorArray());
        }
        animFrame.setVisible(true);
    }//GEN-LAST:event_start3DButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (confFrame == null) {
            confFrame = new ConfigFrame();
        }
        confFrame.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void textInputButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textInputButtonActionPerformed

        if (textFrame == null) {
            textFrame = new TextInputFrame(this);
            textFrame.setLocationRelativeTo(null);
        }
        textFrame.setVisible(true);
    }//GEN-LAST:event_textInputButtonActionPerformed

    private void selectColorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectColorButtonActionPerformed
        Color col = JColorChooser.showDialog(null,
                "Text ColorChooser", selectedColor);
        if (col != null) {
            selectedColor = col;
            setPickedColor();
        }
    }//GEN-LAST:event_selectColorButtonActionPerformed

    private void applyTHButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyTHButtonActionPerformed
        applyTHAction();
    }//GEN-LAST:event_applyTHButtonActionPerformed

    private void prevPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prevPanelMouseClicked
        selectColorButton.doClick();
    }//GEN-LAST:event_prevPanelMouseClicked

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        dispose();
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void applyTHAction() {
        BufferedImage img = new BufferedImage(nX, nY, BufferedImage.TYPE_3BYTE_BGR);
        Color[] colors = gridPanel.getColorArray();

        for (int yIdx = 0; yIdx < nY; yIdx++) {
            for (int xIdx = 0; xIdx < nX; xIdx++) {
                img.setRGB(xIdx, yIdx, colors[(yIdx * nX) + xIdx].getRGB());
            }
        }

        reloadImg(img);
    }

    private Color[] getColorArrayFromFile(File file) throws FileNotFoundException {

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
        int diff;
        float colorWidth;

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

    public void reloadImg(BufferedImage img) {
        orgImg = img;
        if (img != null) {
            scaledImg = Tools.resizeImage(img, nX, nY);
        }
        convertAndShow();
    }

    public void reloadImg_Merge(BufferedImage img1) {
        if (orgImg != null) {
            orgImg = mergeTwo(img1, orgImg);
        }
        reloadImg(orgImg);
    }

    private void convertAndShow() {
        if (scaledImg != null) {
            convertedImg = convertImg(scaledImg);
            showImg(convertedImg);
        }
    }

    public BufferedImage mergeTwo(BufferedImage img1, BufferedImage img2) {

        int w = img1.getWidth(null);
        int h = img1.getHeight(null);

        img2 = Tools.resizeImage(img2, w, h);

        int[] rgb1 = new int[w * h];
        int[] rgb2 = new int[w * h];

        img1.getRGB(0, 0, w, h, rgb1, 0, w); //Get all pixels
        img2.getRGB(0, 0, w, h, rgb2, 0, w); //Get all pixels

        BufferedImage img3 = new BufferedImage(w, h, img1.getType());
        int[] rgb3 = new int[w * h];
        Color black = Color.BLACK;

        int mainIdx = 0;
        for (int yIdx = 0; yIdx < h; yIdx++) {
            for (int xIdx = 0; xIdx < w; xIdx++) {
                if (rgb1[mainIdx] == black.getRGB()) {
                    rgb3[mainIdx] = rgb2[mainIdx];
                } else {
                    rgb3[mainIdx] = rgb1[mainIdx];
                }
                mainIdx++;
            }
        }

        //BufferedImage img3 = (BufferedImage) Tools.getImageFromArray(rgb3, w, h);
        img3.setRGB(0, 0, w, h, rgb3, 0, w);

        return img3;
    }

    /*
     * public BufferedImage getOrgImg() { return orgImg; }
     *
     */
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
    private javax.swing.JSlider blueSlider;
    private javax.swing.JPanel colorPickPanel;
    private javax.swing.JButton exitButton;
    private javax.swing.JSlider greenSlider;
    private main.GridPanel gridPanel;
    private javax.swing.JPanel imagePanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton loadImageButton;
    private javax.swing.JButton loadMatrixButton;
    private main.PrevPanel prevPanel;
    private javax.swing.JSlider redSlider;
    private javax.swing.JButton saveMatrixButton;
    private javax.swing.JButton selectColorButton;
    private javax.swing.JButton setBGButton;
    private javax.swing.JButton start3DButton;
    private javax.swing.JButton textInputButton;
    // End of variables declaration//GEN-END:variables
}
