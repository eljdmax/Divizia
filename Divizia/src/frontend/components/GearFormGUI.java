package frontend.components;

import backend.Persistable;
import core.build.ModdedGear;
import core.components.Gear;
import core.components.GearMod;
import core.components.GearSet;
import core.components.Mod;
import core.components.PropValue;
import core.components.Property;
import core.components.RecalibrationPosition;
import core.utils.Constants;
import frontend.main.MainGUI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author tchabole
 */
public class GearFormGUI extends javax.swing.JPanel {
    private final MainGUI mainGUI;
    private ModdedGear moddedGear;
    private String gearType;
    private boolean editMode;
    
    public GearFormGUI(MainGUI mainGUI, ModdedGear moddedGear, String gearType, boolean editMode) {
        this.mainGUI = mainGUI;
        this.moddedGear = moddedGear;
        this.gearType = gearType;
        this.editMode = editMode;
        
        initComponents();
    }
    
    public GearFormGUI(MainGUI mainGUI) {
        this(mainGUI, null, null, true);
    }
    
    private void initComponents() {
        
        mainParamsPanel = new javax.swing.JPanel();
        gearSetLabel = new javax.swing.JLabel();
        gearSetComboxBox = new javax.swing.JComboBox();
        gearTypeLabel = new javax.swing.JLabel();
        gearTypeComboxBox = new javax.swing.JComboBox();
        talentLabel = new javax.swing.JLabel();
        talentComboxBox = new javax.swing.JComboBox();
        gearScoreLabel = new javax.swing.JLabel();
        gearScoreText = new javax.swing.JTextField();
        armorLabel = new javax.swing.JLabel();
        armorText = new javax.swing.JTextField();
        fireArmsLabel = new javax.swing.JLabel();
        fireArmsText = new javax.swing.JTextField();
        staminaLabel = new javax.swing.JLabel();
        staminaText = new javax.swing.JTextField();
        electronicsLabel = new javax.swing.JLabel();
        electronicsText = new javax.swing.JTextField();
        bonusesPanel = javax.swing.Box.createVerticalBox();//new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        
        bonusMapPanels = new HashMap<RecalibrationPosition,ComponentBonusGUI>();
        
        iconPanel = new javax.swing.JPanel();
        
        setLayout(new java.awt.BorderLayout());

        mainParamsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Main"));
        mainParamsPanel.setMinimumSize(new java.awt.Dimension(336, 60));
        mainParamsPanel.setPreferredSize(new java.awt.Dimension(265, 100));
        mainParamsPanel.setLayout(new java.awt.GridLayout(0, 8));

        gearTypeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gearTypeLabel.setFont(MainGUI.DEFAULT_FONT);
        gearTypeLabel.setText("Gear Type:");
        mainParamsPanel.add(gearTypeLabel);

        gearTypeComboxBox.setModel(new javax.swing.DefaultComboBoxModel(Constants.getGearTypes()));
        gearTypeComboxBox.setFont(MainGUI.DEFAULT_FONT);
        mainParamsPanel.add(gearTypeComboxBox);
        
        gearSetLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gearSetLabel.setFont(MainGUI.DEFAULT_FONT);
        gearSetLabel.setText("Gear Set:");
        mainParamsPanel.add(gearSetLabel);

        gearSetComboxBox.setModel(new javax.swing.DefaultComboBoxModel(mainGUI.getGearSetsList()));
        gearSetComboxBox.setFont(MainGUI.DEFAULT_FONT);
        mainParamsPanel.add(gearSetComboxBox);

        talentLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        talentLabel.setFont(MainGUI.DEFAULT_FONT);
        talentLabel.setText("Talent:");
        mainParamsPanel.add(talentLabel);

        talentComboxBox.setModel(new javax.swing.DefaultComboBoxModel(mainGUI.getGearSetsList()));
        talentComboxBox.setFont(MainGUI.DEFAULT_FONT);
        mainParamsPanel.add(talentComboxBox);
        
        gearScoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gearScoreLabel.setFont(MainGUI.DEFAULT_FONT);
        gearScoreLabel.setText("Gear Score:");
        mainParamsPanel.add(gearScoreLabel);

        gearScoreText.setText("229");
        gearScoreText.setFont(MainGUI.DEFAULT_FONT);
        mainParamsPanel.add(gearScoreText);

        armorLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        armorLabel.setText("Armor:");
        armorLabel.setFont(MainGUI.DEFAULT_FONT);
        mainParamsPanel.add(armorLabel);

        armorText.setText("");
        armorText.setFont(MainGUI.DEFAULT_FONT);
        mainParamsPanel.add(armorText);

        fireArmsLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        fireArmsLabel.setFont(MainGUI.DEFAULT_FONT);
        fireArmsLabel.setText("FireArms:");
        mainParamsPanel.add(fireArmsLabel);

        fireArmsText.setText("148");
        fireArmsText.setFont(MainGUI.DEFAULT_FONT);
        mainParamsPanel.add(fireArmsText);

        staminaLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        staminaLabel.setFont(MainGUI.DEFAULT_FONT);
        staminaLabel.setText("Stamina:");
        mainParamsPanel.add(staminaLabel);

        staminaText.setText("148");
        staminaText.setFont(MainGUI.DEFAULT_FONT);
        mainParamsPanel.add(staminaText);

        electronicsLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        electronicsLabel.setFont(MainGUI.DEFAULT_FONT);
        electronicsLabel.setText("Electronics:");
        mainParamsPanel.add(electronicsLabel);

        electronicsText.setText("148");
        electronicsText.setFont(MainGUI.DEFAULT_FONT);
        mainParamsPanel.add(electronicsText);

        this.add(mainParamsPanel, java.awt.BorderLayout.NORTH);

        bonusesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Attributes"));
        //bonusesPanel.setLayout(new java.awt.GridLayout(0, 1));
        
        PropValue bonus;
        ComponentBonusGUI bonusPanel;
        for ( RecalibrationPosition pos : RecalibrationPosition.values()) {
            if (pos ==  RecalibrationPosition.NONE ) {
                continue;
            }
            
            bonus =  new PropValue();
            bonus.setRecalibrationPosition(pos);
            bonusPanel = new ComponentBonusGUI(mainGUI, bonus);
            bonusPanel.setTransferHandler(new ModListTransferHandler());
            bonusPanel.setPreferredSize(new java.awt.Dimension(30, 30));
            bonusMapPanels.put(pos,bonusPanel);
            bonusesPanel.add(bonusPanel);
        }

        scrollPane.setViewportView(bonusesPanel);
        this.add(scrollPane, java.awt.BorderLayout.CENTER);

        iconPanel.setPreferredSize(new java.awt.Dimension(80, 120));

        this.add(iconPanel, java.awt.BorderLayout.WEST);
        
    }
    
    
    public void updateModdedGear(ModdedGear moddedGear, String gearType) {
        this.moddedGear = moddedGear;
        this.gearType = gearType; 
        
        setEditable(false);
    }
    
    public void clearModdedGear() {
        this.moddedGear = null;
        this.gearType = null;
        
        setEditable(true);
        gearTypeComboxBox.setEnabled(true);
    }
    
    public void onBtnSaveActioned() {
        
        if (!editMode)  {
            setEditable(true);
        } else {
        
            Persistable backend = mainGUI.getBackend();
            
            if (moddedGear ==null) {
                moddedGear  = new ModdedGear();
            }
            Gear gear = moddedGear.getGear();
            if ( gear == null ) {
                try {
                    gearType = (String)gearTypeComboxBox.getSelectedItem();
                    gear = (Gear) Class.forName("core.components.gear."+gearType).newInstance();
                } catch (Exception ex) {
                    System.err.println(ex);
                    return;
                }
            }
            
            gear.setGearSet((GearSet)gearSetComboxBox.getSelectedItem());
            gear.setGearScore(Integer.parseInt(gearScoreText.getText()));
            gear.setArmor(Float.parseFloat(armorText.getText()));
            gear.setFA(Float.parseFloat(fireArmsText.getText()));
            gear.setST(Float.parseFloat(staminaText.getText()));
            gear.setEL(Float.parseFloat(electronicsText.getText()));
            
            moddedGear.clearMods();
            gear.clearBonuses();
            
            PropValue bonus;
            Mod mod;
            
            for (RecalibrationPosition pos : bonusMapPanels.keySet()){
                bonus = bonusMapPanels.get(pos).getUpdatedBonus();
                gear.addBonus(bonus);
                if ( bonus!= null && bonus.getProperty() == Property.EMPTY) {
                    mod = bonusMapPanels.get(pos).getMod();
                    if (mod != null && (mod instanceof GearMod) ) { //TODO: PerfMod?
                        moddedGear.addMod((GearMod)mod, pos);
                    }
                }
            }
            
            moddedGear.setGear(gear);
            backend.saveOrUpdateModdedGear(moddedGear);
            
            mainGUI.getCurrentBuild().refresh(gearType);
            
            setEditable(false);
        }
    }
    
    private void setEditable(boolean editMode) {
        this.editMode = editMode;
        updateLayout();
    }
    
    private void updateLayout() {
        //gearSetComboxBox;
        // gearScore
        mainGUI.getComponentSaveBtn().setText(editMode ? "Save" : "Edit");
        
        if (moddedGear == null) {
            gearSetComboxBox.setSelectedIndex(0);
            gearTypeComboxBox.setSelectedIndex(0);
            talentComboxBox.setSelectedIndex(0);
            armorText.setText("");
            fireArmsText.setText("148");
            staminaText.setText("148");
            electronicsText.setText("148");
            gearScoreText.setText("229");
            
            ComponentBonusGUI bonusPanel;
            bonusesPanel.removeAll();
            for (RecalibrationPosition pos : Constants.asSortedList(bonusMapPanels.keySet())) {
                bonusPanel = bonusMapPanels.get(pos);
                bonusPanel.clearBonus();
                bonusesPanel.add(bonusPanel);
            }
            
        } else {

            gearSetComboxBox.setSelectedItem(moddedGear.getGear().getGearSet());
            gearTypeComboxBox.setSelectedItem(gearType);
            gearScoreText.setText(Integer.toString(moddedGear.getGear().getGearScore()));
            //talentComboxBox.setSelectedItem(moddedGear.getTalent());
            armorText.setText(moddedGear.getFullStats().getProp(Property.ARMOR).toString());
            fireArmsText.setText(moddedGear.getFullStats().getProp(Property.FIREARM).toString());
            staminaText.setText(moddedGear.getFullStats().getProp(Property.STAMINA).toString());
            electronicsText.setText(moddedGear.getFullStats().getProp(Property.ELECTRONIC).toString());




            HashMap<GearMod,RecalibrationPosition> modsPosition = moddedGear.getModsPosition();
            HashMap<RecalibrationPosition,Mod> modsMap = new HashMap<RecalibrationPosition, Mod>();
            HashMap<RecalibrationPosition,PropValue> currentMapPanels =  new HashMap<RecalibrationPosition,PropValue>();
            
            for (PropValue bonus : moddedGear.getGear().getBaseBonuses()) { //gearMod -> Position!!!
                currentMapPanels.put(bonus.getRecalibrationPosition() , bonus);
            }
            
            for (GearMod mod : moddedGear.getMods()) {
                modsMap.put(modsPosition.get(mod), mod);
            }

            ComponentBonusGUI bonusPanel;
            bonusesPanel.removeAll();
            if (editMode) {
                for (RecalibrationPosition pos : Constants.asSortedList(bonusMapPanels.keySet())) {
                    bonusPanel = bonusMapPanels.get(pos);
                    if (currentMapPanels.containsKey(pos)) {
                        bonusPanel.updateBonus(currentMapPanels.get(pos),modsMap.get(pos));
                    } else {
                        bonusPanel.clearBonus();
                    }
                    bonusesPanel.add(bonusPanel);
                }
            } else {
                for (RecalibrationPosition pos : Constants.asSortedList(currentMapPanels.keySet()) ) {
                    bonusPanel = new ComponentBonusGUI(mainGUI,currentMapPanels.get(pos),false);
                    bonusPanel.updateBonus(currentMapPanels.get(pos),modsMap.get(pos));
                    bonusPanel.setPreferredSize(new java.awt.Dimension(30, 30));
                    bonusesPanel.add(bonusPanel );
                }
            }
        }
        
        gearSetComboxBox.setEnabled(editMode);
        gearTypeComboxBox.setEnabled(false);
        talentComboxBox.setEnabled(editMode);
        gearScoreText.setEditable(editMode);
        staminaText.setEditable(editMode);
        fireArmsText.setEditable(editMode);
        armorText.setEditable(editMode);
        electronicsText.setEditable(editMode);
        
        validate();
        repaint();
    }
    
    private HashMap<RecalibrationPosition,ComponentBonusGUI> bonusMapPanels;
    //private javax.swing.JPanel bonusesPanel;
    private javax.swing.Box bonusesPanel;
    private javax.swing.JPanel iconPanel;
    private javax.swing.JComboBox gearSetComboxBox;
    private javax.swing.JComboBox gearTypeComboxBox;
    private javax.swing.JComboBox talentComboxBox;
    
    private javax.swing.JLabel armorLabel;
    private javax.swing.JLabel fireArmsLabel;
    private javax.swing.JLabel staminaLabel;
    private javax.swing.JLabel electronicsLabel;
    private javax.swing.JLabel gearScoreLabel;

    private javax.swing.JLabel gearSetLabel;
    private javax.swing.JLabel gearTypeLabel;
    private javax.swing.JLabel talentLabel;
    
    private javax.swing.JTextField armorText;
    
    private javax.swing.JTextField fireArmsText;
    private javax.swing.JTextField staminaText;
    private javax.swing.JTextField electronicsText;
    private javax.swing.JTextField gearScoreText;
    
    private javax.swing.JPanel mainParamsPanel;
    private  javax.swing.JScrollPane scrollPane ;
    
}
