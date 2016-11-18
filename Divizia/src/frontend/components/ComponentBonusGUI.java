package frontend.components;

import core.components.Mod;
import core.components.PropValue;
import core.utils.Constants;
import frontend.main.MainGUI;

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
    
    public void updateBonus(PropValue bonus) {
        this.bonus = bonus;
        updateLayout();
    }
    
    public void clearBonus() {
        this.bonus = null;
        updateLayout();
    }
    
    private void updateLayout() {
        String bonusValueText = "";
        if (bonus != null) {
            bonusValueText = bonus.getValue().toString();
        }
        
        propertyValueText.setText(bonusValueText);
    }
    
    private javax.swing.JComboBox propertyComboBox;
    private javax.swing.JLabel propertyLabel;
    private javax.swing.JLabel positionLabel;
    private javax.swing.JTextField propertyValueText;
        
}
