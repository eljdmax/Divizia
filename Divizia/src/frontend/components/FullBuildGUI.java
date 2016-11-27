/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package frontend.components;

import backend.Persistable;
import core.build.FullBuild;
import core.build.ModdedWeapon;
import frontend.main.MainGUI;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.ListSelectionModel;

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
        
        scrollPane = new javax.swing.JScrollPane();
        weaponList = new WeaponList();
        
        buildContentPanel = new javax.swing.JPanel();
        row1Panel = new javax.swing.JPanel();
        row2Panel = new javax.swing.JPanel();
        row3Panel = new javax.swing.JPanel();
        row4Panel = new javax.swing.JPanel();
        
        bottomPanel = new javax.swing.JPanel();
        buildSaveBtn = new javax.swing.JButton();
        buildDeleteBtn = new javax.swing.JButton();
        buildNameLabel = new javax.swing.JLabel();
        buildNameText = new javax.swing.JTextField();
        
        
        this.setLayout(new java.awt.BorderLayout());

        //buildContentPanel.setPreferredSize(new java.awt.Dimension(30, 30));
        buildContentPanel.setLayout(new java.awt.GridLayout(0, 1, 0, 0));

        row1Panel.setLayout(new java.awt.GridLayout(0, 2, 10, 5));
        bodyArmorPanel = new GearDisplayGUI(mainGUI,fullBuild.getBodyArmor(), "BodyArmor");        
        backPackPanel = new GearDisplayGUI(mainGUI,fullBuild.getBackpack(), "Backpack");
        row1Panel.add(bodyArmorPanel);
        row1Panel.add(backPackPanel);
        
        row2Panel.setLayout(new java.awt.GridLayout(0, 2, 10, 5));
        glovesPanel = new GearDisplayGUI(mainGUI,fullBuild.getGloves(), "Gloves");
        holsterPanel = new GearDisplayGUI(mainGUI,fullBuild.getHolster(), "Holster");
        row2Panel.add(glovesPanel);
        row2Panel.add(holsterPanel);
        
        row3Panel.setLayout(new java.awt.GridLayout(0, 2, 10, 5));
        kneepadsPanel = new GearDisplayGUI(mainGUI,fullBuild.getKneepads(), "Kneepads");
        maskPanel = new GearDisplayGUI(mainGUI,fullBuild.getMask(), "Mask");
        row3Panel.add(kneepadsPanel);
        row3Panel.add(maskPanel);
        
        row4Panel.setLayout(new java.awt.GridLayout(0, 3, 6, 5));
        weapon1 = new WeaponDisplayGUI(mainGUI,fullBuild.getWeapon1(), "Weapon 1");
        weapon2 = new WeaponDisplayGUI(mainGUI,fullBuild.getWeapon2(), "Weapon 2");

        row4Panel.add(weapon1);
        row4Panel.add(weapon2);
        
        weaponList.setFont(MainGUI.DEFAULT_FONT);
        weaponList.setDragEnabled(true);
        weaponList.setTransferHandler(new ModdedWeaponListTransferHandler());
        
        scrollPane.setViewportView(weaponList);

        row4Panel.add(scrollPane);
        
        buildContentPanel.add(row1Panel);
        buildContentPanel.add(row2Panel);
        buildContentPanel.add(row3Panel);
        buildContentPanel.add(row4Panel);  
      
        
        
        this.add(buildContentPanel, java.awt.BorderLayout.CENTER);

        bottomPanel.setPreferredSize(new java.awt.Dimension(500, 24));

        buildSaveBtn.setFont(MainGUI.DEFAULT_FONT); // NOI18N
        buildSaveBtn.setText("Save");
        buildSaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildSaveBtnActionPerformed(evt);
            }
        });

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
    
    
    public void refresh(String gearType) {
        switch (gearType) {
            case "BodyArmor":
                bodyArmorPanel.refresh();
                break;
            case "BackPack":
                backPackPanel.refresh();
                break;
            case "Gloves":
                glovesPanel.refresh();
                break;
            case "Holster":
                holsterPanel.refresh();
                break;
            case "Kneepads":
                kneepadsPanel.refresh();
                break;
            case "Mask":
                maskPanel.refresh();
                break;
            case "Weapons":
                weaponList.setModel(getWeaponListModel()); //all weapons
            case "All":
                bodyArmorPanel.refresh();
                backPackPanel.refresh();
                glovesPanel.refresh();
                holsterPanel.refresh();
                kneepadsPanel.refresh();
                maskPanel.refresh();
                weaponList.setModel(getWeaponListModel());
                break;
        }
  
    }
    
    //private methods
    private void buildSaveBtnActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        Persistable backend = mainGUI.getBackend();
        
        boolean isNew = (fullBuild.getId() == null);
        
        fullBuild.setName(buildNameText.getText());
        
        fullBuild.updateBodyArmor(bodyArmorPanel.getModdedGear());
        fullBuild.updateBackpack(backPackPanel.getModdedGear());
        fullBuild.updateGloves(glovesPanel.getModdedGear());
        fullBuild.updateHolster(holsterPanel.getModdedGear());
        fullBuild.updateKneepads(kneepadsPanel.getModdedGear());
        fullBuild.updateMask(maskPanel.getModdedGear());
        
        fullBuild.setWeapon1(weapon1.getModdedWeapon());
        fullBuild.setWeapon2(weapon2.getModdedWeapon());
        
        //Weapons
        
        backend.saveOrUpdateFullBuild(fullBuild);
        
        
        if (isNew) {
            mainGUI.addBuildTab(fullBuild);
            clearBuild();
            validate();
            repaint();
        }
    } 
    
    private void clearBuild() {
        fullBuild = new FullBuild();
        bodyArmorPanel.updateModdedGear(null);
        backPackPanel.updateModdedGear(null);
        maskPanel.updateModdedGear(null);
        glovesPanel.updateModdedGear(null);
        kneepadsPanel.updateModdedGear(null);
        holsterPanel.updateModdedGear(null);
    }
    
    
    private javax.swing.AbstractListModel getWeaponListModel() {
        return new javax.swing.AbstractListModel() {
                List<ModdedWeapon> moddedWeapons =  mainGUI.getModdedWeapons();
                public int getSize() { return moddedWeapons.size(); }
                public Object getElementAt(int i) { return moddedWeapons.get(i); }
            };
    }
    
    public class WeaponList extends javax.swing.JList {
        
        WeaponList() {
            
            setModel(getWeaponListModel());
            
            setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            
            getSelectionModel().addListSelectionListener(new SharedListSelectionHandler(this) {
                public void onValueChanged(Object object){
                    mainGUI.getWeaponFormPanel(true).updateModdedWeapon((ModdedWeapon) object);
                }
            });
            
            addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    javax.swing.JList theList = (javax.swing.JList) e.getSource();
                    int index = theList.locationToIndex(e.getPoint());
                    
                    if (index >= 0) {
                        ModdedWeapon theModdedWeapon= (ModdedWeapon) theList.getModel().getElementAt(index);
                        mainGUI.getWeaponFormPanel(true).updateModdedWeapon(theModdedWeapon);
                    }
                }
            });
            
        }
    }
    
    private WeaponList weaponList;
    private javax.swing.JScrollPane scrollPane;
    
    private GearDisplayGUI backPackPanel;
    private GearDisplayGUI bodyArmorPanel;
    private GearDisplayGUI glovesPanel;
    private GearDisplayGUI holsterPanel;
    private GearDisplayGUI kneepadsPanel;
    private GearDisplayGUI maskPanel;
    
    private WeaponDisplayGUI weapon1;
    private WeaponDisplayGUI weapon2;
    
    private javax.swing.JPanel buildContentPanel;
    private javax.swing.JPanel row1Panel;
    private javax.swing.JPanel row2Panel;
    private javax.swing.JPanel row3Panel;
    private javax.swing.JPanel row4Panel;
    private javax.swing.JButton buildDeleteBtn;
    private javax.swing.JButton buildSaveBtn;
    private javax.swing.JLabel buildNameLabel;
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JTextField buildNameText;
}
