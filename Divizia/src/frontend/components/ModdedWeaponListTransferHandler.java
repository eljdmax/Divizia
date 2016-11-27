/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package frontend.components;

import core.build.ModdedWeapon;
import core.components.Mod;
import frontend.main.MainGUI;
import java.awt.Component;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

/**
 *
 * @author tchabole
 */
public class ModdedWeaponListTransferHandler extends TransferHandler {

        @Override
        public boolean canImport(TransferHandler.TransferSupport support) {
            SelectedWeaponPanel droppedPanel = getDroppedPanel(support);
            if ( droppedPanel ==null ||  !support.isDataFlavorSupported(ModdedWeaponTransferable.MODDEDWEAPON_DATA_FLAVOR) ) {
                return false;
            }
            
            return  droppedPanel.canReceiveWeapon(getImportedWeapon(support));
        }

        private ModdedWeapon getImportedWeapon(TransferHandler.TransferSupport support) {
            try {
                Transferable t = support.getTransferable();
                Object value = t.getTransferData(ModdedWeaponTransferable.MODDEDWEAPON_DATA_FLAVOR);
                if (value instanceof ModdedWeapon) {
                    return ((ModdedWeapon) value);
                }
            } catch (Exception exp) {
                exp.printStackTrace();
            }
            return null;
        }
        
        private SelectedWeaponPanel getDroppedPanel(TransferHandler.TransferSupport support) {
            if (support.getComponent() instanceof SelectedWeaponPanel) {
                return ((SelectedWeaponPanel) support.getComponent());
            }
            return null;
        }
        
        @Override
        public boolean importData(TransferHandler.TransferSupport support) {
            boolean accept = false;
            if (canImport(support)) {
                ModdedWeapon moddedWeapon = getImportedWeapon(support);
                
                SelectedWeaponPanel droppedPanel = getDroppedPanel(support);
                if (droppedPanel != null) {
                    droppedPanel.getDisplayParent().updateModdedWeapon(moddedWeapon);
                    accept = true;
                }
            }
            return accept;
        }

        @Override
        public int getSourceActions(JComponent c) {
            return DnDConstants.ACTION_COPY_OR_MOVE;
        }

        @Override
        protected Transferable createTransferable(JComponent c) {
            Transferable t = null;
            if (c instanceof FullBuildGUI.WeaponList) {
                FullBuildGUI.WeaponList list = (FullBuildGUI.WeaponList) c;
                Object value = list.getSelectedValue();
                if (value instanceof ModdedWeapon) {
                    ModdedWeapon moddedWeapon = (ModdedWeapon) value;
                    t = new ModdedWeaponTransferable(moddedWeapon);
                }
            }
            return t;
        }

        @Override
        protected void exportDone(JComponent source, Transferable data, int action) {
            // Here you need to decide how to handle the completion of the transfer,
            // should you remove the item from the list or not...
        }
    }
