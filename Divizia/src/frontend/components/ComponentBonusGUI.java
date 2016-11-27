package frontend.components;

import core.components.Mod;
import core.components.PropValue;
import core.components.Property;
import core.components.RecalibrationPosition;
import core.utils.Constants;
import frontend.main.MainGUI;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author tchabole
 */
public class ComponentBonusGUI extends javax.swing.JPanel {
    private final MainGUI mainGUI;
    private PropValue bonus;
    private Mod mod;
    private boolean editable;
    
    public ComponentBonusGUI(MainGUI mainGUI, PropValue bonus, boolean editable) {
        this.mainGUI = mainGUI;
        this.bonus = bonus;
        this.editable = editable;
        
        initComponents();
    }
    
    public ComponentBonusGUI(MainGUI mainGUI, PropValue bonus) {
        this(mainGUI, bonus, true);
    }
    
    
    private void initComponents() {
        positionLabel = new javax.swing.JLabel();
        
        propertyValueText = new javax.swing.JTextField();
        
        this.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        this.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT)); //new java.awt.GridLayout(1, 3));
        
        positionLabel.setText(bonus.getRecalibrationPosition().name()+":");
        positionLabel.setFont(MainGUI.DEFAULT_FONT); // NOI18N

        this.add(positionLabel);
        
        if (editable) {
            propertyComboBox = new javax.swing.JComboBox();
            propertyComboBox.setModel(new javax.swing.DefaultComboBoxModel( mainGUI.getPropertyList(Constants.recalibrationList(bonus.getRecalibrationPosition()))));
            propertyComboBox.setFont(MainGUI.DEFAULT_FONT); // NOI18N
            this.add(propertyComboBox);
        } else {
            propertyLabel = new javax.swing.JLabel();
            propertyLabel.addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (mod != null) {
                        mainGUI.setSelectedModList(mod);
                    }
                }
            });
            propertyLabel.setFont(MainGUI.DEFAULT_FONT); // NOI18N
            propertyLabel.setText(bonus.getProperty().toString());
            this.add(propertyLabel);
        }

        propertyValueText.setPreferredSize(new java.awt.Dimension(50,28));
        propertyValueText.setText("");
        if (bonus.getValue() != null) {
            propertyValueText.setText(bonus.getValue().toString());
        }
        
        propertyValueText.setFont(MainGUI.DEFAULT_FONT); // NOI18N
        propertyValueText.setEditable(editable);
        this.add(propertyValueText);

    }
    
    public PropValue getUpdatedBonus() {
        Property theProp = (Property) propertyComboBox.getSelectedItem();
        if (theProp == null) {
            return null;
        }
        
        bonus.setProperty(theProp);
        
        if (theProp == Property.EMPTY) {  
            bonus.setValue(0F);
            return bonus;
        }
        
        if (propertyValueText.getText() == null || propertyValueText.getText().isEmpty() )  {
            return null;
        }
        
        bonus.setValue(Float.parseFloat(propertyValueText.getText()));
        return bonus;
    }
    
    public Mod getMod() {
        return mod;
    }
    
    public boolean canReceiveMod() {
        return (propertyComboBox.getSelectedItem() == null || ((Property) propertyComboBox.getSelectedItem()) == Property.EMPTY  );
    }
    
    public void updateBonus(PropValue bonus, Mod mod) {
        this.bonus = bonus;
        this.mod = mod;
        updateLayout();
    }
    
    public void setMod(Mod mod) {
        bonus.setProperty(Property.EMPTY);
        this.mod = mod;
        updateLayout();
    }
    
    public void clearBonus() {
        RecalibrationPosition pos = this.bonus.getRecalibrationPosition();
        this.bonus = new PropValue();
        this.mod = null;
        this.bonus.setRecalibrationPosition(pos);
        
        updateLayout();
    }
    
    private void updateLayout() {
        String bonusValueText = "";
        
        if (mod != null) {
            bonusValueText = mod.getName();
        }
        else if (bonus.getValue() != null) {
            bonusValueText = bonus.getValue().toString();
        }
        propertyValueText.setText(bonusValueText);
        
        if (editable ) {
            propertyComboBox.setSelectedItem(bonus.getProperty());
        }
             
    }
    
  
    
    
    private javax.swing.JComboBox propertyComboBox;
    private javax.swing.JLabel propertyLabel;
    private javax.swing.JLabel positionLabel;
    private javax.swing.JTextField propertyValueText;
        
}
