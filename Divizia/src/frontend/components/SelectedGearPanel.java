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
public class SelectedGearPanel extends javax.swing.JPanel {
        private GearDisplayGUI parent;
        
        SelectedGearPanel(GearDisplayGUI theParent) {
            this.parent = theParent;
            
            initComponents();
            
            addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (parent.getModdedGear() != null) {
                        parent.getMainGUI().getGearFormPanel(true).updateModdedGear(parent.getModdedGear(), parent.getGearType());
                    }
                }
            });
        }
        
        private void initComponents() {
            
            gearLabel =  new javax.swing.JLabel();
            this.setLayout(new java.awt.BorderLayout());
            
            gearLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            gearLabel.setFont(MainGUI.DEFAULT_FONT);
            gearLabel.setText("");
            if (this.parent.getModdedGear() != null) {
                
                gearLabel.setText(this.parent.getModdedGear().displayText());
            } 
            this.add(gearLabel);
        }
        
        public GearDisplayGUI getDisplayParent() {
            return parent;
        }
        
        public void refresh() {
            if (this.parent.getModdedGear() != null) {
                gearLabel.setText(this.parent.getModdedGear().displayText());
            }else{
                gearLabel.setText("");
            }
        }
        
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
        
        
        private javax.swing.JLabel gearLabel;
        
    }
    
