/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package frontend.components;

import core.components.Mod;
import core.components.WeaponMod;
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
public class WeaponModListTransferHandler extends TransferHandler {

        @Override
        public boolean canImport(TransferHandler.TransferSupport support) {
            
            WeaponModGUI droppedPanel = getDroppedPanel(support);
            if ( droppedPanel ==null ||  !support.isDataFlavorSupported(ModTransferable.MOD_DATA_FLAVOR) ) {
                return false;
            }
            
            return  droppedPanel.canReceiveMod(getImportedMod(support));
        }

        private WeaponMod getImportedMod(TransferHandler.TransferSupport support) {
            try {
                Transferable t = support.getTransferable();
                Object value = t.getTransferData(ModTransferable.MOD_DATA_FLAVOR);
                if (value instanceof WeaponMod) {
                    return ((WeaponMod) value);
                }
            } catch (Exception exp) {
                exp.printStackTrace();
            }
            return null;
        }
        
        private WeaponModGUI getDroppedPanel(TransferHandler.TransferSupport support) {
            if (support.getComponent() instanceof WeaponModGUI) {
                return ((WeaponModGUI) support.getComponent());
            }
            return null;
        }
        
        @Override
        public boolean importData(TransferHandler.TransferSupport support) {
            boolean accept = false;
            if (canImport(support)) {
                WeaponModGUI droppedPanel = getDroppedPanel(support);
                if (droppedPanel != null) {
                    droppedPanel.updateWeaponMod(getImportedMod(support));
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
            if (c instanceof MainGUI.ModList) {
                MainGUI.ModList list = (MainGUI.ModList) c;
                Object value = list.getSelectedValue();
                if (value instanceof Mod) {
                    Mod mod = (Mod) value;
                    t = new ModTransferable(mod);
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
