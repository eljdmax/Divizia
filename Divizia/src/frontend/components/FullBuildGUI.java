/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package frontend.components;

import core.build.FullBuild;
import frontend.main.MainGUI;

/**
 *
 * @author tchabole
 */
public class FullBuildGUI extends javax.swing.JPanel {
    private final MainGUI mainGUI;
    private FullBuild fullBuild;
            
    public FullBuildGUI(MainGUI mainGUI, FullBuild fullBuild) {
        this.mainGUI = mainGUI;
        this.fullBuild = fullBuild;
        
        initComponents();
    }
    
    private void initComponents() {
        
        buildContentPanel = new javax.swing.JPanel();
        
        bottomPanel = new javax.swing.JPanel();
        buildSaveBtn = new javax.swing.JButton();
        buildDeleteBtn = new javax.swing.JButton();
        buildNameLabel = new javax.swing.JLabel();
        buildNameText = new javax.swing.JTextField();
        
        
        this.setLayout(new java.awt.BorderLayout());

        //buildContentPanel.setPreferredSize(new java.awt.Dimension(30, 30));
        buildContentPanel.setLayout(new java.awt.GridLayout(0, 2, 10, 5));

        bodyArmorPanel = new GearDisplayGUI(mainGUI,fullBuild.getBodyArmor(), "BodyArmor");        
        backPackPanel = new GearDisplayGUI(mainGUI,fullBuild.getBackpack(), "Backpack");
        glovesPanel = new GearDisplayGUI(mainGUI,fullBuild.getGloves(), "Gloves");
        holsterPanel = new GearDisplayGUI(mainGUI,fullBuild.getHolster(), "Holster");
        kneepadsPanel = new GearDisplayGUI(mainGUI,fullBuild.getKneepads(), "Kneepads");
        maskPanel = new GearDisplayGUI(mainGUI,fullBuild.getMask(), "Mask");
        
        buildContentPanel.add(bodyArmorPanel);
        buildContentPanel.add(backPackPanel);
        buildContentPanel.add(maskPanel);
        buildContentPanel.add(glovesPanel);
        buildContentPanel.add(kneepadsPanel);
        buildContentPanel.add(holsterPanel);

        this.add(buildContentPanel, java.awt.BorderLayout.CENTER);

        bottomPanel.setPreferredSize(new java.awt.Dimension(500, 24));

        buildSaveBtn.setFont(MainGUI.DEFAULT_FONT); // NOI18N
        buildSaveBtn.setText("Save");

        buildDeleteBtn.setFont(MainGUI.DEFAULT_FONT); // NOI18N
        buildDeleteBtn.setText("Delete");

        buildNameLabel.setFont(MainGUI.DEFAULT_FONT); // NOI18N
        buildNameLabel.setText("Name:");

        buildNameText.setFont(MainGUI.DEFAULT_FONT); // NOI18N
        buildNameText.setPreferredSize(new java.awt.Dimension(150,24));
        buildNameText.setText(fullBuild.getName());

        javax.swing.GroupLayout bottomPanelLayout = new javax.swing.GroupLayout(bottomPanel);
        bottomPanel.setLayout(bottomPanelLayout);
        bottomPanelLayout.setHorizontalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buildNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buildNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 296, Short.MAX_VALUE)
                .addComponent(buildDeleteBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buildSaveBtn)
                .addContainerGap())
        );
        bottomPanelLayout.setVerticalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottomPanelLayout.createSequentialGroup()
                .addGap(0, 5, Short.MAX_VALUE)
                .addGroup(bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buildSaveBtn)
                    .addComponent(buildDeleteBtn)
                    .addComponent(buildNameLabel)
                    .addComponent(buildNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        this.add(bottomPanel, java.awt.BorderLayout.SOUTH);
        
    }
    
    private GearDisplayGUI backPackPanel;
    private GearDisplayGUI bodyArmorPanel;
    private GearDisplayGUI glovesPanel;
    private GearDisplayGUI holsterPanel;
    private GearDisplayGUI kneepadsPanel;
    private GearDisplayGUI maskPanel;
    
    private javax.swing.JPanel buildContentPanel;
    private javax.swing.JButton buildDeleteBtn;
    private javax.swing.JButton buildSaveBtn;
    private javax.swing.JLabel buildNameLabel;
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JTextField buildNameText;
}
