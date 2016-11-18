package frontend.components;

import core.build.ModdedGear;
import core.components.PropValue;
import core.components.Property;
import core.components.RecalibrationPosition;
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
    
    public GearFormGUI(MainGUI mainGUI, ModdedGear moddedGear, String gearType) {
        this.mainGUI = mainGUI;
        this.moddedGear = moddedGear;
        this.gearType = gearType;
        
        initComponents();
    }
    
    public GearFormGUI(MainGUI mainGUI) {
        this(mainGUI, null, null);
    }
    
    private void initComponents() {
        
        mainParamsPanel = new javax.swing.JPanel();
        gearSetLabel = new javax.swing.JLabel();
        gearSetComboxBox = new javax.swing.JComboBox();
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
        mainParamsPanel.setLayout(new java.awt.GridLayout(0, 6));

        gearSetLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gearSetLabel.setFont(MainGUI.DEFAULT_FONT);
        gearSetLabel.setText("GearSet/Talent:");
        mainParamsPanel.add(gearSetLabel);

        gearSetComboxBox.setModel(new javax.swing.DefaultComboBoxModel(mainGUI.getGearSetsList()));
        gearSetComboxBox.setFont(MainGUI.DEFAULT_FONT);
        mainParamsPanel.add(gearSetComboxBox);

        gearScoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gearScoreLabel.setFont(MainGUI.DEFAULT_FONT);
        gearScoreLabel.setText("GearScore:");
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

        fireArmsText.setText("");
        fireArmsText.setFont(MainGUI.DEFAULT_FONT);
        mainParamsPanel.add(fireArmsText);

        staminaLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        staminaLabel.setFont(MainGUI.DEFAULT_FONT);
        staminaLabel.setText("Stamina:");
        mainParamsPanel.add(staminaLabel);

        staminaText.setText("");
        staminaText.setFont(MainGUI.DEFAULT_FONT);
        mainParamsPanel.add(staminaText);

        electronicsLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        electronicsLabel.setFont(MainGUI.DEFAULT_FONT);
        electronicsLabel.setText("Electronics:");
        mainParamsPanel.add(electronicsLabel);

        electronicsText.setText("");
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
        
        updateLayout(false);
    }
    
    private void updateLayout(boolean editMode) {
        //gearSetComboxBox;
        gearScoreText.setEditable(editMode); // gearScore
        
        armorText.setText(moddedGear.getFullStats().getProp(Property.ARMOR).toString());
        armorText.setEditable(editMode);
        fireArmsText.setText(moddedGear.getFullStats().getProp(Property.FIREARM).toString());
        fireArmsText.setEditable(editMode);
        staminaText.setText(moddedGear.getFullStats().getProp(Property.STAMINA).toString());
        staminaText.setEditable(editMode);
        electronicsText.setText(moddedGear.getFullStats().getProp(Property.ELECTRONIC).toString());
        electronicsText.setEditable(editMode);
        
        
        HashMap<RecalibrationPosition,PropValue> currentMapPanels =  new HashMap<RecalibrationPosition,PropValue>();
        
        for (PropValue bonus : moddedGear.getGear().getBaseBonuses()) { //gearMod -> Position!!!
            currentMapPanels.put(bonus.getRecalibrationPosition() , bonus);
        }
        
        ComponentBonusGUI bonusPanel;
        bonusesPanel.removeAll();
        if (editMode) {
            for (RecalibrationPosition pos : bonusMapPanels.keySet()) {
                bonusPanel = bonusMapPanels.get(pos);
                if (currentMapPanels.containsKey(pos)) {
                    bonusPanel.updateBonus(currentMapPanels.get(pos));
                } else {
                    bonusPanel.clearBonus();
                }
                bonusesPanel.add(bonusPanel);
            }
        } else {
            for (RecalibrationPosition pos : currentMapPanels.keySet() ) {
                bonusPanel = new ComponentBonusGUI(mainGUI,currentMapPanels.get(pos),false);
                bonusPanel.setPreferredSize(new java.awt.Dimension(30, 30));
                bonusesPanel.add(bonusPanel );
            }
        }
        validate();
        repaint();
    }
    
    private HashMap<RecalibrationPosition,ComponentBonusGUI> bonusMapPanels;
    //private javax.swing.JPanel bonusesPanel;
    private javax.swing.Box bonusesPanel;
    private javax.swing.JPanel iconPanel;
    private javax.swing.JComboBox gearSetComboxBox;
    
    private javax.swing.JLabel armorLabel;
    private javax.swing.JLabel fireArmsLabel;
    private javax.swing.JLabel staminaLabel;
    private javax.swing.JLabel electronicsLabel;
    private javax.swing.JLabel gearScoreLabel;

    private javax.swing.JLabel gearSetLabel;
    
    private javax.swing.JTextField armorText;
    
    private javax.swing.JTextField fireArmsText;
    private javax.swing.JTextField staminaText;
    private javax.swing.JTextField electronicsText;
    private javax.swing.JTextField gearScoreText;
    
    private javax.swing.JPanel mainParamsPanel;
    private  javax.swing.JScrollPane scrollPane ;
    
}
