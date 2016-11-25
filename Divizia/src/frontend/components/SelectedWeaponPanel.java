/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package frontend.components;

import core.build.ModdedGear;
import frontend.main.MainGUI;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author tchabole
 */
public class SelectedWeaponPanel extends javax.swing.JPanel {
        private WeaponDisplayGUI parent;
        
        SelectedWeaponPanel(WeaponDisplayGUI theParent) {
            this.parent = theParent;
            
            initComponents();
            
            addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (parent.getModdedWeapon() != null) {
                        //parent.getMainGUI().getWeaponFormPanel().updateModdedWeapon(parent.getModdedWeapon());
                    }
                }
            });
        }
        
        private void initComponents() {
            
            weaponLabel =  new javax.swing.JLabel();
            this.setLayout(new java.awt.BorderLayout());
            
            weaponLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            weaponLabel.setFont(MainGUI.DEFAULT_FONT);
            weaponLabel.setText("");
            if (this.parent.getModdedWeapon() != null) {
                
                weaponLabel.setText(this.parent.getModdedWeapon().displayText());
            } 
            this.add(weaponLabel);
        }
        
        public WeaponDisplayGUI getDisplayParent() {
            return parent;
        }
        
        public void refresh() {
            if (this.parent.getModdedWeapon() != null) {
                weaponLabel.setText(this.parent.getModdedWeapon().displayText());
            }else{
                weaponLabel.setText("");
            }
        }
        
        /*
        public boolean canReceiveGear(ModdedGear moddedGear) {
            if (moddedGear == null) {
                return false;
            }
            try {
                return Class.forName("core.components.gear."+this.parent.getGearType()).isInstance(moddedGear.getGear());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            return false;
        }
        */
        
        
        private javax.swing.JLabel weaponLabel;
        
    }
    
