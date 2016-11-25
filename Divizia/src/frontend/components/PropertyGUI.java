package frontend.components;

import core.components.Mod;
import core.components.PropValue;
import core.components.Property;
import core.utils.Constants;
import frontend.main.MainGUI;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author tchabole
 */
public class PropertyGUI extends javax.swing.JPanel {
    private final MainGUI mainGUI;
    private PropValue prop;
    private boolean editable;
    
    public PropertyGUI(MainGUI mainGUI, PropValue prop, boolean editable) {
        this.mainGUI = mainGUI;
        this.prop = prop;
        this.editable = editable;
        
        initComponents();
    }
    
    public PropertyGUI(MainGUI mainGUI, PropValue prop) {
        this(mainGUI, prop, true);
    }
    
    private void initComponents() {
        
        
        propertyValueText = new javax.swing.JTextField();
        
        this.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        this.setLayout(new java.awt.GridLayout(1, 2));

        if (editable) {
            propertyComboBox = new javax.swing.JComboBox();
            propertyComboBox.setFont(MainGUI.DEFAULT_FONT); // NOI18N
            propertyComboBox.setModel(new javax.swing.DefaultComboBoxModel(mainGUI.getPropertyList(Constants.GEAR_MAJ)));
            this.add(propertyComboBox);
        } else {
            propertyLabel = new javax.swing.JLabel();
            propertyLabel.setFont(MainGUI.DEFAULT_FONT); // NOI18N
            propertyLabel.setText(prop.getProperty().toString());
            this.add(propertyLabel);
        }
        
        propertyValueText.setText("");
        if (prop.getValue()!= null) {
            propertyValueText.setText(prop.getValue().toString());
        }
        propertyValueText.setPreferredSize(new java.awt.Dimension(50,28));
        propertyValueText.setFont(MainGUI.DEFAULT_FONT); // NOI18N
        propertyValueText.setEditable(editable);
        this.add(propertyValueText);
    }
    
    public PropValue getUpdatedPropValue() {
        if (propertyValueText.getText() == null || propertyValueText.getText().isEmpty() ) {
            return null;
        }
        Property theProp = (Property) propertyComboBox.getSelectedItem();
        if (theProp == null) {
            return null;
        }
            
        prop.setProperty(theProp);
        prop.setValue(Float.parseFloat(propertyValueText.getText()));
        return prop;
    }
    
    public void updateProp(PropValue prop) {
        this.prop = prop;
        
        updateLayout();
    }
    
    public void clearProp() {
        this.prop = new PropValue();
        updateLayout();
    }
    
    private void updateLayout() {
        String propValueText = "";
        if (prop.getValue() != null) {
            propValueText = prop.getValue().toString();
        }
        
        if (editable ) {
            propertyComboBox.setSelectedItem(prop.getProperty());
        }
           
        
        propertyValueText.setText(propValueText);
    }
    
    private javax.swing.JLabel propertyLabel;
    private javax.swing.JComboBox propertyComboBox;
    private javax.swing.JTextField propertyValueText;
    
}
