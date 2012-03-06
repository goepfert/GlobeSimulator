package main;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author goepfert
 */
public class AnimationFrame extends javax.swing.JFrame {

    private Canvas3D canvas3d;
    private SimpleUniverse universe;
    private Appearance[] sphereApps;
    private BranchGroup bGroup;
    private BoundingSphere bounds;
    private TransformGroup tg_spin;
    private AmbientLight ambLight;
    private DirectionalLight dirLight;
    //
    private Color[] colorArray;
    private int nX = 0;
    private int nY = 0;

    public AnimationFrame() {
        initComponents();

        colorArray = new Color[nX * nY];
    }

    public void init(Color[] colors, int nX, int nY) {

        this.colorArray = colors;
        this.nX = nX;
        this.nY = nY;

        createUniverse();
        createAppearance();
        createSpheres();
        createRotator();
        createLights();
        //createMouseInteraction();

        bGroup.addChild(tg_spin);
        bGroup.compile();

        universe.getViewingPlatform().setNominalViewingTransform();
        universe.addBranchGraph(bGroup);
    }

    public void reloadSpheres(Color[] color) {
        this.colorArray = color;
        reloadSpheres(color, sphereLightSlider.getValue()/10.f);
        /*
         * bGroup.detach(); bGroup.removeChild(tg_spin); createAppearance();
         * createSpheres(); createRotator(); bGroup.addChild(tg_spin);
         * universe.addBranchGraph(bGroup);
         */
    }

    private void reloadSpheres(Color[] color, float scale) {
        this.colorArray = color;
        int mainIdx = 0;
        for (int colIdx = 0; colIdx < nX; colIdx++) {
            for (int rowIdx = 0; rowIdx < nY; rowIdx++) {
                Color3f sphereColor = new Color3f(color[(rowIdx * nX) + colIdx]);
                sphereColor.scale(scale);
                sphereApps[mainIdx].setColoringAttributes(new ColoringAttributes(sphereColor, 1));
                sphereApps[mainIdx].getMaterial().setDiffuseColor(sphereColor);
                sphereApps[mainIdx].getMaterial().setAmbientColor(sphereColor);
                mainIdx++;
            }
        }
    }

    public void changeLight(float fLight) {
        Color3f lightColor = new Color3f(fLight, fLight, fLight);
        ambLight.setColor(lightColor);
        dirLight.setColor(lightColor);
    }

    private void createUniverse() {

        animPanel.removeAll();

        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        canvas3d = new Canvas3D(config);
        canvas3d.setSize(animPanel.getWidth(), animPanel.getHeight());
        animPanel.add("Center", canvas3d);

        universe = new SimpleUniverse(canvas3d);
        bGroup = new BranchGroup();
        bGroup.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
        bGroup.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        bGroup.setCapability(BranchGroup.ALLOW_DETACH);

        bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
    }

    private void createAppearance() {

        sphereApps = new Appearance[nX * nY];

        int mainIdx = 0;
        for (int colIdx = 0; colIdx < nX; colIdx++) {
            for (int rowIdx = 0; rowIdx < nY; rowIdx++) {

                sphereApps[mainIdx] = new Appearance();
                sphereApps[mainIdx].setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ);
                sphereApps[mainIdx].setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);

                Material mat = new Material();
                mat.setCapability(Material.ALLOW_COMPONENT_READ);
                mat.setCapability(Material.ALLOW_COMPONENT_WRITE);
                mat.setEmissiveColor(new Color3f(Color.BLACK));
                mat.setShininess(50.0f);

                sphereApps[mainIdx].setMaterial(mat);

                mainIdx++;
            }// end row
        }//end col

        reloadSpheres(colorArray);        
    }

    private void createSpheres() {

        tg_spin = new TransformGroup();
        tg_spin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        float sphereSize = 0.008f;
        double rho = 0.65;
        double dphi = Math.toRadians(360d / 200d);
        double theta_0 = Math.toRadians(20);
        double dtheta = Math.toRadians(140d / 39d);

        Appearance app = new Appearance();
        app.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ);
        app.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
        app.setColoringAttributes(new ColoringAttributes(new Color3f(Color.black), 1));
        TransparencyAttributes t_attr = new TransparencyAttributes(TransparencyAttributes.BLENDED, 0.2f);
        app.setTransparencyAttributes(t_attr);
        Sphere bigSphere = new Sphere(0.64f, 1, 60, app);
        bigSphere.setName("bigSphere");
        tg_spin.addChild(bigSphere);

        int mainIdx = 0;
        for (int colIdx = 0; colIdx < nX; colIdx++) {
            for (int rowIdx = 0; rowIdx < nY; rowIdx++) {

                Sphere sphere = new Sphere(sphereSize, 1, 30, sphereApps[mainIdx]);
                sphere.setName("" + ((rowIdx * nX) + colIdx));

                Transform3D transToGlobe = new Transform3D();
                TransformGroup tg_globe = new TransformGroup();
                tg_globe.setName("" + ((rowIdx * nX) + colIdx));

                double z = -1*rho * Math.sin(theta_0 + rowIdx * dtheta) * Math.cos(colIdx * dphi);
                double x = -1*rho * Math.sin(theta_0 + rowIdx * dtheta) * Math.sin(colIdx * dphi);
                double y = rho * Math.cos(theta_0 + rowIdx * dtheta);

                Vector3f vector = new Vector3f((float) x, (float) y, (float) z);

                transToGlobe.setTranslation(vector);
                tg_globe.setTransform(transToGlobe);
                tg_globe.addChild(sphere);

                tg_spin.addChild(tg_globe);

                mainIdx++;
            }
        }
    }

    private void createLights() {

        Color3f lightColor = new Color3f(0.5f, 0.5f, 0.5f);

        Vector3f lightDirection = new Vector3f(1.0f, -1.0f, -5.0f);
        dirLight = new DirectionalLight(lightColor, lightDirection);
        dirLight.setCapability(Light.ALLOW_COLOR_WRITE);
        dirLight.setInfluencingBounds(bounds);

        ambLight = new AmbientLight(lightColor);
        ambLight.setCapability(Light.ALLOW_COLOR_WRITE);
        ambLight.setInfluencingBounds(bounds);

        bGroup.addChild(ambLight);
        bGroup.addChild(dirLight);

        bgLightSlider.setValue(5);
    }

    private void createRotator() {
        Transform3D axis = new Transform3D();
        Alpha rotationAlpha = new Alpha();//-1, 10000);
        rotationAlpha.setMode(Alpha.DECREASING_ENABLE);
        rotationAlpha.setDecreasingAlphaDuration(10000);
        rotationAlpha.setLoopCount(-1);

        RotationInterpolator rotator =
                new RotationInterpolator(rotationAlpha, tg_spin, axis,
                0.0f, (float) Math.PI * 2.0f);
        rotator.setName("rotator");
        rotator.setSchedulingBounds(bounds);
        tg_spin.addChild(rotator);
    }

    private void createMouseInteraction() {
        MouseRotate myMouseRotate = new MouseRotate();
        myMouseRotate.setTransformGroup(tg_spin);
        myMouseRotate.setSchedulingBounds(bounds);
        bGroup.addChild(myMouseRotate);

        MouseTranslate mouseTranslate = new MouseTranslate();
        mouseTranslate.setTransformGroup(tg_spin);
        mouseTranslate.setSchedulingBounds(bounds);
        bGroup.addChild(mouseTranslate);

        MouseZoom mouseZoom = new MouseZoom();
        mouseZoom.setTransformGroup(tg_spin);
        mouseZoom.setSchedulingBounds(bounds);
        bGroup.addChild(mouseZoom);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        animPanel = new javax.swing.JPanel();
        optPanel = new javax.swing.JPanel();
        bgLightSlider = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        rotSpeedSlider = new javax.swing.JSlider();
        sphereLightSlider = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("3D Simulation");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        animPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout animPanelLayout = new javax.swing.GroupLayout(animPanel);
        animPanel.setLayout(animPanelLayout);
        animPanelLayout.setHorizontalGroup(
            animPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        animPanelLayout.setVerticalGroup(
            animPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 456, Short.MAX_VALUE)
        );

        optPanel.setLayout(new java.awt.GridBagLayout());

        bgLightSlider.setMajorTickSpacing(1);
        bgLightSlider.setMaximum(10);
        bgLightSlider.setMinimum(1);
        bgLightSlider.setSnapToTicks(true);
        bgLightSlider.setValue(5);
        bgLightSlider.setPreferredSize(new java.awt.Dimension(100, 40));
        bgLightSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                bgLightSliderStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 164;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        optPanel.add(bgLightSlider, gridBagConstraints);

        jLabel1.setText("Light Source Brightness");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        optPanel.add(jLabel1, gridBagConstraints);

        rotSpeedSlider.setMaximum(30000);
        rotSpeedSlider.setMinimum(500);
        rotSpeedSlider.setMinorTickSpacing(500);
        rotSpeedSlider.setValue(10000);
        rotSpeedSlider.setPreferredSize(new java.awt.Dimension(100, 40));
        rotSpeedSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rotSpeedSliderStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 164;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        optPanel.add(rotSpeedSlider, gridBagConstraints);

        sphereLightSlider.setMinimum(1);
        sphereLightSlider.setMinorTickSpacing(10);
        sphereLightSlider.setPreferredSize(new java.awt.Dimension(100, 40));
        sphereLightSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sphereLightSliderStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 164;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        optPanel.add(sphereLightSlider, gridBagConstraints);

        jLabel2.setText("LED Brightness");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        optPanel.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Rotation Speed");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        optPanel.add(jLabel3, gridBagConstraints);

        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(animPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(optPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(animPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(optPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bgLightSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_bgLightSliderStateChanged
        changeLight(bgLightSlider.getValue() / 10.f);
    }//GEN-LAST:event_bgLightSliderStateChanged

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        canvas3d.setSize(animPanel.getWidth(), animPanel.getHeight());
    }//GEN-LAST:event_formComponentResized

    private void rotSpeedSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rotSpeedSliderStateChanged

        for (int i = 0; i < tg_spin.numChildren(); i++) {
            if ("rotator".equals(tg_spin.getChild(i).getName()) && tg_spin.getChild(i) instanceof RotationInterpolator) {
                RotationInterpolator rot = (RotationInterpolator) tg_spin.getChild(i);
                int val = rotSpeedSlider.getValue();
                if(rotSpeedSlider.getValue() == rotSpeedSlider.getMaximum()) {
                    val = 0;
                }
                rot.getAlpha().setDecreasingAlphaDuration(val);                                
            }
        }               
    }//GEN-LAST:event_rotSpeedSliderStateChanged

    private void sphereLightSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sphereLightSliderStateChanged
        reloadSpheres(colorArray, sphereLightSlider.getValue()/10.f );
    }//GEN-LAST:event_sphereLightSliderStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(AnimationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AnimationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AnimationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AnimationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new AnimationFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel animPanel;
    private javax.swing.JSlider bgLightSlider;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel optPanel;
    private javax.swing.JSlider rotSpeedSlider;
    private javax.swing.JSlider sphereLightSlider;
    // End of variables declaration//GEN-END:variables
}
