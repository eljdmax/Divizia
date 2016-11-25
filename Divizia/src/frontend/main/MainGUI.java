/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package frontend.main;

import backend.Persistable;
import backend.dbs.SQLLite;
import core.build.*;
import core.components.*;
import core.components.gearsets.*;
import core.utils.Constants;
import frontend.components.*;
import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

/**
 *
 * @author tchabole
 */
public class MainGUI extends javax.swing.JFrame {

    /**
     * Constants for laying out the GUI.
     */
    private final static int HEIGHT = 620,  WIDTH = 1280;
    public final static java.awt.Font DEFAULT_FONT = new java.awt.Font("Tahoma", 0, 9);
    public final static java.awt.Font DEFAULT_FONT_BOLD = new java.awt.Font("Tahoma", java.awt.Font.BOLD, 9);
    
    
    private HashMap<String,GearSet> gearSetsMap;
    private HashMap<String,WeaponTalent> weaponTalentsMap;
    private HashMap<Integer,Property[]> propertyListMap;
    private HashMap<Integer,ModType[]> modPropertyListMap;
    private HashMap<Integer,GearSet> gearSets;
    private HashMap<Integer,WeaponTalent> weaponTalents;
    
    private GearSet[] gearSetsList;
    private WeaponTalent[] weaponTalentsList;
    private WeaponTalent[] extendedWeaponTalentsList;

    
    private HashMap<Integer,ModdedWeapon> mainWeapons;
    private HashMap<Integer,ModdedWeapon> pistols;
    
    private HashMap<Integer,FullBuild> fullbuilds;
            
    private Persistable backend ;
    
    
    public MainGUI() {
        loadData();
        
        initComponents();
    }

    private void loadData() {
        
        gearSetsMap = Constants.getGearSetsMap();
        weaponTalentsMap = Constants.getWeaponTalentsMap() ;
        

        propertyListMap = Constants.getPropertyListMap();
        modPropertyListMap = Constants.getModPropertyListMap();
        
        backend = new SQLLite("resource/dbs/newtest.db");
        
        
        gearSets = backend.loadGearSets(gearSetsMap);        
        gearSetsList = gearSets.values().toArray(new GearSet[gearSets.keySet().size()]);
        
        
        weaponTalents = backend.loadWeaponTalents(weaponTalentsMap);
        weaponTalentsList = weaponTalents.values().toArray(new WeaponTalent[weaponTalents.keySet().size()]);
        
        HashMap<Integer,WeaponTalent> extendedWeaponTalents = new HashMap<Integer,WeaponTalent>();
        extendedWeaponTalents.put(0, null);
        extendedWeaponTalents.putAll(weaponTalents);
        extendedWeaponTalentsList = extendedWeaponTalents.values().toArray(new WeaponTalent[extendedWeaponTalents.keySet().size()]);
        

        
        
        fullbuilds = backend.loadFullBuilds(gearSets, weaponTalents);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")               
    private void initComponents() {

        topStatPanel = new javax.swing.JPanel();
        mainPanel = new javax.swing.JSplitPane();
        buildsPanel = new javax.swing.JPanel();
        jTabbedPanel = new javax.swing.JTabbedPane();
        defaultBuildTab = new FullBuildGUI(this, new FullBuild());
        
        componentPanel = new javax.swing.JSplitPane();
        moddedPanel = new javax.swing.JPanel();
        moddedTopPanel = new javax.swing.JPanel();
        moddedMainPanel = new javax.swing.JPanel();
        gearFormPanel = new GearFormGUI(this);
        weaponFormPanel = new WeaponFormGUI(this);

        moddedBottomPanel = new javax.swing.JPanel();
        componentSaveBtn = new javax.swing.JButton();
        componentDeleteBtn = new javax.swing.JButton();
        componentnewBtn = new javax.swing.JButton();
        modsPanel = new javax.swing.JSplitPane();
        modsScrollPane = new javax.swing.JScrollPane();
        modsList = new ModList();
        mainModsPanel = new javax.swing.JPanel();
        modFormPanel = new ModFormGUI(this);
        
        bottomModsPanel = new javax.swing.JPanel();
        modSaveBtn = new javax.swing.JButton();
        modDeleteBtn = new javax.swing.JButton();
        modNewBtn = new javax.swing.JButton();
        
        
        

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(WIDTH, HEIGHT));

        topStatPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Stats"));
        topStatPanel.setPreferredSize(new java.awt.Dimension(WIDTH, 80));

        
        getContentPane().add(topStatPanel, java.awt.BorderLayout.NORTH);
        
        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        //buildsPanel.setPreferredSize(new java.awt.Dimension(400, 107));
        buildsPanel.setLayout(new java.awt.BorderLayout());

        jTabbedPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Builds"));
        jTabbedPanel.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jTabbedPanel.addTab("Default", defaultBuildTab);

        FullBuild fullBuild;
        FullBuildGUI buildTab;
        for (Integer i : fullbuilds.keySet()) {
            fullBuild = fullbuilds.get(i);
            buildTab = new FullBuildGUI(this,fullBuild);
            jTabbedPanel.addTab(fullBuild.getName(), buildTab);
        }

        buildsPanel.add(jTabbedPanel, java.awt.BorderLayout.CENTER);

        mainPanel.setLeftComponent(buildsPanel);

        
        componentPanel.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        moddedPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Modded Component"));
        moddedPanel.setLayout(new java.awt.BorderLayout());

        moddedTopPanel.setPreferredSize(new java.awt.Dimension(WIDTH, 20));


        moddedPanel.add(moddedTopPanel, java.awt.BorderLayout.NORTH);

        moddedMainPanel.setLayout(new java.awt.BorderLayout());

        // moddedMainPanel.add(gearFormPanel, java.awt.BorderLayout.CENTER);

        moddedMainPanel.add(weaponFormPanel, java.awt.BorderLayout.CENTER);
        
        moddedPanel.add(moddedMainPanel, java.awt.BorderLayout.CENTER);

        moddedBottomPanel.setPreferredSize(new java.awt.Dimension(365, 24));

        componentSaveBtn.setFont(DEFAULT_FONT); // NOI18N
        componentSaveBtn.setText("Save");
        componentSaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                componentSaveBtnActionPerformed(evt);
            }
        });

        componentDeleteBtn.setFont(DEFAULT_FONT); // NOI18N
        componentDeleteBtn.setText("Delete");

        componentnewBtn.setFont(DEFAULT_FONT); // NOI18N
        componentnewBtn.setText("New");
        componentnewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                componentNewBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout moddedBottomPanelLayout = new javax.swing.GroupLayout(moddedBottomPanel);
        moddedBottomPanel.setLayout(moddedBottomPanelLayout);
        moddedBottomPanelLayout.setHorizontalGroup(
            moddedBottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, moddedBottomPanelLayout.createSequentialGroup()
                .addContainerGap(269, Short.MAX_VALUE)
                .addComponent(componentnewBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(componentDeleteBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(componentSaveBtn)
                .addContainerGap())
        );
        moddedBottomPanelLayout.setVerticalGroup(
            moddedBottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, moddedBottomPanelLayout.createSequentialGroup()
                .addGap(0, 5, Short.MAX_VALUE)
                .addGroup(moddedBottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(componentSaveBtn)
                    .addComponent(componentDeleteBtn)
                    .addComponent(componentnewBtn)))
        );

        moddedPanel.add(moddedBottomPanel, java.awt.BorderLayout.SOUTH);

        componentPanel.setTopComponent(moddedPanel);


        modsScrollPane.setMinimumSize(new java.awt.Dimension(100, 0));
        
        modsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Mods"));
        
        modsList.setFont(DEFAULT_FONT);
        
        modsList.setDragEnabled(true);
        modsList.setTransferHandler(new ModListTransferHandler());
        
        modsScrollPane.setViewportView(modsList);

        modsPanel.setLeftComponent(modsScrollPane);

        
        mainModsPanel.setLayout(new java.awt.BorderLayout());

        mainModsPanel.add(modFormPanel, java.awt.BorderLayout.CENTER);

        bottomModsPanel.setPreferredSize(new java.awt.Dimension(100, 24));

        
        modSaveBtn.setFont(DEFAULT_FONT); // NOI18N
        modSaveBtn.setText("Save");
        modSaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modSaveBtnActionPerformed(evt);
            }
        });

        modDeleteBtn.setFont(DEFAULT_FONT); // NOI18N
        modDeleteBtn.setText("Delete");

        modNewBtn.setFont(DEFAULT_FONT);
        modNewBtn.setText("New");
        modNewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modNewBtnActionPerformed(evt);
            }
        });
        
        javax.swing.GroupLayout bottomModsPanelLayout = new javax.swing.GroupLayout(bottomModsPanel);
        bottomModsPanel.setLayout(bottomModsPanelLayout);
        bottomModsPanelLayout.setHorizontalGroup(
            bottomModsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottomModsPanelLayout.createSequentialGroup()
                .addContainerGap(167, Short.MAX_VALUE)
                .addComponent(modNewBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modDeleteBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modSaveBtn)
                .addContainerGap())
        );
        bottomModsPanelLayout.setVerticalGroup(
            bottomModsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottomModsPanelLayout.createSequentialGroup()
                .addGap(0, 5, Short.MAX_VALUE)
                .addGroup(bottomModsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modNewBtn)
                    .addComponent(modDeleteBtn)
                    .addComponent(modSaveBtn)))
        );

        mainModsPanel.add(bottomModsPanel, java.awt.BorderLayout.SOUTH);

        mainModsPanel.setMinimumSize(new java.awt.Dimension(100, 0));
        modsPanel.setRightComponent(mainModsPanel);
        

        componentPanel.setBottomComponent(modsPanel);

        mainPanel.setRightComponent(componentPanel);


        createMenu();

        pack();
        
        mainPanel.setDividerLocation( (double)0.5); 
        componentPanel.setDividerLocation( (double) 0.68 );
        modsPanel.setDividerLocation( (double) 0.2);
    }               

    private void createMenu() {
        
        menuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        
        jMenu1.setText("File");
        menuBar.add(jMenu1);

        jMenu2.setText("Edit");
        menuBar.add(jMenu2);

        jMenu3.setText("Help");
        menuBar.add(jMenu3);

        setJMenuBar(menuBar);
    }
    
                                          

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BuildGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuildGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuildGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuildGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGUI().setVisible(true);
            }
        });
    }

    
    public List<ModdedGear> getModdedGears(String gearType) {
        List<ModdedGear> moddedGearsList = new ArrayList<ModdedGear>();
        HashMap<Integer,ModdedGear>  moddedGearsMap = backend.loadModdedGears(gearSets, gearType);
        
        for (Integer i : moddedGearsMap.keySet() ) {
            moddedGearsList.add(moddedGearsMap.get(i));
        }
        
        return moddedGearsList;
    }
    
    public List<ModdedWeapon> getModdedWeapons() {
        List<ModdedWeapon> moddedWeaponsList = new ArrayList<ModdedWeapon>();
        HashMap<Integer,ModdedWeapon>  moddedWeaponsMap = backend.loadModdedWeapons(weaponTalents);
        
        for (Integer i : moddedWeaponsMap.keySet() ) {
            moddedWeaponsList.add(moddedWeaponsMap.get(i));
        }
        
        return moddedWeaponsList;
    }
    
    public List<Mod> getMods(String modType) {
        List<Mod> mods = new ArrayList<Mod>();
        HashMap<Integer,Mod>  modsMap = backend.loadMods(); // TODO: modtype??
        
        for (Integer i : modsMap.keySet() ) {
            mods.add(modsMap.get(i));
        }
        
        return mods;
    }
    
    
    
    
    public Persistable getBackend() {
        return backend;
    }
    
    public GearSet[] getGearSetsList() {
        return gearSetsList;
    }
    
    public WeaponTalent[] getWeaponTalentsList(boolean extended) {
        return  (extended ? extendedWeaponTalentsList : weaponTalentsList);
    }
    
    public GearFormGUI getGearFormPanel() {
        return this.gearFormPanel;
    }
    
    public WeaponFormGUI getWeaponFormPanel() {
        return this.weaponFormPanel;
    }
    
    
    public ModFormGUI getModFormPanel() {
        return this.modFormPanel;
    }
    
    public FullBuildGUI getCurrentBuild() {
        return (FullBuildGUI) this.jTabbedPanel.getSelectedComponent();
    }
    
    public javax.swing.JButton getComponentSaveBtn() {
        return this.componentSaveBtn;
    }
    
    public javax.swing.JButton getModSaveBtn() {
        return this.modSaveBtn;
    }
    
    public Property[] getPropertyList(int category) {
        return propertyListMap.get(category);
    }
    
    public ModType[] getModFamiliesList(int category) {
        return modPropertyListMap.get(category);
    }
    
    public void addBuildTab(FullBuild fullBuild) {
        FullBuildGUI fullBuildGUI = new FullBuildGUI(this,fullBuild);
        fullbuilds.put( Integer.parseInt(fullBuild.getId()), fullBuild);
        jTabbedPanel.addTab(fullBuild.getName(),fullBuildGUI );
        jTabbedPanel.setSelectedComponent(fullBuildGUI);
    }
    
    private javax.swing.AbstractListModel getModListModel() {
        return new javax.swing.AbstractListModel() {
                List<Mod> theMods =  getMods(null);
                @Override
                public int getSize() { return theMods.size(); }
                @Override
                public Mod getElementAt(int i) { return theMods.get(i); }
            };
    }
    
    
    public void updateModList() {
        this.modsList.setModel(getModListModel());
    }
    
    public class ModList extends javax.swing.JList {
        
        
        ModList() {
            
            setModel(getModListModel());
            
            
            addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    javax.swing.JList theList = (javax.swing.JList) e.getSource();
                    int index = theList.locationToIndex(e.getPoint());
                    
                    if (index >= 0) {
                        Mod theMod = (Mod) theList.getModel().getElementAt(index);
                        modFormPanel.updateMod(theMod);
                    }
                }
            });
            
        }
        
    }
    
    
    //private methods
    private void componentSaveBtnActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        //this.gearFormPanel.onBtnSaveActioned();
        this.weaponFormPanel.onBtnSaveActioned();
    }    
    
    private void modSaveBtnActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        this.modFormPanel.onBtnSaveActioned();
    }    
    
    private void modNewBtnActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        this.modFormPanel.clearMod();
    }  
    
    private void componentNewBtnActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        //this.gearFormPanel.clearModdedGear();
        this.weaponFormPanel.clearModdedWeapon();
    }  
    
    // Variables declaration - do not modify                     
    private ModFormGUI modFormPanel;
    private javax.swing.JPanel bottomModsPanel;
    
    
    private javax.swing.JPanel buildsPanel;
    private javax.swing.JButton componentDeleteBtn;
    private javax.swing.JSplitPane componentPanel;
    private javax.swing.JButton componentSaveBtn;
    private javax.swing.JButton componentnewBtn;
    
    
    
    
    private ModList modsList;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    
    
    
    private javax.swing.JTabbedPane jTabbedPanel;
    
    private javax.swing.JPanel mainModsPanel;
    private javax.swing.JSplitPane mainPanel;
    
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton modDeleteBtn;
    private javax.swing.JButton modNewBtn;
    private javax.swing.JButton modSaveBtn;
    private javax.swing.JPanel moddedBottomPanel;
    private javax.swing.JPanel moddedMainPanel;
    private javax.swing.JPanel moddedPanel;
    private javax.swing.JPanel moddedTopPanel;
    private javax.swing.JScrollPane modsScrollPane;
    private javax.swing.JSplitPane modsPanel;
    private javax.swing.JPanel topStatPanel;
    
    private FullBuildGUI defaultBuildTab;
    
    private GearFormGUI gearFormPanel;
    private WeaponFormGUI weaponFormPanel;
    

    
    // End of variables declaration                   
}
