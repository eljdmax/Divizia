package frontend.components;

import core.components.Mod;
import core.components.ModType;
import core.components.PropValue;
import core.components.Property;
import core.components.RecalibrationPosition;
import core.components.WeaponMod;
import core.utils.Constants;
import frontend.main.MainGUI;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
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
                        mainGUI.getModFormPanel().updateMod(mod);
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
                        mainGUI.getModFormPanel().updateMod(mod);
                    }
                }
            });
            
            this.add(modLabel);
        }
        
        
    }
    
    
    public WeaponMod getWeaponMod() {
        return mod;
    }
    
    public boolean canReceiveMod() {
        //return (propertyComboBox.getSelectedItem() == null || ((Property) propertyComboBox.getSelectedItem()) == Property.EMPTY  );
        return editable; //&& modType is correct
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
        
        modText.setText("");
        if (mod != null) {
            modText.setText(mod.toString());
        }
             
    }
    
  
    
    private javax.swing.JButton clearBtn;
    private javax.swing.JLabel modLabel;
    private javax.swing.JLabel positionLabel;
    private javax.swing.JTextField modText;
        
}
