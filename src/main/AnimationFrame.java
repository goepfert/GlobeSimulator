package main;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.util.Properties;
import javax.media.j3d.*;
import javax.swing.ImageIcon;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author goepfert
 */
public class AnimationFrame extends javax.swing.JFrame implements Subscriber {

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
    private Config conf;
    private int nX;
    private int nY;
    //
    float sphereSize;
    float lightSourceBrightness;
    float ledBrightness;
    int rotSpeed;
    int freeMove;

    public AnimationFrame() {
        initComponents();

        conf = Config.getInstance();
        conf.subscribe("animFrame", this);

        readValues();

        colorArray = new Color[nX * nY];
        
        ImageIcon imageIcon = new ImageIcon("Logo_SL_Bloom_4.png");
        this.setIconImage(imageIcon.getImage());
        
        setLocationRelativeTo(getParent());
    }

    private void readValues() {
        Properties prop = conf.getConfProps();
        nX = Integer.parseInt(prop.getProperty("nX"));
        nY = Integer.parseInt(prop.getProperty("nY"));
        sphereSize = Float.parseFloat(prop.getProperty("sphereSize"));
        lightSourceBrightness = Float.parseFloat(prop.getProperty("lightSourceBrightness"));
        ledBrightness = Float.parseFloat(prop.getProperty("ledBrightness"));
        rotSpeed = Integer.parseInt(prop.getProperty("rotSpeed"));
        freeMove = Integer.parseInt(prop.getProperty("freeMove"));
    }

    @Override
    public void readSubscription() {
        Properties prop = conf.getConfProps();

        float tmp = lightSourceBrightness;
        lightSourceBrightness = Float.parseFloat(prop.getProperty("lightSourceBrightness"));
        if (Math.abs(tmp - lightSourceBrightness) > 0.001f) {
            changeLight();
        }

        tmp = ledBrightness;
        ledBrightness = Float.parseFloat(prop.getProperty("ledBrightness"));
        if (Math.abs(tmp - ledBrightness) > 0.001f) {
            reloadAppearance(colorArray, ledBrightness);           
        }

        tmp = sphereSize;
        sphereSize = Float.parseFloat(prop.getProperty("sphereSize"));
        if (Math.abs(tmp - sphereSize) > 0.001f) {
            reloadSpheres(sphereSize);
        }

        tmp = rotSpeed;
        rotSpeed = Integer.parseInt(prop.getProperty("rotSpeed"));
        if (tmp != rotSpeed && freeMove != 1) {
            for (int i = 0; i < tg_spin.numChildren(); i++) {
                if ("rotator".equals(tg_spin.getChild(i).getName()) && tg_spin.getChild(i) instanceof RotationInterpolator) {
                    RotationInterpolator rot = (RotationInterpolator) tg_spin.getChild(i);
                    rot.getAlpha().setDecreasingAlphaDuration(rotSpeed);
                }
            }
        }

    }

    public void init(Color[] colors) {

        this.colorArray = colors;

        createUniverse();
        createAppearance();
        createSpheres();
        if (freeMove == 0) {
            createRotator();
        }
        createLights();
        if (freeMove == 1) {
            createMouseInteraction();
        }

        bGroup.addChild(tg_spin);
        bGroup.compile();        

        universe.getViewingPlatform().setNominalViewingTransform();
        universe.addBranchGraph(bGroup);
    }

    public void reloadAppearance(Color[] color) {
        this.colorArray = color;
        reloadAppearance(color, ledBrightness);
    }

    private void reloadAppearance(Color[] color, float scale) {
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

    private void reloadSpheres(float scale) {
        int mainIdx = 0;
        for (int colIdx = 0; colIdx < nX; colIdx++) {
            for (int rowIdx = 0; rowIdx < nY; rowIdx++) {
                TransformGroup a = (TransformGroup) tg_spin.getChild(mainIdx + 1);
                Transform3D b = new Transform3D();
                a.getTransform(b);
                b.setScale(scale);
                a.setTransform(b);

                mainIdx++;
            }
        }
    }

    public void changeLight() {
        Color3f lightColor = new Color3f(lightSourceBrightness, lightSourceBrightness, lightSourceBrightness);
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

        reloadAppearance(colorArray, ledBrightness);
    }

    private void createSpheres() {

        tg_spin = new TransformGroup();
        tg_spin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        double rho = 0.65;
        double dphi = Math.toRadians(360d / (double) nX);
        double theta_0 = Math.toRadians(20);
        double dtheta = Math.toRadians(140d / (double) (nY - 1));

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

                Sphere sphere = new Sphere(0.008f, 1, 30, sphereApps[mainIdx]);
                sphere.setName("" + ((rowIdx * nX) + colIdx));

                Transform3D transToGlobe = new Transform3D();
                TransformGroup tg_globe = new TransformGroup();
                tg_globe.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
                tg_globe.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
                tg_globe.setName("" + ((rowIdx * nX) + colIdx));

                double z = -1 * rho * Math.sin(theta_0 + rowIdx * dtheta) * Math.cos(colIdx * dphi);
                double x = -1 * rho * Math.sin(theta_0 + rowIdx * dtheta) * Math.sin(colIdx * dphi);
                double y = rho * Math.cos(theta_0 + rowIdx * dtheta);

                Vector3f vector = new Vector3f((float) x, (float) y, (float) z);

                transToGlobe.setTranslation(vector);
                transToGlobe.setScale(sphereSize);

                tg_globe.setTransform(transToGlobe);
                tg_globe.addChild(sphere);

                tg_spin.addChild(tg_globe);

                mainIdx++;
            }
        }
    }

    private void createLights() {

        Color3f lightColor = new Color3f(lightSourceBrightness, lightSourceBrightness, lightSourceBrightness);

        Vector3f lightDirection = new Vector3f(1.0f, -1.0f, -5.0f);
        dirLight = new DirectionalLight(lightColor, lightDirection);
        dirLight.setCapability(Light.ALLOW_COLOR_WRITE);
        dirLight.setInfluencingBounds(bounds);

        ambLight = new AmbientLight(lightColor);
        ambLight.setCapability(Light.ALLOW_COLOR_WRITE);
        ambLight.setInfluencingBounds(bounds);

        bGroup.addChild(ambLight);
        bGroup.addChild(dirLight);
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
        myMouseRotate.setName("mouseRotate");
        myMouseRotate.setTransformGroup(tg_spin);
        myMouseRotate.setSchedulingBounds(bounds);
        bGroup.addChild(myMouseRotate);

        //MouseTranslate mouseTranslate = new MouseTranslate();
        //mouseTranslate.setTransformGroup(tg_spin);
        //mouseTranslate.setSchedulingBounds(bounds);
        //bGroup.addChild(mouseTranslate);

        MouseZoom mouseZoom = new MouseZoom();
        mouseZoom.setName("mouseZoom");
        mouseZoom.setTransformGroup(tg_spin);
        mouseZoom.setSchedulingBounds(bounds);
        bGroup.addChild(mouseZoom);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        animPanel = new javax.swing.JPanel();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("3D Simulation");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        animPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        animPanel.setPreferredSize(new java.awt.Dimension(500, 500));

        javax.swing.GroupLayout animPanelLayout = new javax.swing.GroupLayout(animPanel);
        animPanel.setLayout(animPanelLayout);
        animPanelLayout.setHorizontalGroup(
            animPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 496, Short.MAX_VALUE)
        );
        animPanelLayout.setVerticalGroup(
            animPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 414, Short.MAX_VALUE)
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
                    .addComponent(animPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(closeButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(animPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(closeButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        canvas3d.setSize(animPanel.getWidth(), animPanel.getHeight());
    }//GEN-LAST:event_formComponentResized

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_closeButtonActionPerformed

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
    private javax.swing.JButton closeButton;
    // End of variables declaration//GEN-END:variables
}
