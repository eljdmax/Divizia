package frontend.components;

import core.components.ModType;
import core.components.WeaponMod;
import core.utils.Constants;
import frontend.main.MainGUI;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author tchabole
 */
public class WeaponModGUI extends javax.swing.JPanel {
    private final MainGUI mainGUI;
    private ModType modType;
    private WeaponMod mod;
    private boolean editable;
    
    public WeaponModGUI(MainGUI mainGUI, ModType modType, boolean editable) {
        this.mainGUI = mainGUI;
        this.modType = modType;
        this.editable = editable;
        
        initComponents();
    }
    
    public WeaponModGUI(MainGUI mainGUI, ModType pos) {
        this(mainGUI, pos, true);
    }
    
    
    private void initComponents() {
        positionLabel = new javax.swing.JLabel();
        
        this.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        this.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT)); //new java.awt.GridLayout(1, 3));
        
        positionLabel.setText(modType.name()+":");
        positionLabel.setFont(MainGUI.DEFAULT_FONT); // NOI18N

        this.add(positionLabel);
        
        if (editable) {
            modText = new javax.swing.JTextField();
            modText.setEditable(false);
            modText.setFont(MainGUI.DEFAULT_FONT); // NOI18N
            modText.setPreferredSize(new java.awt.Dimension(100, 20));
            modText.setText("");
            if (mod != null) {
                modLabel.setText(mod.toString());
            }
            
            modText.addMouseListener(new MouseAdapter() {

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
            
            this.add(modText);
            
            
            clearBtn = new javax.swing.JButton() ;
            clearBtn.setFont(MainGUI.DEFAULT_FONT);
            clearBtn.setText("Clear");
            clearBtn.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    clearWeaponMod();
                }
            });
            
            this.add(clearBtn);
            
        } else {
            modLabel = new javax.swing.JLabel();
            modLabel.setFont(MainGUI.DEFAULT_FONT); // NOI18N
            modLabel.setText("");
            if (mod != null) {
                modLabel.setText(mod.toString());
            }
            
            modLabel.addMouseListener(new MouseAdapter() {

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
            
            this.add(modLabel);
        }
        
        
    }
    
    
    public WeaponMod getWeaponMod() {
        return mod;
    }
    
    public boolean canReceiveMod(WeaponMod weaponMod) {
        if (weaponMod == null) {
            return false;
        }
        //return (propertyComboBox.getSelectedItem() == null || ((Property) propertyComboBox.getSelectedItem()) == Property.EMPTY  );
        return editable && (modType == weaponMod.getType());
    }
    
    public void updateWeaponMod(WeaponMod mod) {
        this.mod = mod;
        updateLayout();
    }
    
    
    public void clearWeaponMod() {
        this.mod = null;
        updateLayout();
    }
    
    private void updateLayout() {
        
        if (editable) {
            modText.setText("");
            if (mod != null) {
                modText.setText(mod.getName());
            }
        } else {
            modLabel.setText(mod.getName());
        }
    }
    
  
    
    private javax.swing.JButton clearBtn;
    private javax.swing.JLabel modLabel;
    private javax.swing.JLabel positionLabel;
    private javax.swing.JTextField modText;
        
}
