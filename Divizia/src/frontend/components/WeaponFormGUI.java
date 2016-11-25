package frontend.components;

import backend.Persistable;
import core.build.ModdedGear;
import core.build.ModdedWeapon;
import core.components.Gear;
import core.components.GearMod;
import core.components.GearSet;
import core.components.Mod;
import core.components.ModType;
import core.components.PropValue;
import core.components.Property;
import core.components.RecalibrationPosition;
import core.components.Weapon;
import core.components.WeaponMod;
import core.components.WeaponTalent;
import core.components.WeaponType;
import core.utils.Constants;
import frontend.main.MainGUI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author tchabole
 */
public class WeaponFormGUI extends javax.swing.JPanel {
    private final MainGUI mainGUI;
    private ModdedWeapon moddedWeapon;

    private boolean editMode;
    
    public WeaponFormGUI(MainGUI mainGUI, ModdedWeapon moddedWeapon, boolean editMode) {
        this.mainGUI = mainGUI;
        this.moddedWeapon = moddedWeapon;
        this.editMode = editMode;
        
        initComponents();
    }
    
    public WeaponFormGUI(MainGUI mainGUI) {
        this(mainGUI, null, true);
    }
    
    private void initComponents() {
        
        mainParamsPanel = new javax.swing.JPanel();
        weaponTypeLabel = new javax.swing.JLabel();
        weaponTypeComboxBox = new javax.swing.JComboBox();
        talentLabel = new javax.swing.JLabel();
        extraTalentsComboxBox = new javax.swing.JComboBox[Constants.MAX_WEAPON_TALENTS];
        mainTalentLabel = new javax.swing.JLabel();
        mainTalentComboxBox = new javax.swing.JComboBox();
        gearScoreLabel = new javax.swing.JLabel();
        gearScoreText = new javax.swing.JTextField();
        bonusLabel = new javax.swing.JLabel();
        bonusText = new javax.swing.JTextField();
        baseDamageLabel = new javax.swing.JLabel();
        baseDamageText = new javax.swing.JTextField();
        modsPanel = javax.swing.Box.createVerticalBox();//new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        
        
        modsMapPanel = new HashMap<ModType,WeaponModGUI>();
        
        iconPanel = new javax.swing.JPanel();
        
        setLayout(new java.awt.BorderLayout());

        mainParamsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Main"));
        mainParamsPanel.setMinimumSize(new java.awt.Dimension(336, 60));
        mainParamsPanel.setPreferredSize(new java.awt.Dimension(265, 100));
        mainParamsPanel.setLayout(new java.awt.GridLayout(0, 8));

        weaponTypeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        weaponTypeLabel.setFont(MainGUI.DEFAULT_FONT);
        weaponTypeLabel.setText("Weapon Type:");
        mainParamsPanel.add(weaponTypeLabel);

        weaponTypeComboxBox.setModel(new javax.swing.DefaultComboBoxModel(WeaponType.values()));
        weaponTypeComboxBox.setFont(MainGUI.DEFAULT_FONT);
        mainParamsPanel.add(weaponTypeComboxBox);
        
        bonusLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        bonusLabel.setText("Bonus:");
        bonusLabel.setFont(MainGUI.DEFAULT_FONT);
        mainParamsPanel.add(bonusLabel);

        bonusText.setText("");
        bonusText.setFont(MainGUI.DEFAULT_FONT);
        mainParamsPanel.add(bonusText);

        baseDamageLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        baseDamageLabel.setFont(MainGUI.DEFAULT_FONT);
        baseDamageLabel.setText("Base Damage:");
        mainParamsPanel.add(baseDamageLabel);

        baseDamageText.setText("1000");
        baseDamageText.setFont(MainGUI.DEFAULT_FONT);
        mainParamsPanel.add(baseDamageText);
        
        gearScoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gearScoreLabel.setFont(MainGUI.DEFAULT_FONT);
        gearScoreLabel.setText("GearScore:");
        mainParamsPanel.add(gearScoreLabel);

        gearScoreText.setText("229");
        gearScoreText.setFont(MainGUI.DEFAULT_FONT);
        mainParamsPanel.add(gearScoreText);
        

        for (int i = 0; i < Constants.MAX_WEAPON_TALENTS; i++) {
            
            talentLabel = new javax.swing.JLabel();
            
            talentLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            talentLabel.setFont(MainGUI.DEFAULT_FONT);
            talentLabel.setText("Talent "+ Integer.toString(i+1)+ ":");
            mainParamsPanel.add(talentLabel);

            extraTalentsComboxBox[i] =  new  javax.swing.JComboBox();
            extraTalentsComboxBox[i].setModel(new javax.swing.DefaultComboBoxModel(mainGUI.getWeaponTalentsList(true)));
            extraTalentsComboxBox[i].setFont(MainGUI.DEFAULT_FONT);
            mainParamsPanel.add(extraTalentsComboxBox[i]);
        }
        

        
        mainTalentLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mainTalentLabel.setFont(MainGUI.DEFAULT_FONT);
        mainTalentLabel.setText("Main talent:");
        mainParamsPanel.add(mainTalentLabel);

        mainTalentComboxBox.setModel(new javax.swing.DefaultComboBoxModel(mainGUI.getWeaponTalentsList(false)));
        mainTalentComboxBox.setFont(MainGUI.DEFAULT_FONT);
        mainParamsPanel.add(mainTalentComboxBox);
        
        this.add(mainParamsPanel, java.awt.BorderLayout.NORTH);

        modsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Mods Layout"));
        //modsPanel.setLayout(new java.awt.GridLayout(0, 1));
        
        WeaponModGUI modPanel;
        for ( ModType modType : mainGUI.getModFamiliesList(Constants.WEAPON_MOD)) {

            modPanel = new WeaponModGUI(mainGUI, modType);
            modPanel.setTransferHandler(new ModListTransferHandler());
            modPanel.setPreferredSize(new java.awt.Dimension(30, 30));
            modsMapPanel.put(modType,modPanel);
            modsPanel.add(modPanel);
        }

        scrollPane.setViewportView(modsPanel);
        this.add(scrollPane, java.awt.BorderLayout.CENTER);

        iconPanel.setPreferredSize(new java.awt.Dimension(80, 120));

        this.add(iconPanel, java.awt.BorderLayout.WEST);
        
    }
    
    
    public void updateModdedWeapon(ModdedWeapon moddedWeapon) {
        this.moddedWeapon = moddedWeapon;
        
        setEditable(false);
    }
    
    public void clearModdedWeapon() {
        this.moddedWeapon = null;
        
        setEditable(true);
        weaponTypeComboxBox.setEnabled(true);
    }
    
    public void onBtnSaveActioned() {
        
        if (!editMode)  {
            setEditable(true);
        } else {
        
            Persistable backend = mainGUI.getBackend();
            
            if (moddedWeapon ==null) {
                moddedWeapon  = new ModdedWeapon();
            }
            Weapon weapon = moddedWeapon.getWeapon();
            //weapon type
            //base damage
            Float baseDamage = Float.parseFloat(baseDamageText.getText());
            WeaponType weaponType =  (WeaponType) weaponTypeComboxBox.getSelectedItem();
            if ( weapon == null ) {
                weapon = new Weapon(weaponType, baseDamage);
            } else {
                weapon.setWeaponType(weaponType);
                weapon.setBaseDamage(baseDamage);
            }

            weapon.setGearScore(Integer.parseInt(gearScoreText.getText()));
            
            weapon.setMainTalent((WeaponTalent)mainTalentComboxBox.getSelectedItem());
            
            weapon.clearExtraTalents();
            for (int i =0; i < Constants.MAX_WEAPON_TALENTS; i ++) {
                if (extraTalentsComboxBox[i].getSelectedItem() != null) {
                    weapon.addExtraTalent((WeaponTalent) extraTalentsComboxBox[i].getSelectedItem());
                }
            }
            
            if (bonusText.getText()!= null && !bonusText.getText().isEmpty()) {
                weapon.setWeaponBonus(new PropValue(weaponType.getProperty(), Float.parseFloat(bonusText.getText())));
            }else {
                weapon.setWeaponBonus(null);
            }
            
            moddedWeapon.setWeapon(weapon);
            
            moddedWeapon.clearMods();
            
            WeaponMod weaponMod;
            for (ModType modType : modsMapPanel.keySet()){
                weaponMod = modsMapPanel.get(modType).getWeaponMod();
                moddedWeapon.addMod(weaponMod, modType);
            }
            
            backend.saveOrUpdateModdedWeapon(moddedWeapon);
            
            mainGUI.getCurrentBuild().refresh("Weapons");
            
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
        
        if (moddedWeapon == null) {
            weaponTypeComboxBox.setSelectedIndex(0);
            
            for (int i =0; i<Constants.MAX_WEAPON_TALENTS; i++) {
                extraTalentsComboxBox[i].setSelectedIndex(0);
            }
            
            mainTalentComboxBox.setSelectedIndex(0);
            
            bonusText.setText("");
            gearScoreText.setText("229");
            baseDamageText.setText("1000");
            
            WeaponModGUI modPanel;
            modsPanel.removeAll();
            for (ModType modType : Constants.asSortedList(modsMapPanel.keySet())) {
                modPanel = modsMapPanel.get(modType);
                modPanel.clearWeaponMod();
                modsPanel.add(modPanel);
            }
            
        } else {
            
            
            weaponTypeComboxBox.setSelectedItem(moddedWeapon.getWeapon().getWeaponType());
            mainTalentComboxBox.setSelectedItem(moddedWeapon.getWeapon().getMainTalent());
            int i= 0;
            for(WeaponTalent talent : moddedWeapon.getWeapon().getExtraTalents()) {
                extraTalentsComboxBox[i].setSelectedItem(talent);
                i++;
            }
            
            while (i < Constants.MAX_WEAPON_TALENTS) {
                extraTalentsComboxBox[i].setSelectedIndex(0);
                i++;
            }
            
            if (moddedWeapon.getWeapon().getWeaponBonus() != null) {
                bonusText.setText(moddedWeapon.getWeapon().getWeaponBonus().getValue().toString());
            }
            
            baseDamageText.setText(moddedWeapon.getWeapon().getBaseDamage().toString());

            gearScoreText.setText(Integer.toString(moddedWeapon.getWeapon().getGearScore()));


            HashMap<WeaponMod,ModType> modsPosition = moddedWeapon.getModsPosition();
            HashMap<ModType,WeaponMod> modsMap = new HashMap<ModType, WeaponMod>();
            HashMap<RecalibrationPosition,PropValue> currentMapPanels =  new HashMap<RecalibrationPosition,PropValue>();
            
            
            for (WeaponMod mod : moddedWeapon.getMods()) {
                modsMap.put(modsPosition.get(mod), mod);
            }

            WeaponModGUI modPanel;
            modsPanel.removeAll();
            if (editMode) {
                for (ModType modType : Constants.asSortedList(modsMapPanel.keySet())) {
                    modPanel = modsMapPanel.get(modType);
                    if (modsMap.containsKey(modType)) {
                        modPanel.updateWeaponMod(modsMap.get(modType));
                    } else {
                        modPanel.clearWeaponMod();
                    }
                    modsPanel.add(modPanel);
                }
            } else {
                for (ModType modType : Constants.asSortedList(modsMap.keySet()) ) {
                    modPanel = new WeaponModGUI(mainGUI,modType,false);
                    modPanel.updateWeaponMod(modsMap.get(modType));
                    modPanel.setPreferredSize(new java.awt.Dimension(30, 30));
                    modsPanel.add(modPanel );
                }
            }
                    
        }
        
        weaponTypeComboxBox.setEnabled(editMode);
        for (int i =0; i<Constants.MAX_WEAPON_TALENTS; i++) {
            extraTalentsComboxBox[i].setEnabled(editMode);
        }
        
        mainTalentComboxBox.setEnabled(editMode);
        gearScoreText.setEditable(editMode);
        baseDamageText.setEditable(editMode);
        bonusText.setEditable(editMode);
        
        validate();
        repaint();
    }
    
    private HashMap<ModType,WeaponModGUI> modsMapPanel;
    //private javax.swing.JPanel modsPanel;
    private javax.swing.Box modsPanel;
    private javax.swing.JPanel iconPanel;
    private javax.swing.JComboBox weaponTypeComboxBox;
    private javax.swing.JComboBox[] extraTalentsComboxBox;
    private javax.swing.JComboBox mainTalentComboxBox;
    
    private javax.swing.JLabel bonusLabel;
    private javax.swing.JLabel baseDamageLabel;
    private javax.swing.JLabel gearScoreLabel;

    private javax.swing.JLabel weaponTypeLabel;
    private javax.swing.JLabel talentLabel;
    private javax.swing.JLabel mainTalentLabel;
    
    private javax.swing.JTextField bonusText;
    
    private javax.swing.JTextField baseDamageText;
    private javax.swing.JTextField gearScoreText;
    
    private javax.swing.JPanel mainParamsPanel;
    private  javax.swing.JScrollPane scrollPane ;
    
}
