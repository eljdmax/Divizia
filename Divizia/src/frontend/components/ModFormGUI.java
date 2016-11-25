package frontend.components;

import backend.Persistable;
import core.build.ModdedGear;
import core.components.Mod;
import core.components.ModType;
import core.components.PropValue;
import core.components.Property;
import core.utils.Constants;
import frontend.main.MainGUI;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author tchabole
 */
public class ModFormGUI extends javax.swing.JPanel {
    private final MainGUI mainGUI;
    private Mod mod;
    private boolean editMode;
    
    public ModFormGUI(MainGUI mainGUI) {
        this(mainGUI, true);
    }
    
    public ModFormGUI(MainGUI mainGUI, boolean editMode) {
        this.mainGUI = mainGUI;
        this.editMode = editMode;
        
        initComponents();
    }
    
    private void initComponents() {
        
        mainParamsPanel = new javax.swing.JPanel();
        bonusPanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel() ;
        typeLabel = new javax.swing.JLabel() ;
        nameText = new javax.swing.JTextField();
        typeComboBox = new javax.swing.JComboBox() ;
    
        propertyListGUI = new ArrayList<PropertyGUI>();
        
        
        this.setLayout(new java.awt.BorderLayout());
        
        mainParamsPanel.setLayout(new java.awt.GridLayout(0, 2));
        mainParamsPanel.setPreferredSize(new java.awt.Dimension(110, 120));
        
        nameLabel.setFont(MainGUI.DEFAULT_FONT);
        nameLabel.setText("Name:");
        mainParamsPanel.add(nameLabel);
        
        nameText.setFont(MainGUI.DEFAULT_FONT);
        nameText.setText("");
        mainParamsPanel.add(nameText);
        
        typeLabel.setFont(MainGUI.DEFAULT_FONT);
        typeLabel.setText("Mod Type:");
        mainParamsPanel.add(typeLabel);
        
        typeComboBox.setModel(new javax.swing.DefaultComboBoxModel(mainGUI.getModFamiliesList(0)));
        typeComboBox.setFont(MainGUI.DEFAULT_FONT);
        mainParamsPanel.add(typeComboBox);
        
        this.add(mainParamsPanel, java.awt.BorderLayout.WEST);
        
        bonusPanel.setLayout(new java.awt.GridLayout(0, 1));
        
        PropertyGUI attribPanel;
        for ( int i =0; i < Constants.MAX_MOD_ATTRIBUTES; i++) {
            attribPanel = new PropertyGUI(mainGUI,new PropValue());
            attribPanel.setPreferredSize(new java.awt.Dimension(100, 30));
            propertyListGUI.add(attribPanel);
            bonusPanel.add(attribPanel);
        }
        
        this.add(bonusPanel, java.awt.BorderLayout.CENTER);
                
    }
    
    public Property[] getPropertyList() {
        //TODO: better list dependy on the modType
            return mainGUI.getPropertyList(Constants.GEAR_MAJ);
    }
    
    public void updateMod(Mod mod) {
        this.mod = mod;
        setEditable(false);
    }
    
    public void clearMod() {
        this.mod = null;
        setEditable(true);
        typeComboBox.setEnabled(true);
    }
    
    public void onBtnSaveActioned() {
        if (!editMode)  {
            setEditable(true);
        } else {
            //save !! checking form
            Persistable backend = mainGUI.getBackend();
            ModType modType = (ModType) typeComboBox.getSelectedItem();
            
            if (mod == null) {
                mod = Constants.getNewMod(nameText.getText(), modType);
            }else{
                mod.clearBonus();
            }
            
            for(PropertyGUI proGUI: propertyListGUI) {
                mod.addBonus(proGUI.getUpdatedPropValue());
            }
            
            mod.setName(nameText.getText());
            
            backend.saveOrUpdateMod(mod);
            
            mainGUI.updateModList();
            
            setEditable(false);

        }
    }
    
    private void setEditable(boolean editMode) {
        this.editMode = editMode;
        updateLayout();
    }    
    
    private void updateLayout() {
        
        mainGUI.getModSaveBtn().setText(editMode ? "Save" : "Edit");
        
        if (mod == null) {
            nameText.setText("");
            typeComboBox.setSelectedIndex(0);
            nameText.setEditable(editMode);
            bonusPanel.removeAll();
            for ( int i =0; i < Constants.MAX_MOD_ATTRIBUTES; i++) {
                propertyListGUI.get(i).clearProp();
                bonusPanel.add(propertyListGUI.get(i));
            }
            validate();
            repaint();
            return;
        }
        
        
        typeComboBox.setSelectedItem(mod.getType());
        typeComboBox.setEnabled(false);
        
        nameText.setText(mod.getName());
        nameText.setEditable(editMode);
        
        int count = 0;
        PropertyGUI attribPanel;
        
        bonusPanel.removeAll();
        if (editMode) {
            for (PropValue prop : mod.getBonus()) {
                attribPanel = propertyListGUI.get(count);
                attribPanel.updateProp(prop);
                bonusPanel.add(attribPanel);
                count++;
            }
            
            for (int i= count; i < Constants.MAX_MOD_ATTRIBUTES; i++ ) {
                attribPanel = propertyListGUI.get(i);
                attribPanel.clearProp();
                bonusPanel.add(attribPanel);
            }
            
        } else {
            for (PropValue prop : mod.getBonus()) {
                attribPanel = new PropertyGUI(mainGUI, prop, false);
                attribPanel.setPreferredSize(new java.awt.Dimension(100, 30));
                bonusPanel.add(attribPanel);
            }
        }
        
        validate();
        repaint();
        
    }
    
    
    private List<PropertyGUI> propertyListGUI;
    private javax.swing.JPanel mainParamsPanel;
    private javax.swing.JPanel bonusPanel;
    
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel typeLabel;
    
    private javax.swing.JTextField nameText;
    private javax.swing.JComboBox typeComboBox;
}
