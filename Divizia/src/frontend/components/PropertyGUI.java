package frontend.components;

import core.components.PropValue;
import core.utils.Constants;
import frontend.main.MainGUI;

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
        
        
        propertyComboBox = new javax.swing.JComboBox();
        propertyValueText = new javax.swing.JTextField();
        
        this.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        this.setLayout(new java.awt.GridLayout(1, 2));

        
        propertyComboBox.setFont(MainGUI.DEFAULT_FONT); // NOI18N
        propertyComboBox.setModel(new javax.swing.DefaultComboBoxModel(mainGUI.getPropertyList(Constants.GEAR_MAJ)));
        this.add(propertyComboBox);

        propertyValueText.setText("");
        propertyValueText.setPreferredSize(new java.awt.Dimension(50,28));
        propertyValueText.setFont(MainGUI.DEFAULT_FONT); // NOI18N
        propertyValueText.setEditable(editable);
        this.add(propertyValueText);
    }
    
    private javax.swing.JComboBox propertyComboBox;
    private javax.swing.JTextField propertyValueText;
    
}
